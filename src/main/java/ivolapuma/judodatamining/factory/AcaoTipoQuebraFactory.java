package ivolapuma.judodatamining.factory;

import java.util.ArrayList;
import java.util.List;

import ivolapuma.judodatamining.dominio.AcaoTipoQuebra;

public class AcaoTipoQuebraFactory {

	public static AcaoTipoQuebra create(long ...ids) {
		List<Long> list = new ArrayList<Long>();
		for (long id : ids) {
			list.add(Long.valueOf(id));
		}
		return new AcaoTipoQuebra(list);
	}
	
}
