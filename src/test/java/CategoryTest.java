//Casos de Teste: ID CT027

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    void deveAbrirListaDeCategorias() {
        driver.get("https://www.amazon.com.br");

        // Clica no menu "Todos" (ícone de 3 linhas no canto superior esquerdo)
        WebElement botaoMenu = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-hamburger-menu")));
        botaoMenu.click();

        // Verifica se o menu lateral foi aberto
        WebElement menuAberto = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.hmenu-visible")));
        assertTrue(menuAberto.isDisplayed(), "Menu de categorias não foi exibido.");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}