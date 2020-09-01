package th.ac.chula.fgxbio2.models.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "summary_data")
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

	@ManyToOne
	@JoinColumn(name = "locus_id")
	private Locus locus;

	@ManyToOne
	@JoinColumn(name = "continent_id")
	private Continent continent;

	@ManyToOne
	@JoinColumn(name = "race_id")
	private Race race;
	
	public SummaryData() {
	}

	public SummaryData(int frequency, float allele, String genotype, Locus locus, Continent continent, Race race) {
		this.frequency = frequency;
		this.allele = allele;
		this.genotype = genotype;
		this.locus = locus;
		this.continent = continent;
		this.race = race;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public float getAllele() {
		return allele;
	}

	public void setAllele(float allele) {
		this.allele = allele;
	}

	public String getGenotype() {
		return genotype;
	}

	public void setGenotype(String genotype) {
		this.genotype = genotype;
	}

	public Locus getLocus() {
		return locus;
	}

	public void setLocus(Locus locus) {
		this.locus = locus;
	}

	public Continent getContinent() {
		return continent;
	}

	public void setContinent(Continent continent) {
		this.continent = continent;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	@Override
	public String toString() {
		return "SummaryData [id=" + id + ", frequency=" + frequency + ", allele=" + allele + ", genotype=" + genotype
				+ ", locus=" + locus + ", continent=" + continent + ", race=" + race + "]";
	}
}
