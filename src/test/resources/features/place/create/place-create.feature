# language: pt

Funcionalidade: Cadastro de Local

  Cenário: Criar um novo Local

    Quando eu envio um POST para "/place"
    """
    {
      "number": "1",
      "letter": "a"
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