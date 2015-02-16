import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;


public class HowToStartTest {

    @BeforeMethod
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://the-internet.herokuapp.com/";
    }

    @Test
    public void startTest() {
        open("/dynamic_controls");
        $(By.linkText("Dynamic Controls")).click();
        $(By.id("btn")).click();
        $(By.id("checkbox")).should(disappear);
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
}
