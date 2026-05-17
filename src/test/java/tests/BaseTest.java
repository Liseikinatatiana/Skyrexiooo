package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

public class BaseTest {
    LoginPage loginPage;
    RegisterPage registerPage;
    RecoverPasswordPage recoverPasswordPage;
    HomePage homePage;
    ConfirmPage confirmPage;

    @BeforeMethod
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.timeout = 10000;
        Configuration.baseUrl = "https://test.skyrexio.com";
        Configuration.holdBrowserOpen = true;

        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        recoverPasswordPage = new RecoverPasswordPage();
        homePage = new HomePage();
        confirmPage = new ConfirmPage();

    }

    @AfterMethod
    public void close() {
        clearBrowserCache();
        Selenide.closeWebDriver();
    }
}
