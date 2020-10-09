package th.ac.chula.fgxbio2.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatternAlignment {
	private String sampleId;
	private int sampleYear;
	private String sequence;
	private int readCount;
	private String patternAlignment;
}
