package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static extensions.RestClient.RECOVER_PASSWORD_URL;

public class RecoverPasswordPage {
    WebDriver driver;

    public final By loginLink = By.linkText("Войти");

    public RecoverPasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Открыть страницу восстановления забытого пароля")
    public RecoverPasswordPage openRecoveryPage() {
        driver.get(RECOVER_PASSWORD_URL);
        return this;
    }
    @Step("Клик по кнопке Войти на странице восстановления")
    public RecoverPasswordPage clickLoginLink() {
        driver.findElement(loginLink).click();
        return this;
    }
}
