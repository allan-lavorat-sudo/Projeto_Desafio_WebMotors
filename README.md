# Projeto_Desafio_WebMotors

Descrição
---------

Repositório com os artefatos entregues para o desafio WebMotors: cenários de teste em formato BDD, automação de interface (Selenium + Cucumber) e testes de API (coleção Postman).

Objetivo do desafio
--------------------

Validar os filtros de busca da página de resultados da WebMotors (Marca, Modelo e Versão) e a listagem de estoque de uma loja específica. Os testes automatizados devem cobrir validações de tela e de API, garantindo que a marca `Honda` e o modelo `City` sejam utilizados como cenário obrigatório.

Principais tecnologias
---------------------

- Java 11
- Maven
- Selenium WebDriver
- Cucumber (BDD)
- WebDriverManager (Gerenciar driver do navegador)
- Postman (coleção de API) — arquivo em `postman/`
- (Opcional) Newman para executar a coleção Postman via CLI

Estrutura do projeto
--------------------

- `features/` : arquivos .feature com cenários BDD (ex.: `search_filters.feature`)
- `src/test/java/com/webmotors/tests/` : step definitions e runner Cucumber (Selenium)
- `pom.xml` : configuração Maven e dependências
- `postman/` : `WebMotors_API_Collection.json` (coleção com requisições e testes)
- `README.md` : (este arquivo)

Pré-requisitos
--------------

- Java 11 ou superior instalado e `JAVA_HOME` configurado
- Maven instalado e disponível no `PATH`
- Google Chrome instalado (os testes usam ChromeDriver)
- Acesso à internet para baixar dependências e acessar APIs/UI
- (Opcional) Node.js + npm caso queira executar a coleção Postman com `newman`

Instalação e preparação
-----------------------

1. Clone este repositório (ou use a pasta entregue):

```bash
git clone https://github.com/allan-lavorat-sudo/Projeto_Desafio_WebMotors.git
cd Projeto_Desafio_WebMotors
```

2. Verifique se `JAVA_HOME` e `mvn` funcionam:

```bash
java -version
mvn -v
```

Como executar os testes automatizados
------------------------------------

1) Testes de UI (Selenium + Cucumber)

- Para executar todos os testes Cucumber/Selenium:

```bash
mvn test
```

- Observações:
	- O projeto usa `WebDriverManager` para baixar automaticamente o ChromeDriver.
	- Os step definitions estão em `src/test/java/com/webmotors/tests/StepDefinitions.java`.
	- Os cenários BDD estão em `features/search_filters.feature`.

2) Testes de API (Postman)

- Importe `postman/WebMotors_API_Collection.json` no Postman e execute a coleção.
- Para executar via CLI com o `newman` (opcional):

```bash
npm install -g newman
newman run postman/WebMotors_API_Collection.json
```

Critérios de avaliação e boas práticas adotadas
---------------------------------------------

- A automação contempla cenários BDD para tornar os requisitos legíveis a stakeholders.
- Separação entre cenários (`features`) e implementação (`StepDefinitions`).
- Dependências gerenciadas pelo Maven (`pom.xml`).
- Mensagens de commit claras e atômicas (ex.: `feat(ui): ...`, `docs: ...`).
- Uso de `WebDriverManager` para evitar dependências manuais de driver.
- Testes de API incluem validações de status e conteúdo (Postman tests).

Contribuindo
------------

1. Abra um fork ou clone do repositório.
2. Crie uma branch para suas mudanças: `git checkout -b feat/descrição`
3. Faça commits pequenos e com mensagens claras.
4. Abra um pull request descrevendo as alterações.

Licença
-------

Este repositório não contém uma licença explícita. Caso deseje aplicar uma licença, adicione um arquivo `LICENSE` com os termos desejados (por exemplo MIT, Apache-2.0, etc.).

Notas finais
------------

Se precisar que eu gere relatórios JUnit/HTML, melhore seletores dos steps para maior robustez, ou adicione integração contínua (GitHub Actions), posso implementar as próximas etapas mediante sua autorização.
