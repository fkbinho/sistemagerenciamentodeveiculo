package sistema.menus.principal;

import java.util.InputMismatchException;
import java.util.Scanner;

import sistema.menus.frenteloja.FrenteDeLoja;
import sistema.menus.manutencaocadastro.ManutencaoDeCadastro;

public class MenuPrincipal {

	private Scanner scan;

	public MenuPrincipal(Scanner scan) {
		this.scan = scan;
	}

	public void exibirMenu() {

		int opcao;

		do {
			System.out.println("╭──────────────────────────────────────╮");
			System.out.println("|            MENU PRINCIPAL            |");
			System.out.println("╰──────────────────────────────────────╯");
			System.out.println("      1. Frente de Loja");
			System.out.println("      2. Manutenção no Cadastro");
			System.out.println("      3. Sair");
			System.out.print("Escolha uma opção: ");

			try {
				opcao = scan.nextInt();
				scan.nextLine();

				switch (opcao) {
				case 1:
					// INSTACIAÇÃO DO MENU FRENTE DE LOJA
					new FrenteDeLoja().menuFrenteDeLoja();
					break;
				case 2:
					// INSTACIAÇÃO DO MENU MANUTENÇÃO CADASTRO
					new ManutencaoDeCadastro().menuManutencaoDeCadastro();
					break;
				case 3:
					System.out.println("Saindo do sistema...");
					break;
				default:
					System.out.println("Opção inválida. Por favor, escolha novamente.\n");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Opção inválida.\n");
				opcao = 0;
				scan.nextLine();
			}
		} while (opcao != 3);
	}
}
