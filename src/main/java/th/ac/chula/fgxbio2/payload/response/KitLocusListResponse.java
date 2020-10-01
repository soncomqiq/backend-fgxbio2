package th.ac.chula.fgxbio2.payload.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import th.ac.chula.fgxbio2.models.tables.Kit;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KitLocusListResponse {
	private List<Kit> aKits;
	private List<Kit> yKits;
	private List<Kit> xKits;
}
