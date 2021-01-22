package ivolapuma.judodatamining.sequencedatabase.arquivo;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ivolapuma.judodatamining.exception.JudoDataMiningException;
import ivolapuma.judodatamining.sequencedatabase.Element;
import ivolapuma.judodatamining.sequencedatabase.Sequence;
import ivolapuma.judodatamining.sequencedatabase.arquivo.SequenceDatabaseArquivo;

public class SequenceDatabaseArquivoTest {

	@Test
	public void criarArquivo___listaSequences___criaArquivoSequenceDatabaseSPMF() throws JudoDataMiningException, IOException {
		
		List<Sequence> sequences = new ArrayList<Sequence>();
		Sequence sequence = null;
		Element element = null;
		
		sequence = new Sequence();
		element = new Element();
		element.addItem(1L);
		element.addItem(10L);
		element.addItem(100L);
		sequence.add(element);
		element = new Element();
		element.addItem(11L);
		element.addItem(101L);
		sequence.add(element);
		sequences.add(sequence);
		
		sequence = new Sequence();
		element = new Element();
		element.addItem(1L);
		element.addItem(10L);
		element.addItem(100L);
		element.addItem(1000L);
		sequence.add(element);
		element = new Element();
		element.addItem(1L);
		element.addItem(11L);
		element.addItem(101L);
		sequence.add(element);
		sequences.add(sequence);
		
		File file = new File(".//src/test/resources/sequenceDatabaseArquivoTest_test2Sequences.txt");
		SequenceDatabaseArquivo database = new SequenceDatabaseArquivo(sequences, file);
		database.criar();
		
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader reader = new BufferedReader(isr);
		List<String> linhas = new ArrayList<String>();
		while (true) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}
			linhas.add(line);
		}
		reader.close();
		assertEquals("linhas.size", 2, linhas.size());
		assertEquals("linha #1", "1 10 100 -1 11 101 -1 -2", linhas.get(0));
		assertEquals("linha #2", "1 10 100 1000 -1 1 11 101 -1 -2", linhas.get(1));
	}
	
}
