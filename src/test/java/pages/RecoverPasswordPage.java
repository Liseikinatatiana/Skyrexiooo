package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class RecoverPasswordPage {
    final SelenideElement pageTitle = $x("//*[text()='Recover password']");

    @Step("Проверить, что страница восстановления пароля открыта и отображается текст 'Recover password'")
    public RecoverPasswordPage shouldBeOpened() {
        pageTitle.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }
}
