package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import pages.*;
import utils.TestListener;

import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {
    LoginPage loginPage;
    RegisterPage registerPage;
    RecoverPasswordPage recoverPasswordPage;
    HomePage homePage;
    ConfirmPage confirmPage;
    TradingTerminalPage tradingTerminalPage;

    @Step("Открытие браузера")
    @Parameters({"browser"})
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
        tradingTerminalPage = new TradingTerminalPage();
    }

    @Step("Закрытие браузера")
    @AfterMethod
    public void close() {
        clearBrowserCache();
        Selenide.closeWebDriver();
    }
}
