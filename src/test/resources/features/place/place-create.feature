# language: pt

Funcionalidade: Cadastro de Residencia

  Contexto:
    zo banco de dados está vazio

  Cenário: Criar um novo Residencia com o minimo de informações

    Quando eu envio um POST para "/place"
    """
    {
      "number": "1",
      "address": {
        "id": "85206725-55ff-11f0-970c-9b31d99c4574",
        "name": "Avenida Fernando Daltro"
      }
    }
    """
    Então o código da resposta deve ser 201
    E o corpo da resposta deve conter
    """
    {
      "number" : "1",
      "address" : {
        "id" : "85206725-55ff-11f0-970c-9b31d99c4574",
        "name" : "Avenida Fernando Daltro"
      }
    }
    """

  Cenário: Criar um novo Residência com todos os campos

    Quando eu envio um POST para "/place"
    """
    {
      "id":"71fd42b7-762f-11f0-a68f-22497372fd86",
      "name": "posto de gasolina",
      "number": "1",
      "letter": "A",
      "address": {
        "id": "85206725-55ff-11f0-970c-9b31d99c4574",
        "name": "Avenida Fernando Daltro"
      }
    }
    """
    Então o código da resposta deve ser 201
    E o corpo da resposta deve conter
    """
    {
      "id":"71fd42b7-762f-11f0-a68f-22497372fd86",
      "name": "posto de gasolina",
      "number": "1",
      "letter": "A",
      "address": {
        "id": "85206725-55ff-11f0-970c-9b31d99c4574",
        "name": "Avenida Fernando Daltro"
      }
    }
    """