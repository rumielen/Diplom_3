package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static extensions.RestClient.PROFILE_URL;

public class UserProfilePage {
    WebDriver driver;

    public final By logOutButton = By.xpath(".//button[text()='Выход']");

    public UserProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Клик по кнопке Выйти на странице просмотра профиля")
    public UserProfilePage clickLogOutButton() {
        driver.findElement(logOutButton).click();
        return this;
    }
    @Step("Открыть страницу профиля пользователя")
    public UserProfilePage openPage() {
        driver.get(PROFILE_URL);
        return this;
    }
}
