package ivolapuma.judodatamining.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver");
		
		String url = "jdbc:postgresql://localhost:5432/mestrado";
	    String user = "ivolapuma";
	    String pass = "";
	    
		Connection connection = (Connection) DriverManager.getConnection(url, user, pass);
		
		PreparedStatement statement = connection.prepareStatement("SELECT count(1) FROM luta");
		ResultSet set = statement.executeQuery();
		while (set.next()) {
			int count = set.getInt(1);
			System.out.println("count LUTAS = "+count);
		}
			
		connection.close();
		System.out.println("Conexão realizada e fechada com sucesso!");
	}
	
}
