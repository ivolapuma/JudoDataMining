package ivolapuma.judodatamining.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import ivolapuma.judodatamining.exception.JudoDataMiningException;

public class MPSItemRepository {

	private Connection connection;

	public MPSItemRepository(Connection connection) {
		super();
		if (connection == null) {
			throw new IllegalArgumentException("Parametro Connection esta nulo.");
		}
		this.connection = connection;
	}

	public Map<String, String> buildMapTraducao() throws JudoDataMiningException {
		Map<String, String> map = new HashMap<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("i.id AS id, ");
		sql.append("i.nome AS nome ");
		sql.append("FROM mps_item i ");
		sql.append("ORDER BY i.id ");
		try {
			PreparedStatement statement = this.connection.prepareStatement(sql.toString());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String nome = rs.getString("nome");
				map.put(id, nome);
			}
		} catch (SQLException e) {
			throw new JudoDataMiningException("Erro ao buscar os Itens MPS na base de dados.", e);
		}
		return map;
	}

}
