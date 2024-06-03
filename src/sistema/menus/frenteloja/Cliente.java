package sistema.menus.frenteloja;

import java.util.InputMismatchException;
import java.util.Scanner;

import sistema.services.ClienteServices;

public class Cliente {

	private Scanner scan;

	public Cliente() {
		this.scan = new Scanner(System.in);
	}

	public void menuGerenciarCliente() {

		int opcao;

		ClienteServices clienteServices = new ClienteServices();
		
		do {
			System.out.println("\n┌─── Frente de Loja - Gerenciar Cliente ───┐");
			System.out.println("|            1. Cadastrar Cliente          |");
			System.out.println("|            2. Consultar por CPF          |");
			System.out.println("|            3. Listar clientes            |");
			System.out.println("|            4. Voltar                     |");
			System.out.println("└──────────────────────────────────────────┘");
			System.out.print("Escolha uma opção: ");

			try {
				opcao = scan.nextInt();
				scan.nextLine();

				switch (opcao) {
				case 1:
					clienteServices.cadastrar(null);
					break;
				case 2:
					clienteServices.consultar();
					break;
				case 3:
					clienteServices.listarClientes();
					break;
				case 4:
					System.out.println("Voltando ao menu anterior.");
					break;
				default:
					System.out.println("Opção inválida. Por favor, escolha novamente.");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Opção inválida.");
				opcao = 0;
				scan.nextLine();
			}
		} while (opcao != 4);
	}
}
