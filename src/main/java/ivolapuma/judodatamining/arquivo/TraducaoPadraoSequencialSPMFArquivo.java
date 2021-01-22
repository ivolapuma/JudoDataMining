package ivolapuma.judodatamining.arquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import ivolapuma.judodatamining.dominio.Elemento;
import ivolapuma.judodatamining.dominio.PadraoSequencial;
import ivolapuma.judodatamining.dominio.TraducaoPadraoSequencialDetalhe;
import ivolapuma.judodatamining.exception.JudoDataMiningException;

public class TraducaoPadraoSequencialSPMFArquivo {

	private static final String NOVA_LINHA = "\n";
	private static final String SEPARADOR_ARQUIVO_SPMF = " ";
	
	private File spmfFile;
	private Map<String, String> mapTraducao;

	public TraducaoPadraoSequencialSPMFArquivo(File spmfFile, Map<String, String> mapTraducao) {
		if (spmfFile == null) {
			throw new IllegalArgumentException("SPMF File esta nulo");
		} else if (mapTraducao == null || mapTraducao.size() <= 0) {
			throw new IllegalArgumentException("Map de Traducao esta nulo ou vazio");
		}
		this.spmfFile = spmfFile;
		this.mapTraducao = mapTraducao;
	}

	public void traduzirParaArquivo(File traducaoFile) throws JudoDataMiningException {
		
		if (traducaoFile == null) {
			throw new IllegalArgumentException("Traducao File esta nulo");
		}
		
		FileInputStream fis = abrirArquivoSpmf();
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader reader = new BufferedReader(isr);

		FileWriter writer = getWriterArquivoTraducao(traducaoFile);
		gravarRegistro(writer, "SUST.;ELEMENTOS;ACOES;PADRAO");

		// loop de leitura e traducao do arquivo SPMF
		while (true) {
			
			String line = lerProximaLinha(reader);
			if (line == null) {
				break;
			}
			
			String[] strings = line.split(SEPARADOR_ARQUIVO_SPMF);
			PadraoSequencial padrao = extrairPadraoSequencial(strings);
			PadraoSequencial padraoTraduzido = traduzirPadraoSequencial(padrao, mapTraducao);
			int numeroSustentacao = extrairNumeroSustentacao(strings);
			int numeroElementos = padraoTraduzido.getTotalElementos();
			int numeroAcoes = padraoTraduzido.getTotalItems();
			
			TraducaoPadraoSequencialDetalhe detalhe = 
					new TraducaoPadraoSequencialDetalhe(numeroSustentacao, numeroElementos, numeroAcoes, 
							padraoTraduzido.toString());
			gravarRegistro(writer, NOVA_LINHA);
			gravarRegistro(writer, detalhe.toString());
		}
		
		fecharArquivoTraduzido(writer);
		fecharArquivoSpmf(reader, isr, fis);
	}

	private void gravarRegistro(FileWriter writer, String registro)
			throws JudoDataMiningException {
		try {
			writer.write(registro);
		} catch (IOException e) {
			throw new JudoDataMiningException("Erro ao gravar registro no Traducao File: "+e.getMessage(), e);
		}
	}

	private void fecharArquivoTraduzido(FileWriter writer) throws JudoDataMiningException {
		try {
			writer.close();
		} catch (IOException e) {
			throw new JudoDataMiningException("Erro ao fechar Traducao File: "+e.getMessage(), e);
		}
	}

	private int extrairNumeroSustentacao(String[] strings) {
		boolean pegarProximo = false;
		for (String s : strings) {
			if (pegarProximo) {
				return Integer.valueOf(s).intValue();
			}
			if (s.equals("#SUP:")) {
				pegarProximo = true;
			} 
		}
		return 0;
	}

	private PadraoSequencial traduzirPadraoSequencial(PadraoSequencial padrao, Map<String, String> mapTraducao) {
		PadraoSequencial padraoTraduzido = new PadraoSequencial();
		for (Elemento elemento : padrao.getElementos()) {
			Elemento elementoTraduzido = new Elemento();
			for (String item : elemento.getItems()) {
				elementoTraduzido.adicionaItem(mapTraducao.get(item));
			}
			padraoTraduzido.adicionaElemento(elementoTraduzido);
		}
		return padraoTraduzido;
	}

	private PadraoSequencial extrairPadraoSequencial(String[] strings) {
		PadraoSequencial padrao = new PadraoSequencial();
		Elemento elemento = new Elemento();
		for (String s : strings) {
			if (s.equals("#SUP:")) {
				break;
			} else if (s.equals("-1")) {
				padrao.adicionaElemento(elemento);
				elemento = new Elemento();
			} else {
				elemento.adicionaItem(s);	
			}
		}
		return padrao;
	}

	private FileWriter getWriterArquivoTraducao(File traducaoFile) throws JudoDataMiningException {
		FileWriter writer;
		try {
			writer = new FileWriter(traducaoFile);
		} catch (IOException e) {
			throw new JudoDataMiningException("Erro ao preparar criacao do Traducao File: "+e.getMessage(), e);
		}
		return writer;
	}

	private void fecharArquivoSpmf(BufferedReader reader, InputStreamReader isr, FileInputStream fis) throws JudoDataMiningException {
		try {
			reader.close();
			isr.close();
			fis.close();
		} catch (IOException e) {
			throw new JudoDataMiningException("Erro ao fechar SPMF File: "+e.getMessage(), e);
		}
	}

	private String lerProximaLinha(BufferedReader reader) throws JudoDataMiningException {
		String line;
		try {
			line = reader.readLine();
		} catch (IOException e) {
			throw new JudoDataMiningException("Erro ao ler proxima linha do SPMF File: "+e.getMessage(), e);
		}
		return line;
	}

	private FileInputStream abrirArquivoSpmf() throws JudoDataMiningException {
		FileInputStream fis;
		try {
			fis = new FileInputStream(spmfFile);
		} catch (FileNotFoundException e) {
			throw new JudoDataMiningException("Erro ao abrir SPMF File: "+e.getMessage(), e);
		}
		return fis;
	}

}
