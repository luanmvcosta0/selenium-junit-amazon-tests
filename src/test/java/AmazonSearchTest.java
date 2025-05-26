//Casos de Teste: ID CT001

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AmazonSearchTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // espera de até 30 segundos
    }

    @Test
    void devePesquisarProdutoNaAmazon() {
        driver.get("https://www.amazon.com.br");

        WebElement campoBusca = wait.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));
        campoBusca.sendKeys("Clean Code");
        campoBusca.submit();

        // Espera o resultado da busca aparecer
        WebElement resultado = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.a-color-state")));
        assertTrue(resultado.isDisplayed(), "Resultado de busca não foi exibido.");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}