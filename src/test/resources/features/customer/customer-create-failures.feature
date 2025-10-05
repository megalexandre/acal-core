# language: pt

Funcionalidade: Cadastro de cliente

  Contexto:
    Dado o banco de dados está vazio

  Cenário: Criar um novo cliente sem nome
    Quando eu envio um POST para "/customer"
    """
    {
      "identity_card": "03396885562"
    }
    """
    Então o código da resposta deve ser 400
    E o corpo da resposta deve conter
    """
    {
      "status" : 400,
      "error" : "Bad Request",
      "message" : "Field 'name' is required.",
      "path" : "/customer"
    }
    """

  Cenário: Criar um novo cliente numero de identidade inválido
    Quando eu envio um POST para "/customer"
    """
    {
      "name": "Alexandre"
    }
    """
    Então o código da resposta deve ser 400
    E o corpo da resposta deve conter
    """
    {
      "status" : 400,
      "error" : "Bad Request",
      "message" : "Field 'identity_card' is required.",
      "path" : "/customer"
    }
    """
