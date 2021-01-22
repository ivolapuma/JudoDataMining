package ivolapuma.judodatamining.sequencedatabase;

import java.util.Set;
import java.util.TreeSet;

public class Element {

	private Set<Long> items;
	
	public Element() {
		this.items = new TreeSet<>();
	}

	public void addItem(Long item) {
		this.items.add(item);
	}
	
	public Set<Long> getItems() {
		return this.items;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		boolean primeiro = true;
		for (Long item : items) {
			if (primeiro) {
				builder.append(item.toString());
				primeiro = false;
			} else {
				builder.append(" ").append(item.toString());
			}
		}
		return builder.toString();
	}
	
}
