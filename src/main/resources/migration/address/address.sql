ALTER TABLE endereco
ADD COLUMN uuid CHAR(36);

UPDATE endereco
SET uuid = UUID()
WHERE uuid IS NULL;

ALTER TABLE endereco
MODIFY uuid CHAR(36) NOT NULL;

ALTER TABLE endereco
ADD CONSTRAINT unique_uuid UNIQUE (uuid);

select
	uuid as id,
	concat(trim(tipo),' ',trim(nome)) as name
from endereco
order by tipo, nome

