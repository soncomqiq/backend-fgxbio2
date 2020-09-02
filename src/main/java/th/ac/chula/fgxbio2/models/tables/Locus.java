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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "loci")
@Getter
@Setter
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
	
	@OneToMany(mappedBy = "locus")
	private List<SummaryData> summaryData;
	
	public Locus() {
	}

	public Locus(String locus, List<Kit> kits) {
		this.locus = locus;
		this.kits = kits;
	}

	@Override
	public String toString() {
		return "Locus [id=" + id + ", locus=" + locus + ", kits=" + kits + "]";
	}
}
