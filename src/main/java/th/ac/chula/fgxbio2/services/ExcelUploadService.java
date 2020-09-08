package th.ac.chula.fgxbio2.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.ac.chula.fgxbio2.datastucture.SequenceDetail;
import th.ac.chula.fgxbio2.models.tables.EGender;
import th.ac.chula.fgxbio2.models.tables.Forenseq;
import th.ac.chula.fgxbio2.models.tables.ForenseqSequence;
import th.ac.chula.fgxbio2.models.tables.Person;
import th.ac.chula.fgxbio2.models.tables.Sample;
import th.ac.chula.fgxbio2.repository.tables.PersonRepository;

@Service
public class ExcelUploadService {
	@Autowired
	private PersonRepository personRepository;

	@Transactional
	public String readExcelData(String fileName) throws IOException {
		FileInputStream fis = new FileInputStream(fileName);
		Workbook workbook = openCompatibleWorkbook(fileName, fis);

		int numberOfSheets = workbook.getNumberOfSheets();

		Person personInfo = new Person();
		personInfo.setAge(0);
		Sample sample = new Sample();
		personInfo.add(sample);

		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = workbook.getSheetAt(i);
			String sheetName = sheet.getSheetName();

			Map<String, SequenceDetail> locusAllele = new LinkedHashMap<>();

			if (sheetName.equals("Autosomal STRs")) {
				extractInfo(personInfo, sample, sheet);
				extractForenseqData(personInfo, sample, sheet, locusAllele, 42, 47, "Autosome");
			}else if (sheetName.equals("Y STRs")) {
				System.out.println("Y STRS");
				extractForenseqData(personInfo, sample, sheet, locusAllele, 38, 43, "Y");
			}else if (sheetName.equals("X STRs")) {
				extractForenseqData(personInfo, sample, sheet, locusAllele, 21, 26, "X");
			}

			personRepository.save(personInfo);

		}

		return "Successfully uploaded";
	}

	public Workbook openCompatibleWorkbook(String fileName, FileInputStream fis) throws IOException {
		if (fileName.toLowerCase().endsWith("xlsx")) {
			return new XSSFWorkbook(fis);
		} else if (fileName.toLowerCase().endsWith("xls")) {
			return new HSSFWorkbook(fis);
		}
		return null;
	}

	private void extractForenseqData(Person personInfo, Sample sample, Sheet sheet,
			Map<String, SequenceDetail> locusAllele, int endSummaryLine, int startDetailLine, String chromosomeTag) {

		int line = 1;
		for (Row row : sheet) {
			List<String> data = getCells(row);
			System.out.println(data);
			if (line >= 15 && line <= endSummaryLine) {
				locusAllele.put(data.get(0), new SequenceDetail(data.get(1), data.get(2)));
			} else if (line >= startDetailLine) {
				if (data.get(0).equals("n/a")) {
					break;
				}

				if (data.get(2).equals("Yes")) {
					ForenseqSequence tempFS = new ForenseqSequence((int) Double.parseDouble(data.get(1)),
							(int) Double.parseDouble(data.get(3)), data.get(4));
					locusAllele.get(data.get(0)).getFsList().add(tempFS);
				}
			}

			if (line == 200)
				break;

			line++;
		}

		for (Entry<String, SequenceDetail> entry : locusAllele.entrySet()) {
			Forenseq forenseq = new Forenseq(entry.getKey(), entry.getValue().getGenotype(),
					entry.getValue().getQcIndicator(), chromosomeTag);
			sample.add(forenseq);
			for (ForenseqSequence curFS : entry.getValue().getFsList()) {
				forenseq.add(curFS);
			}
		}
	}

	private void extractInfo(Person personInfo, Sample sample, Sheet sheet) {
		int line = 1;
		for (Row row : sheet) {
			List<String> data = getCells(row);

			if (line >= 1 && line <= 8) {
				if (data.get(0).equals("Created")) {
					int sampleYear = Integer.parseInt(data.get(1).split(" ")[2]);
					sample.setSampleYear(sampleYear);
				} else if (data.get(0).equals("Sample")) {
					String sampleId = data.get(1);
					sample.setSampleId(sampleId);
				} else if (data.get(0).equals("Gender")) {
					EGender gender = data.get(1) == "XY" ? EGender.MALE : EGender.FEMALE;
					personInfo.setGender(gender);
				}
			}

			line++;
		}
	}

	private List<String> getCells(Row row) {
		List<String> data = new ArrayList<>();
		for (Cell cell : row) {
			switch (cell.getCellType()) {
			case STRING:
				data.add(cell.getStringCellValue());
				break;
			case NUMERIC:
				data.add(String.valueOf(cell.getNumericCellValue()));
				break;
			case BOOLEAN:
				data.add(String.valueOf(cell.getBooleanCellValue()));
				break;
			default:
				data.add("n/a");
			}
		}
		return data;
	}
}
