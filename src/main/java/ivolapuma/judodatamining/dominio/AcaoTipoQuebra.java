package ivolapuma.judodatamining.dominio;

import java.util.List;

public class AcaoTipoQuebra {

	private List<Long> ids;
	
	public AcaoTipoQuebra(List<Long> ids) {
		this.ids = ids;
	}
	
	public List<Long> getIds() {
		return this.ids;
	}
	
}
