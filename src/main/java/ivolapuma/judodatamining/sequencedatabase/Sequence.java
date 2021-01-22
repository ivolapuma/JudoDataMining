package ivolapuma.judodatamining.sequencedatabase;

import java.util.ArrayList;
import java.util.List;

public class Sequence {

	List<Element> elements;
	
	public Sequence() {
		this.elements = new ArrayList<Element>();
	}

	public void add(Element element) {
		this.elements.add(element);
	}

	public List<Element> getElements() {
		return elements;
	}
	
}
