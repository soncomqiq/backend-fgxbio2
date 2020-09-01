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

@Entity
@Table(name = "kits")
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

	public Kit() {

	}

	public Kit(String kit, String chromosome_type, List<Locus> loci) {
		super();
		this.kit = kit;
		this.chromosome_type = chromosome_type;
		this.loci = loci;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKit() {
		return kit;
	}

	public void setKit(String kit) {
		this.kit = kit;
	}

	public String getChromosome_type() {
		return chromosome_type;
	}

	public void setChromosome_type(String chromosome_type) {
		this.chromosome_type = chromosome_type;
	}

	public List<Locus> getLoci() {
		return loci;
	}

	public void setLoci(List<Locus> loci) {
		this.loci = loci;
	}

	@Override
	public String toString() {
		return "Kit [id=" + id + ", kit=" + kit + ", chromosome_type=" + chromosome_type + ", loci=" + loci + "]";
	}

}
