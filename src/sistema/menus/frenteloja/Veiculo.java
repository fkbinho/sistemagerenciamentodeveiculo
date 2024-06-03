package sistema.menus.frenteloja;

import java.util.InputMismatchException;
import java.util.Scanner;

import sistema.services.VeiculoServices;

public class Veiculo {

	private Scanner scan;

    public Veiculo() {
        this.scan = new Scanner(System.in);
    }
    
	public void menuFrenteVeiculo() {
		
		int opcao;
		
		VeiculoServices veiculoServices = new VeiculoServices();

		do {
			System.out.println("\n┌──── Frente de Loja - Gerenciar Veículo ────┐");
			System.out.println("|       1. Consultar Veículo por Placa       |");
			System.out.println("|       2. Listar Veículos                   |");
			System.out.println("|       3. Voltar                            |");
			System.out.println("└────────────────────────────────────────────┘");
			System.out.print("Escolha uma opção: ");

			try {
				opcao = scan.nextInt();
				scan.nextLine();

				switch (opcao) {
				case 1:
					veiculoServices.consultar();
					break;
				case 2:
					veiculoServices.listarVeiculos();
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
