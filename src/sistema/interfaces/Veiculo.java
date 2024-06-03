package sistema.interfaces;

public class Veiculo {
	
	private String marca;
	private String modelo;
	private int ano;
	private String placa;
	private double preco;
	private TipoVeiculo tipoVeiculo;
	
	public Veiculo() {
	}
	
	public Veiculo(String marca, String modelo, int ano, String placa, double preco, TipoVeiculo tipoVeiculo) {
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.placa = placa;
		this.preco = preco;
		this.tipoVeiculo = tipoVeiculo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}
	
	@Override
	public String toString() {
	    return  "Marca: " + this.marca +
	            "\nModelo: " + this.modelo +
	            "\nAno: " + this.ano +
	            "\nPlaca: " + this.placa +
	            "\nPreço: R$ " + this.preco +
	            "\nCategoria: " + this.tipoVeiculo.getCategoria() +
	            "\nDescrição: " + this.tipoVeiculo.getDescricao() +
	            "\n";
	}
	
}
