package th.ac.chula.fgxbio2.models.tables;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "summary_data")
public class SummaryData {
	private int id;
	
	private int frequency;
}
