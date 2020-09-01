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

@Entity
@Table(name = "regions")
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
}
