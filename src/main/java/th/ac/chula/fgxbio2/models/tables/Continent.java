package th.ac.chula.fgxbio2.models.tables;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "continents")
public class Continent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "continent")
	private String continent;
	
	@OneToMany(mappedBy = "continent")
	private List<SummaryData> summaryData;
	
	public Continent() {
	}

	public Continent(String continent, List<SummaryData> summaryData) {
		this.continent = continent;
		this.summaryData = summaryData;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public List<SummaryData> getSummaryData() {
		return summaryData;
	}

	public void setSummaryData(List<SummaryData> summaryData) {
		this.summaryData = summaryData;
	}

	@Override
	public String toString() {
		return "Continent [id=" + id + ", continent=" + continent + ", summaryData=" + summaryData + "]";
	}
}
