package ivolapuma.judodatamining;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import ivolapuma.judodatamining.dominio.AcaoTipoQuebra;
import ivolapuma.judodatamining.dominio.Judoca;
import ivolapuma.judodatamining.dominio.LutaAcao;
import ivolapuma.judodatamining.exception.JudoDataMiningException;
import ivolapuma.judodatamining.factory.AcaoTipoQuebraFactory;
import ivolapuma.judodatamining.factory.ConnectionFactory;
import ivolapuma.judodatamining.ferramenta.AnalisadorTecnicoTatico;
import ivolapuma.judodatamining.repository.JudocaRepository;
import ivolapuma.judodatamining.repository.LutaAcaoRepository;
import ivolapuma.judodatamining.repository.MPSItemRepository;

public class JudoDataMining {

	private static final int COMANDO_DE_TEMPO = 6;
	private static final int BISCHOF = 3;
	private static final int GUILHEIRO = 23;
	private static final int VALOIS_FORTIER = 65;
	private static final int ELMONT_G = 21;
	private static final int IPPON = 2722;
	private static final int SCHMITT = 54;
	private static final int STEVENS = 58;
	private static final int LUCENTI = 34;
	private static final int BOZBAYEV = 5;
	private static final int NAKAI = 43;
	private static final int MARIJANOVIC = 38;

	public static void main(String[] args) throws JudoDataMiningException, SQLException, IOException {
		System.out.println("*** Iniciando JudoDataMining... *** ");
		File dir = new File("C:/Users/ivolapuma/Documents/IPT/MESTRADO/SIMULACAO");
		JudoDataMiningLog log = new JudoDataMiningLog(new File(dir, "LOG_JudoDataMining.csv"));
		log.criarLog();
		BigDecimal indiceSustentacaoMinimo = BigDecimal.valueOf(0.2);
		BigDecimal indiceDecremento = BigDecimal.valueOf(0.1);
		BigDecimal tempoExecucaoLimite = BigDecimal.valueOf(2.0);
		Integer tamanhoPadraoMinimo = Integer.valueOf(4);
		int[] itensObrigatorios = new int[] {IPPON};
		simulacaoDadosJudoca(BISCHOF, dir, log, tempoExecucaoLimite, indiceDecremento, indiceSustentacaoMinimo, tamanhoPadraoMinimo, itensObrigatorios); // 10 lutas  
		simulacaoDadosJudoca(GUILHEIRO, dir, log, tempoExecucaoLimite, indiceDecremento, indiceSustentacaoMinimo, tamanhoPadraoMinimo, itensObrigatorios); // 10 lutas
		simulacaoDadosJudoca(VALOIS_FORTIER, dir, log, tempoExecucaoLimite, indiceDecremento, indiceSustentacaoMinimo, tamanhoPadraoMinimo, itensObrigatorios); // 9 lutas
		simulacaoDadosJudoca(ELMONT_G, dir, log, tempoExecucaoLimite, indiceDecremento, indiceSustentacaoMinimo, tamanhoPadraoMinimo, itensObrigatorios); // 8 lutas 
		simulacaoDadosJudoca(SCHMITT, dir, log, tempoExecucaoLimite, indiceDecremento, indiceSustentacaoMinimo, tamanhoPadraoMinimo, itensObrigatorios); // 7 lutas
		simulacaoDadosJudoca(STEVENS, dir, log, tempoExecucaoLimite, indiceDecremento, indiceSustentacaoMinimo, tamanhoPadraoMinimo, itensObrigatorios); // 6 lutas
		simulacaoDadosJudoca(LUCENTI, dir, log, tempoExecucaoLimite, indiceDecremento, indiceSustentacaoMinimo, tamanhoPadraoMinimo, itensObrigatorios); // 5 lutas
		simulacaoDadosJudoca(BOZBAYEV, dir, log, tempoExecucaoLimite, indiceDecremento, indiceSustentacaoMinimo, tamanhoPadraoMinimo, itensObrigatorios); // 4 lutas
		simulacaoDadosJudoca(NAKAI, dir, log, tempoExecucaoLimite, indiceDecremento, BigDecimal.valueOf(0.7), tamanhoPadraoMinimo, itensObrigatorios); // 3 lutas
		simulacaoDadosJudoca(MARIJANOVIC, dir, log, tempoExecucaoLimite, indiceDecremento, BigDecimal.valueOf(0.6), tamanhoPadraoMinimo, itensObrigatorios); // 2 lutas
		System.out.println("*** JudoDataMining finalizado! *** ");
	}

	private static void simulacaoDadosJudoca(int idJudoca, File dir, JudoDataMiningLog log, 
			BigDecimal tempoExecucaoLimite, BigDecimal indiceDecremento, BigDecimal indiceMinimo, Integer tamanhoPadraoMinimo, int[] itensObrigatorios)
			throws JudoDataMiningException, SQLException, IOException {

		Connection connection = ConnectionFactory.getConnection();
		Map<String, String> mapTraducao = new MPSItemRepository(connection).buildMapTraducao();
		Judoca judoca = new JudocaRepository(connection).buscarJudoca(idJudoca); 
		List<LutaAcao> lutaAcoes = new LutaAcaoRepository(connection).buscarPorJudoca(judoca.getId());
		connection.close();

		AcaoTipoQuebra acaoTipoQuebra = AcaoTipoQuebraFactory.create(COMANDO_DE_TEMPO);
		AnalisadorTecnicoTatico analisador = new AnalisadorTecnicoTatico(judoca, lutaAcoes, acaoTipoQuebra, mapTraducao);

		File databaseFile = new File(dir, buildDatabaseFilename(judoca));

		BigDecimal indiceSustentacao;
		indiceSustentacao = BigDecimal.ONE;
		do {
			analisador.executar(
					databaseFile, 
					new File(dir, buildResultadoFilename(judoca, indiceSustentacao.doubleValue())), 
					new File(dir, buildTraducaoFilename(judoca, indiceSustentacao.doubleValue())), 
					indiceSustentacao.doubleValue());
			log.logAnalise(analisador);
			if (analisador.getTempoExecucaoAlgoritmo() >= tempoExecucaoLimite.doubleValue()) {
				break;
			}
			indiceSustentacao = indiceSustentacao.subtract(indiceDecremento);
		} while (indiceSustentacao.compareTo(indiceMinimo) > 0);
		
		indiceSustentacao = BigDecimal.ONE;
		do {
			analisador.executar(
					databaseFile, 
					new File(dir, buildResultadoFilename(judoca, indiceSustentacao.doubleValue(), tamanhoPadraoMinimo)), 
					new File(dir, buildTraducaoFilename(judoca, indiceSustentacao.doubleValue(), tamanhoPadraoMinimo)), 
					indiceSustentacao.doubleValue(), tamanhoPadraoMinimo);
			log.logAnalise(analisador);
			if (analisador.getTempoExecucaoAlgoritmo() >= tempoExecucaoLimite.doubleValue()) {
				break;
			}
			indiceSustentacao = indiceSustentacao.subtract(indiceDecremento);
		} while (indiceSustentacao.compareTo(indiceMinimo) > 0);
		
		indiceSustentacao = BigDecimal.ONE;
		do {
			analisador.executar(
					databaseFile, 
					new File(dir, buildResultadoFilename(judoca, indiceSustentacao.doubleValue(), itensObrigatorios)), 
					new File(dir, buildTraducaoFilename(judoca, indiceSustentacao.doubleValue(), itensObrigatorios)), 
					indiceSustentacao.doubleValue(), itensObrigatorios);
			log.logAnalise(analisador);
			if (analisador.getTempoExecucaoAlgoritmo() >= tempoExecucaoLimite.doubleValue()) {
				break;
			}
			indiceSustentacao = indiceSustentacao.subtract(indiceDecremento);
		} while (indiceSustentacao.compareTo(indiceMinimo) > 0);
	}

	private static String buildTraducaoFilename(Judoca judoca, double indiceSustentacao, int[] itensObrigatorios) {
		return new StringBuffer().append("Traducao").append("_").append(judoca.getNome()).append("_").append(indiceSustentacao).append("_").append(arrayToString(itensObrigatorios)).append(".CSV").toString();
	}

	private static String buildTraducaoFilename(Judoca judoca, double indiceSustentacao, int tamanhoMinimoPadrao) {
		return new StringBuffer().append("Traducao").append("_").append(judoca.getNome()).append("_").append(indiceSustentacao).append("_").append(tamanhoMinimoPadrao).append(".CSV").toString();
	}

	private static String buildTraducaoFilename(Judoca judoca, double indiceSustentacao) {
		return new StringBuffer().append("Traducao").append("_").append(judoca.getNome()).append("_").append(indiceSustentacao).append(".CSV").toString();
	}

	private static String buildResultadoFilename(Judoca judoca, double indiceSustentacao, int[] itensObrigatorios) {
		return new StringBuffer().append("Resultado").append("_").append(judoca.getNome()).append("_").append(indiceSustentacao).append("_").append(arrayToString(itensObrigatorios)).append(".TXT").toString();
	}

	private static String buildResultadoFilename(Judoca judoca, double indiceSustentacao, int tamanhoMinimoPadrao) {
		return new StringBuffer().append("Resultado").append("_").append(judoca.getNome()).append("_").append(indiceSustentacao).append("_").append(tamanhoMinimoPadrao).append(".TXT").toString();
	}

	private static String buildResultadoFilename(Judoca judoca, double indiceSustentacao) {
		return new StringBuffer().append("Resultado").append("_").append(judoca.getNome()).append("_").append(indiceSustentacao).append(".TXT").toString();
	}

	private static String buildDatabaseFilename(Judoca judoca) {
		return new StringBuffer().append("Database").append("_").append(judoca.getNome()).append(".TXT").toString();
	}

	private static String arrayToString(int[] itensObrigatorios) {
		if (itensObrigatorios == null || itensObrigatorios.length == 0) {
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		for (int i : itensObrigatorios) {
			buffer.append(i).append(" ");
		}
		return buffer.toString().trim();
	}
}
