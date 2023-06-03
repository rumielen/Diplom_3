import extensions.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import static org.junit.Assert.assertEquals;

public class ConstructorSectionTest {
    private static WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setup() {
        driver = WebDriverFactory.get();

    }

    @After
    public void exitBrowser() {
        driver.quit();
    }

    @Test
    @DisplayName("Перейти в раздел Булки")
    public void checkCanGoToBunsSection() {
        mainPage = new MainPage(driver);
        mainPage
                .openPage()
                .clickSaucesSection()
                .clickBunsSection();
        mainPage
                .getTextActiveTab();
        assertEquals("Ошибка перехода к разделу конструктора", mainPage.getExpectedNameActiveSectionBuns(), mainPage.getTextActiveTab());
    }
    @Test
    @DisplayName("Перейти в раздел Соусы")
    public void checkCanGoToSauceSection() {
        mainPage = new MainPage(driver);
        mainPage
                .openPage()
                .clickSaucesSection();
        mainPage
                .getTextActiveTab();
        assertEquals("Ошибка перехода к разделу конструктора", mainPage.getExpectedNameActiveSectionSauces(), mainPage.getTextActiveTab());
    }
    @Test
    @DisplayName("Перейти в раздел Начинки")
    public void checkCanGoToFillingsSection() {
        mainPage = new MainPage(driver);
        mainPage
                .openPage()
                .clickFillingsSection();
        mainPage
                .getTextActiveTab();
        assertEquals("Ошибка перехода к разделу конструктора", mainPage.getExpectedNameActiveSectionFillings(), mainPage.getTextActiveTab());
    }
}
