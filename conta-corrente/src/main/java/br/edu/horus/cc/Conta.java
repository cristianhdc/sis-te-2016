package br.edu.horus.cc;

public class Conta {
	private Integer id;
	private String numero;
	
	public Conta(String numero){
		this.numero = numero;
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
