// Casos de teste ID: CT032

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrimeVideoTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    void deveIrParaPaginaDoPrimeVideoESelecionarTheBoys() {
        driver.get("https://www.amazon.com.br");

        // Abre o menu
        WebElement botaoMenu = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-hamburger-menu")));
        botaoMenu.click();

        // Espera o item Prime Video aparecer e clica nele
        WebElement linkPrimeVideo = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[normalize-space()='Prime Video']")
        ));
        linkPrimeVideo.click();

        // Espera a nova página carregar (ou redirecionar)
        wait.until(ExpectedConditions.urlContains("primevideo.com"));

        // Agora, espera o elemento "The Boys" aparecer e clica nele
        // Vou supor que o texto "The Boys" aparece em algum link ou elemento clicável na página principal do Prime Video
        WebElement theBoys = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(., 'The Boys')]") // Pode ajustar o xpath conforme o site
        ));
        theBoys.click();

        // Você pode esperar a página de The Boys carregar, por exemplo:
        wait.until(ExpectedConditions.urlContains("the-boys"));

        // Verifica se foi para a página da série The Boys
        String urlAtual = driver.getCurrentUrl();
        assertTrue(urlAtual.toLowerCase().contains("the-boys"), "Não foi redirecionado para a página de The Boys.");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}