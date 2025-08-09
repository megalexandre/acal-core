# language: pt

Funcionalidade: Cadastro de Residencia

  Cenário: Criar um novo Residencia

    Quando eu envio um POST para "/place"
    """
    {
      "number": "1",
      "letter": "a",
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