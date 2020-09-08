package th.ac.chula.fgxbio2.models.tables;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "persons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", length = 6)
	private EGender gender;

	@Column(name = "age")
	private int age;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "race_id")
	private Race race;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "country_id")
	private Country country;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "region_id")
	private Region region;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "province_id")
	private Province province;

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	private List<Sample> samples;

	public Person(String firstname, String lastname, EGender gender, @Size(min = 0, max = 200) int age, Race race,
			Country country, Region region, Province province) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.age = age;
		this.race = race;
		this.country = country;
		this.region = region;
		this.province = province;
	}

	public void add(Sample sample) {
		if (samples == null) {
			samples = new ArrayList<>();
		}

		samples.add(sample);
		sample.setPerson(this);
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", gender=" + gender
				+ ", age=" + age + ", race=" + race + ", country=" + country + ", region=" + region + ", province="
				+ province + "]";
	}
}
