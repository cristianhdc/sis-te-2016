package br.edu.horus.cc;

public class Conta {
	private Integer id;
	private String numero;
	private Double saldo = 0.00;
	
	public Conta(String numero){
		this.numero = numero;
	}
	
	public Double sacar(Double valor){
		if(valor > saldo){
			throw new SaldoNegativoException();
		}		
		saldo = saldo - valor;
		return saldo;
	}
	
	public Double depositar(Double valor){
		this.saldo = this.saldo + valor;
		return this.saldo;
	}
	
	public Double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
}
