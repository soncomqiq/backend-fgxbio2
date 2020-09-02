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
@Table(name = "regions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Region {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "region")
	private String region;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;
	
	@OneToMany(mappedBy = "region")
	private List<Province> provinces;
	
	@OneToMany(mappedBy = "region")
	private List<Person> persons;

	public Region(String region, Country country) {
		super();
		this.region = region;
		this.country = country;
	}

	@Override
	public String toString() {
		return "Region [id=" + id + ", region=" + region + ", country=" + country + ", provinces=" + provinces
				+ ", persons=" + persons + "]";
	}
}
