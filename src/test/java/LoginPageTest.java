import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class LoginPageTest {
    private WebDriver driver = new ChromeDriver();
    private LoginPage log = new LoginPage(driver);

    @BeforeClass
    public void start() {
        driver.get(LoginPage.URLMAIL);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        log.login();
    }

    @Test
    public void loginTest() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(log.findLogout()));
        assertTrue(log.findLogout().isDisplayed());
    }

    @Test
    public void logoutTest() {
        log.logoutClick();
        Assert.assertTrue(log.loginIsDisplayed());
    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}
