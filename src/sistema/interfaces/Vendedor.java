package sistema.interfaces;

public class Vendedor extends Pessoa {

	private Loja loja;

	public Vendedor() {

	}

	public Vendedor(String nome, String cpf, String telefone, Loja loja) {
		super(nome, cpf, telefone);
		this.loja = loja;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	@Override
	public String toString() {
		return "Nome: " + this.getNome() + "\nCPF: " + this.getCpf() + "\nTelefone: " + this.getTelefone()
				+ "\nLoja associada: \n" + this.getLoja() + "\n";
	}
}
