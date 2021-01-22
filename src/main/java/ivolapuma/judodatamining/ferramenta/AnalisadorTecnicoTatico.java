package ivolapuma.judodatamining.ferramenta;

import java.io.File;
import java.util.List;
import java.util.Map;

import ivolapuma.judodatamining.arquivo.TraducaoPadraoSequencialSPMFArquivo;
import ivolapuma.judodatamining.dominio.AcaoTipoQuebra;
import ivolapuma.judodatamining.dominio.Judoca;
import ivolapuma.judodatamining.dominio.LutaAcao;
import ivolapuma.judodatamining.exception.JudoDataMiningException;
import ivolapuma.judodatamining.mineracaopadraosequencial.algoritmo.CMSPAM;
import ivolapuma.judodatamining.sequencedatabase.SequenceDatabase;
import ivolapuma.judodatamining.sequencedatabase.arquivo.SequenceDatabaseArquivo;
import ivolapuma.judodatamining.sequencedatabase.builder.LutaSequenceDatabaseBuilder;

public class AnalisadorTecnicoTatico {

	private final Judoca judoca;
	private final List<LutaAcao> lutaAcoes;
	private final AcaoTipoQuebra acaoTipoQuebra;
	private final Map<String, String> mapTraducao;
	private final CMSPAM cmspam;

	private Long numeroPadroes;
	private Double tempoExecucaoAlgoritmo;
	private Double tempoExecucaoTraducao;
	private Double indiceSustentacao;
	private Integer tamanhoMinimoPadrao;
	private int[] itensObrigatorios;
	private File databaseFile;
	private File resultadoFile;
	private File traducaoFile;

	public AnalisadorTecnicoTatico(Judoca judoca, List<LutaAcao> lutaAcoes, AcaoTipoQuebra acaoTipoQuebra,
			Map<String, String> mapTraducao) {
		if (judoca == null) {
			throw new IllegalArgumentException("Judoca esta nulo");
		} else if (lutaAcoes == null 
				|| lutaAcoes.size() <= 0) {
			throw new IllegalArgumentException("List de LutaAcao esta nula ou vazia");
		} else if (acaoTipoQuebra == null 
					|| acaoTipoQuebra.getIds() == null 
					|| acaoTipoQuebra.getIds().size() <= 0) {
			throw new IllegalArgumentException("AcaoTipoQuebra esta nulo ou nao contem acaoTipoIds de quebra");
		} else if (mapTraducao == null
					|| mapTraducao.size() <= 0) {
			throw new IllegalArgumentException("Map de Traducao das Acoes esta nulo ou vazio");
		}
		this.judoca = judoca;
		this.lutaAcoes = lutaAcoes;
		this.acaoTipoQuebra = acaoTipoQuebra;
		this.mapTraducao = mapTraducao;
		this.cmspam = new CMSPAM();
	}

	public void executar(File databaseFile, File resultadoFile, File traducaoFile, double indiceSustentacao, 
			Integer tamanhoMinimoPadrao, int[] itensObrigatorios) throws JudoDataMiningException {
		
		if (databaseFile == null) {
			throw new IllegalArgumentException("Database File esta nulo");
		} else if (resultadoFile == null) {
			throw new IllegalArgumentException("Resultado File esta nulo");
		} else if (traducaoFile == null) {
			throw new IllegalArgumentException("Traducao File esta nulo");
		}
		
		SequenceDatabase database = new LutaSequenceDatabaseBuilder(lutaAcoes, acaoTipoQuebra.getIds(), judoca.getNome()).build();
		new SequenceDatabaseArquivo(database, databaseFile).criar();
		
		if (tamanhoMinimoPadrao != null && itensObrigatorios != null) {
			cmspam.executar(databaseFile, resultadoFile, indiceSustentacao, tamanhoMinimoPadrao, itensObrigatorios);	
		} else if (tamanhoMinimoPadrao != null) {
			cmspam.executar(databaseFile, resultadoFile, indiceSustentacao, tamanhoMinimoPadrao);
		} else if (itensObrigatorios != null) {
			cmspam.executar(databaseFile, resultadoFile, indiceSustentacao, itensObrigatorios);
		} else {
			cmspam.executar(databaseFile, resultadoFile, indiceSustentacao);
		}
		
		long traducaoInicio = System.currentTimeMillis();
		new TraducaoPadraoSequencialSPMFArquivo(resultadoFile, mapTraducao).traduzirParaArquivo(traducaoFile);
		long traducaoFim = System.currentTimeMillis();
		
		this.numeroPadroes = cmspam.getNumeroPadroes();
		this.tempoExecucaoAlgoritmo = ((double) cmspam.getTempoExecucaoMilissegundos() / 1000.0); 
		this.tempoExecucaoTraducao = ((double) (traducaoFim - traducaoInicio)) / 1000.0;
		this.indiceSustentacao = indiceSustentacao;
		this.tamanhoMinimoPadrao = tamanhoMinimoPadrao;
		this.itensObrigatorios = itensObrigatorios;
		this.databaseFile = databaseFile;
		this.resultadoFile = resultadoFile;
		this.traducaoFile = traducaoFile;
	}
	
	public void executar(File databaseFile, File resultadoFile, File traducaoFile, double indiceSustentacao, 
			int tamanhoMinimoPadrao) throws JudoDataMiningException {
		this.executar(databaseFile, resultadoFile, traducaoFile, indiceSustentacao, 
				Integer.valueOf(tamanhoMinimoPadrao), null);
	}

	public void executar(File databaseFile, File resultadoFile, File traducaoFile, double indiceSustentacao, 
			int[] itensObrigatorios) throws JudoDataMiningException {
		this.executar(databaseFile, resultadoFile, traducaoFile, indiceSustentacao, null, itensObrigatorios);
	}

	public void executar(File databaseFile, File resultadoFile, File traducaoFile, double indiceSustentacao) 
			throws JudoDataMiningException {
		this.executar(databaseFile, resultadoFile, traducaoFile, indiceSustentacao, null, null);
	}

	public long getNumeroPadroes() {
		return numeroPadroes;
	}

	public double getTempoExecucaoAlgoritmo() {
		return tempoExecucaoAlgoritmo;
	}

	public double getTempoExecucaoTraducao() {
		return tempoExecucaoTraducao;
	}

	public Judoca getJudoca() {
		return judoca;
	}

	public Double getIndiceSustentacao() {
		return indiceSustentacao;
	}

	public Integer getTamanhoMinimoPadrao() {
		return tamanhoMinimoPadrao;
	}

	public int[] getItensObrigatorios() {
		return itensObrigatorios;
	}

	public File getDatabaseFile() {
		return databaseFile;
	}

	public File getResultadoFile() {
		return resultadoFile;
	}

	public File getTraducaoFile() {
		return traducaoFile;
	}
	
}
