/***
* QUERY PARA OBTER O DATABASE SEQUENCE DE UM LUTADOR
*/
SELECT la.id_luta    AS luta,
       la.sequencia  AS sequencia,
       j1.nome       AS judoca_faz, 
       j2.nome       AS judoca_recebe,
       at.id         AS acao_tipo_id,
       at.nome       AS acao_tipo,
       a.id          AS acao_id, 
       a.nome        AS acao, 
       ia.id         AS acao_mps,
       t.id          AS tecnica_id, 
       t.nome        AS tecnica, 
       td.id         AS direcao_id, 
       td.nome       AS direcao,
       iatd.id       AS acao_tecnica_direcao_mps, 
       tp.id         AS pontuacao_id, 
       tp.nome       AS pontuacao, 
       ip.id         AS pontuacao_mps
FROM judoca j
  INNER JOIN luta              l    ON (l.id_lutador1 = j.id)
  INNER JOIN luta_acao         la   ON (la.id_luta = l.id)
  INNER JOIN acao              a    ON (a.id = la.id_acao)
  INNER JOIN acao_tipo         at   ON (at.id = a.id_tipo)
  INNER JOIN mps_item          ia   ON (ia.id_tipo = 1 AND 
                                        ia.id_acao = a.id)
  LEFT  JOIN tecnica           t    ON (t.id = la.id_tecnica)
  LEFT  JOIN tecnica_direcao   td   ON (td.id = la.id_direcao)
  LEFT  JOIN mps_item          iatd ON (iatd.id_tipo IN (2, 3)          AND   
                                        iatd.id_acao    = la.id_acao    AND 
                                        iatd.id_tecnica = la.id_tecnica AND 
                                        iatd.id_direcao = COALESCE(la.id_direcao, 9))
  LEFT  JOIN tecnica_pontuacao tp   ON (tp.id = la.id_pontuacao)
  LEFT  JOIN mps_item          ip   ON (ip.id_tipo      = 4 AND 
                                        ip.id_pontuacao = tp.id)
  INNER JOIN judoca            j1   ON (j1.id = la.id_quem_faz)
  LEFT  JOIN judoca            j2   ON (j2.id = la.id_quem_recebe)
WHERE j.id = 3
  --AND l.id = 10
ORDER BY 1, 2
;


/***
* QUERY PARA OBTER O DATABASE SEQUENCE DE UM LUTADOR - PARA MONTAR FIGURA NA DISSERTAÇÃO
*/
SELECT la.id_luta                  AS luta,
       la.sequencia                AS sequencia,
       j1.nome                     AS judoca_faz, 
       j2.nome                     AS judoca_recebe,
       at.nome                     AS acao_tipo,
       COALESCE(iatd.nome, a.nome) AS acao_tecnica_direcao, 
       tp.nome                     AS pontuacao, 
       COALESCE(iatd.id, ia.id)    AS mps_acao_tecnica_direcao_id, 
       ip.id                       AS mps_pontuacao_id
FROM judoca j
  INNER JOIN luta              l    ON (l.id_lutador1 = j.id)
  INNER JOIN luta_acao         la   ON (la.id_luta = l.id)
  INNER JOIN acao              a    ON (a.id = la.id_acao)
  INNER JOIN acao_tipo         at   ON (at.id = a.id_tipo)
  INNER JOIN mps_item          ia   ON (ia.id_tipo = 1 AND 
                                        ia.id_acao = a.id)
  LEFT  JOIN tecnica           t    ON (t.id = la.id_tecnica)
  LEFT  JOIN tecnica_direcao   td   ON (td.id = la.id_direcao)
  LEFT  JOIN mps_item          iatd ON (iatd.id_tipo IN (2, 3)          AND   
                                        iatd.id_acao    = la.id_acao    AND 
                                        iatd.id_tecnica = la.id_tecnica AND 
                                        iatd.id_direcao = COALESCE(la.id_direcao, 9))
  LEFT  JOIN tecnica_pontuacao tp   ON (tp.id = la.id_pontuacao)
  LEFT  JOIN mps_item          ip   ON (ip.id_tipo      = 4 AND 
                                        ip.id_pontuacao = tp.id)
  INNER JOIN judoca            j1   ON (j1.id = la.id_quem_faz)
  LEFT  JOIN judoca            j2   ON (j2.id = la.id_quem_recebe)
WHERE j.id = 3
  --AND l.id = 10
ORDER BY 1, 2
;


/**
* QUERY PARA OBTER O MAP DE TRADUCAO
*/
SELECT i.id, i.nome
FROM mps_item i
ORDER BY i.id
;


SELECT la.id_luta,
       la.sequencia,
       j1.nome AS judoca_faz,
       j2.nome AS judoca_recebe,
       a.nome AS acao,
       t.nome AS tecnica,
       CASE WHEN la.sequencia = 6 THEN NULL ELSE td.nome END AS direcao,
       tp.nome AS pontuacao
FROM luta_acao la
INNER JOIN judoca j1 ON (j1.id = la.id_quem_faz)
INNER JOIN judoca j2 ON (j2.id = la.id_quem_recebe)
INNER JOIN acao a ON (a.id = la.id_acao)
LEFT  JOIN tecnica t ON (t.id = la.id_tecnica)
LEFT  JOIN tecnica_direcao td ON (td.id = la.id_direcao)
LEFT  JOIN tecnica_pontuacao tp ON (tp.id = la.id_pontuacao)
WHERE la.id_luta = 4
--WHERE la.id_pontuacao IS NOT NULL
;

SELECT mi.id,
       mi.nome,
       mit.nome AS tipo_acao,
       a.nome AS acao,
       t.nome AS tecnica,
       td.nome AS direcao,
       tp.nome AS pontuacao
FROM mps_item mi
INNER JOIN mps_item_tipo mit ON (mit.id = mi.id_tipo)
INNER JOIN acao a ON (a.id = mi.id_acao)
LEFT JOIN tecnica t ON (t.id = mi.id_tecnica)
LEFT JOIN tecnica_direcao td ON (td.id = mi.id_direcao)
LEFT JOIN tecnica_pontuacao tp ON (tp.id = mi.id_pontuacao)
;