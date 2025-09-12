WITH dados AS (
  SELECT
    IFNULL(NULLIF(REGEXP_SUBSTR(ep.numero, '^[0-9]+'), ''), '1000') AS number,
    COALESCE(NULLIF(TRIM(REGEXP_REPLACE(ep.numero, '^[0-9]+[- ]*', '')), ''), 'A') AS letter,
    ep.uuid AS uuid,
    CONCAT(TRIM(tipo), ' ', TRIM(nome)) AS name
  FROM enderecopessoa ep
  INNER JOIN endereco e ON e.id = ep.idEndereco
),
numerados AS (
  SELECT
    d.*,
    ROW_NUMBER() OVER (PARTITION BY name, number, letter ORDER BY uuid) AS rn
  FROM dados d
)
SELECT
  uuid as id,
  name,
  number,
  CONCAT(letter, REPEAT('A', rn - 1)) AS letter
FROM numerados
ORDER BY name asc, number asc, letter asc;


