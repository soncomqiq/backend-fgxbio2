package th.ac.chula.fgxbio2.models.tables;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ce_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CEData {
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

	public CEData(String locus, String genotype, String qcIndicator, String chromosomeType, Sample sample) {
		super();
		this.locus = locus;
		this.genotype = genotype;
		this.qcIndicator = qcIndicator;
		this.chromosomeType = chromosomeType;
		this.sample = sample;
	}

	@Override
	public String toString() {
		return "CEData [id=" + id + ", locus=" + locus + ", genotype=" + genotype + ", qcIndicator=" + qcIndicator
				+ ", chromosomeType=" + chromosomeType + ", sample=" + sample + "]";
	}
}
