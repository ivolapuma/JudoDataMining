package ivolapuma.judodatamining.simulacao;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import ivolapuma.judodatamining.arquivo.TraducaoPadraoSequencialSPMFArquivo;
import ivolapuma.judodatamining.dominio.LutaAcao;
import ivolapuma.judodatamining.exception.JudoDataMiningException;
import ivolapuma.judodatamining.factory.ConnectionFactory;
import ivolapuma.judodatamining.mineracaopadraosequencial.algoritmo.CMSPAM;
import ivolapuma.judodatamining.repository.LutaAcaoRepository;
import ivolapuma.judodatamining.repository.MPSItemRepository;
import ivolapuma.judodatamining.sequencedatabase.SequenceDatabase;
import ivolapuma.judodatamining.sequencedatabase.arquivo.SequenceDatabaseArquivo;
import ivolapuma.judodatamining.sequencedatabase.builder.LutaSequenceDatabaseBuilder;

public class SimulacaoAlgoritmoCMSPAM {

	public static void main(String[] args) throws JudoDataMiningException, SQLException {
//		simulacaoBischof();
//		simulacaoBischof_obrigatorioYuko();
		simulacaoBischof_padraoTamanhoMinimo4();
	}

	private static void simulacaoBischof_padraoTamanhoMinimo4() throws JudoDataMiningException, SQLException {
		Connection connection = ConnectionFactory.getConnection();
		long judoca = 3;
		String nomeJudoca = "BISCHOF";
		List<LutaAcao> lutaAcoes = new LutaAcaoRepository(connection).buscarPorJudoca(judoca);
		Map<String, String> mapTraducao = new MPSItemRepository(connection).buildMapTraducao();
		connection.close();
		List<Long> acaoTipoQuebras = new ArrayList<>();
		acaoTipoQuebras.add(6L); // tipo acao COMANDO DE TEMPO delimita um Element
		SequenceDatabase database = new LutaSequenceDatabaseBuilder(lutaAcoes, acaoTipoQuebras, nomeJudoca).build();
		File databaseFile = new File("C:/Users/ivolapuma/Documents/MESTRADO/RESULTADOS/TEMP/sequenceDatabase_Simulacao_BISCHOF.txt");
		File outcomeFile = new File("C:/Users/ivolapuma/Documents/MESTRADO/RESULTADOS/TEMP/Resultado_Simulacao_BISCHOF.txt");
		new SequenceDatabaseArquivo(database, databaseFile).criar();
		double inicio, fim;
		System.out.println("*** INICIANDO SIMULACAO ***");
		inicio = Calendar.getInstance().getTimeInMillis();
		new CMSPAM().executar(databaseFile, outcomeFile, 0.8, 4);
		fim = Calendar.getInstance().getTimeInMillis();
		File traducaoFile = new File("C:/Users/ivolapuma/Documents/MESTRADO/RESULTADOS/TEMP/Resultado_Simulacao_BISCHOF_traducao.csv");
		new TraducaoPadraoSequencialSPMFArquivo(outcomeFile, mapTraducao).traduzirParaArquivo(traducaoFile);
		System.out.println("TEMPO EXECUCAO: "+((fim-inicio)/1000.0)+" (s)");
		System.out.println("*** SIMULACAO CONCLUIDA ***");
		System.out.println("");
	}

	@SuppressWarnings("unused")
	private static void simulacaoBischof_obrigatorioYuko() throws JudoDataMiningException, SQLException {
		
		Connection connection = ConnectionFactory.getConnection();
		long judoca = 3;
		String nomeJudoca = "BISCHOF";
		List<LutaAcao> lutaAcoes = new LutaAcaoRepository(connection).buscarPorJudoca(judoca);
		connection.close();
		List<Long> acaoTipoQuebras = new ArrayList<>();
		acaoTipoQuebras.add(6L); // tipo acao COMANDO DE TEMPO delimita um Element
		SequenceDatabase database = new LutaSequenceDatabaseBuilder(lutaAcoes, acaoTipoQuebras, nomeJudoca).build();
		File databaseFile = new File(".//src/test/resources/sequenceDatabase_Simulacao_BISCHOF.txt");
//		File outcomeFile = new File("C:/Users/ivolapuma/Documents/MESTRADO/RESULTADOS/TEMP/Resultado_Simulacao_BISCHOF.txt");
		new SequenceDatabaseArquivo(database, databaseFile).criar();
		
		double inicio, fim;
		
//		System.out.println("*** INICIANDO SIMULACAO ***");
//		inicio = Calendar.getInstance().getTimeInMillis();
//		new CMSPAM().executar(databaseFile, new File("C:/Users/ivolapuma/Documents/MESTRADO/RESULTADOS/TEMP/BISCHOF_AlgoCMSPAM_YUKO_0.9.txt"), 0.9, new int[] {2724}); // padroes com YUKO
//		fim = Calendar.getInstance().getTimeInMillis();
//		System.out.println("TEMPO EXECUCAO: "+((fim-inicio)/1000.0)+" (s)");
//		System.out.println("*** SIMULACAO CONCLUIDA ***");
//		System.out.println("");
//
//		System.out.println("*** INICIANDO SIMULACAO ***");
//		inicio = Calendar.getInstance().getTimeInMillis();
//		new CMSPAM().executar(databaseFile, new File("C:/Users/ivolapuma/Documents/MESTRADO/RESULTADOS/TEMP/BISCHOF_AlgoCMSPAM_YUKO_0.8.txt"), 0.8, new int[] {2724}); // padroes com YUKO
//		fim = Calendar.getInstance().getTimeInMillis();
//		System.out.println("TEMPO EXECUCAO: "+((fim-inicio)/1000.0)+" (s)");
//		System.out.println("*** SIMULACAO CONCLUIDA ***");
//		System.out.println("");
//
//		System.out.println("*** INICIANDO SIMULACAO ***");
//		inicio = Calendar.getInstance().getTimeInMillis();
//		new CMSPAM().executar(databaseFile, new File("C:/Users/ivolapuma/Documents/MESTRADO/RESULTADOS/TEMP/BISCHOF_AlgoCMSPAM_YUKO_0.7.txt"), 0.7, new int[] {2724}); // padroes com YUKO
//		fim = Calendar.getInstance().getTimeInMillis();
//		System.out.println("TEMPO EXECUCAO: "+((fim-inicio)/1000.0)+" (s)");
//		System.out.println("*** SIMULACAO CONCLUIDA ***");
//		System.out.println("");
//
//		System.out.println("*** INICIANDO SIMULACAO ***");
//		inicio = Calendar.getInstance().getTimeInMillis();
//		new CMSPAM().executar(databaseFile, new File("C:/Users/ivolapuma/Documents/MESTRADO/RESULTADOS/TEMP/BISCHOF_AlgoCMSPAM_YUKO_0.6.txt"), 0.6, new int[] {2724}); // padroes com YUKO
//		fim = Calendar.getInstance().getTimeInMillis();
//		System.out.println("TEMPO EXECUCAO: "+((fim-inicio)/1000.0)+" (s)");
//		System.out.println("*** SIMULACAO CONCLUIDA ***");
//		System.out.println("");
//
//		System.out.println("*** INICIANDO SIMULACAO ***");
//		inicio = Calendar.getInstance().getTimeInMillis();
//		new CMSPAM().executar(databaseFile, new File("C:/Users/ivolapuma/Documents/MESTRADO/RESULTADOS/TEMP/BISCHOF_AlgoCMSPAM_YUKO_0.5.txt"), 0.5, new int[] {2724}); // padroes com YUKO
//		fim = Calendar.getInstance().getTimeInMillis();
//		System.out.println("TEMPO EXECUCAO: "+((fim-inicio)/1000.0)+" (s)");
//		System.out.println("*** SIMULACAO CONCLUIDA ***");
//		System.out.println("");
//
//		System.out.println("*** INICIANDO SIMULACAO ***");
//		inicio = Calendar.getInstance().getTimeInMillis();
//		new CMSPAM().executar(databaseFile, new File("C:/Users/ivolapuma/Documents/MESTRADO/RESULTADOS/TEMP/BISCHOF_AlgoCMSPAM_YUKO_0.4.txt"), 0.4, new int[] {2724}); // padroes com YUKO
//		fim = Calendar.getInstance().getTimeInMillis();
//		System.out.println("TEMPO EXECUCAO: "+((fim-inicio)/1000.0)+" (s)");
//		System.out.println("*** SIMULACAO CONCLUIDA ***");
//		System.out.println("");
//
//		System.out.println("*** INICIANDO SIMULACAO ***");
//		inicio = Calendar.getInstance().getTimeInMillis();
//		new CMSPAM().executar(databaseFile, new File("C:/Users/ivolapuma/Documents/MESTRADO/RESULTADOS/TEMP/BISCHOF_AlgoCMSPAM_YUKO_0.3.txt"), 0.3, new int[] {2724}); // padroes com YUKO
//		fim = Calendar.getInstance().getTimeInMillis();
//		System.out.println("TEMPO EXECUCAO: "+((fim-inicio)/1000.0)+" (s)");
//		System.out.println("*** SIMULACAO CONCLUIDA ***");
//		System.out.println("");

		System.out.println("*** INICIANDO SIMULACAO ***");
		inicio = Calendar.getInstance().getTimeInMillis();
		new CMSPAM().executar(databaseFile, new File("C:/Users/ivolapuma/Documents/MESTRADO/RESULTADOS/TEMP/BISCHOF_AlgoCMSPAM_YUKO_0.2.txt"), 0.2, new int[] {2724}); // padroes com YUKO
		fim = Calendar.getInstance().getTimeInMillis();
		System.out.println("TEMPO EXECUCAO: "+((fim-inicio)/1000.0)+" (s)");
		System.out.println("*** SIMULACAO CONCLUIDA ***");
		System.out.println("");
	}

	@SuppressWarnings("unused")
	private static void simulacaoBischof() throws JudoDataMiningException, SQLException {
		Connection connection = ConnectionFactory.getConnection();
		long judoca = 3;
		String nomeJudoca = "BISCHOF";
		List<LutaAcao> lutaAcoes = new LutaAcaoRepository(connection).buscarPorJudoca(judoca);
		connection.close();
		List<Long> acaoTipoQuebras = new ArrayList<>();
		acaoTipoQuebras.add(6L); // tipo acao COMANDO DE TEMPO delimita um Element
		SequenceDatabase database = new LutaSequenceDatabaseBuilder(lutaAcoes, acaoTipoQuebras, nomeJudoca).build();
		File databaseFile = new File(".//src/test/resources/sequenceDatabase_Simulacao_BISCHOF.txt");
		File outcomeFile = new File("C:/Users/ivolapuma/Documents/MESTRADO/RESULTADOS/TEMP/Resultado_Simulacao_BISCHOF.txt");
		new SequenceDatabaseArquivo(database, databaseFile).criar();
		double inicio, fim;
		System.out.println("*** INICIANDO SIMULACAO ***");
		inicio = Calendar.getInstance().getTimeInMillis();
//		new CMSPAM().executar(databaseFile, outcomeFile, 0.9);
//		new CMSPAM().executar(databaseFile, outcomeFile, 0.8);
//		new CMSPAM().executar(databaseFile, outcomeFile, 0.7);
//		new CMSPAM().executar(databaseFile, outcomeFile, 0.6);
//		new CMSPAM().executar(databaseFile, outcomeFile, 0.5);
//		new CMSPAM().executar(databaseFile, outcomeFile, 0.4);
//		new CMSPAM().executar(databaseFile, outcomeFile, 0.3);
//		new CMSPAM().executar(databaseFile, outcomeFile, 0.2); // nao finalizado
//		new CMSPAM().executar(databaseFile, outcomeFile, 0.9, 4);
//		new CMSPAM().executar(databaseFile, outcomeFile, 0.8, 4);
//		new CMSPAM().executar(databaseFile, outcomeFile, 0.7, 4);
//		new CMSPAM().executar(databaseFile, outcomeFile, 0.6, 4);
//		new CMSPAM().executar(databaseFile, outcomeFile, 0.5, 4);
//		new CMSPAM().executar(databaseFile, outcomeFile, 0.4, 4);
//		new CMSPAM().executar(databaseFile, outcomeFile, 0.3, 4);
//		new CMSPAM().executar(databaseFile, outcomeFile, 0.9, new int[] {2722}); // padroes com IPPON
//		new CMSPAM().executar(databaseFile, outcomeFile, 0.3, new int[] {2722}); // padroes com IPPON
//		new CMSPAM().executar(databaseFile, outcomeFile, 0.2, new int[] {2722}); // padroes com IPPON
		new CMSPAM().executar(databaseFile, outcomeFile, 0.1, new int[] {2722}); // padroes com IPPON // nao finalizado
		fim = Calendar.getInstance().getTimeInMillis();
		System.out.println("*** SIMULACAO CONCLUIDA ***");
		System.out.println("TEMPO EXECUCAO: "+((fim-inicio)/1000.0)+" (s)");
		
//		System.out.println("*** INICIANDO SIMULACAO ***");
//		inicio = Calendar.getInstance().getTimeInMillis();
//		new CMSPAM().executar(databaseFile, new File("C:/Users/ivolapuma/Documents/MESTRADO/RESULTADOS/TEMP/Resultado_Simulacao_BISCHOF_AlgoCMSPAM_0.8_IPPON.txt"), 0.8, new int[] {2722}); // padroes com IPPON
//		fim = Calendar.getInstance().getTimeInMillis();
//		System.out.println("*** SIMULACAO CONCLUIDA ***");
//		System.out.println("TEMPO EXECUCAO: "+((fim-inicio)/1000.0)+" (s)");
//
//		System.out.println("*** INICIANDO SIMULACAO ***");
//		inicio = Calendar.getInstance().getTimeInMillis();
//		new CMSPAM().executar(databaseFile, new File("C:/Users/ivolapuma/Documents/MESTRADO/RESULTADOS/TEMP/Resultado_Simulacao_BISCHOF_AlgoCMSPAM_0.7_IPPON.txt"), 0.7, new int[] {2722}); // padroes com IPPON
//		fim = Calendar.getInstance().getTimeInMillis();
//		System.out.println("*** SIMULACAO CONCLUIDA ***");
//		System.out.println("TEMPO EXECUCAO: "+((fim-inicio)/1000.0)+" (s)");
//
//		System.out.println("*** INICIANDO SIMULACAO ***");
//		inicio = Calendar.getInstance().getTimeInMillis();
//		new CMSPAM().executar(databaseFile, new File("C:/Users/ivolapuma/Documents/MESTRADO/RESULTADOS/TEMP/Resultado_Simulacao_BISCHOF_AlgoCMSPAM_0.6_IPPON.txt"), 0.6, new int[] {2722}); // padroes com IPPON
//		fim = Calendar.getInstance().getTimeInMillis();
//		System.out.println("*** SIMULACAO CONCLUIDA ***");
//		System.out.println("TEMPO EXECUCAO: "+((fim-inicio)/1000.0)+" (s)");
//
//		System.out.println("*** INICIANDO SIMULACAO ***");
//		inicio = Calendar.getInstance().getTimeInMillis();
//		new CMSPAM().executar(databaseFile, new File("C:/Users/ivolapuma/Documents/MESTRADO/RESULTADOS/TEMP/Resultado_Simulacao_BISCHOF_AlgoCMSPAM_0.5_IPPON.txt"), 0.5, new int[] {2722}); // padroes com IPPON
//		fim = Calendar.getInstance().getTimeInMillis();
//		System.out.println("*** SIMULACAO CONCLUIDA ***");
//		System.out.println("TEMPO EXECUCAO: "+((fim-inicio)/1000.0)+" (s)");
//
//		System.out.println("*** INICIANDO SIMULACAO ***");
//		inicio = Calendar.getInstance().getTimeInMillis();
//		new CMSPAM().executar(databaseFile, new File("C:/Users/ivolapuma/Documents/MESTRADO/RESULTADOS/TEMP/Resultado_Simulacao_BISCHOF_AlgoCMSPAM_0.4_IPPON.txt"), 0.4, new int[] {2722}); // padroes com IPPON
//		fim = Calendar.getInstance().getTimeInMillis();
//		System.out.println("*** SIMULACAO CONCLUIDA ***");
//		System.out.println("TEMPO EXECUCAO: "+((fim-inicio)/1000.0)+" (s)");
	}
	
}
