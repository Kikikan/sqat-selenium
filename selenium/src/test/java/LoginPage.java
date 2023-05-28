import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final String username = System.getenv("username");
    private final String password = System.getenv("password");

    private final By reqUsername = By.name("req_username");
    private final By reqPassword = By.name("req_password");
    private final By login = By.name("login");

    public LoginPage(WebDriver driver) throws IllegalStateException {
        super(driver);
        if (username == null || password == null)
            throw new IllegalStateException("Username and/or password was not provided in environmental variables.");
        driver.get("https://forums.everybodyedits.com/login.php");
    }

    public void login() {
        waitAndReturnElement(reqUsername).sendKeys(username);
        waitAndReturnElement(reqPassword).sendKeys(password);
        waitAndReturnElement(login).click();
    }

    public String getUsername() {
        return username;
    }
}
