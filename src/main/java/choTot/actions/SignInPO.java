package choTot.actions;

import choTot.interfaces.SignInUI;
import org.openqa.selenium.WebDriver;
import core.AbstractPage;
import core.config.Domain;

public class SignInPO extends AbstractPage {

    WebDriver driver;

    public SignInPO(WebDriver driver) {
        this.driver = driver;
    }

    public void gotoLoginPage() {
        openAnyUrl(driver, Domain.SECURE + "login");
    }

    public void inputUserName(String userName) {
        sendKeyToElement(driver, SignInUI.USERNAME_TXT, userName);
    }

    public void inputPassWord(String userPass) {
        sendKeyToElement(driver, SignInUI.PASSWORD_TXT, userPass);
    }

    public void clickLogin() {
        clickToElement(driver, SignInUI.LOGIN_BTN);
    }

}
