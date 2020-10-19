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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "kits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Kit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "kit")
	private String kit;

	@Column(name = "chromosome_type")
	private String chromosomeType;

	@OneToMany(mappedBy = "kit", cascade = CascadeType.ALL)
	private List<Locus> loci;

	public Kit(String kit, String chromosome_type) {
		super();
		this.kit = kit;
		this.chromosomeType = chromosome_type;
	}

	@Override
	public String toString() {
		return "Kit [id=" + id + ", kit=" + kit + ", chromosome_type=" + chromosomeType + ", loci=" + loci + "]";
	}

}
