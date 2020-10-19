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

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "kit_id")
	private Kit kit;

	@OneToMany(mappedBy = "locus", cascade = CascadeType.ALL)
	private List<SummaryData> summaryData;

	public Locus() {
	}

	public Locus(String locus, Kit kit) {
		this.locus = locus;
		this.kit = kit;
	}

	@Override
	public String toString() {
		return "Locus [id=" + id + ", locus=" + locus + ", kit=" + kit + "]";
	}
}
