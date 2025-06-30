# language: pt

Funcionalidade: Endereço

  Contexto:
    Dado o banco de dados está vazio

  Cenário: Deletar um endereço por id

    Dado o endereço está cadastrado
      | id | name                    |
      |  85206725-55ff-11f0-970c-9b31d99c4574 | Avenida Fernando Daltro |

    Quando eu envio um DELETE para "/address/85206725-55ff-11f0-970c-9b31d99c4574"
    Então o código da resposta deve ser 200
    E o banco de dados não deve conter o endereço

  Cenário: Deletar um endereço por id quando ele não existe

    Quando eu envio um DELETE para "/address/85206725-55ff-11f0-970c-9b31d99c4574"
    Então o código da resposta deve ser 404
    E o banco de dados não deve conter o endereço

