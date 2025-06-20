#language: pt

Funcionalidade: Cadastro de Endereço

  Contexto:
    Dado o banco de dados está vazio

  Cenário: Criar endereço sem nome
    Quando eu envio um POST para "/address"
    """
    {
    }
    """
    Então o código da resposta deve ser 400
    E o corpo da resposta deve conter
    """
    {
      "status" : 400,
      "error" : "Bad Request",
      "message" : "Field 'name' is required.",
      "path" : "/address"
    }
    """

