package th.ac.chula.fgxbio2.models.tables;

import java.util.List;

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
	@Size(min = 0, max = 200)
	private int age;

	@ManyToOne
	@JoinColumn(name = "race_id")
	private Race race;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

	@ManyToOne
	@JoinColumn(name = "region_id")
	private Region region;

	@ManyToOne
	@JoinColumn(name = "province_id")
	private Province province;
	
	@OneToMany(mappedBy = "person")
	private List<Sample> samples;

	public Person(int id, String firstname, String lastname, EGender gender, @Size(min = 0, max = 200) int age,
			Race race, Country country, Region region, Province province) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.age = age;
		this.race = race;
		this.country = country;
		this.region = region;
		this.province = province;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", gender=" + gender
				+ ", age=" + age + ", race=" + race + ", country=" + country + ", region=" + region + ", province="
				+ province + ", samples=" + samples + "]";
	}
}
