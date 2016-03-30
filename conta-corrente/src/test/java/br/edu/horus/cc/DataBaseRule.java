package br.edu.horus.cc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class DataBaseRule implements TestRule {

	@Override
	public Statement apply(Statement base, Description description){
		String sql = ler();
		Connection conexao = conectar();
		
		try {
			conexao.createStatement().executeQuery(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return base;
	}
	
	public Connection conectar(){
		String url = "jdbc:hsqldb:file:target/conta-corrent";
		try {
			return DriverManager.getConnection(url);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private String ler() {
		Path caminho = Paths.get("src/main/resources/db/script.sql"); 
		try {
			byte[] bytes = Files.readAllBytes(caminho);
			String sql = new String(bytes);
			return sql;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
