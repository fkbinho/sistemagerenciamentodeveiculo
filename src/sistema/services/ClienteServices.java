package sistema.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sistema.interfaces.Cliente;

public class ClienteServices implements Service<Cliente> {

	private Scanner scan;
	private static List<Cliente> listaClientes = new ArrayList<>();

	public ClienteServices() {
		this.scan = new Scanner(System.in);
	}

	@Override
	public void cadastrar(Cliente cliente) {
		if (cliente == null) {
			System.out.println("\n======= Cadastrar Cliente =======");
			cliente = criarCliente();
		}
		listaClientes.add(cliente);
		System.out.println("Cliente cadastrado com sucesso.");
	}

	@Override
	public void excluir() {

		if (listaClientes.isEmpty()) {
			System.err.println("Não há clientes cadastrados.");
			return;
		}

		System.out.print("Informe o CPF do cliente que deseja excluir: ");
		String cpf = scan.nextLine();

		boolean remover = listaClientes.removeIf(cliente -> cliente.getCpf().equals(cpf));
		if (remover) {
			System.out.println("Cliente removido com sucesso.");
		} else {
			System.out.println("Cliente não encontrado.");
		}
	}

	@Override
	public void alterar() {
		if (listaClientes.isEmpty()) {
			System.out.println("Não há clientes cadastrados.");
			return;
		}

		System.out.print("Digite o CPF do cliente que deseja editar: ");
		String cpf = scan.nextLine();

		Cliente clienteExistente = buscarClientePorCpf(cpf);
		if (clienteExistente != null) {
			System.out.println("Cliente encontrado:");
			System.out.println(clienteExistente);

			atualizarCliente(clienteExistente);
			System.out.println("Dados do cliente alterados com sucesso!");
		} else {
			System.out.println("Cliente não encontrado.");
		}

	}

	@Override
	public void consultar() {
		System.out.print("Digite o CPF do cliente que deseja consultar: ");
        String cpf = scan.nextLine();

        Cliente cliente = buscarClientePorCpf(cpf);
        if (cliente != null) {
            System.out.println("Cliente encontrado:");
            System.out.println(cliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
	}
	
	public void listarClientes() {
        if (listaClientes.isEmpty()) {
            System.err.println("Não há clientes cadastrados.");
        } else {
            System.out.println("\nLista de Clientes Cadastrados:");
            for (Cliente cliente : listaClientes) {
                System.out.println(cliente);
            }
        }
    }
	
	Cliente criarCliente() {
        System.out.print("Nome: ");
        String nome = scan.nextLine();

        String cpf;
        do {
            System.out.print("CPF: ");
            cpf = scan.nextLine();
            if (!verificarCpf(cpf)) {
                System.out.println("CPF inválido. Por favor, insira um CPF válido.");
            }
        } while (!verificarCpf(cpf));

        System.out.print("Telefone: ");
        String telefone = scan.nextLine();

        return new Cliente(nome, cpf, telefone);
    }
	
	Cliente buscarClientePorCpf(String cpf) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }
	
	private void atualizarCliente(Cliente cliente) {
        System.out.println("Digite os novos dados do cliente (deixe em branco para manter o valor atual):");

        System.out.print("Novo nome (atual: " + cliente.getNome() + "): ");
        String novoNome = scan.nextLine();
        if (!novoNome.isEmpty()) {
            cliente.setNome(novoNome);
        }

        System.out.print("Novo telefone (atual: " + cliente.getTelefone() + "): ");
        String novoTelefone = scan.nextLine();
        if (!novoTelefone.isEmpty()) {
            cliente.setTelefone(novoTelefone);
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
}
