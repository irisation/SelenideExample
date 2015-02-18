package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;


public class LoginPage {

    @FindBy(how = How.ID, using = "username")
    private SelenideElement userName;

    @FindBy(how = How.ID, using = "password")
    private SelenideElement password;

    @FindBy(how = How.CSS, using = "button[type=submit]")
    private SelenideElement submit;

    public LoginPage fillForm(String user, String pass) {
        userName.val(user);
        password.val(pass);
        return this;
    }

    public LoginPage submit() {
        submit.click();
        return this;
    }

    public SuccessfulLoginPage login(String user, String pass) {
        fillForm(user, pass);
        submit();
        return page(SuccessfulLoginPage.class);
    }

    public LoginPage setUserName(String user) {
        userName.val(user);
        return this;
    }

    public LoginPage setPassword(String pass) {
        password.val(pass);
        return this;
    }

    public String getUserName() {
        return userName.val();
    }

    public String getPassword() {
        return password.val();
    }
}
