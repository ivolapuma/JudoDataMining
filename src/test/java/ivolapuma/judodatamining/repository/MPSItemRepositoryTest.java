package ivolapuma.judodatamining.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.junit.Test;

import ivolapuma.judodatamining.exception.JudoDataMiningException;
import ivolapuma.judodatamining.factory.ConnectionFactory;

public class MPSItemRepositoryTest {

	@Test
	public void buildMapTraducao___retornaMapTraducao() throws JudoDataMiningException, SQLException  {
		Connection connection = ConnectionFactory.getConnection();
		MPSItemRepository repository = new MPSItemRepository(connection);
		Map<String, String> map = repository.buildMapTraducao();
		connection.close();
		assertNotNull(map);
		assertTrue(map.size() > 0);
		System.out.println(String.format("map.size=%s", map.size()));
	}

}
