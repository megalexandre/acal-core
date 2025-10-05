# language: pt

Funcionalidade: Cadastro de Categorias

  Contexto:
    Dado o banco de dados está vazio

  Cenário: Criar um novo categoria

    Quando eu envio um POST para "/category"
    """
    {
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
      "status" : 400,
      "error" : "Bad Request",
      "message" : "Field 'name' is required.",
      "path" : "/category"
    }
    """
