package ivolapuma.judodatamining.dominio;

public class Judoca {

	private final long id;
	private final String nome;

	public Judoca(long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
}
