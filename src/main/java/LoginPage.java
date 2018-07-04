import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;

public class LoginPage extends LoadableComponent<LoginPage> {
    private static final String USERNAME = "seleniumtests10@mail.ru";
    private static final String PASSWORD = "060788avavav";
    public static final String URLMAIL = "https://e.mail.ru/login";
    private static final By USERFIELD = By.cssSelector("[name=Login]");
    private static final By PASSFIELD = By.cssSelector("[name=Password]");
    private static final By SUBMITBUTTON = By.cssSelector("[type=submit]");
    private static final By FRAMEFORLOGIN = By.cssSelector(".ag-popup__frame__layout__iframe");
    private static final By LOGOUTLINK = By.cssSelector("#PH_logoutLink");
    private static final By LOGINLINK = By.cssSelector("#PH_authLink");
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void load() {
        driver.get(URLMAIL);
    }

    @Override
    protected void isLoaded() {
        String url = driver.getCurrentUrl();
        if (!url.equals(URLMAIL)) {
            try {
                throw new Exception("Wrong page is loaded");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void login() {
        driver.switchTo().frame(driver.findElement(FRAMEFORLOGIN));
        driver.findElement(USERFIELD).sendKeys(USERNAME);
        driver.findElement(PASSFIELD).sendKeys(PASSWORD);
        driver.findElement(SUBMITBUTTON).submit();
    }

    public WebElement findLogout() {
        return driver.findElement(LOGOUTLINK);
    }

    public void logoutClick() {
        driver.findElement(LOGOUTLINK).click();
    }

    public boolean loginIsDisplayed() {
        return driver.findElement(LOGINLINK).isDisplayed();
    }
}
