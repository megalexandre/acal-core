# language: pt

Funcionalidade: Cadastro de cliente

  Cenário: Criar um novo cliente

    Dado o banco de dados está vazio
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

  Cenário: Buscar um cliente existente

    Dado o banco de dados está vazio
    Dado o cliente está cadastrado
      | id | name      | identity_card | phone_number | partner_number | voter |
      | 08bf9438-47de-11f0-9466-63b77fba1b3e  | Alexandre | 03396885562   | 71988872749  | 1              | true  |

    Quando eu envio um GET para "/customer"
    Então o código da resposta deve ser 200
    E o corpo da resposta deve conter
    """
    [
      {
        "id":"08bf9438-47de-11f0-9466-63b77fba1b3e",
        "name":"Alexandre",
        "identity_card":"033.968.855-62",
        "phone_number":"(71) 98887-2749",
        "partner_number":"1",
        "voter":true
      }
    ]
    """
