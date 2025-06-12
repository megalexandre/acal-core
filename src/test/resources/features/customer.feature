# language: pt

Funcionalidade: Cadastro de cliente

  Cenário: Criar um novo cliente

    Quando eu envio um POST para "/customer"
    """
    {
      "name": "Alexandre1",
      "identity_card": "03396885562",
      "phone_number": "71988872749",
      "partner_number": "1",
      "voter": true
    }
    """

    Então a resposta deve ser 201
