package ivolapuma.judodatamining.mineracaopadraosequencial.algoritmo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ca.pfv.spmf.algorithms.sequentialpatterns.gsp_AGP.AlgoGSP;
import ca.pfv.spmf.algorithms.sequentialpatterns.gsp_AGP.items.SequenceDatabase;
import ca.pfv.spmf.algorithms.sequentialpatterns.gsp_AGP.items.Sequences;
import ca.pfv.spmf.algorithms.sequentialpatterns.gsp_AGP.items.creators.AbstractionCreator;
import ca.pfv.spmf.algorithms.sequentialpatterns.gsp_AGP.items.creators.AbstractionCreator_Qualitative;
import ivolapuma.judodatamining.exception.JudoDataMiningException;
import ivolapuma.judodatamining.mineracaopadraosequencial.MineracaoPadraoSequencial;

public class GSP implements MineracaoPadraoSequencial {

	@Override
	public void executar(File database, File resultado, double indiceSustentacao) throws JudoDataMiningException {
		
		if (database == null) {
			throw new IllegalArgumentException("File Database esta nulo");
		} else if (resultado == null) {
			throw new IllegalArgumentException("File Resultado esta nulo");
		} else if (indiceSustentacao <= 0.0 && indiceSustentacao > 1.0) {
			throw new IllegalArgumentException("Indice de Sustentacao deve ser maior que 0 e menor ou igual a 1");
		}
		
		executarAlgoritmo(database, resultado, indiceSustentacao);
	}

	private void executarAlgoritmo(File database, File resultado, double indiceSustentacao)
			throws JudoDataMiningException {
		
		double support = indiceSustentacao;
		double mingap = (double) 0;
		double maxgap = (double) Integer.MAX_VALUE;
		double windowSize = (double) 0;
        boolean keepPatterns = true;
        boolean verbose = false;
        boolean outputSequenceIdentifiers = true;

        AbstractionCreator abstractionCreator = AbstractionCreator_Qualitative.getInstance();
        SequenceDatabase sequenceDatabase = new SequenceDatabase(abstractionCreator);
        try {
			sequenceDatabase.loadFile(database.getAbsolutePath(), support);
		} catch (IOException e) {
			throw new JudoDataMiningException("Erro ao carregar o Arquivo SequenceDabatase.", e);
		}

        AlgoGSP algorithm = new AlgoGSP(support, mingap, maxgap, windowSize, abstractionCreator);
        
        Sequences sequences;
        try {
			sequences = algorithm.runAlgorithm(sequenceDatabase, keepPatterns, verbose, null, outputSequenceIdentifiers);
		} catch (IOException e) {
			throw new JudoDataMiningException("Erro ao executar o algoritmo de Mineracao Padrao Sequencial.", e);
		}
        
        geraArquivoResultado(database, resultado, indiceSustentacao, outputSequenceIdentifiers, sequenceDatabase,
				algorithm, sequences);
	}

	private void geraArquivoResultado(File database, File resultado, double indiceSustentacao,
			boolean outputSequenceIdentifiers, SequenceDatabase sequenceDatabase, AlgoGSP algorithm,
			Sequences sequences) throws JudoDataMiningException {
		try {
			FileWriter writer = new FileWriter(resultado);
			writer.write("___RESULTADO DA MINERAÇAO DE PADRAO SEQUENCIAL___");
			writer.write("\n");
			writer.write("\nArquivo SequenceDatabase: "+database.getAbsolutePath());
			writer.write("\n");
			writer.write("\n");
			writer.write(sequenceDatabase.toString());
			writer.write("\n");
			writer.write("___VALORES DA EXECUCAO___");
			writer.write("\nIndice Sustentacao (execucao)     : "+indiceSustentacao);
			writer.write("\nIndice Sustentacao Minima Absoluta: "+algorithm.getMinSupAbsolut());
			writer.write("\nTempo Execucao (ms)               : "+algorithm.runningTime());
			writer.write("\nNumero de Padroes Frequentes      : "+algorithm.getNumberOfFrequentPatterns());
			writer.write("\n");
			writer.write("\n");
			writer.write("___PADROES FREQUENTES___");
			writer.write("\n");
			writer.write("\n");
			writer.write(sequences.toStringToFile(outputSequenceIdentifiers));
			writer.write("\n");
			writer.write("\n");
			writer.write(sequences.toString());
//	        List<List<Pattern>> levels = sequences.levels;
//	        int level = 0;
//	        for (List<Pattern> patterns : levels) {
//	        	System.out.println("level="+level+" patterns.size="+patterns.size());
//	        	int numPattern = 1;
//				for (Pattern pattern : patterns) {
//					System.out.println("#pattern="+numPattern+" support="+pattern.getSupport()+" size="+pattern.size()+" pattern="+pattern.toString());
//					numPattern++;
//				}
//				level++;
//			}
			writer.close();
		} catch (IOException e) {
			throw new JudoDataMiningException("Erro ao gravar arquivo com resultado da Mineraçao de Padrao Sequencial.", e);
		}
	}

}
