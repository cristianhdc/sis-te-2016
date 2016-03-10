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
			System.out.println("4 - para transferencia");
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
			} else if (opcao == 4){
				System.out.println("Informa o numero da conta para sacar");
				String conta1 = leitor.next();
				System.out.println("Informa o numero da conta para depositar");
				String conta2 = leitor.next();
				System.out.println("Informe o valor que deseja tranferir");
				Double valor = leitor.nextDouble();
				try{					
					transacao.transferir(conta1, conta2, valor);
				}catch(SaldoNegativoException e){
					System.err.println("Saldo insuficiente para sacar");
				} catch(ContaNaoExisteException e){
					System.err.println("Uma das Contas não existe");
				}
			}
		}while(opcao != 0);
		System.out.println("Saindo do sistema");
		leitor.close();
	}

}
