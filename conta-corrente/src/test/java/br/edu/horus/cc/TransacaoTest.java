package br.edu.horus.cc;


import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TransacaoTest {
	Map<String, Double> contas = new HashMap<>();
	Transacao transacao = new Transacao(contas);
	
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
		contas.put("2", 5.99);
		Double atual =  transacao.sacar("2", 3.0);
		
		Double esperado = 2.99;
		Assert.assertEquals(esperado, atual);
	}
	
	@Test
	public void testDepositar(){
		contas.put("2", 1.99);
		Double atual = transacao.depositar("2", 3.0);
		Double esperado = 4.99;
		Assert.assertEquals(esperado, atual);
	}
	
	@Test(expected = SaldoNegativoException.class)
	public void testNaoDeixaSaldoNegativo(){
		contas.put("2", 9.01);
		transacao.sacar("2", 9.02);
	}
	
	/*
	 * Não deve sacar da primeira conta, quando ela não tiver saldo
	 */
	@Test(expected = SaldoNegativoException.class)
	public void testNaoDeveTransaferirQuandoSemSaldo(){
		contas.put("1", 9.01);
		contas.put("2", 9.01);
		try{
			transacao.transferir("1", "2", 9.02);			
		}catch(SaldoNegativoException e){			
			assertEquals(Double.valueOf(9.01), contas.get("1"));
			assertEquals(Double.valueOf(9.01), contas.get("2"));
			throw e;
		}
	}
	
	/*
	 * Não deve transferir quando a primeira conta não existir
	 */
	@Test(expected = ContaNaoExisteException.class)
	public void testNaoDeveTransaferirQuandoPrimeiraContaNaoExiste(){
		contas.put("2", 9.01);
		try{
			transacao.transferir("1", "2", 9.02);			
		}catch(ContaNaoExisteException e){			
			assertEquals(Double.valueOf(9.01), contas.get("2"));
			throw e;
		}
	}
	
	/*
	 * Não deve transferir quando a segunda conta não existir
	 */
	@Test(expected = ContaNaoExisteException.class)
	public void testNaoDeveTransaferirQuandoSegundaContaNaoExiste(){
		contas.put("1", 9.01);
		try{
			transacao.transferir("1", "2", 9.01);			
		}catch(ContaNaoExisteException e){			
			assertEquals(Double.valueOf(9.01), contas.get("1"));
			throw e;
		}
	}
}



