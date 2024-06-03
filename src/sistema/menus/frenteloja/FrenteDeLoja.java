package sistema.menus.frenteloja;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FrenteDeLoja {

	private Scanner scan;

	public FrenteDeLoja() {
		this.scan = new Scanner(System.in);
	}

	public void menuFrenteDeLoja() {

		int opcao;

		do {
			System.out.println("\n|---------- MENU - Frente de Loja ----------|");
			System.out.println("|            1. Gerenciar Veículo           |");
			System.out.println("|            2. Gerenciar Loja              |");
			System.out.println("|            3. Gerenciar Vendedor          |");
			System.out.println("|            4. Gerenciar Cliente           |");
			System.out.println("|            5. Gerenciar Venda             |");
			System.out.println("|            6. Voltar                      |");
			System.out.println("|-------------------------------------------|");
			System.out.print("Escolha uma opção: ");

			try {
				opcao = scan.nextInt();
				scan.nextLine();

				switch (opcao) {
				case 1:
					new Veiculo().menuFrenteVeiculo();
					break;
				case 2:
					new Loja().menuFrenteLoja();
					break;
				case 3:
					new Vendedor().menuFrenteVendedor();
					break;
				case 4:
					new Cliente().menuGerenciarCliente();
					break;
				case 5:
					new Venda().menuFrenteVenda();
					break;
				case 6:
					System.out.println("Voltando ao menu principal...\n");
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
		} while (opcao != 6);
	}

}
