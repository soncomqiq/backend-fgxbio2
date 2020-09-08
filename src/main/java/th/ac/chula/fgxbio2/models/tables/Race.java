package th.ac.chula.fgxbio2.models.tables;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "races")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Race {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "race")
	private String race;

	@OneToMany(mappedBy = "race", cascade = CascadeType.ALL)
	private List<SummaryData> summaryData;

	@OneToMany(mappedBy = "race", cascade = CascadeType.ALL)
	private List<Person> persons;

	public Race(String race, List<SummaryData> summaryData) {
		super();
		this.race = race;
		this.summaryData = summaryData;
	}

	@Override
	public String toString() {
		return "Race [id=" + id + ", race=" + race + ", summaryData=" + summaryData + ", persons=" + persons + "]";
	}

}
