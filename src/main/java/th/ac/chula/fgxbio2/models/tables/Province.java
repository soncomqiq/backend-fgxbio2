package th.ac.chula.fgxbio2.models.tables;

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
@Table(name = "provinces")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Province {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "provice")
	private String province;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "region_id")
	private Region region;

	@OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
	private List<Person> persons;

	public Province(String province, Region region) {
		super();
		this.province = province;
		this.region = region;
	}

	@Override
	public String toString() {
		return "Province [id=" + id + ", province=" + province + ", region=" + region + ", persons=" + persons + "]";
	}
}
