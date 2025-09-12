ALTER TABLE categoriasocio
ADD COLUMN uuid VARCHAR2(36);

UPDATE categoriasocio
SET uuid = UUID()
WHERE uuid IS NULL;

ALTER TABLE categoriasocio
MODIFY uuid VARCHAR2(36) NOT NULL;

ALTER TABLE categoriasocio
ADD CONSTRAINT unique_uuid UNIQUE (uuid);

SELECT
	uuid as id,
  cs.nome AS name,
  CASE 
    WHEN g.id = 1 THEN 'FOUNDER'
    WHEN g.id = 2 THEN 'EFFECTIVE'
    WHEN g.id = 3 THEN 'TEMPORARY'	
  END AS 'group',
  t.valor AS water_value, 
  coalesce(t.valor_socio, 0) AS partner_value,
  CASE 
    WHEN lower((cs.nome)) COLLATE latin1_general_ci LIKE lower('%hidr%') THEN 'TRUE'
    ELSE 'FALSE'
  END AS is_hydrometer	
FROM categoriasocio cs 
INNER JOIN grupo g ON g.id = cs.group_id
INNER JOIN taxa t ON t.id = cs.taxasId;