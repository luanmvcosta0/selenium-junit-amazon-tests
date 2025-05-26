import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrdersTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    void deveAcessarDevolucoesEPedidos() {
        driver.get("https://www.amazon.com.br");

        // Clicar no botão "Devoluções e Pedidos"
        WebElement devolucoesEPedidos = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-orders")));
        devolucoesEPedidos.click();

        // Verifica se a URL mudou para a página de pedidos
        wait.until(ExpectedConditions.urlContains("your-orders"));

        // Verifica se algum elemento da página de pedidos está visível (ex: filtro por período)
        WebElement filtroPeriodo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("orderFilter")));
        assertTrue(filtroPeriodo.isDisplayed(), "Página de pedidos não foi carregada corretamente.");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}