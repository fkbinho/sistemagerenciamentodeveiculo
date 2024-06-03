package sistema.aplicacao;

import java.util.Scanner;

import sistema.menus.principal.MenuPrincipal;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		new MenuPrincipal(scan).exibirMenu();
	}
}
