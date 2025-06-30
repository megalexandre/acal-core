# language: pt

Funcionalidade: Endereço

  Contexto:
    Dado o banco de dados está vazio

  Cenário: Recuperar um endereço por id

    Dado o endereço está cadastrado
      | id | name                    |
      |  85206725-55ff-11f0-970c-9b31d99c4574 | Avenida Fernando Daltro |

    Quando eu envio um GET para "/address/85206725-55ff-11f0-970c-9b31d99c4574"
    Então o código da resposta deve ser 200
    E o corpo da resposta deve conter
    """
    {
      "id": "85206725-55ff-11f0-970c-9b31d99c4574",
      "name": "Avenida Fernando Daltro"
    }
    """

