package th.ac.chula.fgxbio2.payload.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import th.ac.chula.fgxbio2.datastucture.SampleIDYear;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchedSampleResponse {
	private List<SampleIDYear> sampleDetails;
	private int total;
	private int amount;
}
