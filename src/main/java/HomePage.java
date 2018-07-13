import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

public class HomePage extends LoadableComponent<HomePage> {
    private WebDriver driver;
    private static final String HOMEPAGE_URL = "https://mail.ru/";
    private static final By LOGOUT_LINK = By.cssSelector("#PH_logoutLink");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void logoutClick() {
        driver.findElement(LOGOUT_LINK).click();
    }

    @Override
    protected void load() {
        driver.get(HOMEPAGE_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        if (!url.equals(HOMEPAGE_URL)) {
            try {
                throw new IncorrectPageLoadedException(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
