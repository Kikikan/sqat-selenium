import org.openqa.selenium.WebDriver;

public class RulesPage extends BasePage{
    public RulesPage(WebDriver webDriver) {
        super(webDriver);
        webDriver.get("https://forums.everybodyedits.com/misc.php?action=rules");
    }
}
