package sistema.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sistema.interfaces.Loja;
import sistema.interfaces.Vendedor;

public class VendedorServices implements Service<Vendedor> {

	private Scanner scan;
	private LojaServices lojaServices;
	private static List<Vendedor> listaVendedores = new ArrayList<>();

	public VendedorServices() {
		this.scan = new Scanner(System.in);
		this.lojaServices = new LojaServices();
	}

	@Override
	public void cadastrar(Vendedor vendedor) {

		if (vendedor == null) {
			System.out.println("\n======= Cadastrar Vendedor =======");
			vendedor = criarVendedor();
		}
		listaVendedores.add(vendedor);
		System.out.println("Vendedor cadastrado com sucesso.");
	}

	@Override
	public void excluir() {

		if (listaVendedores.isEmpty()) {
			System.err.println("Não há vendedores cadastrados.");
			return;
		}

		System.out.print("Informe o nome do vendeddor que deseja excluir: ");
		String nome = scan.nextLine();

		boolean remover = listaVendedores.removeIf(vendedor -> vendedor.getNome().equalsIgnoreCase(nome));
		if (remover) {
			System.out.println("Vendedor removido com sucesso.");
		} else {
			System.out.println("Vendedor não encontrado.");
		}
	}

	@Override
	public void alterar() {

		if (listaVendedores.isEmpty()) {
			System.out.println("Não há vendedores cadastrados.");
			return;
		}

		System.out.print("Digite o nome do vendedor que deseja editar: ");
		String nome = scan.nextLine();

		Vendedor vendedorExistente = buscarVendedorPorNome(nome);
		if (vendedorExistente != null) {
			System.out.println("Vendedor encontrado:");
			System.out.println(vendedorExistente);

			atualizarVendedor(vendedorExistente);
			System.out.println("Dados do vendedor alterados com sucesso!");
		} else {
			System.out.println("Vendedor não encontrado.");
		}
	}

	@Override
	public void consultar() {

		System.out.print("Digite o nome do vendedor que deseja consultar: ");
		String nome = scan.nextLine();

		Vendedor vendedor = buscarVendedorPorNome(nome);

		if (vendedor != null) {
			System.out.println("Vendedor encontrado:");
			System.out.println(vendedor);
		} else {
			System.out.println("Vendedor não encontrado");
		}
	}

	public void listarVendedores() {
		if (listaVendedores.isEmpty()) {
			System.out.println("Não há vendedores cadastrados.");
		} else {
			System.out.println("\nLista de Vendedores Cadastrados:");
			for (Vendedor vendedor : listaVendedores) {
				System.out.println(vendedor);
			}
		}
	}

	Vendedor criarVendedor() {

		System.out.print("Nome: ");
		String nome = scan.nextLine();

		// SOLICITA O CPF E O VALIDA
		String cpf;
		boolean validarCpf;
		do {
			System.out.print("CPF: ");
			cpf = scan.nextLine();
			validarCpf = verificarCpf(cpf);
			if (!validarCpf) {
				System.out.println("CPF inválido. Por favor, insira um CPF válido.");
			}
		} while (!validarCpf);

		System.out.print("Telefone: ");
		String telefone = scan.nextLine();

		// ASSOSCIAR VENDEDOR A UMA LOJA CADASTRADA
		Loja loja = selecionarLoja();

		return new Vendedor(nome, cpf, telefone, loja);
	}

	Vendedor buscarVendedorPorNome(String nome) {
		for (Vendedor vendedor : listaVendedores) {
			if (vendedor.getNome().equalsIgnoreCase(nome)) {
				return vendedor;
			}
		}
		return null;
	}

	private void atualizarVendedor(Vendedor vendedor) {

		System.out.println("Digite os novos dados do vendedor (deixe em branco para manter o valor atual):");

		System.out.print("Novo nome (atual: " + vendedor.getNome() + "): ");
		String novoNome = scan.nextLine();
		if (!novoNome.isEmpty()) {
			vendedor.setNome(novoNome);
		}

		System.out.print("Novo telefone (atual: " + vendedor.getTelefone() + "): ");
		String novoTelefone = scan.nextLine();
		if (!novoTelefone.isEmpty()) {
			vendedor.setTelefone(novoTelefone);
		}
		
	    System.out.print("Deseja atualizar a loja associada? (S/N): ");
	    String resposta = scan.nextLine().toUpperCase();
	    if (resposta.equals("S") || resposta.equals("SIM")) {
	        Loja novaLoja = selecionarLoja();
	        vendedor.setLoja(novaLoja);
	    }
	}

	private boolean verificarCpf(String cpf) {
		// REMOVER OS CARACTERES QUE NÃO SÃO DIGITOS
		cpf = cpf.replaceAll("[^\\d]", "");

		// VERIFICA SE TODOS OS DIGITOS SÃO IGUAL OU SE TEM 11 DIGITOS
		if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999") || (cpf.length() != 11)) {
			return false;
		}

		// CALCULAR O PRIMEIRO DIGITO
		int soma = 0;
		for (int i = 0; i < 9; i++) {
			soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
		}

		int digito1 = 11 - (soma % 11);
		if (digito1 > 9) {
			digito1 = 0;
		}

		// CALCULAR O SEGUNDO DIGITO
		soma = 0;
		for (int i = 0; i < 10; i++) {
			soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
		}

		int digito2 = 11 - (soma % 11);
		if (digito2 > 9)
			digito2 = 0;

		return Character.getNumericValue(cpf.charAt(9)) == digito1
				&& Character.getNumericValue(cpf.charAt(10)) == digito2;
	}

	private Loja selecionarLoja() {
		lojaServices.listarLojas();
		System.out.print("Informe o nome da loja associada ao vendedor: ");
		String nomeLoja = scan.nextLine();
		
		Loja loja = lojaServices.buscarLojaPorNome(nomeLoja);
		if (loja == null) {
			System.out.println("Loja não encontrada. Por favor, cadastre a loja primeiro.");
		}
		return loja;
	}
}
