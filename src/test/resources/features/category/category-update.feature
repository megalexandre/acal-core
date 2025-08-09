# language: pt

Funcionalidade: Atualização de Categorias

  Contexto:
    Dado o banco de dados está vazio

  Cenário: Atualizar uma categoria

    Quando a categoria está cadastrada
      | id                                   | group     | name      | water_value | partner_value | is_hydrometer |
      | 85206725-55ff-11f0-970c-9b31d99c4574 | EFFECTIVE | Alexandre | 10          | 10            | true          |

    Quando eu envio um PUT para "/category"
    """
    {
      "id": "85206725-55ff-11f0-970c-9b31d99c4574",
      "name": "Alexandre2",
      "water_value": 10.00,
      "partner_value": 20.00,
      "group": "FOUNDER",
      "is_hydrometer": true
    }
    """
    Então o código da resposta deve ser 200
    Então o corpo da resposta deve conter
    """
    {
      "id":"85206725-55ff-11f0-970c-9b31d99c4574",
      "name":"Alexandre2",
      "water_value":"10.00",
      "partner_value":"20.00",
      "total":"30.00",
      "group":"FOUNDER",
      "is_hydrometer":true
    }
    """
    Então o banco deve possuir 1 categorias
