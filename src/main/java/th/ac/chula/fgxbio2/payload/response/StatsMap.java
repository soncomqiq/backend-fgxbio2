package th.ac.chula.fgxbio2.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatsMap {
	private String province;
	private float latitude;
	private float longitude;
	private float allele;
	private int count;
	private String color;
}
