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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "countries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "regions", "persons" })
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "country")
	private String country;

	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	private List<Region> regions;

	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	private List<Person> persons;

	public Country(int id, String country) {
		super();
		this.id = id;
		this.country = country;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", country=" + country + ", regions=" + regions + ", persons=" + persons + "]";
	}
}
