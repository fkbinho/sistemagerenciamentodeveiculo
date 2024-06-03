package sistema.menus.manutencaocadastro;

import java.util.InputMismatchException;
import java.util.Scanner;

import sistema.services.VeiculoServices;

public class Veiculo {

	private Scanner scan;

	public Veiculo() {
		this.scan = new Scanner(System.in);
	}

	public void menuManutencaoVeiculo() {

		int opcao;

		VeiculoServices veiculoServices = new VeiculoServices();

		do {
			System.out.println("\n==== Gerenciar Veículo ====");
			System.out.println("1. Cadastrar veículo");
			System.out.println("2. Excluir veículo");
			System.out.println("3. Alterar dados");
			System.out.println("4. Voltar");
			System.out.print("Escolha uma opção: ");

			try {
				opcao = scan.nextInt();
				scan.nextLine();

				switch (opcao) {
				case 1:
					veiculoServices.cadastrar(null); // null PORQUE AQUI ESTÁ CRIANDO UM VEÍCULO.
					break;
				case 2:
					veiculoServices.excluir();
					break;
				case 3:
					veiculoServices.alterar();
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
