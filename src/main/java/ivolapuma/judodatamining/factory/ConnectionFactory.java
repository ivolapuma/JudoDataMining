package ivolapuma.judodatamining.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ivolapuma.judodatamining.exception.JudoDataMiningException;

public class ConnectionFactory {

	private static final String URL = "jdbc:postgresql://localhost:5432/mestrado";
	private static final String USER = "ivolapuma";
	private static final String PASS = "";
    
	public static Connection getConnection() throws JudoDataMiningException {
		try {
			Connection connection = (Connection) DriverManager.getConnection(URL, USER, PASS);
			return connection;
		} catch (SQLException e) {
			throw new JudoDataMiningException("Erro ao obter uma Conneciton com a base de dados.", e);
		}
	}

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
