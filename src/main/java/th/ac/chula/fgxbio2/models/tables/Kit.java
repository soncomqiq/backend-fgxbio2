package th.ac.chula.fgxbio2.models.tables;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	private String chromosome_type;

	@ManyToMany
	@JoinTable(name = "kit_locus", joinColumns = @JoinColumn(name = "kit_id"), inverseJoinColumns = @JoinColumn(name = "locus_id"))
	private List<Locus> loci;

	public Kit(String kit, String chromosome_type) {
		super();
		this.kit = kit;
		this.chromosome_type = chromosome_type;
	}

	@Override
	public String toString() {
		return "Kit [id=" + id + ", kit=" + kit + ", chromosome_type=" + chromosome_type + ", loci=" + loci + "]";
	}

}
