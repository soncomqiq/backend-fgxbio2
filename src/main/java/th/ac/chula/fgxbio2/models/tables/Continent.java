package th.ac.chula.fgxbio2.models.tables;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "continents")
@Getter
@Setter
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

	@Override
	public String toString() {
		return "Continent [id=" + id + ", continent=" + continent + ", summaryData=" + summaryData + "]";
	}
}
