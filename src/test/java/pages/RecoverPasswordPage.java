package pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class RecoverPasswordPage {
    final SelenideElement pageTitle = $x("//*[text()='Recover password']");

    public RecoverPasswordPage shouldBeOpened() {
        pageTitle.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }
}
