package sistema.menus.frenteloja;

import java.util.InputMismatchException;
import java.util.Scanner;

import sistema.services.VendaServices;

public class Venda {

	private Scanner scan;

	public Venda() {
		this.scan = new Scanner(System.in);
	}

	public void menuFrenteVenda() {

		int opcao;

		VendaServices vendaServices = new VendaServices();
		
		do {
			System.out.println("\n┌─── Frente de Loja - Gerenciar Venda ───┐");
			System.out.println("|            1. Cadastrar                |");
			System.out.println("|            2. Consultar por ID         |");
			System.out.println("|            3. Listar vendas            |");
			System.out.println("|            4. Voltar                   |");
			System.out.println("└────────────────────────────────────────┘");
			System.out.print("Escolha uma opção: ");

			try {
				opcao = scan.nextInt();
				scan.nextLine();

				switch (opcao) {
				case 1:
					vendaServices.cadastrar(null);
					break;
				case 2:
					vendaServices.consultar();
					break;
				case 3:
					vendaServices.listarVendas();
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
