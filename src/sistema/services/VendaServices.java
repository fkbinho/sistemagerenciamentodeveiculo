package sistema.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import sistema.interfaces.Cliente;
import sistema.interfaces.Veiculo;
import sistema.interfaces.Venda;
import sistema.interfaces.Vendedor;

public class VendaServices implements Service<Venda> {

	private static List<Venda> listaVendas = new ArrayList<>();
	private ClienteServices clienteServices = new ClienteServices();
	private VendedorServices vendedorServices = new VendedorServices();
	private VeiculoServices veiculoService = new VeiculoServices();
	private Scanner scan;

	public VendaServices() {
		this.scan = new Scanner(System.in);
	}

	@Override
	public void cadastrar(Venda venda) {
		if (venda == null) {
			System.out.println("\n======= Cadastrar Venda =======");
			venda = criarVenda();
		}
		listaVendas.add(venda);
		System.out.println("Venda cadastrada com sucesso.");
		imprimirRecibo(venda);
	}

	@Override
	public void excluir() {
		if (listaVendas.isEmpty()) {
			System.err.println("Não há vendas cadastradas.");
			return;
		}

		System.out.print("Informe o ID da venda que deseja excluir: ");
		int id = scan.nextInt();
		scan.nextLine();

		boolean remover = listaVendas.removeIf(venda -> venda.getId() == id);
		if (remover) {
			System.out.println("Venda removida com sucesso.");
		} else {
			System.out.println("Venda não encontrada.");
		}
	}

	@Override
	public void alterar() {
		if (listaVendas.isEmpty()) {
			System.out.println("Não há vendas cadastradas.");
			return;
		}

		System.out.print("Digite o ID da venda que deseja editar: ");
		int id = scan.nextInt();
		scan.nextLine();

		Venda vendaExistente = buscarVendaPorId(id);
		if (vendaExistente != null) {
			System.out.println("Venda encontrada:");
			System.out.println(vendaExistente);

			atualizarVenda(vendaExistente);
			System.out.println("Dados da venda alterados com sucesso!");
		} else {
			System.out.println("Venda não encontrada.");
		}
	}

	@Override
	public void consultar() {
		System.out.print("Digite o ID da venda que deseja consultar: ");
		int id = scan.nextInt();
		scan.nextLine();

		Venda venda = buscarVendaPorId(id);
		if (venda != null) {
			System.out.println("Venda encontrada:");
			System.out.println(venda);
		} else {
			System.out.println("Venda não encontrada.");
		}
	}

	public void listarVendas() {
		if (listaVendas.isEmpty()) {
			System.err.println("Não há vendas cadastradas.");
		} else {
			System.out.println("\nLista de Vendas Cadastradas:");
			for (Venda venda : listaVendas) {
				System.out.println(venda);
			}
		}
	}

	private Venda criarVenda() {
		Cliente cliente = selecionarCliente();
		Vendedor vendedor = selecionarVendedor();
		Veiculo veiculo = selecionarVeiculo();

		System.out.println("Escolha a forma de pagamento:");
        System.out.println("1. Dinheiro");
        System.out.println("2. Pix");
        System.out.println("3. Cartão de Crédito");
        System.out.println("4. Cartão de Débito");
        System.out.print("Opção: ");
        int opcaoPagamento = scan.nextInt();
        scan.nextLine();
        
        String formaPagamento;
        switch (opcaoPagamento) {
            case 1:
                formaPagamento = "Dinheiro";
                break;
            case 2:
                formaPagamento = "Pix";
                break;
            case 3:
                formaPagamento = "Cartão de Crédito";
                break;
            case 4:
                formaPagamento = "Cartão de Débito";
                break;
            default:
                formaPagamento = "Não especificado";
                break;
        }
        
		Date dataVenda = new Date(); // DATA ATUAL

		return new Venda(veiculo, vendedor, cliente, dataVenda, formaPagamento);
	}

	private Venda buscarVendaPorId(int id) {
		for (Venda venda : listaVendas) {
			if (venda.getId() == id) {
				return venda;
			}
		}
		return null;	
	}

	private void atualizarVenda(Venda venda) {
		System.out.println("Digite os novos dados da venda (deixe em branco para manter o valor atual):");

		Cliente cliente = venda.getClienteComprador();
		System.out.print("Deseja alterar o cliente? (S/N): ");
		String resposta = scan.nextLine();
		if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("SIM")) {
			cliente = selecionarCliente();
		}
		venda.setClienteComprador(cliente);

		Vendedor vendedor = venda.getVendedorResponsavel();
		System.out.print("Deseja alterar o vendedor? (S/N): ");
		resposta = scan.nextLine();
		if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("SIM")) {
			vendedor = selecionarVendedor();
		}
		venda.setVendedorResponsavel(vendedor);

		Veiculo veiculo = venda.getVeiculoVendido();
		System.out.print("Deseja alterar o veículo? (S/N): ");
		resposta = scan.nextLine();
		if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("SIM")) {
			veiculo = selecionarVeiculo();
		}
		venda.setVeiculoVendido(veiculo);

		venda.setDataVenda(new Date()); // ATUALIZA PARA DATA ATUAL
		
		System.out.print("Deseja alterar a forma de pagamento? (S/N): ");
        resposta = scan.nextLine();
        if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("SIM")) {
            System.out.println("Escolha a forma de pagamento:");
            System.out.println("1. Dinheiro");
            System.out.println("2. Pix");
            System.out.println("3. Cartão de Crédito");
            System.out.println("4. Cartão de Débito");
            System.out.print("Opção: ");
            int opcaoPagamento = scan.nextInt();
            scan.nextLine();

            String formaPagamento;
            switch (opcaoPagamento) {
                case 1:
                    formaPagamento = "Dinheiro";
                    break;
                case 2:
                    formaPagamento = "Pix";
                    break;
                case 3:
                    formaPagamento = "Cartão de Crédito";
                    break;
                case 4:
                    formaPagamento = "Cartão de Débito";
                    break;
                default:
                    formaPagamento = "Não especificado";
                    break;
            }
            venda.setFormaPagamento(formaPagamento);
        }
	}

	private Cliente selecionarCliente() {
		clienteServices.listarClientes();
		System.out.print("Informe o CPF do cliente: ");
		String cpf = scan.nextLine();
		Cliente cliente = clienteServices.buscarClientePorCpf(cpf);
		if (cliente == null) {
			System.out.println("Cliente não encontrado. Por favor, cadastre o cliente primeiro.");
			cliente = clienteServices.criarCliente();
			clienteServices.cadastrar(cliente);
		}
		return cliente;
	}

	private Vendedor selecionarVendedor() {
		vendedorServices.listarVendedores();
		System.out.print("Informe o NOME do vendedor: ");
		String nome = scan.nextLine();
		Vendedor vendedor = vendedorServices.buscarVendedorPorNome(nome);
		if (vendedor == null) {
			System.out.println("Vendedor não encontrado. Por favor, cadastre o vendedor primeiro.");
			vendedor = vendedorServices.criarVendedor();
			vendedorServices.cadastrar(vendedor);
		}
		return vendedor;
	}

	private Veiculo selecionarVeiculo() {
		veiculoService.listarVeiculos();
		System.out.print("Informe a placa do veículo: ");
		String placa = scan.nextLine();
		Veiculo veiculo = veiculoService.buscarVeiculoPorPlaca(placa);
		if (veiculo == null) {
			System.out.println("Veículo não encontrado. Por favor, cadastre o veículo primeiro.");
			veiculo = veiculoService.criarVeiculo();
			veiculoService.cadastrar(veiculo);
		}
		return veiculo;
	}
	
	private void imprimirRecibo(Venda venda) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy 'às' HH:mm:ss", new Locale("pt", "BR"));
        System.out.println("\n======= Recibo de Venda =======");
        System.out.println("ID da Venda: " + venda.getId());
        System.out.println("Cliente: " + venda.getClienteComprador().getNome());
        System.out.println("Vendedor: " + venda.getVendedorResponsavel().getNome());
        System.out.println("Veículo: " + venda.getVeiculoVendido().getMarca() + " " + venda.getVeiculoVendido().getModelo());
        System.out.println("Data e Hora: " + sdf.format(venda.getDataVenda()));
        System.out.println("Forma de Pagamento: " + venda.getFormaPagamento());
        System.out.println("================================\n");
    }
}


