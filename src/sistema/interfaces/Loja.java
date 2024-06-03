package sistema.interfaces;

public class Loja {

	private String nome;
	private String endereco;
	private String telefone;
	
	public Loja() {
	}
	
	public Loja(String nome, String endereco, String telefone) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Override
	public String toString() {
		return "Nome: " + this.getNome() +
				"\nEndereço: " + this.getEndereco() +
				"\nTelefone: " + this.getTelefone() +
				"\n";
	}
	
}
