package ivolapuma.judodatamining.mineracaopadraosequencial.algoritmo;

import java.io.File;
import java.io.IOException;

import ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.AlgoCMSPADE;
import ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.candidatePatternsGeneration.CandidateGenerator;
import ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.candidatePatternsGeneration.CandidateGenerator_Qualitative;
import ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.dataStructures.creators.AbstractionCreator;
import ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.dataStructures.creators.AbstractionCreator_Qualitative;
import ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.dataStructures.database.SequenceDatabase;
import ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.idLists.creators.IdListCreator;
import ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.idLists.creators.IdListCreator_FatBitmap;
import ivolapuma.judodatamining.exception.JudoDataMiningException;
import ivolapuma.judodatamining.mineracaopadraosequencial.MineracaoPadraoSequencial;

public class CMSPADE implements MineracaoPadraoSequencial {

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

	private void executarAlgoritmo(File database, File resultado, double indiceSustentacao) throws JudoDataMiningException {
		
		double support = indiceSustentacao;
        boolean keepPatterns = true;
        boolean verbose = false;

        AbstractionCreator abstractionCreator = AbstractionCreator_Qualitative.getInstance();
        boolean depthFirstSearch = true;
        boolean outputSequenceIdentifiers = true; 

        IdListCreator idListCreator = IdListCreator_FatBitmap.getInstance();
        CandidateGenerator candidateGenerator = CandidateGenerator_Qualitative.getInstance();
        
        SequenceDatabase sequenceDatabase = new SequenceDatabase(abstractionCreator, idListCreator);
        try {
			sequenceDatabase.loadFile(database.getAbsolutePath(), support);
		} catch (IOException e) {
			throw new JudoDataMiningException("Erro ao carregar o Arquivo SequenceDabatase.", e);
		}
        
        AlgoCMSPADE algorithm = new AlgoCMSPADE(support, depthFirstSearch, abstractionCreator);
        
        try {
			algorithm.runAlgorithm(sequenceDatabase, candidateGenerator, keepPatterns, verbose, resultado.getAbsolutePath(), outputSequenceIdentifiers);
		} catch (IOException e) {
			throw new JudoDataMiningException("Erro ao executar o algoritmo de Mineracao Padrao Sequencial.", e);		
		}

        System.out.println("Relative Minimum support = "+support);
        System.out.println(algorithm.getNumberOfFrequentPatterns()+ " frequent patterns.");
        System.out.println(algorithm.printStatistics());
	}

}
