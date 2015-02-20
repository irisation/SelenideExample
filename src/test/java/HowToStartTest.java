import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.testng.BrowserPerTest;
import com.codeborne.selenide.testng.ScreenShooter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Listeners({BrowserPerTest.class, ScreenShooter.class})
//указываем когда и как мы создаём браузер -- для каждого теста(класса);
// задаём создание скриншотов
public class HowToStartTest {

    @BeforeMethod
    public void setup() {
//        Configuration.browser = "chrome"; default is firefox -- не оставлять в коде!
        // mvn -Dbrowser=chrome
        Configuration.baseUrl = "http://the-internet.herokuapp.com";
        Configuration.timeout = 10000;
//        Configuration.remote = "http://localhost:4444/wd/hub";

        //Если необходимо работать с драйвером, его можно сконфигурировать заранее(например, подключить плагин и тд),
        // а потом передать в селенид
//        WebDriver driver = new FirefoxDriver(new DesiredCapabilities());
//        WebDriverRunner.setWebDriver(driver);
    }


    @Test
    public void startTest() {
        open("/dynamic_controls");
//        open("http://the-internet.herokuapp.com");
//        $(By.linkText("Dynamic Controls")).click();
        $(By.id("btn")).click();
        $(By.id("checkbox")).should(disappear);
    }

    @Test
    public void collectionTest() {
        open("");
        $$("li>a").shouldHaveSize(35).filter(Condition.text("File")).shouldHaveSize(3).shouldHave(texts("file"));

    }

    @Test
    public void startWebDriverTest() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/");
        driver.findElement(By.linkText("Dynamic Controls")).click();
        driver.findElement(By.id("btn")).click();
        new WebDriverWait(driver, 10000).until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkbox")));
        driver.quit();
    }


    @Test
    public void loginTest() {
        open("", HomePage.class)
                .goTo("Form Authentication", LoginPage.class)
                .login("tomsmith", "SuperSecretPassword!")
                .shouldLogin();
    }
//    negativeLoginTest
}
