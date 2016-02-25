import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ContaCorrente {

	public static void main(String[] args) {
		Map<String, Double> contas = new HashMap<>();
		Scanner leitor = new Scanner(System.in);
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
				String codigo = leitor.next();
				System.out.println("Informe o valor que deseja depositar");
				Double valor = leitor.nextDouble();
				Double saldo = contas.get(codigo);
				contas.put(codigo, saldo + valor);
				System.err.println("Saldo: " + contas.get(codigo));
			} else if( opcao == 3) {
				System.out.println("Informa o numero da conta");
				String codigo = leitor.next();
				System.out.println("Informe o valor que deseja sacar");
				Double valor = leitor.nextDouble();
				Double saldo = contas.get(codigo);
				if(saldo < valor){
					System.err.println("Valor de saque insuficiente");
					continue;
				}
				contas.put(codigo, saldo - valor);
				System.err.println("Saldo: " + contas.get(codigo));		
			}
		}while(opcao != 0);
		System.out.println("Saindo do sistema");
		leitor.close();
	}

}
