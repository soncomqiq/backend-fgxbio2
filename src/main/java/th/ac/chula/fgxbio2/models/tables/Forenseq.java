package th.ac.chula.fgxbio2.models.tables;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "forenseq")
public class Forenseq {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "locus")
	private String locus;

	@Column(name = "genotype")
	private String genotype;

	@Column(name = "qc_indicator")
	private String qcIndicator;

	@Column(name = "chromosome_type")
	private String chromosomeType;
	
	@ManyToOne
	@JoinColumn(name = "sample_id")
	private Sample sample;
	
	@OneToMany(mappedBy = "forenseq")
	private List<ForenseqSequence> forenseqSequences;
}
