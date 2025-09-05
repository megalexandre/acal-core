# language: pt

Funcionalidade: Livro de caixa

  Contexto:
    Dado o banco de dados está vazio

  Cenário: Gerar a Paginação do livro de caixa

    Quando eu envio um POST para "/financial-record/paginate"
    """
    {
    }
    """
    Então o código da resposta deve ser 200
    E o corpo da resposta deve conter
    """
    {
      "content": [],
      "pageable": {
          "page_number": 0,
          "page_size": 20,
          "sort": {
              "empty": true,
              "sorted": false,
              "unsorted": true
          },
          "offset": 0,
          "paged": true,
          "unpaged": false
      },
      "last": true,
      "total_elements": 0,
      "total_pages": 0,
      "first": true,
      "size": 20,
      "number": 0,
      "sort": {
          "empty": true,
          "sorted": false,
          "unsorted": true
      },
      "number_of_elements": 0,
      "empty": true
    }
    """