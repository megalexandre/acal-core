SELECT
    nome,
    CASE
        WHEN COALESCE(REPLACE(REPLACE(REPLACE(cpf, '.', ''), '-', ''), ' ', ''), '') = ''
        THEN COALESCE(REPLACE(REPLACE(REPLACE(REPLACE(cnpj, '.', ''), '-', ''), ' ', ''), '/',''),'')
        ELSE COALESCE(REPLACE(REPLACE(REPLACE(cpf, '.', ''), '-', ''), ' ', ''), '')
    END AS identity_card,
    'true' AS voter
FROM pessoa
	where cnpj not like "%141975860001"

order by nome


;