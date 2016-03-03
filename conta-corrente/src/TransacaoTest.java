
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
}



