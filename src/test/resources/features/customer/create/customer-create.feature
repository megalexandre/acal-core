# language: pt

Funcionalidade: Cadastro de cliente

  Contexto:
    Dado o banco de dados está vazio

  Cenário: Criar um novo cliente com os campos obrigatórios

    Quando eu envio um POST para "/customer"
    """
    {
      "name": "Alexandre",
      "identity_card": "03396885562"
    }
    """
    Então o código da resposta deve ser 201
    E o corpo da resposta deve conter
    """
    {
      "name" : "Alexandre",
      "identity_card" : "033.968.855-62",
      "phone_number" : null,
      "partner_number" : null,
      "voter" : true
    }
    """
    E a base de dados deve conter um cliente
      |  | name      | identity_card | phone_number | partner_number | voter |
      |  | Alexandre | 03396885562   | null         | null           | true  |

  Cenário: Criar um novo cliente com todos os campos

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
    Então o código da resposta deve ser 201
    E o corpo da resposta deve conter
    """
    {
      "name" : "Alexandre",
      "identity_card" : "033.968.855-62",
      "phone_number" : "(71) 98887-2749",
      "partner_number" : 1,
      "voter" : true
    }
    """
    E a base de dados deve conter um cliente
      |  | name      | identity_card | phone_number | partner_number | voter |
      |  | Alexandre | 03396885562   | 71988872749  | 1              | true  |

