ALTER TABLE pessoa
ADD COLUMN uuid CHAR(36);

UPDATE pessoa
SET uuid = UUID()
WHERE uuid IS NULL;

ALTER TABLE pessoa
MODIFY uuid CHAR(36) NOT NULL;

ALTER TABLE pessoa
ADD CONSTRAINT unique_uuid UNIQUE (uuid);
WITH dados AS (
  SELECT
    uuid,
    MIN(TRIM(nome)) AS name,
    CASE
      WHEN COALESCE(REPLACE(REPLACE(REPLACE(cpf, '.', ''), '-', ''), ' ', ''), '') = ''
      THEN COALESCE(REPLACE(REPLACE(REPLACE(REPLACE(cnpj, '.', ''), '-', ''), ' ', ''), '/',''),'')
      ELSE COALESCE(REPLACE(REPLACE(REPLACE(cpf, '.', ''), '-', ''), ' ', ''), '')
    END AS identity_card
  FROM pessoa
  GROUP BY uuid, cpf, cnpj, nome
),
duplicados AS (
  SELECT identity_card
  FROM dados
  GROUP BY identity_card
  HAVING COUNT(*) = 1
)
SELECT
  d.uuid AS id,
  d.name,
  d.identity_card,
  'true' AS voter
FROM dados d
JOIN duplicados dup ON d.identity_card = dup.identity_card
ORDER BY d.name;