# language: pt

Funcionalidade: Cadastro de Endereço

  Contexto:
    Dado o banco de dados está vazio

  Cenário: Criar um novo endereço

    Quando eu envio um POST para "/address"
    """
    {
      "name": "Avenida Fernando Daltro"
    }
    """
    Então o código da resposta deve ser 201
    E o corpo da resposta deve conter
    """
    {
      "name" : "Avenida Fernando Daltro"
    }
    """

