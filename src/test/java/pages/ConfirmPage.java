package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ConfirmPage extends BasePage{
    final SelenideElement pageTitle = $x("//*[text()='Email confirmation']");

    @Step("Проверить, что страница подтверждения email открыта и отображается текст 'Email confirmation'")
    public ConfirmPage shouldBeOpened() {
        pageTitle.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }
}
