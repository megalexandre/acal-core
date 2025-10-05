# language: pt

Funcionalidade: Deleção de Categorias

  Contexto:
    Dado o banco de dados está vazio

  Cenário: Deleta uma categoria

    Quando a categoria está cadastrada
      | id                                   | group     | name      | water_value | partner_value | is_hydrometer |
      | 85206725-55ff-11f0-970c-9b31d99c4574 | EFFECTIVE | Alexandre | 10          | 10            | true          |

    Quando eu envio um DELETE para "/category/85206725-55ff-11f0-970c-9b31d99c4574"
    Então o código da resposta deve ser 200

