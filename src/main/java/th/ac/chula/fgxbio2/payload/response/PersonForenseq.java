package th.ac.chula.fgxbio2.payload.response;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import th.ac.chula.fgxbio2.datastucture.ForenseqRow;
import th.ac.chula.fgxbio2.datastucture.ForenseqSequenceRow;
import th.ac.chula.fgxbio2.models.tables.Person;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PersonForenseq {
	private Person person;
	private List<ForenseqRow> frA;
	private List<ForenseqRow> frX;
	private List<ForenseqRow> frY;
	private List<ForenseqRow> frI;
	private List<ForenseqSequenceRow> fsrA;
	private List<ForenseqSequenceRow> fsrX;
	private List<ForenseqSequenceRow> fsrY;
	private List<ForenseqSequenceRow> fsrI;
}
