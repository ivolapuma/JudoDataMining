package ivolapuma.judodatamining.spmf.test;

import java.io.IOException;

import ca.pfv.spmf.algorithms.sequentialpatterns.gsp_AGP.AlgoGSP;
import ca.pfv.spmf.algorithms.sequentialpatterns.gsp_AGP.items.SequenceDatabase;
import ca.pfv.spmf.algorithms.sequentialpatterns.gsp_AGP.items.creators.AbstractionCreator;
import ca.pfv.spmf.algorithms.sequentialpatterns.gsp_AGP.items.creators.AbstractionCreator_Qualitative;

public class AlgoritmoGSPTest {

    public static void main(String[] args) throws IOException {

    	// Load a sequence database
        double support = (double)0.5, mingap = 0, maxgap = Integer.MAX_VALUE, windowSize = 0;
        boolean keepPatterns = true;
        boolean verbose = false;
        // if you set the following parameter to true, the sequence ids of the sequences where
        // each pattern appears will be shown in the result
//        boolean outputSequenceIdentifiers = false;
        boolean outputSequenceIdentifiers = true;

        AbstractionCreator abstractionCreator = AbstractionCreator_Qualitative.getInstance();
        SequenceDatabase sequenceDatabase = new SequenceDatabase(abstractionCreator);
        sequenceDatabase.loadFile(".//src/test/resources/sequenceDatabase_exemplo.txt", support);

        AlgoGSP algorithm = new AlgoGSP(support, mingap, maxgap, windowSize, abstractionCreator);
        
        System.out.println(sequenceDatabase.toString());
        
        algorithm.runAlgorithm(sequenceDatabase, keepPatterns, verbose, null, outputSequenceIdentifiers);
        
        System.out.println(algorithm.getNumberOfFrequentPatterns()+ " frequent pattern found.");
        System.out.println(algorithm.printedOutputToSaveInFile());
        System.out.println(algorithm.printStatistics());
    }
    
}
