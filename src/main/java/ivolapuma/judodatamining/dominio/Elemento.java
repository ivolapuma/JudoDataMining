package ivolapuma.judodatamining.dominio;

import java.util.ArrayList;
import java.util.List;

public class Elemento {

	private List<String> items;
	
	public Elemento() {
		this.items = new ArrayList<>();
	}

	public void adicionaItem(String s) {
		this.items.add(s);
	}

	public List<String> getItems() {
		return this.items;
	}
	
}
