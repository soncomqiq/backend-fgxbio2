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

@Entity
@Table(name = "forenseq_sequence")
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
}
