package sistema.menus.frenteloja;

import java.util.InputMismatchException;
import java.util.Scanner;

import sistema.services.LojaServices;

public class Loja {

	private Scanner scan;

    public Loja() {
        this.scan = new Scanner(System.in);
    }
    
	public void menuFrenteLoja() {
		
		int opcao;
		
		LojaServices lojaServices = new LojaServices();

		do {
			System.out.println("\n┌────Frente de Loja - Gerenciar Loja ────┐");
			System.out.println("|           1. Consultar loja por nome   |");
			System.out.println("|           2. Listar lojas              |");
			System.out.println("|           3. Voltar                    |");
			System.out.println("└────────────────────────────────────────┘");
			System.out.print("Escolha uma opção: ");

			try {
				opcao = scan.nextInt();
				scan.nextLine();

				switch (opcao) {
				case 1:
					lojaServices.consultar();
					break;
				case 2:
					lojaServices.listarLojas();
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
