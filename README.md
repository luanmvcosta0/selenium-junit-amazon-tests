# 🧪 Selenium + JUnit — Testes Automatizados na Amazon

Projeto acadêmico desenvolvido para a disciplina de **Teste de Software** (5º período) do curso de **Sistemas de Informação**.

O objetivo é aplicar na prática conceitos de **testes automatizados de interface (E2E)**, utilizando **Selenium WebDriver** com **JUnit 5** para simular interações reais de um usuário no site da [Amazon Brasil](https://www.amazon.com.br).

## 📋 Casos de Teste

Cada classe de teste corresponde a um caso de teste documentado no plano de testes da disciplina:

| ID | Classe | Cenário testado |
|----|--------|-----------------|
| CT001 | `AmazonSearchTest` | Pesquisar um produto na barra de busca e validar os resultados |
| CT004 | `CategoryNavigationTest` | Acessar a página de "Mais Vendidos" e listar os produtos |
| CT016 | `ClickLoginTest` | Acessar a tela de login da Amazon |
| CT026 | `ShoppingCartTest` | Acessar o carrinho de compras |
| CT027 | `CategoryTest` | Abrir a lista de categorias do menu |
| CT028 | `HomePageTest` | Pesquisar um produto e retornar para a página inicial |
| CT029 | `OrdersTest` | Acessar a área de "Devoluções e Pedidos" |
| — | `BestSellersTest` | Navegar até a página de "Mais Vendidos" |
| — | `OpenZipCodeTest` | Atualizar o CEP de entrega para um novo valor |
| — | `PrimeVideoTest` | Navegar até o Prime Video e selecionar uma série |

## 🛠️ Tecnologias utilizadas

- **Java 21**
- **JUnit 5 (Jupiter)** — estrutura e asserções dos testes
- **Selenium WebDriver 4.18** — automação do navegador
- **WebDriverManager** — gerenciamento automático do ChromeDriver
- **Maven** — gerenciamento de dependências e build (plugin Surefire para execução dos testes)

## 📁 Estrutura do projeto

```
selenium-junit-amazon-tests/
├── src/
│   ├── main/java/org/example/Main.java
│   └── test/java/
│       ├── AmazonSearchTest.java
│       ├── BestSellersTest.java
│       ├── CategoryNavigationTest.java
│       ├── CategoryTest.java
│       ├── ClickLoginTest.java
│       ├── HomePageTest.java
│       ├── OpenZipCodeTest.java
│       ├── OrdersTest.java
│       ├── PrimeVideoTest.java
│       └── ShoppingCartTest.java
├── chromedriver-win64/
└── pom.xml
```

## ▶️ Como executar

### Pré-requisitos

- Java 21+
- Maven
- Google Chrome instalado

### Rodando os testes

Clone o repositório e execute:

```bash
git clone https://github.com/luanmvcosta0/selenium-junit-amazon-tests.git
cd selenium-junit-amazon-tests
mvn test
```

Também é possível rodar uma classe específica:

```bash
mvn test -Dtest=AmazonSearchTest
```

Ou executar os testes diretamente pela IDE (IntelliJ IDEA), clicando em ▶️ ao lado de cada classe/método de teste.

## 🔍 Como os testes funcionam

Todos os testes seguem o mesmo padrão:

1. **`@BeforeEach`** — abre uma nova instância do Chrome maximizada e configura um `WebDriverWait` com espera explícita de até 30 segundos.
2. **`@Test`** — navega até a Amazon, interage com os elementos da página (busca, cliques, navegação) usando `ExpectedConditions` para aguardar os elementos, e valida o resultado com asserções do JUnit (`assertTrue`, etc).
3. **`@AfterEach`** — encerra o navegador com `driver.quit()`.

Em caso de falha, screenshots do erro podem ser salvos para análise (ex: `erro_test_*.png`).

## ⚠️ Observações

- Por serem testes E2E contra o site real da Amazon, alterações no layout/seletores do site podem quebrar os testes com o tempo — isso faz parte do aprendizado sobre a fragilidade e manutenção de testes de UI.
- A Amazon pode exibir CAPTCHAs ou variações de página dependendo da região/sessão, o que pode afetar a execução.

## 🎓 Contexto acadêmico

Projeto desenvolvido como avaliação da cadeira de **Teste de Software** — 5º período de **Sistemas de Informação**, com foco em:

- Elaboração de casos de teste
- Automação de testes funcionais de interface
- Uso de esperas explícitas e boas práticas com Selenium
- Ciclo de vida de testes com JUnit 5
