package pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ConfirmPage {
    final SelenideElement pageTitle = $x("//*[text()='Email confirmation']");

    public ConfirmPage shouldBeOpened() {
        pageTitle.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }
}
