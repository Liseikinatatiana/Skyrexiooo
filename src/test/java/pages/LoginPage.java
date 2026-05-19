package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    final SelenideElement emailInput = $(By.id("email"));
    final SelenideElement passwordInput = $(By.id("password"));
    final SelenideElement signInBtn = $x("//button[text()='Sign in']");
    final SelenideElement pageTitle = $x("//h1[text()='Sign in']");
    final SelenideElement forgotPasswordBtn = $x("//button[text()='Forgot password?']");

    @Step("Открыть страницу login")
    public LoginPage openPageLogin() {
        open("/login");
        return this;
    }

    @Step("Выполнить вход с валидными данными")
    public HomePage login() {
        emailInput.setValue("test@mail.ru").pressEnter();
        passwordInput.sendKeys("password123");
        signInBtn.click();
        return new HomePage();
    }

    @Step("Нажать на кнопку 'Forgot password?'")
    public RecoverPasswordPage forgotPassword() {
        forgotPasswordBtn.click();
        return new RecoverPasswordPage();
    }

    @Step("Выполнить вход с неверным паролем")
    public LoginPage loginWithInvalidPassword() {
        emailInput.setValue("test@mail.ru").pressEnter();
        passwordInput.sendKeys("123");
        signInBtn.click();
        pageTitle.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }
}
