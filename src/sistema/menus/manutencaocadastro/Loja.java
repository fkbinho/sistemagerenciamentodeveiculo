package sistema.menus.manutencaocadastro;

import java.util.InputMismatchException;
import java.util.Scanner;

import sistema.services.LojaServices;

public class Loja {
	
	private Scanner scan;

    public Loja () {
        this.scan = new Scanner(System.in);
    }

	public void menuManutencaoLoja() {
		
		int opcao;

		LojaServices lojaServices = new LojaServices();
		
		do {
			System.out.println("\n==== Gerenciar Loja ====");
			System.out.println("1. Cadastrar loja");
			System.out.println("2. Excluir loja");
			System.out.println("3. Alterar loja");
			System.out.println("4. Voltar");
			System.out.print("Escolha uma opção: ");

			try {
				opcao = scan.nextInt();
				scan.nextLine();
				
				switch (opcao) {
				case 1:
					lojaServices.cadastrar(null);
					break;
				case 2:
					lojaServices.excluir();
					break;
				case 3:
					lojaServices.alterar();
					break;
				case 4:
					System.out.println("Voltando ao menu anterior");
					break;
				default:
					System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Opção inválida.\n");
				opcao = 0;
				scan.nextLine();
			}
		} while (opcao != 4);
	}
}
