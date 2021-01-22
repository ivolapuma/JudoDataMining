package ivolapuma.judodatamining.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ivolapuma.judodatamining.dominio.Judoca;
import ivolapuma.judodatamining.exception.JudoDataMiningException;

public class JudocaRepository {

	private Connection connection;
	
	public JudocaRepository(Connection connection) {
		super();
		if (connection == null) {
			throw new IllegalArgumentException("Parametro Connection esta nulo.");
		}
		this.connection = connection;
	}

	public Judoca buscarJudoca(long id) throws JudoDataMiningException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT nome ");
		sql.append("FROM judoca ");
		sql.append("WHERE id = ? ");
		try {
			PreparedStatement statement = this.connection.prepareStatement(sql.toString());
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String nome = rs.getString("nome");
				return new Judoca(id, nome);
			}
			return null;
		} catch (SQLException e) {
			throw new JudoDataMiningException("Erro ao buscar dados do Judoca na base de dados", e);
		}
	}
	
}
