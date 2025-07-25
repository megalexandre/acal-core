# Core API - Projeto Spring Boot com MongoDB e Cucumber

Este projeto é uma API desenvolvida em Kotlin utilizando Spring Boot, MongoDB e testes BDD com Cucumber.

## Funcionalidades
- Cadastro de clientes
- Consulta de clientes
- Testes automatizados BDD com Cucumber

## Tecnologias Utilizadas
- **Kotlin**
- **Spring Boot**
- **MongoDB** (com Docker)
- **Cucumber** (BDD)
- **RestAssured** (para testes de API)

## Como rodar o projeto

### 1. Suba o MongoDB com Docker Compose

Edite o arquivo `compose.yaml` se necessário e execute:

```sh
docker compose up -d
```

O MongoDB estará disponível em `localhost:27017` com:
- Usuário: `root`
- Senha: `secret` (ou `example`, conforme seu compose.yaml)
- Database: `acal`

### 2. Configure a aplicação

No arquivo `src/main/resources/application.yml`, a URI do MongoDB deve estar assim:

```
spring:
  data:
    mongodb:
      uri: mongodb://root:secret@localhost:27017/acal?authSource=admin
```

### 3. Rode a aplicação

```sh
./gradlew bootRun
```

A API estará disponível em `http://localhost:8080`.

### 4. Rodando os testes BDD (Cucumber)

Os testes estão em `src/test/resources/features` e os steps em `src/test/kotlin/acal/com/core/steps`.

Para rodar os testes:

```sh
./gradlew test
```

### 5. Estrutura dos testes Cucumber
- Features: `src/test/resources/features/*.feature`
- Steps: `src/test/kotlin/acal/com/core/steps/*Step.kt`
- Runner: `src/test/kotlin/acal/com/core/CucumberTestRunner.kt`
- Configuração Spring: `src/test/kotlin/acal/com/core/configuration/CucumberSpringConfiguration.kt`

### 6. Exemplo de uso da API

**Cadastro de cliente:**

```sh
curl --location 'localhost:8080/customer' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Alexandre",
    "identity_card": "03396885562",
    "phone_number": "71988872749",
    "partner_number": "1",
    "is_a_voter": true
}'
```

## Observações
- O projeto utiliza UUIDs baseados em tempo para identificação.
- O padrão de nomes dos campos JSON é snake_case.
- Os testes BDD estão em português.

## Contribuição
Pull requests são bem-vindos!

## Licença
MIT


docker exec -it mongo-db-acal mongosh --username root --password example --authenticationDatabase admin