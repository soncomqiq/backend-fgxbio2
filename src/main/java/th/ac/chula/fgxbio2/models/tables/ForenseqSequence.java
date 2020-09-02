package th.ac.chula.fgxbio2.models.tables;

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
	private int allele;

	@Column(name = "read_count")
	private int readCount;

	@Column(name = "type")
	private int type;

	@Size(max = 1000)
	@Column(name = "sequence")
	private String sequence;
	
	@ManyToOne
	@JoinColumn(name = "forenseq_id")
	private Forenseq forenseq;

	public ForenseqSequence(int id, int allele, int readCount, int type, @Size(max = 1000) String sequence,
			Forenseq forenseq) {
		super();
		this.id = id;
		this.allele = allele;
		this.readCount = readCount;
		this.type = type;
		this.sequence = sequence;
		this.forenseq = forenseq;
	}

	@Override
	public String toString() {
		return "ForenseqSequence [id=" + id + ", allele=" + allele + ", readCount=" + readCount + ", type=" + type
				+ ", sequence=" + sequence + ", forenseq=" + forenseq + "]";
	}
}
