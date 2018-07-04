import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class LoginPage extends LoadableComponent<LoginPage> {
    private static final String USERNAME = "seleniumtests10@mail.ru";
    private static final String PASSWORD = "060788avavav";
    public static final String URLMAIL = "https://e.mail.ru/login";
    private WebDriver driver;

    @FindBy(how = How.CSS, using = "[name=Login]")
    WebElement userfield;

    @FindBy(how = How.CSS, using = "[name=Password]")
    WebElement passfield;

    @FindBy(how = How.CSS, using = "[type=submit]")
    WebElement loginbutton;

    @FindBy(how = How.CSS, using = ".ag-popup__frame__layout__iframe")
    WebElement loginframe;

    @FindBy(how = How.CSS, using = "#PH_logoutLink")
    WebElement logout;

    @FindBy(how = How.CSS, using = "#PH_authLink")
    WebElement loginlink;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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
        driver.switchTo().frame(loginframe);
        userfield.sendKeys(USERNAME);
        passfield.sendKeys(PASSWORD);
        loginbutton.submit();
    }

    public boolean logoutIsDisplayed() {
        return logout.isDisplayed();
    }

    public boolean loginIsDisplayed() {
        return loginlink.isDisplayed();
    }
}
