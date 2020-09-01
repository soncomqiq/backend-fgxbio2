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
@Table(name = "races")
public class Race {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "race")
	private String race;

	@OneToMany(mappedBy = "race")
	private List<SummaryData> summaryData;
	
	@OneToMany(mappedBy = "race")
	private List<Person> persons;
}
