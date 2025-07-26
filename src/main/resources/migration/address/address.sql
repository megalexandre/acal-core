ALTER TABLE endereco
ADD COLUMN uuid CHAR(36);

UPDATE endereco
SET uuid = UUID()
WHERE uuid IS NULL;

ALTER TABLE endereco
MODIFY uuid CHAR(36) NOT NULL;

ALTER TABLE endereco
ADD CONSTRAINT unique_uuid UNIQUE (uuid);

SELECT
  MIN(uuid) as id,
  CONCAT(TRIM(tipo), ' ', TRIM(nome)) AS name
FROM endereco
GROUP BY CONCAT(TRIM(tipo), ' ', TRIM(nome))
ORDER BY name;
