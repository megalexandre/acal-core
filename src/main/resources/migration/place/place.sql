ALTER TABLE enderecopessoa
ADD COLUMN uuid CHAR(36);

UPDATE enderecopessoa
SET uuid = UUID()
WHERE uuid IS NULL;

ALTER TABLE enderecopessoa
MODIFY uuid CHAR(36) NOT NULL;

ALTER TABLE enderecopessoa
ADD CONSTRAINT unique_uuid UNIQUE (uuid);

WITH dados AS (

	SELECT
	    IFNULL(NULLIF(REGEXP_SUBSTR(ep.numero, '^[0-9]+'), ''), '1000') AS number,
	    IFNULL(NULLIF(TRIM(REGEXP_REPLACE(ep.numero, '^[0-9]+[- ]*', '')), ''), 'A') AS letter,
	    e.uuid AS id,
	    CONCAT(TRIM(tipo), ' ', TRIM(nome)) AS address
  	FROM enderecopessoa ep
	INNER JOIN endereco e ON e.id = ep.idEndereco
),
numerados AS (
  SELECT
    d.*,
    ROW_NUMBER() OVER (PARTITION BY address, number, letter ORDER BY id) AS rn
  FROM dados d
)
SELECT
  id,
  address,
  number,
  -- adiciona 'A' a mais de acordo com o número de ocorrência
  CONCAT(letter, REPEAT('A', rn - 1)) AS letter
FROM numerados
ORDER BY address, number;

