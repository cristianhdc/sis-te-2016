package br.edu.horus.cc;

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
	
	public void transferir(String conta1, String conta2, Double valor){
		//faz o saque na primeira conta
		sacar(conta1, valor);
		try{
			/*
			 * deposita na segunda conta, protegendo contra
			 * uma exceção do tipo ContaNaoExiste
			 */
			depositar(conta2, valor);
		}catch(ContaNaoExisteException e){
			/*
			 * caso a exceção for lançada, deposita o valor sacado
			 * da primeira conta devolta
			 */
			depositar(conta1, valor);
			//promulga a exceção("quem está chamando deve decidir o que fazer")
			throw e;
		}
	}	
}
