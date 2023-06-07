package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static extensions.RestClient.LOGIN_URL;

public class LoginPage {

    private final By emailField = By.xpath(".//fieldset[1]//input");
    private final By passwordField = By.xpath(".//fieldset[2]//input");
    private final By loginButton = By.xpath(".//button[text()='Войти']");

    private final String expectedTextButtonLogin = "Войти";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;

    @Step("Заполнить поля email и пароль для входа в аккаунт пользователя")
    public LoginPage userDataLogin(String valueEmail, String valuePassword) {
        driver.findElement(emailField).sendKeys(valueEmail);
        driver.findElement(passwordField).sendKeys(valuePassword);
        return this;
    }

    @Step("Открыть страницу для входа пользователя")
    public LoginPage openPage() {
        driver.get(LOGIN_URL);
        return this;
    }
    @Step("Клик по кнопке входа на странице для логина пользователя")
    public LoginPage clickButtonLoginIn() {
        driver.findElement(loginButton).click();
        return this;
    }
    public String getExpectedTextButtonLogin() {
        return expectedTextButtonLogin;
    }
    public String getTextButtonLogin(){
        return driver.findElement(loginButton).getText();
    }
}
