//Casos de Teste: ID CT026

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShoppingCartTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void testAcessarCarrinhoCompras() {
        // 1. Acessar a página da Amazon
        driver.get("https://www.amazon.com.br");

        // 2. Clicar no ícone do carrinho de compras
        WebElement carrinhoIcon = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("nav-cart")));
        carrinhoIcon.click();

        // 3. Verificar se a página do carrinho foi carregada corretamente
        WebElement tituloCarrinho = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//h1[contains(text(), 'Carrinho de compras') or contains(text(), 'Shopping Cart')]")));

        // Verificação adicional - checar se o subtotal está visível (pode estar zerado)
        WebElement subtotal = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("#sc-subtotal-label-activecart")));

        // Assertivas para confirmar que estamos na página correta
        assertTrue(tituloCarrinho.isDisplayed(), "Título do carrinho não está visível");
        assertTrue(subtotal.isDisplayed(), "Seção de subtotal não está visível");

        // Verificar a URL contém o padrão do carrinho
        assertTrue(driver.getCurrentUrl().contains("/cart/"), "URL não corresponde à página do carrinho");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}