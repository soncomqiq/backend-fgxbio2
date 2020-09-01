package th.ac.chula.fgxbio2.models.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "persons")
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
}
