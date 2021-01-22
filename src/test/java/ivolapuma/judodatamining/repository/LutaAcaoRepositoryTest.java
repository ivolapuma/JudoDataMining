package ivolapuma.judodatamining.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import ivolapuma.judodatamining.dominio.LutaAcao;
import ivolapuma.judodatamining.exception.JudoDataMiningException;
import ivolapuma.judodatamining.factory.ConnectionFactory;

public class LutaAcaoRepositoryTest {

	@Test
	public void buscarPorJudoca___judoca___retornaListaLutaAcaoVO() throws JudoDataMiningException, SQLException {
		Connection connection = ConnectionFactory.getConnection();
		LutaAcaoRepository repository = new LutaAcaoRepository(connection);
		long judoca = 3;
		List<LutaAcao> list = repository.buscarPorJudoca(judoca);
		connection.close();
		assertNotNull(list);
		assertTrue(list.size() > 0);
		System.out.println(String.format("list.size=%s", list.size()));
	}
	
}
