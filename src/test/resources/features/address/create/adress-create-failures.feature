#language: pt

Funcionalidade: Cadastro de Endereço

  Contexto:
    Dado o banco de dados está vazio

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

  Cenário: Salvar um endereço com nome duplicado
    o nome do endereço é único e não deve ser duplicado
    Dado o endereço está cadastrado
      | id                                   | name                    |
      | 08bf9438-47de-11f0-9466-63b77fba1b3e | Avenida Fernando Daltro |
    Quando eu envio um POST para "/address"
    """
    {
      "name": "Avenida Fernando Daltro"
    }
    """
    Então o código da resposta deve ser 400
