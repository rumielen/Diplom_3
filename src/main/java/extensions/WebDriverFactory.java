package extensions;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;


import static config.WebDriverConfig.WAIT_SECONDS_TIMEOUT;
import static extensions.RestClient.BASE_URL;

public class WebDriverFactory {


        @Before
        public static WebDriver get () {

            String browserName = System.getenv().get("browser");
            WebDriver driver;
            switch (browserName) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "yandex":
                    System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandexdriver.exe");
                    driver = new ChromeDriver();

                    break;
                default:
                    throw new RuntimeException("Browser" + browserName + "not exist");
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT_SECONDS_TIMEOUT));
            driver.navigate().to(BASE_URL);
            return driver;
        }


    }