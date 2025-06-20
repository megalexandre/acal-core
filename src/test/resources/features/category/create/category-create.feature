# language: pt

Funcionalidade: Cadastro de Categorias

  Contexto:
    Dado o banco de dados está vazio

  Cenário: Criar um novo categoria

    Quando eu envio um POST para "/category"
    """
    {
      "name": "Alexandre",
      "water_value": 10,
      "partner_value": 20,
      "group": "FOUNDER",
      "is_hydrometer": true
    }
    """
    Então o código da resposta deve ser 201

