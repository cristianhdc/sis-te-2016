package br.edu.horus.cc;

import java.sql.Connection;

import org.junit.ClassRule;
import org.junit.Test;

public class RepositorioTest {

	@ClassRule
	public static DataBaseRule test = new DataBaseRule();
	Connection conexao = test.conectar();
	Repositorio repositorio = new Repositorio(conexao);

	@Test
	public void testSalvar() {
		repositorio.salvar(new Conta("1234-5"));
	}
	
	@Test
	public void testSalvarMovimento(){
		Conta conta = new Conta("1234-6");
		repositorio.salvar(conta);
		Movimento movimento = new Movimento(conta, 10.0);
		repositorio.salvar(movimento);
	}
	
	@Test
	public void testSelecionarConta(){
		String numero = "1729-24";
		Conta conta = new Conta(numero);
		repositorio.salvar(conta);		
		Conta conta2 = repositorio.selecionar(numero);
	}	
}
