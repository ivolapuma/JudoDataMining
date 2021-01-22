-- sequence
DROP SEQUENCE mps_item_id ;
CREATE SEQUENCE mps_item_id
  INCREMENT BY 1
  MINVALUE 1 
  NO MAXVALUE
  START WITH 1 
  NO CYCLE
;

DELETE FROM MPS_ITEM ;
DELETE FROM MPS_ITEM_TIPO ;

-- tipos de item MPS
INSERT INTO MPS_ITEM_TIPO VALUES (1, 'ACAO') ;
INSERT INTO MPS_ITEM_TIPO VALUES (2, 'ATAQUE+TECNICA+DIRECAO') ;
INSERT INTO MPS_ITEM_TIPO VALUES (3, 'FINTA+TECNICA+DIRECAO') ;
INSERT INTO MPS_ITEM_TIPO VALUES (4, 'PONTUACAO') ;

-- itens MPS - ACAO
INSERT INTO MPS_ITEM
 (id, 
  nome,
  id_tipo, 
  id_acao)
SELECT
  NEXTVAL('mps_item_id'),
  a.nome,
  1,
  a.id
FROM ACAO a
ORDER BY a.id
;

-- itens MPS - ATAQUE+TECNICA+DIRECAO
INSERT INTO MPS_ITEM
 (id, 
  nome,
  id_tipo,
  id_acao, 
  id_tecnica,
  id_direcao)
SELECT
  NEXTVAL('mps_item_id'),
  'ATAQUE'||'+'||t.nome||'+'||td.nome,
  2,
  17, -- acao ATAQUE
  t.id,
  td.id
FROM TECNICA t, TECNICA_DIRECAO td
ORDER BY t.id, td.id
;

-- itens MPS - FINTA+TECNICA+DIRECAO
INSERT INTO MPS_ITEM
 (id, 
  nome,
  id_tipo,
  id_acao, 
  id_tecnica,
  id_direcao)
SELECT
  NEXTVAL('mps_item_id'),
  'FINTA'||'+'||t.nome||'+'||td.nome,
  3,
  36, -- acao FINTA
  t.id,
  td.id
FROM TECNICA t, TECNICA_DIRECAO td
ORDER BY t.id, td.id
;

-- itens MPS - PONTUACAO
INSERT INTO MPS_ITEM
 (id,
  nome, 
  id_tipo, 
  id_pontuacao)
SELECT
  NEXTVAL('mps_item_id'),
  tp.nome,
  4,
  tp.id
FROM TECNICA_PONTUACAO tp
ORDER BY tp.id
;

SELECT * FROM MPS_ITEM ;

