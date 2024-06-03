package sistema.interfaces;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Venda {
	
	private static int idCounter = 1; // Contador estático para geração de IDs
	private int id;
	private Veiculo veiculoVendido; // INSTANCIA DA CLASSE VEICULO (VEICULO VENDIDO)
	private Vendedor vendedorResponsavel; // INSTANCIA DA CLASSE VENDENDOR (VENDEDOR RESPONSÁVEL)
	private Cliente clienteComprador; // INSTANCIA DA CLASSE CLIENTE (CLIENTE QUE EFETUOU A COMPRA)
	private Date dataVenda; // CLASSE DATA PARA REGISTAR A DATA E HORA DA VENDA EXATA.
	private String formaPagamento;
	
	public Venda() {
	}
	
	public Venda(Veiculo veiculoVendido, Vendedor vendedorResponsavel, Cliente clienteComprador, Date dataVenda, String formaPagamento) {
		this.id = idCounter++;
		this.veiculoVendido = veiculoVendido;
		this.vendedorResponsavel = vendedorResponsavel;
		this.clienteComprador = clienteComprador;
		this.dataVenda = dataVenda;
		this.formaPagamento = formaPagamento;
	}

	public int getId() {
        return id;
    }
	
	public Veiculo getVeiculoVendido() {
		return veiculoVendido;
	}

	public void setVeiculoVendido(Veiculo veiculoVendido) {
		this.veiculoVendido = veiculoVendido;
	}

	public Vendedor getVendedorResponsavel() {
		return vendedorResponsavel;
	}

	public void setVendedorResponsavel(Vendedor vendedorResponsavel) {
		this.vendedorResponsavel = vendedorResponsavel;
	}

	public Cliente getClienteComprador() {
		return clienteComprador;
	}

	public void setClienteComprador(Cliente clienteComprador) {
		this.clienteComprador = clienteComprador;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}
	
	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy 'às' HH:mm:ss", new Locale("pt", "BR"));
		return "ID: " + this.getId() +
				"\nCliente: " + this.getClienteComprador() +
				"\nVendedor: " + this.getVendedorResponsavel() +
				"\nVeículo: " + this.getVeiculoVendido() +
				"\nData: " + sdf.format(this.getDataVenda()) +
				"\nForma de Pagamento: " + this.getFormaPagamento() +
				"\n";
	}
	
}
