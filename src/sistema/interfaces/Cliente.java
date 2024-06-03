package sistema.interfaces;

public class Cliente extends Pessoa {

	public Cliente() {
		
	}

	public Cliente(String nome, String cpf, String telefone) {
		super(nome, cpf, telefone);
	}
	
	@Override
	public String toString() {
		return "Nome: " + this.getNome() +
				"\nCPF: " + this.getCpf() +
				"\nTelefone: " + this.getTelefone() +
				"\n";
	}
}
