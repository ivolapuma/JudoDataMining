package ivolapuma.judodatamining.arquivo;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import ivolapuma.judodatamining.exception.JudoDataMiningException;

public class TraducaoPadraoSequencialSPMFArquivoTest {

	@Test
	public void traduzirParaArquivo___arquivoPadraoSequencialSpmf___criaArquivoTraduzido() throws IOException, JudoDataMiningException {
		
		File spmfFile = new File(".//src/test/resources/padraoSequenciaSPMF_exemplo.txt");
		Map<String, String> mapTraducao = new HashMap<>();
		mapTraducao.put("1", "ANTEROPOSTERIOR DIREITA");
		mapTraducao.put("4", "TENTATIVA DE PEGADA");
		mapTraducao.put("9", "GOLA DIREITA");
		File traducaoFile = new File(".//src/test/resources/padraoSequencialSPMF_exemploTraduzido.csv");
		
		new TraducaoPadraoSequencialSPMFArquivo(spmfFile, mapTraducao).traduzirParaArquivo(traducaoFile);
		
		FileInputStream fis = new FileInputStream(traducaoFile);
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
		assertEquals("linhas.size", 9, linhas.size());
		assertEquals("cabecalho", "SUST.;ELEMENTOS;ACOES;PADRAO", linhas.get(0));
		assertEquals("linha #1", "8;3;5;(ANTEROPOSTERIOR DIREITA) (ANTEROPOSTERIOR DIREITA) (ANTEROPOSTERIOR DIREITA, TENTATIVA DE PEGADA, GOLA DIREITA)", linhas.get(1));
		assertEquals("linha #2", "8;4;6;(ANTEROPOSTERIOR DIREITA) (ANTEROPOSTERIOR DIREITA) (ANTEROPOSTERIOR DIREITA, TENTATIVA DE PEGADA, GOLA DIREITA) (ANTEROPOSTERIOR DIREITA)", linhas.get(2));
		assertEquals("linha #3", "8;3;4;(ANTEROPOSTERIOR DIREITA) (ANTEROPOSTERIOR DIREITA) (TENTATIVA DE PEGADA, GOLA DIREITA)", linhas.get(3));
		assertEquals("linha #4", "8;4;5;(ANTEROPOSTERIOR DIREITA) (ANTEROPOSTERIOR DIREITA) (TENTATIVA DE PEGADA, GOLA DIREITA) (ANTEROPOSTERIOR DIREITA)", linhas.get(4));
		assertEquals("linha #5", "8;3;5;(ANTEROPOSTERIOR DIREITA) (ANTEROPOSTERIOR DIREITA, TENTATIVA DE PEGADA, GOLA DIREITA) (ANTEROPOSTERIOR DIREITA)", linhas.get(5));
		assertEquals("linha #6", "8;3;5;(ANTEROPOSTERIOR DIREITA) (ANTEROPOSTERIOR DIREITA, TENTATIVA DE PEGADA, GOLA DIREITA) (TENTATIVA DE PEGADA)", linhas.get(6));
		assertEquals("linha #7", "9;3;5;(ANTEROPOSTERIOR DIREITA) (TENTATIVA DE PEGADA) (ANTEROPOSTERIOR DIREITA, TENTATIVA DE PEGADA, GOLA DIREITA)", linhas.get(7));
		assertEquals("linha #8", "8;4;6;(ANTEROPOSTERIOR DIREITA) (TENTATIVA DE PEGADA) (ANTEROPOSTERIOR DIREITA, TENTATIVA DE PEGADA, GOLA DIREITA) (ANTEROPOSTERIOR DIREITA)", linhas.get(8));
	}
	
}
