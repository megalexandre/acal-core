#language: pt

Funcionalidade: Cadastro de Endereço

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

  Cenário: Criar um novo endereço

    Dado o endereço está cadastrado
      | id | name                    |
      |  85206725-55ff-11f0-970c-9b31d99c4574 | Avenida Fernando Daltro |

    Quando eu envio um POST para "/address"
    """
    {
      "name": "Avenida Fernando Daltro"
    }
    """
    Então o código da resposta deve ser 400
    E o corpo da resposta deve conter
    """
    {
      "name" : "Avenida Fernando Daltro"
    }
    """

