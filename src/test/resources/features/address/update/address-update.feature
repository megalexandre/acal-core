# language: pt

Funcionalidade: Endereço

  Cenário: Atualizar um endereço por id

    Dado o endereço está cadastrado
      | id | name                    |
      |  85206725-55ff-11f0-970c-9b31d99c4574 | Avenida Fernando Daltro |

    Quando eu envio um PUT para "/address"
    """
    {
      "id": "85206725-55ff-11f0-970c-9b31d99c4574",
      "name": "Novo nome"
    }
    """
    Então o código da resposta deve ser 200
    E o corpo da resposta deve conter
    """
    {
      "id": "85206725-55ff-11f0-970c-9b31d99c4574",
      "name": "Novo nome"
    }
    """

