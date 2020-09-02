package th.ac.chula.fgxbio2.models.tables;

import java.util.List;

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
@Table(name = "samples")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sample {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "sample_year")
	private int sample_year;

	@Column(name = "sample_id")
	private String sample_id;
	
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	
	@OneToMany(mappedBy = "sample")
	private List<Razor> razorList;

	@OneToMany(mappedBy = "sample")
	private List<CEData> cEDataList;
	
	@OneToMany(mappedBy = "sample")
	private List<Forenseq> forenseqList;

	public Sample(int sample_year, String sample_id, Person person) {
		super();
		this.sample_year = sample_year;
		this.sample_id = sample_id;
		this.person = person;
	}

	@Override
	public String toString() {
		return "Sample [id=" + id + ", sample_year=" + sample_year + ", sample_id=" + sample_id + ", person=" + person
				+ ", razorList=" + razorList + ", cEDataList=" + cEDataList + ", forenseqList=" + forenseqList + "]";
	}
}
