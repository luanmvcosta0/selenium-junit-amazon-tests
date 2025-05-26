// Casos de teste ID: CT031

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BestSellersTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    void deveIrParaPaginaMaisVendidos() {
        driver.get("https://www.amazon.com.br");

        // Abre o menu
        WebElement botaoMenu = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-hamburger-menu")));
        botaoMenu.click();

        // Espera o item "Mais Vendidos" aparecer e clica nele
        WebElement linkMaisVendidos = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[normalize-space()='Mais Vendidos']")
        ));
        linkMaisVendidos.click();

        // Espera redirecionar para a página correta
        wait.until(ExpectedConditions.urlContains("bestsellers"));

        // Verifica se o redirecionamento foi correto
        String urlAtual = driver.getCurrentUrl();
        assertTrue(urlAtual.contains("bestsellers"), "Não foi redirecionado para a página de Mais Vendidos.");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}