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

  @ignore
  Cenário: Salvar um cliente com número de identidade duplicado

    o número de identidade é único e não deve ser duplicado

    Dado o cliente está cadastrado
      | id | name      | identity_card | phone_number | partner_number | voter |
      | 08bf9438-47de-11f0-9466-63b77fba1b3e  | Alexandre | 03396885562   | 71988872749  | 1              | true  |

    Quando eu envio um POST para "/customer"
    """
    {
      "name": "Alexandre",
      "identity_card": "03396885562",
      "phone_number": "71988872749",
      "partner_number": "1",
      "voter": true
    }
    """
    Então o código da resposta deve ser 400
