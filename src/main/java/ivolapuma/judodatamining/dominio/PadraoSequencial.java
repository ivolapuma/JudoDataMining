package ivolapuma.judodatamining.dominio;

import java.util.ArrayList;
import java.util.List;

public class PadraoSequencial {

	private static final String ABRE_ELEMENTO = "(";
	private static final String FECHA_ELEMENTO = ")";
	private static final String ENTRE_ELEMENTOS = " ";
	private static final String ENTRE_ITEMS = ", ";

	private List<Elemento> elementos;
	
	public PadraoSequencial() {
		this.elementos = new ArrayList<>();
	}

	public void adicionaElemento(Elemento elemento) {
		this.elementos.add(elemento);
	}

	public List<Elemento> getElementos() {
		return this.elementos;
	}

	public int getTotalElementos() {
		return this.elementos.size();
	}

	public int getTotalItems() {
		int total = 0;
		for (Elemento e : elementos) {
			total += e.getItems().size();
		}
		return total;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		boolean primeiroElemento = true;
		for (Elemento elemento : elementos) {
			if (!primeiroElemento) {
				builder.append(ENTRE_ELEMENTOS);
			}
			builder.append(ABRE_ELEMENTO);
			boolean primeiroItem = true;
			for (String item : elemento.getItems()) {
				if (primeiroItem) {
					builder.append(item);
					primeiroItem = false;
				} else {
					builder.append(ENTRE_ITEMS).append(item);	
				}
			}
			builder.append(FECHA_ELEMENTO);
			primeiroElemento = false;
		}
		return builder.toString();
	}
	
}
