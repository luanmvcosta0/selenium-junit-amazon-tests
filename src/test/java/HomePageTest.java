//Casos de Teste: ID CT028

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    void devePesquisarProdutoEVotarParaHome() {
        driver.get("https://www.amazon.com.br");

        // Pesquisa um produto
        WebElement campoBusca = wait.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));
        campoBusca.sendKeys("Clean Code");
        campoBusca.submit();

        // Aguarda os resultados da busca
        WebElement resultado = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.a-color-state")));
        assertTrue(resultado.isDisplayed(), "Resultado de busca não foi exibido.");

        // Clica no logo da Amazon para voltar à home
        WebElement logoAmazon = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-logo-sprites")));
        logoAmazon.click();

        // Verifica se voltou à página inicial (campo de busca visível e URL correta)
        wait.until(ExpectedConditions.urlToBe("https://www.amazon.com.br/"));
        WebElement campoBuscaHome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
        assertTrue(campoBuscaHome.isDisplayed(), "Não voltou para a página inicial.");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}