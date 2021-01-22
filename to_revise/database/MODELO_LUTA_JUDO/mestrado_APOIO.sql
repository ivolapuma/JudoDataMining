SELECT at.nome, a.nome, COUNT(1)
FROM luta_acao la
INNER JOIN judoca j ON (j.id = la.id_quem_faz)
INNER JOIN acao a ON (a.id = la.id_acao)
INNER JOIN acao_tipo at ON (at.id = a.id_tipo)
WHERE j.nome = 'SCHMITT'
GROUP BY at.nome, a.nome
ORDER BY 3 DESC
;

SELECT at.nome, a.nome, t.nome, td.nome, COUNT(1)
FROM luta_acao la
INNER JOIN judoca j ON (j.id = la.id_quem_faz)
INNER JOIN acao a ON (a.id = la.id_acao)
INNER JOIN acao_tipo at ON (at.id = a.id_tipo)
INNER JOIN tecnica t ON (t.id = la.id_tecnica)
INNER JOIN tecnica_direcao td ON (td.id = la.id_direcao)
WHERE j.nome = 'SCHMITT'
GROUP BY at.nome, a.nome, t.nome, td.nome
ORDER BY 5 DESC
;

select i.id,
       tp.id,
       tp.nome
from mps_item i
  inner join tecnica_pontuacao tp on (tp.id = i.id_pontuacao)
;

select i.id, a.id, a.nome, t.id, t.nome, td.id, td.nome, tp.id, tp.nome
from mps_item i
  left join acao a on (a.id = i.id_acao)
  left join tecnica t on (t.id = i.id_tecnica)
  left join tecnica_direcao td on (td.id = i.id_direcao)
  left join tecnica_pontuacao tp on (tp.id = i.id_pontuacao)
where i.id in (1, 4, 9)
order by i.id
;

select * from luta ;

-- judocas x no. lutas
select j.id, j.nome, count(1) 
from luta l inner join judoca j on (j.id = l.id_lutador1)
group by j.id, j.nome
order by 3 desc
;

select la.*
from luta_acao la
where la.id_luta = 8
order by la.sequencia
;

select la.id_acao, a.nome, count(1)
from luta_acao la
  inner join acao a on (a.id = la.id_acao)
where la.id_tecnica is not null
group by la.id_acao, a.nome
;

select * from acao_tipo at order by at.id ;

select at.id, at.nome, a.id, a.nome, count(1)
from luta_acao la
  inner join acao a on (a.id = la.id_acao)
  inner join acao_tipo at on (at.id = a.id_tipo)
where at.id in (6, 7, 9, 10)
group by at.id, at.nome, a.id, a.nome
order by at.id, a.id
;


select min(data_hora), max(data_hora) from luta ;

select l.*, j1.nome, j2.nome, c.nome
from luta l
  inner join judoca j1 on (j1.id = l.id_lutador1)
  inner join judoca j2 on (j2.id = l.id_lutador2)
  inner join competicao c on (c.id = l.id_competicao)
where j1.nome = 'BISCHOF'
;

SELECT j1.nome AS JUDOCA, 
       COUNT(DISTINCT l.id) AS TOTAL_LUTAS,
       COUNT(1) AS TOTAL_ACOES
FROM LUTA l
INNER JOIN LUTA_ACAO la ON (la.id_luta = l.id AND la.id_quem_faz = l.id_lutador1)
INNER JOIN JUDOCA j1 ON (j1.id = l.id_lutador1)
GROUP BY j1.nome
ORDER BY 2 DESC, 3 DESC, 1
;