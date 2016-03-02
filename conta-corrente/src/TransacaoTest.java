import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TransacaoTest {
	Map<String, Double> contas = new HashMap<>();
	Transacao transacao = new Transacao(contas);

	@Test(expected=RuntimeException.class)
	public void testSacarQuandoContaNaoExiste() {
		transacao.sacar("1", 3.0);
	}
	
	@Test
	public void testSacar(){
		contas.put("2", 1.99);
		transacao.sacar("2", 3.0);
	}
}
