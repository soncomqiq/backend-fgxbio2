package th.ac.chula.fgxbio2.models.tables;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "forenseq")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Forenseq {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "locus")
	private String locus;

	@Column(name = "genotype")
	private String genotype;

	@Column(name = "qc_indicator")
	private String qcIndicator;

	@Column(name = "chromosome_type")
	private String chromosomeType;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "sample_id")
	private Sample sample;

	@OneToMany(mappedBy = "forenseq", cascade = CascadeType.ALL)
	private List<ForenseqSequence> forenseqSequences;

	public Forenseq(String locus, String genotype, String qcIndicator, String chromosomeType) {
		super();
		this.locus = locus;
		this.genotype = genotype;
		this.qcIndicator = qcIndicator;
		this.chromosomeType = chromosomeType;
	}

	public void add(ForenseqSequence fs) {
		if (forenseqSequences == null) {
			forenseqSequences = new ArrayList<>();
		}

		forenseqSequences.add(fs);
		fs.setForenseq(this);
	}

	@Override
	public String toString() {
		return "Forenseq [id=" + id + ", locus=" + locus + ", genotype=" + genotype + ", qcIndicator=" + qcIndicator
				+ ", chromosomeType=" + chromosomeType + ", sample=" + sample + ", forenseqSequences="
				+ forenseqSequences + "]";
	}
}
