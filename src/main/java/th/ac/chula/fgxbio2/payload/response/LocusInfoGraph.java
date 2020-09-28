package th.ac.chula.fgxbio2.payload.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import th.ac.chula.fgxbio2.datastucture.AlleleAmount;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocusInfoGraph {
	private List<AlleleAmount> aaList;
	private float hetero;
	private int total;
}
