# language: pt

Funcionalidade: Cadastro de Residencia

  Cenário: Criar um novo Residencia

    Quando eu envio um POST para "/place"
    """
    {
      "number": "1",
      "letter": "a",
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