//Casos de teste ID: CT030

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class OpenZipCodeTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://www.amazon.com.br/");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void deveAtualizarOCepParaNovoValor() {
        // Aceita cookies se aparecer
        try {
            WebElement aceitarCookies = wait.until(ExpectedConditions.elementToBeClickable(By.id("sp-cc-accept")));
            aceitarCookies.click();
        } catch (Exception ignored) {}

        // Clica no botão de localização
        WebElement botaoEndereco = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-global-location-popover-link")));
        botaoEndereco.click();

        // Espera o input de CEP ficar visível e habilitado
        WebElement inputCep = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("GLUXZipUpdateInput")));
        wait.until(ExpectedConditions.elementToBeClickable(inputCep));

        // Força o scroll até o campo (garante visibilidade)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", inputCep);
        inputCep.clear();
        inputCep.sendKeys("01310-200"); // Avenida Paulista - SP

        // Clica no botão aplicar
        WebElement botaoAplicar = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#GLUXZipUpdate > span > span > input")));
        botaoAplicar.click();

        // Aguarda carregamento da nova localização
        wait.until(ExpectedConditions.invisibilityOf(inputCep));

        // Aguarda o texto atualizado na barra superior
        WebElement enderecoAtualizado = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("glow-ingress-line2")));

        String textoEndereco = enderecoAtualizado.getText();
        System.out.println("Texto após atualização: " + textoEndereco);

        // Verifica que o CEP foi atualizado
        Assertions.assertFalse(textoEndereco.toLowerCase().contains("informe"), "CEP não foi atualizado corretamente.");
    }
}