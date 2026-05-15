package tests;

import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {
    @Test
    public void testRegistration() {
        registerPage
                .openPageRegister()
                .register()
                .shouldBeOpened();
    }

    @Test
    public void testRegistrationWithEmptyFields() {
        registerPage
                .openPageRegister()
                .registerWithEmptyFields();
    }
}
