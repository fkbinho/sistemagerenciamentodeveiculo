package sistema.menus.manutencaocadastro;

import java.util.InputMismatchException;
import java.util.Scanner;

import sistema.services.ClienteServices;

public class Cliente {
	
	private Scanner scan;

    public Cliente() {
        this.scan = new Scanner(System.in);
    }

	public void menuManutencaoCliente() {
		
		int opcao;

		ClienteServices clienteServices = new ClienteServices();
		
		do {
			System.out.println("\n==== Gerenciar Cliente ====");
			System.out.println("1. Cadastrar");
			System.out.println("2. Excluir");
			System.out.println("3. Alterar");
			System.out.println("4. Voltar");
			System.out.print("Escolha uma opção: ");

			try {
				opcao = scan.nextInt();
				scan.nextLine();
				
				switch (opcao) {
				case 1:
					clienteServices.cadastrar(null);
					break;
				case 2:
					clienteServices.excluir();
					break;
				case 3:
					clienteServices.alterar();
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
