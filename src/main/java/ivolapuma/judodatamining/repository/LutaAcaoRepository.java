package ivolapuma.judodatamining.repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ivolapuma.judodatamining.dominio.LutaAcao;
import ivolapuma.judodatamining.exception.JudoDataMiningException;

public class LutaAcaoRepository {

	private Connection connection;

	public LutaAcaoRepository(Connection connection) {
		super();
		if (connection == null) {
			throw new IllegalArgumentException("Parametro Connection esta nulo.");
		}
		this.connection = connection;
	}

	public List<LutaAcao> buscarPorJudoca(long judoca) throws JudoDataMiningException {
		
		List<LutaAcao> list = new ArrayList<LutaAcao>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("la.id_luta    AS luta, ");
		sql.append("la.sequencia  AS sequencia, ");
		sql.append("j1.nome       AS judoca_faz, ");
		sql.append("j2.nome       AS judoca_recebe, ");
		sql.append("at.id         AS acao_tipo_id, ");
		sql.append("at.nome       AS acao_tipo, ");
		sql.append("a.id          AS acao_id, ");
		sql.append("a.nome        AS acao, ");
		sql.append("ia.id         AS acao_mps, ");
		sql.append("t.id          AS tecnica_id, ");
		sql.append("t.nome        AS tecnica, ");
		sql.append("td.id         AS direcao_id, ");
		sql.append("td.nome       AS direcao, ");
		sql.append("iatd.id       AS acao_tecnica_direcao_mps, ");
		sql.append("tp.id         AS pontuacao_id, ");
		sql.append("tp.nome       AS pontuacao, ");
		sql.append("ip.id         AS pontuacao_mps ");
		sql.append("FROM judoca j ");
		sql.append("INNER JOIN luta              l    ON (l.id_lutador1 = j.id) ");
		sql.append("INNER JOIN luta_acao         la   ON (la.id_luta = l.id) ");
		sql.append("INNER JOIN acao              a    ON (a.id = la.id_acao) ");
		sql.append("INNER JOIN acao_tipo         at   ON (at.id = a.id_tipo) ");
		sql.append("INNER JOIN mps_item          ia   ON (ia.id_tipo = 1 AND ia.id_acao = a.id) ");
		sql.append("LEFT  JOIN tecnica           t    ON (t.id = la.id_tecnica) ");
		sql.append("LEFT  JOIN tecnica_direcao   td   ON (td.id = la.id_direcao) ");
		sql.append("LEFT  JOIN mps_item          iatd ON (iatd.id_tipo IN (2, 3) AND iatd.id_acao = la.id_acao AND iatd.id_tecnica = la.id_tecnica AND iatd.id_direcao = COALESCE(la.id_direcao, 9)) ");
		sql.append("LEFT  JOIN tecnica_pontuacao tp   ON (tp.id = la.id_pontuacao) ");
		sql.append("LEFT  JOIN mps_item          ip   ON (ip.id_tipo = 4 AND ip.id_pontuacao = tp.id) ");
		sql.append("INNER JOIN judoca            j1   ON (j1.id = la.id_quem_faz) ");
		sql.append("LEFT  JOIN judoca            j2   ON (j2.id = la.id_quem_recebe) ");
		sql.append("WHERE j.id = ? ");
		sql.append("ORDER BY 1, 2 ");
		
		try {
			
			PreparedStatement statement = this.connection.prepareStatement(sql.toString());
			statement.setLong(1, judoca);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				
				long luta = rs.getLong("luta");
				long sequencia = rs.getLong("sequencia");
				String judocaFaz = rs.getString("judoca_faz");
				String judocaRecebe = rs.getString("judoca_recebe");
				long acaoTipoId = rs.getLong("acao_tipo_id");
				String acaoTipo = rs.getString("acao_tipo");
				long acaoId = rs.getLong("acao_id");
				String acao = rs.getString("acao");
				long acaoMps = rs.getLong("acao_mps");
				BigDecimal tecnicaId = rs.getBigDecimal("tecnica_id");
				String tecnica = rs.getString("tecnica");
				BigDecimal direcaoId = rs.getBigDecimal("direcao_id");
				String direcao = rs.getString("direcao");
				BigDecimal acaoTecnicaDirecaoMps = rs.getBigDecimal("acao_tecnica_direcao_mps");
				BigDecimal pontuacaoId = rs.getBigDecimal("pontuacao_id");
				String pontuacao = rs.getString("pontuacao");
				BigDecimal pontuacaoMps = rs.getBigDecimal("pontuacao_mps");
				
				LutaAcao vo = new LutaAcao(luta, sequencia, judocaFaz, judocaRecebe, acaoTipoId, acaoTipo, acaoId, 
						acao, acaoMps, tecnicaId, tecnica, direcaoId, direcao, acaoTecnicaDirecaoMps, pontuacaoId, 
						pontuacao, pontuacaoMps);
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			throw new JudoDataMiningException("Erro ao buscar as Acoes de Luta do Judoca.", e);
		}
		
		return list;
	}

}
