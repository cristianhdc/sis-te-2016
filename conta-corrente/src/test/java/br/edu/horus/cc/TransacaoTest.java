package br.edu.horus.cc;


import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

public class TransacaoTest {
	
	@ClassRule
	public static Conexao test = new DataBaseRule();
	
	Map<String, Double> contas = new HashMap<>();
	Repositorio repositorio = new Repositorio(test.conectar());
	Transacao transacao = new Transacao(repositorio);
	
	@Before
	public void iniciar(){
		contas.clear();
	}

	@Test(expected=RuntimeException.class)
	public void testSacarQuandoContaNaoExiste() {
		transacao.sacar("1", 3.0);
	}
	
	@Test
	public void testSacar(){
		Conta conta = new Conta("2");
		conta.depositar(5.99);
		repositorio.salvar(conta);

		Double atual =  transacao.sacar("2", 3.0);
		
		Double esperado = 2.99;
		Assert.assertEquals(esperado, atual);
	}
	
	@Test
	public void testDepositar(){
		Conta conta = new Conta("12");
		conta.depositar(1.99);
		repositorio.salvar(conta);
		
		Double atual = transacao.depositar("12", 3.0);
		Double esperado = 4.99;
		Assert.assertEquals(esperado, atual);
	}
	
	@Test(expected = SaldoNegativoException.class)
	public void testNaoDeixaSaldoNegativo(){
		Conta conta = new Conta("007");
		conta.setSaldo(9.01);
		repositorio.salvar(conta);
		transacao.sacar("007", 9.02);
	}
	
	/*
	 * Não deve sacar da primeira conta, quando ela não tiver saldo
	 */
	@Test(expected = SaldoNegativoException.class)
	public void testNaoDeveTransaferirQuandoSemSaldo(){
		Conta conta1 = new Conta("99");
		conta1.setSaldo(9.01);
		repositorio.salvar(conta1);
		Conta conta2 = new Conta("910");
		conta2.setSaldo(9.01);
		repositorio.salvar(conta2);
		try{
			transacao.transferir("99", "910", 9.02);			
		}catch(SaldoNegativoException e){			
			assertEquals(Double.valueOf(9.01), repositorio.selecionar("99").getSaldo());
			assertEquals(Double.valueOf(9.01), repositorio.selecionar("910").getSaldo());
			throw e;
		}
	}
	
	/*
	 * Não deve transferir quando a primeira conta não existir
	 */
	@Test(expected = ContaNaoExisteException.class)
	public void testNaoDeveTransaferirQuandoPrimeiraContaNaoExiste(){
		Conta conta = new Conta("26");
		conta.setSaldo(9.01);
		repositorio.salvar(conta);
		try{
			transacao.transferir("100", "26", 9.02);			
		}catch(ContaNaoExisteException e){			
			assertEquals(Double.valueOf(9.01), repositorio.selecionar("26").getSaldo());
			throw e;
		}
	}
	
	/*
	 * Não deve transferir quando a segunda conta não existir
	 */
	@Test(expected = ContaNaoExisteException.class)
	public void testNaoDeveTransferirQuandoSegundaContaNaoExiste(){
		Conta conta = new Conta("123456");
		conta.setSaldo(9.01);
		repositorio.salvar(conta);
		try{
			transacao.transferir("123456", "X", 9.01);			
		}catch(ContaNaoExisteException e){			
			assertEquals(Double.valueOf(9.01), repositorio.selecionar("123456").getSaldo());
			throw e;
		}
	}
}



