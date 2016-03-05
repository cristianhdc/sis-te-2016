package br.edu.horus.cc;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ContaCorrente {

	public static void main(String[] args) {
		Map<String, Double> contas = new HashMap<>();
		Scanner leitor = new Scanner(System.in);
		Transacao transacao = new Transacao(contas);
		Integer opcao;
		do{
			System.out.println("Digite alguma coisa");
			System.out.println("0 - para sair");
			System.out.println("1 - para criar uma conta");
			System.out.println("2 - para fazer um depósito");
			System.out.println("3 - para saque");
			opcao = leitor.nextInt();
			if(opcao == 1){
				System.out.println("Informa o numero da conta");
				String codigo = leitor.next();
				contas.put(codigo, 0.00);
				System.out.println("Você criou a conta: " + codigo);
			} else if( opcao == 2){
				System.out.println("Informa o numero da conta");
				String conta = leitor.next();
				System.out.println("Informe o valor que deseja depositar");
				Double valor = leitor.nextDouble();
				try{
					Double saldo = transacao.depositar(conta, valor);
					System.out.println("Saldo: " + saldo);					
				}catch(ContaNaoExisteException e){
					System.err.println("Conta não existe");
				}
			} else if( opcao == 3) {
				System.out.println("Informa o numero da conta");
				String conta = leitor.next();
				System.out.println("Informe o valor que deseja sacar");
				Double valor = leitor.nextDouble();
				try{
					Double saldo = transacao.sacar(conta, valor);
					System.out.println("Saldo: " + saldo);					
				}catch(SaldoNegativoException e){
					System.err.println("Saldo insuficiente");
				} catch(ContaNaoExisteException e){
					System.err.println("Conta não existe");
				}
			}
		}while(opcao != 0);
		System.out.println("Saindo do sistema");
		leitor.close();
	}

}
