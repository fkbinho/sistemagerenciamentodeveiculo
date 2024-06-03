package sistema.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sistema.interfaces.Loja;

public class LojaServices implements Service<Loja> {

	private Scanner scan;
	private static List<Loja> listaLojas = new ArrayList<>();

	public LojaServices() {
		this.scan = new Scanner(System.in);
	}

	@Override
	public void cadastrar(Loja loja) {
		if (loja == null) {
			System.out.println("\n======= Cadastrar Loja =======");
			loja = criarLoja();
		}
		listaLojas.add(loja);
		System.out.println("Loja cadastrada com sucesso.");
	}

	@Override
	public void excluir() {

		// ANTES DE EXCLUIR, VERIFICAR SE HÁ VEICULO NA LISTA.
		if (listaLojas.isEmpty()) {
			System.err.println("Não há lojas cadastradas.");
			return;
		}

		System.out.print("Informe o nome da loja que deseja excluir: ");
		String nome = scan.nextLine();

		boolean remover = listaLojas.removeIf(loja -> loja.getNome().equalsIgnoreCase(nome));

		if (remover) {
			System.out.println("Loja removida com sucesso.");
		} else {
			System.out.println("Loja não encontrada.");
		}
	}

	@Override
	public void alterar() {

		// VERIFICA SE HÁ VEICULO CADASTRADO
		if (listaLojas.isEmpty()) {
			System.out.println("Não há lojas cadastradas.");
			return;
		}

		System.out.print("Informe o nome da loja que deseja editar: ");
		String nome = scan.nextLine();

		Loja lojaExistente = buscarLojaPorNome(nome);
		if (lojaExistente != null) {
			System.out.println("\nLoja encontrada:");
			System.out.println(lojaExistente);

			atualizarLoja(lojaExistente);
			System.out.println("Dados da loja alterados com sucesso!");
		} else {
			System.out.println("Loja não encontrada.");
		}
	}

	@Override
	public void consultar() {

		System.out.print("Digite o nome da loja que deseja consultar: ");
		String nome = scan.nextLine();

		Loja loja = buscarLojaPorNome(nome);
		if (loja != null) {
			System.out.println("\nLoja encontrada:");
			System.out.println(loja);
		} else {
			System.out.println("Loja não encontrada.");
		}
	}

	public void listarLojas() {
		if (listaLojas.isEmpty()) {
			System.err.println("Não há lojas cadastradas.");
		} else {
			System.out.println("\nLista de Lojas Cadastradas:");
			for (Loja loja : listaLojas) {
				System.out.println(loja);
			}
		}
	}

	private Loja criarLoja() {

		System.out.print("Nome: ");
		String nome = scan.nextLine();

		System.out.print("Endereço: ");
		String endereco = scan.nextLine();

		System.out.print("Telefone: ");
		String telefone = scan.nextLine();

		return new Loja(nome, endereco, telefone);
	}

	Loja buscarLojaPorNome(String nome) {
		for (Loja loja : listaLojas) {
			if (loja.getNome().equalsIgnoreCase(nome)) {
				return loja;
			}
		}
		return null;
	}

	private void atualizarLoja(Loja loja) {

		System.out.println("Digite os novos dados da loja (deixe em branco para manter o valor atual):");

		System.out.print("Novo nome (atual: " + loja.getNome() + "): ");
		String novoNome = scan.nextLine();
		if (!novoNome.isEmpty()) {
			loja.setNome(novoNome);
		}

		System.out.print("Novo endereço (atual: " + loja.getEndereco() + "): ");
		String novoEndereco = scan.nextLine();
		if (!novoEndereco.isEmpty()) {
			loja.setEndereco(novoEndereco);
		}

		System.out.print("Novo telefone (atual: " + loja.getTelefone() + "): ");
		String novoTelefone = scan.nextLine();
		if (!novoTelefone.isEmpty()) {
			loja.setTelefone(novoTelefone);
		}
	}
}
