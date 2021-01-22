package ivolapuma.judodatamining.sequencedatabase.arquivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import ivolapuma.judodatamining.exception.JudoDataMiningException;
import ivolapuma.judodatamining.sequencedatabase.Element;
import ivolapuma.judodatamining.sequencedatabase.Sequence;
import ivolapuma.judodatamining.sequencedatabase.SequenceDatabase;

public class SequenceDatabaseArquivo {

	private List<Sequence> sequences;
	private File file;

	public SequenceDatabaseArquivo(List<Sequence> sequences, File file) {
		if (sequences == null) {
			throw new IllegalArgumentException("Lista de Sequences esta nula");
		} else if (sequences.size() <= 0) {
			throw new IllegalArgumentException("Lista de Sequences esta vazia");
		} else if (file == null) {
			throw new IllegalArgumentException("File esta nulo");
		}
		this.sequences = sequences;
		this.file = file;
	}

	public SequenceDatabaseArquivo(SequenceDatabase database, File file) {
		if (database == null) {
			throw new IllegalArgumentException("SequenceDatabase esta nula");
		} else if (database.getSequences() == null 
				|| database.getSequences().size() <= 0) {
			throw new IllegalArgumentException("Lista de Sequences da SequenceDatabase esta nula ou vazia");
		} else if (file == null) {
			throw new IllegalArgumentException("File esta nulo");
		}
		this.sequences = database.getSequences();
		this.file = file;
	}

	public void criar() throws JudoDataMiningException {
		try {
			FileWriter writer = new FileWriter(file);
			boolean primeiraSequence = true;
			for (Sequence sequence : sequences) {
				if (!primeiraSequence) {
					writer.write("\n");
				}
				StringBuffer linha = new StringBuffer();
				List<Element> elements = sequence.getElements();
				for (Element element : elements) {
					Set<Long> items = element.getItems();
					for (Long item : items) {
						linha.append(item.toString()).append(" ");
					}
					linha.append("-1").append(" ");
				}
				linha.append("-2");
				writer.write(linha.toString());
				primeiraSequence = false;
			}
			writer.close();
		} catch (IOException e) {
			throw new JudoDataMiningException("Erro ao criar arquivo SPMF SequenceDatabase.", e);
		}
	}

}
