import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends LoadableComponent<LoginPage> {
    private static final String USERNAME = "seleniumtests10@mail.ru";
    private static final String PASSWORD = "060788avavav";
    public static final String URL_LOGIN_PAGE = "https://e.mail.ru/login";
    private static final By USER_INPUT = By.cssSelector("[name=Login]");
    private static final By PASSWORD_INPUT = By.cssSelector("[name=Password]");
    private static final By SUBMIT_BUTTON = By.cssSelector("[type=submit]");
    private static final By LOGIN_FRAME = By.cssSelector(".ag-popup__frame__layout__iframe");
    private static final By LOGOUT_LINK = By.cssSelector("#PH_logoutLink");
    private static final By LOGIN_LINK = By.cssSelector("#PH_authLink");
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void load() {
        driver.get(URL_LOGIN_PAGE);
    }

    @Override
    protected void isLoaded() {
        String url = driver.getCurrentUrl();
        if (!url.equals(URL_LOGIN_PAGE)) {
            try {
                throw new IncorrectPageLoadedException(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void login() {
        driver.switchTo().frame(driver.findElement(LOGIN_FRAME));
        driver.findElement(USER_INPUT).sendKeys(USERNAME);
        driver.findElement(PASSWORD_INPUT).sendKeys(PASSWORD);
        driver.findElement(SUBMIT_BUTTON).submit();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(findLogout()));
    }

    private WebElement findLogout() {
        return driver.findElement(LOGOUT_LINK);
    }

    public boolean isLoggedIn() {
        return findLogout().isDisplayed();
    }

    public boolean loginIsDisplayed() {
        return driver.findElement(LOGIN_LINK).isDisplayed();
    }
}
