import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {
    public RegisterPage(WebDriver webDriver) {
        super(webDriver);
        webDriver.get("https://forums.everybodyedits.com/register.php");
    }
}
