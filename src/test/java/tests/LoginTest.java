package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Авторизация")
@Feature("Аутентификация пользователя")
@Owner("Лисейкина Татьяна 89168331085@mail.ru")
public class LoginTest extends BaseTest {
    @Test
    @Story("Успешний вход с валидными данными")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
    public void testLogin() {
        loginPage
                .openPageLogin()
                .login()
                .shouldBeOpened();
    }

    @Test
    @Story("Переход на страницу восстановления пароля")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
    public void testForgotPassword() {
        loginPage
                .openPageLogin()
                .forgotPassword()
                .shouldBeOpened();
    }

    @Test
    @Story("Попытка входа с неверным паролем")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
    public void testLoginWithInvalidPassword() {
        loginPage
                .openPageLogin()
                .loginWithInvalidPassword();
    }
}
