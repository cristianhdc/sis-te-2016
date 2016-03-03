import java.util.Map;

public class Transacao {
	Map<String, Double> contas;
	
	public Transacao(Map<String, Double> contas){
		this.contas = contas;
	}
	
	public Double sacar(String conta, Double valor){
		if(contas.get(conta) == null){
			throw new ContaNaoExisteException();
		}
		Double saldo = contas.get(conta);
		if(saldo < valor){
			throw new SaldoNegativoException();
		}
		contas.put(conta, saldo - valor);
		return contas.get(conta);
	}
	
	
	public Double depositar(String conta, Double valor){
		if(contas.get(conta) == null){
			throw new ContaNaoExisteException();
		}
		Double saldo = contas.get(conta);
		contas.put(conta, saldo + valor);
		return contas.get(conta);
	}
	
}
