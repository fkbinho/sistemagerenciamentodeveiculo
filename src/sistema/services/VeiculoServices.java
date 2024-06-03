package sistema.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sistema.interfaces.TipoVeiculo;
import sistema.interfaces.Veiculo;

public class VeiculoServices implements Service<Veiculo> {

	private Scanner scan;
	private static List<Veiculo> listaVeiculos = new ArrayList<>();

	public VeiculoServices() {
		this.scan = new Scanner(System.in);
	}

	@Override
	public void cadastrar(Veiculo veiculo) {
		if (veiculo == null) {
            System.out.println("\n======= Cadastrar Veículo =======");
            veiculo = criarVeiculo();
        }
        listaVeiculos.add(veiculo);
        System.out.println("Veículo cadastrado com sucesso.");
    }

	@Override
	public void excluir() {

		// ANTES DE EXCLUIR, VERIFICAR SE HÁ VEICULO NA LISTA.
		if (listaVeiculos.isEmpty()) {
			System.err.println("Não há veículos cadastrados.");
			return;
		}
		
		System.out.print("Informe a placa do veículo que deseja excluir: ");
		String placa = scan.nextLine();

		/*
		 * O removeIf É DA CLASSE LIST, E UTILIZA A EXPRESSÃO LAMBDA PARA REMOVER UM
		 * ELEMENTO DA LISTA COM BASE NA CONDIÇÃO. VEICULO É O PARÂMETRO DA EXPRESSÃO, O
		 * QUAL REPRESENTA CADA ELEMENTO DA LISTA.
		 * veiculo.getPlaca().equalsIgnoreCase(placa) - É A EXPRESSÃO.
		 */
		boolean remover = listaVeiculos.removeIf(veiculo -> veiculo.getPlaca().equalsIgnoreCase(placa));

		if (remover) {
			System.out.println("Veículo removido com sucesso.");
		} else {
			System.out.println("Veículo não encontrado.");
		}
	}

	@Override
	public void alterar() {

		// VERIFICA SE HÁ VEICULO CADASTRADO
		if (listaVeiculos.isEmpty()) {
			System.err.println("Não há veículos cadastrados");
			return;
		}

		System.out.print("Digite a placa do veículo que deseja editar: ");
		String placa = scan.nextLine();
		
		/*
		 * CHAMA O MÉTODO buscarVeiculoPorPlaca, PARA PROCURAR NA LISTA UM VEICULO COM A
		 * PLACA ESPECIFICADA. O VALOR RETORNADO(TIPO VEICULOS) DO MÉTODO CHAMADO SERÁ
		 * ATRIBUIDO A VARIÁVEL VEICULO DO TIPO VEICULOS
		 */

		Veiculo veiculoExistente = buscarVeiculoPorPlaca(placa);

		if (veiculoExistente != null) {
			System.out.println("\nVeículo encontrado:");
			System.out.println(veiculoExistente);

			atualizarVeiculo(veiculoExistente);
			System.out.println("Dados do veículo, alterados com sucesso!");
		} else {
			System.out.println("Veículo não encontrado");
		}
	}

	@Override
	public void consultar() {
		
		System.out.print("Digite a placa do veículo que deseja consultar: ");
		String placa = scan.nextLine();

		Veiculo veiculo = buscarVeiculoPorPlaca(placa);
		if (veiculo != null) {
			System.out.println("\nVeículo encontrado:");
			System.out.println(veiculo);
		} else {
			System.out.println("Veículo não encontrado.");
		}
	}

	public void listarVeiculos() {
		if (listaVeiculos.isEmpty()) {
			System.err.println("Não há veículos cadastrados.");
		} else {
			System.out.println("\nLista de Veículos Cadastrados:");
			for (Veiculo veiculo : listaVeiculos) {
				System.out.println(veiculo);
			}
		}
	}

	Veiculo criarVeiculo() {

		System.out.print("Marca: ");
		String marca = scan.nextLine();

		System.out.print("Modelo: ");
		String modelo = scan.nextLine();

		System.out.print("Ano: ");
		int ano = scan.nextInt();
		scan.nextLine();

		System.out.print("Placa: ");
		String placa = scan.nextLine();

		System.out.print("Preço: R$ ");
		double preco = scan.nextDouble();
		scan.nextLine();

		// INFORMAÇÕES DO TIPO DE VEICULO
		System.out.print("Categoria do veículo: ");
		String categoria = scan.nextLine();

		System.out.print("Descrição do veículo: ");
		String descricao = scan.nextLine();

		// CRIA UM OBJETO VEICULO E RETORNA COM AS INFORMAÇÕES
		return new Veiculo(marca, modelo, ano, placa, preco, new TipoVeiculo(categoria, descricao));
	}

	Veiculo buscarVeiculoPorPlaca(String placa) {
		for (Veiculo veiculo : listaVeiculos) {
			if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
				return veiculo;
			}
		}
		return null;
	}

	private void atualizarVeiculo(Veiculo veiculo) {

		// SOLICITA AO USUÁRIO NOVOS DADOS
		System.out.println("Digite os novos dados do veículo (deixe em branco para manter o valor atual):");

        System.out.print("Nova marca (atual: " + veiculo.getMarca() + "): ");
        String novaMarca = scan.nextLine();
        if (!novaMarca.isEmpty()) {
            veiculo.setMarca(novaMarca);
        }

        System.out.print("Novo modelo (atual: " + veiculo.getModelo() + "): ");
        String novoModelo = scan.nextLine();
        if (!novoModelo.isEmpty()) {
            veiculo.setModelo(novoModelo);
        }

        System.out.print("Novo ano (atual: " + veiculo.getAno() + "): ");
        String novoAnoStr = scan.nextLine();
        if (!novoAnoStr.isEmpty()) {
            int novoAno = Integer.parseInt(novoAnoStr);
            veiculo.setAno(novoAno);
        }

        System.out.print("Nova placa (atual: " + veiculo.getPlaca() + "): ");
        String novaPlaca = scan.nextLine();
        if (!novaPlaca.isEmpty()) {
            veiculo.setPlaca(novaPlaca);
        }

        System.out.print("Novo preço (atual: R$ " + veiculo.getPreco() + "): ");
        String novoPrecoStr = scan.nextLine();
        if (!novoPrecoStr.isEmpty()) {
            double novoPreco = Double.parseDouble(novoPrecoStr);
            veiculo.setPreco(novoPreco);
        }

        System.out.print("Nova categoria (atual: " + veiculo.getTipoVeiculo().getCategoria() + "): ");
        String novaCategoria = scan.nextLine();
        if (!novaCategoria.isEmpty()) {
            veiculo.getTipoVeiculo().setCategoria(novaCategoria);
        }

        System.out.print("Nova descrição (atual: " + veiculo.getTipoVeiculo().getDescricao() + "): ");
        String novaDescricao = scan.nextLine();
        if (!novaDescricao.isEmpty()) {
            veiculo.getTipoVeiculo().setDescricao(novaDescricao);
        }
	}
}
