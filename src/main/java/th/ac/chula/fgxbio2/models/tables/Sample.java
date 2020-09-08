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
	private int sampleYear;

	@Column(name = "sample_id")
	private String sampleId;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "person_id")
	private Person person;

	@OneToMany(mappedBy = "sample", cascade = CascadeType.ALL)
	private List<Razor> razorList;

	@OneToMany(mappedBy = "sample", cascade = CascadeType.ALL)
	private List<CEData> cEDataList;

	@OneToMany(mappedBy = "sample", cascade = CascadeType.ALL)
	private List<Forenseq> forenseqList;

	public Sample(int sampleYear, String sampleId, Person person) {
		super();
		this.sampleYear = sampleYear;
		this.sampleId = sampleId;
		this.person = person;
	}

	public void add(Forenseq forenseq) {
		if (forenseqList == null) {
			forenseqList = new ArrayList<>();
		}

		forenseqList.add(forenseq);
		forenseq.setSample(this);
	}

	@Override
	public String toString() {
		return "Sample [id=" + id + ", sample_year=" + sampleYear + ", sample_id=" + sampleId + ", person=" + person
				+ ", razorList=" + razorList + ", cEDataList=" + cEDataList + ", forenseqList=" + forenseqList + "]";
	}

}
