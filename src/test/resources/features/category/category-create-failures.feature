# language: pt

Funcionalidade: Cadastro de Categorias

  Contexto:
    Dado o banco de dados está vazio

  Cenário: Criar um novo categoria sem nome

    Quando eu envio um POST para "/category"
    """
    {
      "water_value": 10,
      "partner_value": 20,
      "group": "EFFECTIVE",
      "is_hydrometer": true
    }
    """
    Então o código da resposta deve ser 400
    E o corpo da resposta deve conter
    """
    {
      "status" : 400,
      "error" : "Bad Request",
      "message" : "Field 'name' is required.",
      "path" : "/category"
    }
    """

  Cenário: Criar um novo categoria sem o valor da agua

    Quando eu envio um POST para "/category"
    """
    {
      "name": "Alexandre",
      "partner_value": 20,
      "group": "EFFECTIVE",
      "is_hydrometer": true
    }
    """
    Então o código da resposta deve ser 400
    E o corpo da resposta deve conter
    """
    {
      "status" : 400,
      "error" : "Bad Request",
      "message" : "Field 'water_value' is required.",
      "path" : "/category"
    }
    """

  Cenário: Criar um novo categoria sem o valor do parceiro

    Quando eu envio um POST para "/category"
    """
    {
      "name": "Alexandre",
      "water_value": 10,
      "group": "EFFECTIVE",
      "is_hydrometer": true
    }
    """
    Então o código da resposta deve ser 400
    E o corpo da resposta deve conter
    """
    {
      "status" : 400,
      "error" : "Bad Request",
      "message" : "Field 'partner_value' is required.",
      "path" : "/category"
    }
    """

  Cenário: Criar um novo categoria sem o valor do parceiro

    Quando eu envio um POST para "/category"
    """
    {
      "name": "Alexandre",
      "water_value": 10,
      "partner_value": 20,
      "is_hydrometer": true
    }
    """
    Então o código da resposta deve ser 400
    E o corpo da resposta deve conter
    """
    {
      "status" : 400,
      "error" : "Bad Request",
      "message" : "Field 'group' is required.",
      "path" : "/category"
    }
    """