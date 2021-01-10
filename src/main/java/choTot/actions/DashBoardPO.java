package choTot.actions;

import choTot.interfaces.DashBoardUI;
import org.openqa.selenium.WebDriver;
import core.AbstractPage;

public class DashBoardPO extends AbstractPage {

    WebDriver driver;

    public DashBoardPO(WebDriver driver) {
        this.driver = driver;
    }

    public String getFullName() {
        return getTextElement(driver, DashBoardUI.FULL_NAME_LBL);
    }

    public void clickMore() {
        clickToElement(driver, DashBoardUI.MORT_BTN);
    }

    public void clickLogout() {
        clickToElement(driver, DashBoardUI.LOGOUT_BTN);
    }

    public boolean checkAvatar() {
        return isDisplayed(driver, DashBoardUI.AVATAR_IMG);
    }

    public boolean checkSignInLabel() {
        return isDisplayed(driver, DashBoardUI.SIGN_IN_LBL);
    }
}
