package ivolapuma.judodatamining.simulacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ivolapuma.judodatamining.dominio.LutaAcao;
import ivolapuma.judodatamining.exception.JudoDataMiningException;
import ivolapuma.judodatamining.factory.ConnectionFactory;
import ivolapuma.judodatamining.mineracaopadraosequencial.algoritmo.GSP;
import ivolapuma.judodatamining.repository.LutaAcaoRepository;
import ivolapuma.judodatamining.sequencedatabase.SequenceDatabase;
import ivolapuma.judodatamining.sequencedatabase.arquivo.SequenceDatabaseArquivo;
import ivolapuma.judodatamining.sequencedatabase.builder.LutaSequenceDatabaseBuilder;

public class SimulacaoAlgoritmoGSP {

	public static void main(String[] args) throws JudoDataMiningException, FileNotFoundException, IOException, SQLException {
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
		System.out.println("*** INICIANDO SIMULACAO ***");
		double inicio = Calendar.getInstance().getTimeInMillis();
		new GSP().executar(databaseFile, outcomeFile, 0.9);
//		new GSP().executar(databaseFile, outcomeFile, 0.8);
//		new GSP().executar(databaseFile, outcomeFile, 0.7);
//		new GSP().executar(databaseFile, outcomeFile, 0.6);
//		new GSP().executar(databaseFile, outcomeFile, 0.5);
//		new GSP().executar(databaseFile, outcomeFile, 0.4); // nao finalizado
		double fim = Calendar.getInstance().getTimeInMillis();
		System.out.println("*** SIMULACAO CONCLUIDA ***");
		System.out.println("TEMPO EXECUCAO: "+((fim-inicio)/1000.0)+" (s)");
	}

	@SuppressWarnings("unused")
	private static void sysoutSequenceDatabase(File file) throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader reader = new BufferedReader(isr);
		while (true) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}
			System.out.println(line);
		}
		reader.close();
	}
	
}
