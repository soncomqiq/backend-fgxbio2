package th.ac.chula.fgxbio2.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class iSNPResponse {
	private String locus;
	private String allele;
	private int amount;
}
