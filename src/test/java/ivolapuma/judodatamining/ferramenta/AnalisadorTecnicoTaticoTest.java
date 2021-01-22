package ivolapuma.judodatamining.ferramenta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import ivolapuma.judodatamining.dominio.AcaoTipoQuebra;
import ivolapuma.judodatamining.dominio.Judoca;
import ivolapuma.judodatamining.dominio.LutaAcao;
import ivolapuma.judodatamining.exception.JudoDataMiningException;
import ivolapuma.judodatamining.factory.AcaoTipoQuebraFactory;
import ivolapuma.judodatamining.factory.ConnectionFactory;
import ivolapuma.judodatamining.repository.JudocaRepository;
import ivolapuma.judodatamining.repository.LutaAcaoRepository;
import ivolapuma.judodatamining.repository.MPSItemRepository;

public class AnalisadorTecnicoTaticoTest {

	private static final int ID_ACAO_TIPO_COMANDO_TEMPO = 6;
	private static final int ID_JUDOCA_BISCHOF = 3;
	private static final int IPPON = 2722;

	@Test
	public void executar___databaseEResultadoETraducaoFiles_indiceSustentacaoETamanhoMinimoPadraoEItensObrigatorios___realizaAnaliseEGravaArquivos() throws JudoDataMiningException, SQLException {
		Connection connection = ConnectionFactory.getConnection();
		Judoca judoca = new JudocaRepository(connection).buscarJudoca(ID_JUDOCA_BISCHOF); 
		List<LutaAcao> lutaAcoes = new LutaAcaoRepository(connection).buscarPorJudoca(judoca.getId());
		AcaoTipoQuebra acaoTipoQuebra = AcaoTipoQuebraFactory.create(ID_ACAO_TIPO_COMANDO_TEMPO);
		Map<String, String> mapTraducao = new MPSItemRepository(connection).buildMapTraducao();
		connection.close();
		File databaseFile = new File("C:/Users/ivolapuma/Documents/IPT/MESTRADO/TEMP/sequenceDatabase_Simulacao_BISCHOF.txt");
		File resultadoFile = new File("C:/Users/ivolapuma/Documents/IPT/MESTRADO/TEMP/Resultado_Simulacao_BISCHOF.txt");
		File traducaoFile = new File("C:/Users/ivolapuma/Documents/IPT/MESTRADO/TEMP/Resultado_Simulacao_BISCHOF_traducao.csv");
		double indiceSustentacao = 0.2;
		Integer tamanhoMinimoPadrao = 4;
		int[] itensObrigatorios = {IPPON};
		AnalisadorTecnicoTatico analisador = new AnalisadorTecnicoTatico(judoca, lutaAcoes, acaoTipoQuebra, mapTraducao);
		analisador.executar(databaseFile, resultadoFile, traducaoFile, indiceSustentacao, tamanhoMinimoPadrao, itensObrigatorios);
		assertTrue("databaseFile", databaseFile.exists());
		assertTrue("resultadoFile", resultadoFile.exists());
		assertTrue("traducaoFile", traducaoFile.exists());
		assertTrue("numeroPadroes >= 0", analisador.getNumeroPadroes() >= 0);
		assertTrue("tempoExecucaoAlgoritmo >= 0.0", analisador.getTempoExecucaoAlgoritmo() >= 0.0);
		assertTrue("tempoExecucaoTraducao >= 0.0", analisador.getTempoExecucaoTraducao() >= 0.0);
		assertEquals("indiceSustentacao", Double.valueOf(indiceSustentacao), analisador.getIndiceSustentacao());
		assertEquals("tamanhoMinimoPadrao", tamanhoMinimoPadrao, analisador.getTamanhoMinimoPadrao());
		assertEquals("itensObrigatorios", String.valueOf(IPPON), arrayToString(analisador.getItensObrigatorios()));
		assertNotNull("databaseFile", analisador.getDatabaseFile());
		assertNotNull("resultadoFile", analisador.getResultadoFile());
		assertNotNull("traducaoFile", analisador.getTraducaoFile());
	}

	@Test
	public void executar___databaseEResultadoETraducaoFiles_indiceSustentacaoEtamanhoMinimoPadrao___realizaAnaliseEGravaArquivos() throws JudoDataMiningException, SQLException {
		Connection connection = ConnectionFactory.getConnection();
		Judoca judoca = new JudocaRepository(connection).buscarJudoca(ID_JUDOCA_BISCHOF); 
		List<LutaAcao> lutaAcoes = new LutaAcaoRepository(connection).buscarPorJudoca(judoca.getId());
		AcaoTipoQuebra acaoTipoQuebra = AcaoTipoQuebraFactory.create(ID_ACAO_TIPO_COMANDO_TEMPO); 
		Map<String, String> mapTraducao = new MPSItemRepository(connection).buildMapTraducao();
		connection.close();
		File databaseFile = new File("C:/Users/ivolapuma/Documents/IPT/MESTRADO/TEMP/sequenceDatabase_Simulacao_BISCHOF.txt");
		File resultadoFile = new File("C:/Users/ivolapuma/Documents/IPT/MESTRADO/TEMP/Resultado_Simulacao_BISCHOF.txt");
		File traducaoFile = new File("C:/Users/ivolapuma/Documents/IPT/MESTRADO/TEMP/Resultado_Simulacao_BISCHOF_traducao.csv");
		double indiceSustentacao = 0.8;
		Integer tamanhoMinimoPadrao = 4;
		AnalisadorTecnicoTatico analisador = new AnalisadorTecnicoTatico(judoca, lutaAcoes, acaoTipoQuebra, mapTraducao);
		analisador.executar(databaseFile, resultadoFile, traducaoFile, indiceSustentacao, tamanhoMinimoPadrao);
		assertTrue("databaseFile", databaseFile.exists());
		assertTrue("resultadoFile", resultadoFile.exists());
		assertTrue("traducaoFile", traducaoFile.exists());
		System.out.println("numeroPadroes="+analisador.getNumeroPadroes()+" algoritmo="+analisador.getTempoExecucaoAlgoritmo()+" traducao="+analisador.getTempoExecucaoTraducao());
		assertTrue("numeroPadroes >= 0", analisador.getNumeroPadroes() >= 0);
		assertTrue("tempoExecucaoAlgoritmo >= 0.0", analisador.getTempoExecucaoAlgoritmo() >= 0.0);
		assertTrue("tempoExecucaoTraducao >= 0.0", analisador.getTempoExecucaoTraducao() >= 0.0);
		assertEquals("indiceSustentacao", Double.valueOf(indiceSustentacao), analisador.getIndiceSustentacao());
		assertEquals("tamanhoMinimoPadrao", tamanhoMinimoPadrao, analisador.getTamanhoMinimoPadrao());
		assertNull("itensObrigatorios", analisador.getItensObrigatorios());
		assertNotNull("databaseFile", analisador.getDatabaseFile());
		assertNotNull("resultadoFile", analisador.getResultadoFile());
		assertNotNull("traducaoFile", analisador.getTraducaoFile());
	}

	@Test
	public void executar___databaseEResultadoETraducaoFiles_indiceSustentacaoEItensObrigatorios___realizaAnaliseEGravaArquivos() throws JudoDataMiningException, SQLException {
		Connection connection = ConnectionFactory.getConnection();
		Judoca judoca = new JudocaRepository(connection).buscarJudoca(ID_JUDOCA_BISCHOF); 
		List<LutaAcao> lutaAcoes = new LutaAcaoRepository(connection).buscarPorJudoca(judoca.getId());
		AcaoTipoQuebra acaoTipoQuebra = AcaoTipoQuebraFactory.create(ID_ACAO_TIPO_COMANDO_TEMPO); 
		Map<String, String> mapTraducao = new MPSItemRepository(connection).buildMapTraducao();
		connection.close();
		File databaseFile = new File("C:/Users/ivolapuma/Documents/IPT/MESTRADO/TEMP/sequenceDatabase_Simulacao_BISCHOF.txt");
		File resultadoFile = new File("C:/Users/ivolapuma/Documents/IPT/MESTRADO/TEMP/Resultado_Simulacao_BISCHOF.txt");
		File traducaoFile = new File("C:/Users/ivolapuma/Documents/IPT/MESTRADO/TEMP/Resultado_Simulacao_BISCHOF_traducao.csv");
		double indiceSustentacao = 0.2;
		int[] itensObrigatorios = {IPPON};
		AnalisadorTecnicoTatico analisador = new AnalisadorTecnicoTatico(judoca, lutaAcoes, acaoTipoQuebra, mapTraducao);
		analisador.executar(databaseFile, resultadoFile, traducaoFile, indiceSustentacao, itensObrigatorios);
		assertTrue("databaseFile", databaseFile.exists());
		assertTrue("resultadoFile", resultadoFile.exists());
		assertTrue("traducaoFile", traducaoFile.exists());
		System.out.println("numeroPadroes="+analisador.getNumeroPadroes()+" algoritmo="+analisador.getTempoExecucaoAlgoritmo()+" traducao="+analisador.getTempoExecucaoTraducao());
		assertTrue("numeroPadroes >= 0", analisador.getNumeroPadroes() >= 0);
		assertTrue("tempoExecucaoAlgoritmo >= 0.0", analisador.getTempoExecucaoAlgoritmo() >= 0.0);
		assertTrue("tempoExecucaoTraducao >= 0.0", analisador.getTempoExecucaoTraducao() >= 0.0);
		assertEquals("indiceSustentacao", Double.valueOf(indiceSustentacao), analisador.getIndiceSustentacao());
		assertEquals("itensObrigatorios", String.valueOf(IPPON), arrayToString(analisador.getItensObrigatorios()));
		assertNull("tamanhoMinimoPadrao", analisador.getTamanhoMinimoPadrao());
		assertNotNull("databaseFile", analisador.getDatabaseFile());
		assertNotNull("resultadoFile", analisador.getResultadoFile());
		assertNotNull("traducaoFile", analisador.getTraducaoFile());
	}

	@Test
	public void executar___databaseEResultadoETraducaoFiles_indiceSustentacao___realizaAnaliseEGravaArquivos() throws JudoDataMiningException, SQLException {
		Connection connection = ConnectionFactory.getConnection();
		Judoca judoca = new JudocaRepository(connection).buscarJudoca(ID_JUDOCA_BISCHOF); 
		List<LutaAcao> lutaAcoes = new LutaAcaoRepository(connection).buscarPorJudoca(judoca.getId());
		AcaoTipoQuebra acaoTipoQuebra = AcaoTipoQuebraFactory.create(ID_ACAO_TIPO_COMANDO_TEMPO); 
		Map<String, String> mapTraducao = new MPSItemRepository(connection).buildMapTraducao();
		connection.close();
		File databaseFile = new File("C:/Users/ivolapuma/Documents/IPT/MESTRADO/TEMP/sequenceDatabase_Simulacao_BISCHOF.txt");
		File resultadoFile = new File("C:/Users/ivolapuma/Documents/IPT/MESTRADO/TEMP/Resultado_Simulacao_BISCHOF.txt");
		File traducaoFile = new File("C:/Users/ivolapuma/Documents/IPT/MESTRADO/TEMP/Resultado_Simulacao_BISCHOF_traducao.csv");
		double indiceSustentacao = 0.8;
		AnalisadorTecnicoTatico analisador = new AnalisadorTecnicoTatico(judoca, lutaAcoes, acaoTipoQuebra, mapTraducao);
		analisador.executar(databaseFile, resultadoFile, traducaoFile, indiceSustentacao);
		assertTrue("databaseFile", databaseFile.exists());
		assertTrue("resultadoFile", resultadoFile.exists());
		assertTrue("traducaoFile", traducaoFile.exists());
		System.out.println("numeroPadroes="+analisador.getNumeroPadroes()+" algoritmo="+analisador.getTempoExecucaoAlgoritmo()+" traducao="+analisador.getTempoExecucaoTraducao());
		assertTrue("numeroPadroes >= 0", analisador.getNumeroPadroes() >= 0);
		assertTrue("tempoExecucaoAlgoritmo >= 0.0", analisador.getTempoExecucaoAlgoritmo() >= 0.0);
		assertTrue("tempoExecucaoTraducao >= 0.0", analisador.getTempoExecucaoTraducao() >= 0.0);
		assertEquals("indiceSustentacao", Double.valueOf(indiceSustentacao), analisador.getIndiceSustentacao());
		assertNull("tamanhoMinimoPadrao", analisador.getTamanhoMinimoPadrao());
		assertNull("itensObrigatorios", analisador.getItensObrigatorios());
		assertNotNull("databaseFile", analisador.getDatabaseFile());
		assertNotNull("resultadoFile", analisador.getResultadoFile());
		assertNotNull("traducaoFile", analisador.getTraducaoFile());
	}

	private String arrayToString(int[] itensObrigatorios) {
		StringBuffer buffer = new StringBuffer();
		for (int i : itensObrigatorios) {
			buffer.append(i).append(" ");
		}
		return buffer.toString().trim();
	}

}
