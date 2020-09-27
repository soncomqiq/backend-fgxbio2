package th.ac.chula.fgxbio2.payload.response;

import java.util.List;

import lombok.AllArgsConstructor;

import lombok.Setter;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KitLocusResponse {
	private List<String> aKits;

	private List<String> xKits;

	private List<String> yKits;
}
