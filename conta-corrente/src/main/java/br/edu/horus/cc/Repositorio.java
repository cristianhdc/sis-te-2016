package br.edu.horus.cc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Repositorio {
	private Connection conexao;
	
	public Repositorio(Connection conexao) {
		this.conexao = conexao;
	}
	
	public void salvar(Conta conta){
		String sql = "INSERT INTO contas (numero) VALUES (?)";
		try{
			PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, conta.getNumero());
			statement.executeUpdate();
			
			//recupera o id de conta gerado pelo banco
			ResultSet result = statement.getGeneratedKeys();
			if(result.next()){
				conta.setId(result.getInt(1));
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public void salvar(Movimento movimento) {
		String sql = "INSERT INTO movimentos (valor, conta_id) VALUES (?, ?)";
		try{
			PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setDouble(1, movimento.getValor());
			statement.setInt(2, movimento.getConta().getId());
			statement.executeUpdate();
			
			//recupera o id de conta gerado pelo banco
			ResultSet result = statement.getGeneratedKeys();
			if(result.next()){
				movimento.setId(result.getInt(1));
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public Conta selecionar(String numero) {
		String sql = "SELECT * FROM contas WHERE numero = ?";
		try{
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, numero);
			ResultSet result = statement.executeQuery();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

}







