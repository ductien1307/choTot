package choTot.testCases;

import choTot.actions.DashBoardPO;
import choTot.actions.SignInPO;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import core.AbstractTest;

import java.lang.reflect.Method;

public class SignIn extends AbstractTest {

    public SignInPO signInPO;
    public DashBoardPO dashBoardPO;

    @BeforeClass()
    public void beforeClass() {
        signInPO = new SignInPO(driver);
        dashBoardPO = new DashBoardPO(driver);
    }

    @Test(dataProvider = "dataSignIn")
    public void validAccount(String userName, String passWord, String expected) {
        signInPO.gotoLoginPage();
        signInPO.inputUserName(userName);
        signInPO.inputPassWord(passWord);
        signInPO.clickLogin();
        String fullName = dashBoardPO.getFullName();
        verifyEquals(fullName, expected);
    }

    @DataProvider(name = "dataSignIn")
    public Object[][] dataSignIn(Method method) {
        Object[][] result = null;
        if (method.getName().equals("validAccount")) {
            result = new Object[][]{
                    {"0984555475", "130787", "Nguyễn Đức Tiến"},
            };
        }
        return result;
    }
}
