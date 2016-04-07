package br.edu.horus.cc;

import java.util.Map;

public class Transacao {
	Map<String, Double> contas;
	
	private Repositorio repositorio;
	
	public Transacao(Repositorio repositorio){
		this.repositorio = repositorio;
	}
	
	public Transacao(Map<String, Double> contas){
		this.contas = contas;
	}
	
	public Double sacar(String numero, Double valor){
		Conta conta = repositorio.selecionar(numero);
		Double saldo = conta.sacar(valor);
		Movimento movimento = new Movimento(conta, valor * -1);
		repositorio.salvar(movimento);
		return saldo;
	}
	
	
	public Double depositar(String numero, Double valor){
		Conta conta = repositorio.selecionar(numero);
		Double saldo = conta.depositar(valor);
		Movimento movimento = new Movimento(conta, valor);
		repositorio.salvar(movimento);
		return saldo;
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
