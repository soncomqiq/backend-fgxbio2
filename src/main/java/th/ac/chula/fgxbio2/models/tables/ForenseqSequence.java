package th.ac.chula.fgxbio2.models.tables;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "forenseq_sequence")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForenseqSequence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "allele")
	private String allele;

	@Column(name = "read_count")
	private int readCount;

	@Size(max = 1000)
	@Column(name = "sequence")
	private String sequence;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "forenseq_id")
	private Forenseq forenseq;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "sample_id")
	private Sample sample;

	public ForenseqSequence(String allele, int readCount, @Size(max = 1000) String sequence, Sample sample) {
		super();
		this.allele = allele;
		this.readCount = readCount;
		this.sequence = sequence;
		this.sample = sample;
	}

	@Override
	public String toString() {
		return "ForenseqSequence [id=" + id + ", allele=" + allele + ", readCount=" + readCount + ", sequence="
				+ sequence + ", forenseq=" + forenseq + "]";
	}
}
