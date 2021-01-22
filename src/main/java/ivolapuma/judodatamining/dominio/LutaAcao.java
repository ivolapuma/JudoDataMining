package ivolapuma.judodatamining.dominio;

import java.math.BigDecimal;

public class LutaAcao {

	long luta;
	long sequencia;
	String judocaFaz;
	String judocaRecebe;
	long acaoTipoId;
	String acaoTipo;
	long acaoId;
	String acao;
	long acaoMps;
	BigDecimal tecnicaId;
	String tecnica;
	BigDecimal direcaoId;
	String direcao;
	BigDecimal acaoTecnicaDirecaoMps;
	BigDecimal pontuacaoId;
	String pontuacao;
	BigDecimal pontuacaoMps;
	
	public LutaAcao() {
	}

	public LutaAcao(long luta, long sequencia, String judocaFaz, String judocaRecebe, long acaoTipoId,
			String acaoTipo, long acaoId, String acao, long acaoMps, BigDecimal tecnicaId, String tecnica,
			BigDecimal direcaoId, String direcao, BigDecimal acaoTecnicaDirecaoMps,
			BigDecimal pontuacaoId, String pontuacao, BigDecimal pontuacaoMps) {
		this.luta = luta;
		this.sequencia = sequencia;
		this.judocaFaz = judocaFaz;
		this.judocaRecebe = judocaRecebe;
		this.acaoTipoId = acaoTipoId;
		this.acaoTipo = acaoTipo;
		this.acaoId = acaoId;
		this.acao = acao;
		this.acaoMps = acaoMps;
		this.tecnicaId = tecnicaId;
		this.tecnica = tecnica;
		this.direcaoId = direcaoId;
		this.direcao = direcao;
		this.acaoTecnicaDirecaoMps = acaoTecnicaDirecaoMps;
		this.pontuacaoId = pontuacaoId;
		this.pontuacao = pontuacao;
		this.pontuacaoMps = pontuacaoMps;
	}

	public long getLuta() {
		return luta;
	}

	public long getSequencia() {
		return sequencia;
	}

	public String getJudocaFaz() {
		return judocaFaz;
	}

	public String getJudocaRecebe() {
		return judocaRecebe;
	}

	public long getAcaoTipoId() {
		return acaoTipoId;
	}

	public String getAcaoTipo() {
		return acaoTipo;
	}

	public long getAcaoId() {
		return acaoId;
	}

	public String getAcao() {
		return acao;
	}

	public long getAcaoMps() {
		return acaoMps;
	}

	public BigDecimal getTecnicaId() {
		return tecnicaId;
	}

	public String getTecnica() {
		return tecnica;
	}

	public BigDecimal getDirecaoId() {
		return direcaoId;
	}

	public String getDirecao() {
		return direcao;
	}

	public BigDecimal getAcaoTecnicaDirecaoMps() {
		return acaoTecnicaDirecaoMps;
	}

	public BigDecimal getPontuacaoId() {
		return pontuacaoId;
	}

	public String getPontuacao() {
		return pontuacao;
	}

	public BigDecimal getPontuacaoMps() {
		return pontuacaoMps;
	}

}
