package sistema.menus.frenteloja;

import java.util.InputMismatchException;
import java.util.Scanner;

import sistema.services.VendedorServices;

public class Vendedor {

	private Scanner scan;

	public Vendedor() {
		this.scan = new Scanner(System.in);
	}

	public void menuFrenteVendedor() {

		int opcao;

		VendedorServices vendedorServices = new VendedorServices();
		
		do {
			System.out.println("\n┌─── Frente de Loja - Gerenciar Vendedor ───┐");
			System.out.println("|        1. Consultar vendedor por nome     |");
			System.out.println("|        2. Listar vendedores               |");
			System.out.println("|        3. Voltar                          |");
			System.out.println("└───────────────────────────────────────────┘");
			System.out.print("Escolha uma opção: ");

			try {
				opcao = scan.nextInt();
				scan.nextLine();

				switch (opcao) {
				case 1:
					vendedorServices.consultar();
					break;
				case 2:
					vendedorServices.listarVendedores();
					break;
				case 3:
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
		} while (opcao != 3);
	}
}
