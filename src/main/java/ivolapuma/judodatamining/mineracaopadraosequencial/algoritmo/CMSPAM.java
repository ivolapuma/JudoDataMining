package ivolapuma.judodatamining.mineracaopadraosequencial.algoritmo;

import java.io.File;
import java.io.IOException;

import ca.pfv.spmf.algorithms.sequentialpatterns.spam.AlgoCMSPAM;
import ivolapuma.judodatamining.exception.JudoDataMiningException;
import ivolapuma.judodatamining.mineracaopadraosequencial.MineracaoPadraoSequencial;

public class CMSPAM implements MineracaoPadraoSequencial {

	private long numeroPadroes;
	private long tempoExecucaoMilissegundos;
	
	@Override
	public void executar(File database, File resultado, double indiceSustentacao) throws JudoDataMiningException {

		if (database == null) {
			throw new IllegalArgumentException("File Database esta nulo");
		} else if (resultado == null) {
			throw new IllegalArgumentException("File Resultado esta nulo");
		} else if (indiceSustentacao <= 0.0 && indiceSustentacao > 1.0) {
			throw new IllegalArgumentException("Indice de Sustentacao deve ser maior que 0 e menor ou igual a 1");
		}

		executarAlgoritmo(database, resultado, indiceSustentacao, true, null, null, null, null);
	}

	public void executar(File database, File resultado, double indiceSustentacao, int tamanhoMinimoPadrao) 
			throws JudoDataMiningException {

		if (database == null) {
			throw new IllegalArgumentException("File Database esta nulo");
		} else if (resultado == null) {
			throw new IllegalArgumentException("File Resultado esta nulo");
		} else if (indiceSustentacao <= 0.0 && indiceSustentacao > 1.0) {
			throw new IllegalArgumentException("Indice de Sustentacao deve ser maior que 0 e menor ou igual a 1");
		} else if (tamanhoMinimoPadrao < 0) {
			throw new IllegalArgumentException("Indice de Sustentacao deve ser maior que 0");
		}
		
		executarAlgoritmo(database, resultado, indiceSustentacao, true, tamanhoMinimoPadrao, null, null, null);
	}
	
	public void executar(File database, File resultado, double indiceSustentacao, int[] itensObrigatorios) 
			throws JudoDataMiningException {
		
		if (database == null) {
			throw new IllegalArgumentException("File Database esta nulo");
		} else if (resultado == null) {
			throw new IllegalArgumentException("File Resultado esta nulo");
		} else if (indiceSustentacao <= 0.0 && indiceSustentacao > 1.0) {
			throw new IllegalArgumentException("Indice de Sustentacao deve ser maior que 0 e menor ou igual a 1");
		} else if (itensObrigatorios == null || itensObrigatorios.length <= 0) {
			throw new IllegalArgumentException("Array de Itens Obrigatorios esta nulo ou vazio");
		}
		
		executarAlgoritmo(database, resultado, indiceSustentacao, true, null, null, null, itensObrigatorios);
	}

	public void executar(File database, File resultado, double indiceSustentacao, int tamanhoMinimoPadrao, 
			int[] itensObrigatorios) throws JudoDataMiningException {
		
		if (database == null) {
			throw new IllegalArgumentException("File Database esta nulo");
		} else if (resultado == null) {
			throw new IllegalArgumentException("File Resultado esta nulo");
		} else if (indiceSustentacao <= 0.0 && indiceSustentacao > 1.0) {
			throw new IllegalArgumentException("Indice de Sustentacao deve ser maior que 0 e menor ou igual a 1");
		} else if (tamanhoMinimoPadrao < 0) {
			throw new IllegalArgumentException("Indice de Sustentacao deve ser maior que 0");
		} else if (itensObrigatorios == null || itensObrigatorios.length <= 0) {
			throw new IllegalArgumentException("Array de Itens Obrigatorios esta nulo ou vazio");
		}
		
		executarAlgoritmo(database, resultado, indiceSustentacao, true, tamanhoMinimoPadrao, null, null, itensObrigatorios);
	}

	private void executarAlgoritmo(File database, File resultado, double indiceSustentacao, boolean retornaIdsSequencias, 
			Integer tamanhoMinimoPadrao, Integer tamanhoMaximoPadrao, Integer intervaloMaximo, int[] itensObrigatorios)
			throws JudoDataMiningException {
		
		AlgoCMSPAM algorithm = new AlgoCMSPAM();
		
		// parametros opcionais
		if (tamanhoMinimoPadrao != null) {
			algorithm.setMinimumPatternLength(tamanhoMinimoPadrao.intValue());
		}
		if (tamanhoMaximoPadrao != null) {
			algorithm.setMaximumPatternLength(tamanhoMaximoPadrao.intValue());
		}
		if (intervaloMaximo != null) {
			algorithm.setMaxGap(intervaloMaximo.intValue()); // 1 -> no gap
		}
		if (itensObrigatorios != null && itensObrigatorios.length > 0) {
			algorithm.setMustAppearItems(itensObrigatorios);	
		}
		
		try {
			long execucaoInicio = System.currentTimeMillis();
			algorithm.runAlgorithm(database.getAbsolutePath(), resultado.getAbsolutePath(), indiceSustentacao, retornaIdsSequencias);
			long execucaoFim = System.currentTimeMillis();
			this.numeroPadroes = algorithm.patternCount;
			this.tempoExecucaoMilissegundos = execucaoFim - execucaoInicio;
		} catch (IOException e) {
			throw new JudoDataMiningException("Erro ao executar o algoritmo de Mineracao Padrao Sequencial.", e);
		}
	}

	public long getNumeroPadroes() {
		return numeroPadroes;
	}

	public long getTempoExecucaoMilissegundos() {
		return tempoExecucaoMilissegundos;
	}
	
}
