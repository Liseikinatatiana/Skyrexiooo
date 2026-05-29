package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Торговый терминал")
@Feature("UI и переключение вкладок")
@Owner("Лисейкина Татьяна 89168331085@mail.ru")
public class TradingTerminalTest extends BaseTest {

    @Test
    @Story("Отображение вкладок Buy и Sell")
    @Severity(SeverityLevel.TRIVIAL)
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
    public void testBuySellTabAreDisplayed() {
        tradingTerminalPage
                .openTradingTerminal()
                .shouldHaveBuyTabDisplayed()
                .shouldHaveSellTabDisplayed();
    }

    @Test
    @Story("Переключение между вкладками Buy и Sell")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
    public void testSwitchBetweenBuyAndSellTabs() {
        tradingTerminalPage
                .openTradingTerminal()
                .shouldHaveActiveTabText("Buy")
                .clickSellTab()
                .shouldHaveActiveTabText("Sell")
                .clickBuyTab()
                .shouldHaveActiveTabText("Buy");
    }

    @Test
    @Story("Отображение ордеров Limit, Market, Conditional")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
    public void testOrderTypeAreDisplayed() {
        tradingTerminalPage
                .openTradingTerminal()
                .shouldHaveLimitOrderDisplayed()
                .shouldHaveMarketOrderDisplayed()
                .shouldHaveConditionalOrderDisplayed();
    }

    @Test
    @Story("Переключение между типами ордеров")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
    public void testSwitchBetweenOrderType() {
        tradingTerminalPage
                .openTradingTerminal()
                .clickMarketOrder()
                .shouldHaveActiveOrderTypeText("Market")
                .clickConditionalOrder()
                .shouldHaveActiveOrderTypeText("Conditional")
                .clickLimitOrder()
                .shouldHaveActiveOrderTypeText("Limit");
    }

    @Test
    @Story("Поле цены disabled при выборе Market ордера")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
    public void testMarketOrderPriceFieldIsDisabled() {
        tradingTerminalPage
                .openTradingTerminal()
                .clickMarketOrder()
                .shouldHaveOrderPriceFieldDisabled();
    }

    @Test
    @Story("Включение только DSA")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
    public void testEnableDcaSwitchAlone() {
        tradingTerminalPage
                .openTradingTerminal()
                .shouldHaveDcaSwitchEnabled(false)
                .shouldHaveTakeProfitSwitchEnabled(false)
                .shouldHaveStopLossSwitchEnabled(false)
                .clickDcaSwitch()
                .shouldHaveDcaSwitchEnabled(true)
                .shouldHaveTakeProfitSwitchEnabled(false)
                .shouldHaveStopLossSwitchEnabled(false);
    }

    @Test
    @Story("Включение только Take Profit")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
    public void testEnableTakeProfitSwitchAlone() {
        tradingTerminalPage
                .openTradingTerminal()
                .shouldHaveTakeProfitSwitchEnabled(false)
                .clickTakeProfitSwitch()
                .shouldHaveTakeProfitSwitchEnabled(true)
                .shouldHaveDcaSwitchEnabled(false)
                .shouldHaveStopLossSwitchEnabled(false);
    }

    @Test
    @Story("Включение только Stop Loss")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
    public void testEnableStopLossSwitchAlone() {
        tradingTerminalPage
                .openTradingTerminal()
                .shouldHaveStopLossSwitchEnabled(false)
                .clickStopLossSwitch()
                .shouldHaveStopLossSwitchEnabled(true)
                .shouldHaveDcaSwitchEnabled(false)
                .shouldHaveTakeProfitSwitchEnabled(false);
    }

    @Test
    @Story("Одновременное включение трех свитчеров")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
    public void testEnableAllThreeSwitchesTogether() {
        tradingTerminalPage
                .openTradingTerminal()
                .clickDcaSwitch()
                .clickStopLossSwitch()
                .clickTakeProfitSwitch()
                .shouldHaveDcaSwitchEnabled(true)
                .shouldHaveTakeProfitSwitchEnabled(true)
                .shouldHaveStopLossSwitchEnabled(true);
    }
}
