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
@Table(name = "summary_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SummaryData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "frequency")
	private int frequency;

	@Column(name = "allele")
	private float allele;

	@Column(name = "genotype")
	private String genotype;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "locus_id")
	private Locus locus;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "continent_id")
	private Continent continent;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "race_id")
	private Race race;

	@Override
	public String toString() {
		return "SummaryData [id=" + id + ", frequency=" + frequency + ", allele=" + allele + ", genotype=" + genotype
				+ ", locus=" + locus + ", continent=" + continent + ", race=" + race + "]";
	}
}
