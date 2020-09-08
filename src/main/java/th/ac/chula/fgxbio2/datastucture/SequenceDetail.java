package th.ac.chula.fgxbio2.datastucture;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import th.ac.chula.fgxbio2.models.tables.ForenseqSequence;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SequenceDetail {
	private String genotype;

	private String qcIndicator;

	private List<ForenseqSequence> fsList = new ArrayList<ForenseqSequence>();

	public SequenceDetail(String genotype, String qcIndicator) {
		this.genotype = genotype;
		this.qcIndicator = qcIndicator;
	}

	@Override
	public String toString() {
		return "SequenceDetail [allele=" + genotype + ", fsList=" + fsList + "]";
	}
}