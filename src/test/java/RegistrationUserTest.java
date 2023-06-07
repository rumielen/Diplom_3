import extensions.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.RegistrationPage;


import static org.junit.Assert.assertEquals;

public class RegistrationUserTest {
    private static WebDriver driver;
    private String email;
        private String password;
        private String name;
        private String accessToken;
        private String actualLoginTextButton;
    private final UserGenerator userGenerator = new UserGenerator();

    User user1 = userGenerator.getRandomUser();



    @Before
    public void setup() {
        driver = WebDriverFactory.get();
    }

    @After
    public void exitBrowserDeleteUser() {
        if(actualLoginTextButton != null) {
            UserRequest userRequest = new UserRequest();
            UserCredentials userCredentials = UserCredentials.from(user1);
            ValidatableResponse login = userRequest.loginUser(userCredentials);
            accessToken = userRequest.assertUserAuthorizationIsSuccess(login);
            ValidatableResponse response = userRequest.deleteUser(accessToken);
            userRequest.assertUserDeleteSuccessfully(response);
        }
        driver.quit();
    }


    @DisplayName("Регистрация пользователя с корректными данными")
    @Test
    public void checkUserWithCorrectDataCanRegisterSuccessfully() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        email = user1.getEmail();
        name = user1.getName();
        password = user1.getPassword();
        registrationPage
                .openPage()
                .userCreateWithData(name, email, password)
                .registrationButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        actualLoginTextButton = loginPage.getTextButtonLogin();
        assertEquals("Ошибка регистрации", loginPage.getExpectedTextButtonLogin(), actualLoginTextButton);
    }

    @DisplayName("Регистрация с невалидным паролем")
    @Test
    public void checkUserCanNotRegisterWithNoValidPassword() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        email = user1.getEmail();
        name = user1.getName();
        user1.setPassword(RandomStringUtils.randomAlphanumeric(5));
        password = user1.getPassword();
        registrationPage
                .openPage()
                .userCreateWithData(name, email, password)
                .registrationButtonClick();
        assertEquals("Ошибочная проверка на ввод некорректного пароля", registrationPage.getExpectedWrongPasswordMessage(), registrationPage.getWrongPasswordText());
    }
}
