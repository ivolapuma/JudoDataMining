package ivolapuma.judodatamining.sequencedatabase;

import java.util.ArrayList;
import java.util.List;

public class SequenceDatabase {

	private List<Sequence> sequences;
	
	public SequenceDatabase() {
		this.sequences = new ArrayList<>();
	}
	
	public void add(Sequence sequence) {
		this.sequences.add(sequence);
	}
	
	public void addSequences(List<Sequence> sequences) {
		this.sequences = sequences;
	}
	
	public List<Sequence> getSequences() {
		return this.sequences;
	}
	
}
