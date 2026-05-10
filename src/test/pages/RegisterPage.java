package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class RegisterPage {
    final SelenideElement SignUpBtn = $x("//button[text()='Sign up']");
    final SelenideElement emailInput = $(By.id("email"));
    final SelenideElement passwordInput = $(By.id("password"));
    final SelenideElement pageTitle = $x("//h1[text()='Sign up']");

    public RegisterPage openPageRegister() {
        open("/register");
        return this;
    }

    public ConfirmPage register() {
        emailInput.setValue("test@mail.ru").pressEnter();
        passwordInput.sendKeys("password123");
        SignUpBtn.click();
        return new ConfirmPage();
    }

    public RegisterPage registerWithEmptyFields() {
        SignUpBtn.click();
        pageTitle.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }
}
