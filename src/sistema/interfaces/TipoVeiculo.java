package sistema.interfaces;

public class TipoVeiculo {

	private String categoria;
    private String descricao;
    
    public TipoVeiculo() {
    }
    
	public TipoVeiculo(String categoria, String descricao) {
		this.categoria = categoria;
		this.descricao = descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
    
    
}
