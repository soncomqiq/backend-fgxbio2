package th.ac.chula.fgxbio2.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import th.ac.chula.fgxbio2.repository.tables.ForenseqRepository;
import th.ac.chula.fgxbio2.repository.tables.ForenseqSequenceRepository;
import th.ac.chula.fgxbio2.repository.tables.SampleRepository;

@Service
public class ExcelUploadService {
	@Autowired
	private SampleRepository sampleRepository;

	@Autowired
	private ForenseqRepository forenseqRepository;

	@Autowired
	private ForenseqSequenceRepository forenseqSequenceRepository;
	
	@Value("${fgxbio.excel.sampleId-line}")
	private int sampleIdLine;

	@Value("${fgxbio.excel.sampleYear-line}")
	private int sampleYearLine;

	public String readExcelData(String fileName) throws IOException {
		FileInputStream fis = new FileInputStream(fileName);
		Workbook workbook = null;
		String sampleYear = null;
		String sampleId = null;

		if (fileName.toLowerCase().endsWith("xlsx")) {
			workbook = new XSSFWorkbook(fis);
		} else if (fileName.toLowerCase().endsWith("xls")) {
			workbook = new HSSFWorkbook(fis);
		}

		int numberOfSheets = workbook.getNumberOfSheets();

		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = workbook.getSheetAt(i);

			int line = 1;
			if (sheet.getSheetName().equals("Autosomal STRs")) {
				for (Row row : sheet) {
					List<String> data = getCells(row);

					if (line == sampleIdLine || line == sampleYearLine) {
						if (data.get(0).equals("Created")) {
							sampleYear = data.get(1).split(" ")[2];
						} else if (data.get(0).equals("Sample")) {
							sampleId = data.get(1);
						}
					} else if (line >= 13 && line <= 40) {
						System.out.println(data);
					}

					if (line == 200)
						break;

					line++;
				}
				System.out.println("Sample Year:" + sampleYear);
				System.out.println("Sample ID:" + sampleId);
			}

		}

		return "Successfully uploaded";
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
				data.add(String.valueOf(cell.getNumericCellValue()));
				break;
			case FORMULA:
				data.add(String.valueOf(cell.getNumericCellValue()));
				break;
			default:
				data.add("n/a");
			}
		}
		return data;
	}
}
