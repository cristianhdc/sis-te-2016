package br.edu.horus.cc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class DataBaseRule2 implements TestRule{
	private static final String URL = "jdbc:hsqldb:file:target/conta-corrent;hsqldb.lock_file=false;create=true";

	@Override
	public Statement apply(Statement base, Description description) {
		Connection conexao = null;
		try {
			String script = new String(Files.readAllBytes(Paths.get("src/main/resources/db/script.sql")));
			conexao = DriverManager.getConnection(URL);
			conexao.setAutoCommit(false);
			conexao.createStatement().executeQuery(script);
			conexao.commit();
			
			String sql = "INSERT INTO contas (numero) VALUES (?)";
			//prepara a inserção
			PreparedStatement statement = conexao.prepareStatement(
				sql, 
				java.sql.Statement.RETURN_GENERATED_KEYS
			);
			//seta os valor para as colunas presente na string sql
			statement.setString(1, "12345-6");
			
			//executa o comando no banco de dados
			statement.executeUpdate();
			
			//retorna os dados gerados automaticamente pelo banco
			ResultSet keys = statement.getGeneratedKeys();
			if(keys.next()){
				System.out.println("id gerado " + keys.getInt(1));
			}
			
			
		} catch (Exception e) {
			if(conexao != null){				
				try {
					conexao.rollback();
				} catch (SQLException e1) {}
			}
			throw new RuntimeException(e);
		}
		return base;
	}

}
