import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;


public class LoginPageTest extends BaseClass {
    private WebDriver dr = getDriver();
    private LoginPage log;


    @BeforeClass
    public void start() {
        dr = new ChromeDriver();
        log = new LoginPage(dr);
        dr.manage().window().maximize();
        dr.get(LoginPage.URL_LOGIN_PAGE);
        dr.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        log.get();
        log.login();
    }

    @Feature("Login")
    @Description("Test checks login functionality to Mail.ru")
    @Issue("1")
    @Test
    public void loginTest() {
        assertTrue(log.isLoggedIn(), "Failed to login");
    }

    @Ignore
    @Feature("Logout")
    @Description("Test checks logout functionality")
    @Issue("2")
    @Test
    public void logoutTest() {
        HomePage home = new HomePage(dr);
        home.logoutClick();
        Assert.assertTrue(log.loginIsDisplayed(), "Failed to logout");
    }

    @AfterClass
    public void close() {
        dr.quit();
    }
}
