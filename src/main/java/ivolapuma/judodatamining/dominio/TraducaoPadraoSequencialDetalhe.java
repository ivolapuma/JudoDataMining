package ivolapuma.judodatamining.dominio;

public class TraducaoPadraoSequencialDetalhe {

	private static final String ENTRE_CAMPOS = ";";
	private int numeroSustentacao;
	private int numeroElementos;
	private int numeroAcoes;
	private String padraoSequencial;

	public TraducaoPadraoSequencialDetalhe(int numeroSustentacao, int numeroElementos, int numeroAcoes, String padraoSequencial) {
		this.numeroSustentacao = numeroSustentacao;
		this.numeroElementos = numeroElementos;
		this.numeroAcoes = numeroAcoes;
		this.padraoSequencial = padraoSequencial;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(numeroSustentacao).append(ENTRE_CAMPOS);
		builder.append(numeroElementos).append(ENTRE_CAMPOS);
		builder.append(numeroAcoes).append(ENTRE_CAMPOS);
		builder.append(padraoSequencial);
		return builder.toString();
	}
	
}
