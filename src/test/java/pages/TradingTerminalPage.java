package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TradingTerminalPage extends BasePage{

    final SelenideElement buyTab = $x("//button[text()='Buy']");
    final SelenideElement sellTab = $x("//button[text()='Sell']");
    final SelenideElement limitOrder = $x("//button[text()='Limit']");
    final SelenideElement marketOrder = $x("//button[text()='Market']");
    final SelenideElement conditionalOrder = $x("//button[text()='Conditional']");
    final SelenideElement activeTab = $("[role='tab'][data-state='active']");
    final SelenideElement activeOrder = $("[role='tab'][data-state='active']");
    final SelenideElement orderPriceField = $("#terminalOrderPrice");
    final SelenideElement dcaSwitch = $x("//h3[text()='Average (DCA)']/preceding-sibling::button[@role='switch']");
    final SelenideElement takeProfitSwitch = $x("//h3[text()='Take profit']/preceding-sibling::button[@role='switch']");
    final SelenideElement stopLossSwitch = $x("//h3[text()='Stop loss']/preceding-sibling::button[@role='switch']");

    @Step("Открыть страницу торгового терминала")
    public TradingTerminalPage openTradingTerminal() {
        open("/manual-trading/trading-terminal");
        return this;
    }

    @Step("Нажать на вкладку Buy")
    public TradingTerminalPage clickBuyTab() {
        buyTab.shouldBe(Condition.interactable).click();
        return this;
    }

    @Step("Нажать на вкладку Sell")
    public TradingTerminalPage clickSellTab() {
        sellTab.shouldBe(Condition.interactable).click();
        return this;
    }

    @Step("Проверить отображение вкладки Buy")
    public TradingTerminalPage shouldHaveBuyTabDisplayed() {
        buyTab.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверить отображение вкладки Sell")
    public TradingTerminalPage shouldHaveSellTabDisplayed() {
        sellTab.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверить, что активная вкладка имеет текст: {tabName}")
    public TradingTerminalPage shouldHaveActiveTabText(String tabName) {
        activeTab.shouldHave(Condition.text(tabName));
        return this;
    }

    @Step("Нажать на тип ордера Limit")
    public TradingTerminalPage clickLimitOrder() {
        limitOrder.shouldBe(Condition.interactable).click();
        return this;
    }

    @Step("Нажать на тип ордера Conditional")
    public TradingTerminalPage clickConditionalOrder() {
        conditionalOrder.shouldBe(Condition.interactable).click();
        return this;
    }

    @Step("Нажать на тип ордера Market")
    public TradingTerminalPage clickMarketOrder() {
        marketOrder.shouldBe(Condition.interactable).click();
        return this;
    }

    @Step("Проверить отображение кнопки Limit")
    public TradingTerminalPage shouldHaveLimitOrderDisplayed() {
        limitOrder.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверить отображение кнопки Market")
    public TradingTerminalPage shouldHaveMarketOrderDisplayed() {
        marketOrder.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверить отображение кнопки Conditional")
    public TradingTerminalPage shouldHaveConditionalOrderDisplayed() {
        conditionalOrder.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверить, что активный тип ордера имеет текст {orderType}")
    public TradingTerminalPage shouldHaveActiveOrderTypeText(String orderType) {
        activeOrder.shouldHave(Condition.text(orderType));
        return this;
    }

    @Step("Проверить, что поле цены ордера disabled")
    public TradingTerminalPage shouldHaveOrderPriceFieldDisabled() {
        orderPriceField.shouldBe(Condition.disabled);
        return this;
    }

    @Step("Нажать на переключатель DSA")
    public TradingTerminalPage clickDcaSwitch() {
        dcaSwitch.shouldBe(Condition.interactable).click();
        return this;
    }

    @Step("Нажать на переключатель TakeProfit")
    public TradingTerminalPage clickTakeProfitSwitch() {
        takeProfitSwitch.shouldBe(Condition.interactable).click();
        return this;
    }

    @Step("Нажать на переключатель StopLoss")
    public TradingTerminalPage clickStopLossSwitch() {
        stopLossSwitch.shouldBe(Condition.interactable).click();
        return this;
    }

    @Step("Проверить состояние переключателя DSA: включен = {enabled}")
    public TradingTerminalPage shouldHaveDcaSwitchEnabled(boolean enabled) {
        String expectedValue = enabled ? "true" : "false";
        dcaSwitch.shouldHave(Condition.attribute("aria-checked", expectedValue));
        return this;
    }

    @Step("Проверить состояние переключателя TakeProfit: включен = {enabled}")
    public TradingTerminalPage shouldHaveTakeProfitSwitchEnabled(boolean enabled) {
        String expectedValue = enabled ? "true" : "false";
        takeProfitSwitch.shouldHave(Condition.attribute("aria-checked", expectedValue));
        return this;
    }

    @Step("Проверить состояние переключателя StopLoss: включен = {enabled}")
    public TradingTerminalPage shouldHaveStopLossSwitchEnabled(boolean enabled) {
        String expectedValue = enabled ? "true" : "false";
        stopLossSwitch.shouldHave(Condition.attribute("aria-checked", expectedValue));
        return this;
    }
}
