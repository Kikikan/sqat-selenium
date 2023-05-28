import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    private final By footer = By.id("brdfooternav");
    private final By logout = By.xpath("//li[contains(@id, 'navlogout')]//a");
    private final By welcomeDiv = By.id("brdwelcome");
    protected final WebDriver webDriver;
    protected WebDriverWait driverWait;


    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.driverWait = new WebDriverWait(webDriver, 10);
    }

    protected final WebElement waitAndReturnElement(By locator) {
        this.driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.webDriver.findElement(locator);
    }

    protected final String getBodyText() {
        return waitAndReturnElement(By.tagName("body")).getText();
    }

    public String getFooterText() {
        return waitAndReturnElement(footer).getText();
    }

    public String getWelcomeText() {
        return waitAndReturnElement(welcomeDiv).getText();
    }

    public void logout() {
        System.out.println(waitAndReturnElement(logout).getAttribute("href"));
        webDriver.get(waitAndReturnElement(logout).getAttribute("href"));
    }
}
