# language: pt

Funcionalidade: Cadastro de Categorias

  Contexto:
    Dado o banco de dados está vazio

  Cenário: Criar um novo categoria com os mininos campos obrigatórios

    Quando eu envio um POST para "/category"
    """
    {
      "name": "Alexandre",
      "water_value": 10,
      "partner_value": 20,
      "group": "EFFECTIVE"
    }
    """
    Então o código da resposta deve ser 201
    E o corpo da resposta deve conter
    """
    {
      "name" : "Alexandre",
      "water_value" : "10.00",
      "partner_value" : "20.00",
      "total" : "30.00",
      "group" : "EFFECTIVE",
      "is_hydrometer" : false
    }
    """

  Cenário: Criar um novo categoria com os todos os campos

    Quando eu envio um POST para "/category"
    """
    {
      "id": "85206725-55ff-11f0-970c-9b31d99c4574",
      "name": "Alexandre",
      "water_value": 10,
      "partner_value": 20,
      "group": "EFFECTIVE",
      "is_hydrometer": true
    }
    """
    Então o código da resposta deve ser 201
    E o corpo da resposta deve conter
    """
    {
      "id" : "85206725-55ff-11f0-970c-9b31d99c4574",
      "name" : "Alexandre",
      "water_value" : "10.00",
      "partner_value" : "20.00",
      "total" : "30.00",
      "group" : "EFFECTIVE",
      "is_hydrometer" : true
    }
    """
