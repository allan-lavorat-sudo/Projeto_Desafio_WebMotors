Feature: Validar filtros de busca e estoque de loja

  Scenario: Filtrar por Marca Honda e Modelo City na página de resultados
    Given que eu estou na página de resultado de busca
    When eu seleciono a marca "Honda"
    And eu seleciono o modelo "City"
    Then os resultados devem conter veículos da marca "Honda" e modelo "City"

  Scenario: Validar seleção de Versão (se disponível)
    Given que eu selecionei a marca "Honda" e o modelo "City"
    When eu seleciono a versão disponível
    Then os resultados devem filtrar pela versão selecionada

  Scenario: Visualizar estoque de loja por IdRevendedor
    Given que eu acesso o estoque da loja com IdRevendedor 3834764
    Then devo ver listagem de veículos pertencentes à loja
