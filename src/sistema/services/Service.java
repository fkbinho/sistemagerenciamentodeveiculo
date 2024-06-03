package sistema.services;

public interface Service<O>{

	void cadastrar(O o);
    void excluir();
    void alterar();
    void consultar();
}
