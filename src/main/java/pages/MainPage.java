package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static extensions.RestClient.BASE_URL;


public class MainPage {
    private WebDriver driver;

    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By doOrder = By.xpath(".//button[text()='Оформить заказ']");
    private final By accountButton = By.linkText("Личный Кабинет");
    private final By bunSection = By.xpath(".//div/span[text()='Булки']");
    private final By sauceSection = By.xpath(".//div/span[text()='Соусы']");
    private final By fillingSection = By.xpath(".//div/span[text()='Начинки']");
    private final By actTab = By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[@class='text text_type_main-default']");

    private final String expectedNameActiveSectionFillings = "Начинки";
    private final String expectedNameActiveSectionBuns = "Булки";
    private final String expectedNameActiveSectionSauces = "Соусы";
    private final String expectedTextСreateOrderButton = "Оформить заказ";
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть главную страницу")
    public MainPage openPage() {
        driver.get(BASE_URL);
        return this;
    }

    @Step("Клик по кнопке личного кабинета на главной странице")
    public MainPage clickAccountButton() {
        driver.findElement(accountButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver -> driver.findElement(accountButton)).click();
        return this;
    }
    @Step("Кликнуть на кнопку Войти в аккаунт для незарегистрированных пользователей")
    public MainPage clickButtonLogin() {
        driver.findElement(loginButton).click();
        return this;
    }


    public String getСreateOrderButtonExpectedText() {
        return expectedTextСreateOrderButton;
    }

    public String getСreateOrderButtonText(){
        return driver.findElement(doOrder).getText();
    }
    @Step("Кликнуть по вкладке Булочки ")
    public MainPage clickBunsSection() {
        driver.findElement(bunSection).click();
        return this;
    }
    @Step("Кликнуть по вкладке Соусы ")
    public MainPage clickSaucesSection() {
        driver.findElement(sauceSection).click();
        return this;
    }
    @Step("Кликнуть по вкладке начинки")
    public MainPage clickFillingsSection() {
        driver.findElement(fillingSection).click();
        return this;
    }


    public String getExpectedNameActiveSectionBuns() {
        return expectedNameActiveSectionBuns;
    }

    public String getExpectedNameActiveSectionSauces() {
        return expectedNameActiveSectionSauces;
    }

    public String getExpectedNameActiveSectionFillings() {
        return expectedNameActiveSectionFillings;
    }
    public String getTextActiveTab() {
        return driver.findElement(actTab).getText();
    }
}
