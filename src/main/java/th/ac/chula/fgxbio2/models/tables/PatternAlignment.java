package th.ac.chula.fgxbio2.models.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pattern_alignment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatternAlignment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "locus")
	private String locus;

	@Column(name = "pattern")
	private String pattern;
	
	@Column(name = "seq_no")
	private String seqNo;
}
