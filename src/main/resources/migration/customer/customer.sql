SELECT
    MIN(nome) AS name,
    CASE
        WHEN COALESCE(REPLACE(REPLACE(REPLACE(cpf, '.', ''), '-', ''), ' ', ''), '') = ''
        THEN COALESCE(REPLACE(REPLACE(REPLACE(REPLACE(cnpj, '.', ''), '-', ''), ' ', ''), '/',''),'')
        ELSE COALESCE(REPLACE(REPLACE(REPLACE(cpf, '.', ''), '-', ''), ' ', ''), '')
    END AS identity_card,
    'true' AS voter
FROM pessoa
GROUP BY identity_card
ORDER BY name;