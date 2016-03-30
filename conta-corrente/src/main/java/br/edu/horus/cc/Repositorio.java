package br.edu.horus.cc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Repositorio {
	private Connection conexao;
	
	public Repositorio(Connection conexao) {
		this.conexao = conexao;
	}
	
	public void salvar(Conta conta){
		String sql = "INSERT INTO contas (numero) VALUES (?)";
		try{
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, conta.getNumero());
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
