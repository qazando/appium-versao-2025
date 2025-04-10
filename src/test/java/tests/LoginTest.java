package tests;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class LoginTest {

    private AndroidDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("emulator-5554")
                .setApp("C:\\Users\\Usuário\\Downloads\\qazandofood2.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginComSucesso() {
        loginPage.preencherEmail("teste@teste.com");
        loginPage.preencherSenha("123456");
        loginPage.clicarLogin();

        // Exemplo de validação (ajuste conforme sua tela)
        // Assertions.assertTrue(driver.findElement(AppiumBy.accessibilityId("home-screen")).isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Page Object Interno
    public static class LoginPage {

        @AndroidFindBy(accessibility = "email")
        private WebElement emailField;

        @AndroidFindBy(accessibility = "password")
        private WebElement passwordField;

        @AndroidFindBy(accessibility = "login-button")
        private WebElement loginButton;

        public LoginPage(AndroidDriver driver) {
            PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        }

        public void preencherEmail(String email) {
            emailField.sendKeys(email);
        }

        public void preencherSenha(String senha) {
            passwordField.sendKeys(senha);
        }

        public void clicarLogin() {
            loginButton.click();
        }
    }
}
