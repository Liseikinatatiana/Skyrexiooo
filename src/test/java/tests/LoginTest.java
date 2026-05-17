package tests;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void testLogin() {
        loginPage
                .openPageLogin()
                .login()
                .shouldBeOpened();
    }

    @Test
    public void testForgotPassword() {
        loginPage
                .openPageLogin()
                .forgotPassword()
                .shouldBeOpened();
    }

    @Test
    public void testLoginWithInvalidPassword() {
        loginPage
                .openPageLogin()
                .loginWithInvalidPassword();
    }
}
