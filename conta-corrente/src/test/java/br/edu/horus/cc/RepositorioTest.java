package br.edu.horus.cc;

import java.sql.Connection;

import org.junit.ClassRule;
import org.junit.Test;

public class RepositorioTest {

	@ClassRule
	public static DataBaseRule test = new DataBaseRule();

	@Test
	public void testSalvar() {
		Connection conexao = test.conectar();
		Repositorio repositorio = new Repositorio(conexao);
		repositorio.salvar(new Conta("1234-5"));
	}

}
