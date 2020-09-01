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
@Table(name = "loci")
public class Locus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "locus")
	private String locus;
	
	@ManyToMany
	@JoinTable(name = "kit_locus", joinColumns = @JoinColumn(name = "locus_id"), inverseJoinColumns = @JoinColumn(name = "kit_id"))
	private List<Kit> kits;
	
	public Locus() {
	}

	public Locus(String locus, List<Kit> kits) {
		this.locus = locus;
		this.kits = kits;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocus() {
		return locus;
	}

	public void setLocus(String locus) {
		this.locus = locus;
	}

	public List<Kit> getKits() {
		return kits;
	}

	public void setKits(List<Kit> kits) {
		this.kits = kits;
	}

	@Override
	public String toString() {
		return "Locus [id=" + id + ", locus=" + locus + ", kits=" + kits + "]";
	}
}
