package sistema.menus.manutencaocadastro;

import java.util.InputMismatchException;
import java.util.Scanner;

import sistema.services.VendedorServices;

public class Vendedor {

	private Scanner scan;

	public Vendedor() {
		this.scan = new Scanner(System.in);
	}

	public void menuGerenciarVendedorCadastro() {

		int opcao;
		
		VendedorServices vendedorServices = new VendedorServices();

		do {
			System.out.println("\n==== Gerenciar Vendedor ====");
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
					vendedorServices.cadastrar(null);
					break;
				case 2:
					vendedorServices.excluir();
					break;
				case 3:
					vendedorServices.alterar();
					break;
				case 4:
					System.out.println("Voltando ao menu anterior\n");
					break;
				default:
					System.out.println("Opção inválida. Por favor, escolha uma opção válida.\n");
				}
			} catch (InputMismatchException e) {
				System.out.println("Opção inválida.\n");
				opcao = 0;
				scan.nextLine();
			}
		} while (opcao != 4);
	}
}
