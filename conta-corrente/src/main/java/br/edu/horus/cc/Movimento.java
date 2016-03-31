package br.edu.horus.cc;

public class Movimento {
	private Integer id;
	private Double valor;
	private Conta conta;
	
	public Movimento(Conta conta, Double valor){
		this.conta = conta;
		this.valor = valor;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public Conta getConta() {
		return conta;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
}
