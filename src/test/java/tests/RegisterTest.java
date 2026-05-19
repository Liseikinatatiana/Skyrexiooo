package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Регистрация")
@Feature("Создание нового аккаунта")
@Owner("Лисейкина Татьяна 89168331085@mail.ru")
public class RegisterTest extends BaseTest {
    @Test
    @Story("Регистрация с валидными данными")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
    public void testRegistration() {
        registerPage
                .openPageRegister()
                .register()
                .shouldBeOpened();
    }

    @Test
    @Story("Попытка регистрации с пустыми полями")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
    public void testRegistrationWithEmptyFields() {
        registerPage
                .openPageRegister()
                .registerWithEmptyFields();
    }
}
