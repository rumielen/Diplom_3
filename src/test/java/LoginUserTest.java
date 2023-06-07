import extensions.User;
import extensions.UserGenerator;
import extensions.UserRequest;
import extensions.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.*;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class LoginUserTest {
    private final UserRequest userRequest = new UserRequest();
    private final UserGenerator userGenerator = new UserGenerator();

    User user1 = userGenerator.getRandomUser();
    private String accessToken, email, password;
    private static WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;


    @Before
    public void setupAndCreateUser() {

        driver = WebDriverFactory.get();
        ValidatableResponse create = userRequest.createUser(user1);
        accessToken = userRequest.assertUserCreatedSuccessfully(create);
    }

    @After
    public void deleteUserExitBrowser() {
        ValidatableResponse response = userRequest.deleteUser(accessToken);
        userRequest.assertUserDeleteSuccessfully(response);
        driver.quit();
    }

    @Test
    @DisplayName("Войти в аккаунт через личный кабинет")
    public void accountButtonSighInCheck() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        email = user1.getEmail();
        password = user1.getPassword();
        mainPage
                .openPage()
                .clickAccountButton();
        loginPage
                .userDataLogin(email, password)
                .clickButtonLoginIn();
        mainPage
                .getСreateOrderButtonExpectedText();
        assertEquals("Ошибка входа", mainPage.getСreateOrderButtonExpectedText(), mainPage.getСreateOrderButtonText());
    }
    @Test
    @DisplayName("Войти в аккаунт через главную страницу")
    public void homePageSignInButtonCheck() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        email = user1.getEmail();
        password = user1.getPassword();
        mainPage
                .openPage()
                .clickButtonLogin();
        loginPage
                .userDataLogin(email, password)
                .clickButtonLoginIn();
        mainPage
                .getСreateOrderButtonExpectedText();
        assertEquals("Ошибка входа", mainPage.getСreateOrderButtonExpectedText(), mainPage.getСreateOrderButtonText());
    }


    @Test
    @DisplayName("Войти в аккаунт по кнопке на форме регистрации")
    public void checkUserCanLoginOnClickToRegistrationButton() {
        mainPage = new MainPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        email = user1.getEmail();
        password = user1.getPassword();
        registrationPage
                .openPage()
                .loginButtonClick();
        loginPage
                .userDataLogin(email, password)
                .clickButtonLoginIn();
        mainPage
                .getСreateOrderButtonExpectedText();
        assertEquals("Ошибка входа", mainPage.getСreateOrderButtonExpectedText(), mainPage.getСreateOrderButtonText());
    }

    @Test
    @DisplayName("Восстановить пароль и войти в аккаунт")
    public void checkUserCalLoginAfterRecoverPassword() {
        mainPage = new MainPage(driver);
        RecoverPasswordPage recoverPasswordPage = new RecoverPasswordPage(driver);
        loginPage = new LoginPage(driver);
        email = user1.getEmail();
        password = user1.getPassword();
        recoverPasswordPage
                .openRecoveryPage()
                .clickLoginLink();
        loginPage
                .userDataLogin(email, password)
                .clickButtonLoginIn();
        mainPage
                .getСreateOrderButtonText();
        assertEquals("Ошибка входа", mainPage.getСreateOrderButtonExpectedText(), mainPage.getСreateOrderButtonText());
    }

    @Test
    @DisplayName("Выйти из аккаунта")
    public void checkUserCanLogOut() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        UserProfilePage userProfilePage = new UserProfilePage(driver);
        email = user1.getEmail();
        password = user1.getPassword();
        mainPage
                .openPage()
                .clickAccountButton();
        loginPage
                .userDataLogin(email, password)
                .clickButtonLoginIn();
        mainPage
                .clickAccountButton();
        userProfilePage
                .clickLogOutButton();
        loginPage
                .getTextButtonLogin();
        assertEquals("Ошибка выхода из аккаунта", loginPage.getExpectedTextButtonLogin(), loginPage.getTextButtonLogin());
    }
}
