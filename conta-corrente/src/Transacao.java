import java.util.Map;

public class Transacao {
	Map<String, Double> contas;
	
	public Transacao(Map<String, Double> contas){
		this.contas = contas;
	}
	
	public Double sacar(String conta, Double valor){
		if(contas.get(conta) == null){
			throw new RuntimeException("Conta não existe");
		}
		Double saldo = contas.get(conta);
//		if(saldo < valor){
//			System.err.println("Valor de saque insuficiente");
//			continue;
//		}
		contas.put(conta, saldo - valor);
		return contas.get(conta);
	}
	
}
