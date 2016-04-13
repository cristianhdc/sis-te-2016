package br.edu.horus.cc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	public Conexao() {
		super();
	}

	public void criarBanco() {
		String sql = ler();
		Connection conexao = conectar();
		
		try {
			conexao.createStatement().executeQuery(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Connection conectar() {
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