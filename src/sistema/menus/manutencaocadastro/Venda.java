package sistema.menus.manutencaocadastro;

import java.util.InputMismatchException;
import java.util.Scanner;

import sistema.services.VendaServices;

public class Venda {
	
	private Scanner scan;

    public Venda() {
        this.scan = new Scanner(System.in);
    }
    
	public void menuManutencaoVenda() {

		int opcao;
		
		VendaServices vendaServices = new VendaServices(); 
		
		do {
			System.out.println("\n==== Gerenciar Venda ====");
			System.out.println("1. Cadastrar");
			System.out.println("2. Excluir");
			System.out.println("3. Alterar dados da venda");
			System.out.println("4. Voltar");
			System.out.print("Escolha uma opção: ");

			try {
				opcao = scan.nextInt();
				scan.nextLine();

				switch (opcao) {
				case 1:
					vendaServices.cadastrar(null);
					break;
				case 2:
					vendaServices.excluir();
					break;
				case 3:
					vendaServices.alterar();
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
