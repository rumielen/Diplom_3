package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static extensions.RestClient.REGISTRATION_PAGE;

public class RegistrationPage {
    WebDriver driver;
    private final By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By nameField = By.xpath(".//fieldset[1]//input");
    private final By emailField = By.xpath(".//fieldset[2]//input");
    private final By passwordField = By.xpath(".//fieldset[3]//input");

    private final By loginButton = By.linkText("Войти");
    private final By wrongPasswordText = By.xpath(".//p[text()='Некорректный пароль']");
    private final String expectedWrongPasswordMessage = "Некорректный пароль";


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Страница регистрации")
    public RegistrationPage openPage() {
        driver.get(REGISTRATION_PAGE);
        return this;
    }
    @Step("Заполнить необходимые поля для регистрации пользователя")
    public RegistrationPage userCreateWithData(String valueName, String valueEmail, String valuePassword) {
        driver.findElement(nameField).sendKeys(valueName);
        driver.findElement(emailField).sendKeys(valueEmail);
        driver.findElement(passwordField).sendKeys(valuePassword);
        return this;
    }
    @Step("Клик по кнопке регистрации")
    public RegistrationPage registrationButtonClick() {
        driver.findElement(registrationButton).click();
        return this;
    }
    @Step("Кликнуть на кнопку Войти на странице регистрации")
    public RegistrationPage loginButtonClick() {
        driver.findElement(loginButton).click();
        return this;
    }

    public String getWrongPasswordText(){
        return driver.findElement(wrongPasswordText).getText();
    }
    public String getExpectedWrongPasswordMessage() {
        return expectedWrongPasswordMessage;
    }
}
