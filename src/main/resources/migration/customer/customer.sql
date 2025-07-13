ALTER TABLE pessoa
ADD COLUMN uuid CHAR(36);

UPDATE pessoa
SET uuid = UUID()
WHERE uuid IS NULL;

ALTER TABLE pessoa
MODIFY uuid CHAR(36) NOT NULL;

ALTER TABLE pessoa
ADD CONSTRAINT unique_uuid UNIQUE (uuid);

SELECT
	uuid as id,
    MIN( trim(nome)) AS name,
    CASE
        WHEN COALESCE(REPLACE(REPLACE(REPLACE(cpf, '.', ''), '-', ''), ' ', ''), '') = ''
        THEN COALESCE(REPLACE(REPLACE(REPLACE(REPLACE(cnpj, '.', ''), '-', ''), ' ', ''), '/',''),'')
        ELSE COALESCE(REPLACE(REPLACE(REPLACE(cpf, '.', ''), '-', ''), ' ', ''), '')
    END AS identity_card,
    'true' AS voter
FROM pessoa
GROUP BY identity_card, nome, uuid
ORDER BY name;