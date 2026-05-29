package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage extends BasePage{
    final SelenideElement pageTitle = $x("//*[text()='Statistics']");

    @Step("Проверить, что домашняя страница открыта и отображается текст 'Statistics'")
    public HomePage shouldBeOpened() {
        pageTitle.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }
}
