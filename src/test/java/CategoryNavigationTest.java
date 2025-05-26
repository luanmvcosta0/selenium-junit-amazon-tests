//Casos de Teste: ID CT004

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryNavigationTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    void deveAcessarMaisVendidosEListarProdutos() {
        driver.get("https://www.amazon.com.br");

        // Clica na opção "Mais Vendidos"
        WebElement maisVendidos = wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Mais Vendidos")));
        maisVendidos.click();

        // Aguarda os produtos aparecerem
        List<WebElement> produtos = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("div.p13n-grid-content div.p13n-sc-uncoverable-faceout")));

        assertTrue(produtos.size() > 0, "Nenhum produto foi exibido em 'Mais Vendidos'.");

        System.out.println("Produtos - Mais Vendidos:");
        for (WebElement produto : produtos) {
            try {
                WebElement titulo = produto.findElement(By.cssSelector("._cDEzb_p13n-sc-css-line-clamp-3_g3dy1"));
                System.out.println("- " + titulo.getText());
            } catch (NoSuchElementException e) {
                // Produto sem título visível, ignora
            }
        }
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}