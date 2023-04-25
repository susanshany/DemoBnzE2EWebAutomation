package PageObjects;
import Resources.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MenuOnPage extends Base {
    public WebDriver driver;
    public MenuOnPage(WebDriver driver) {
        this.driver = driver;
    }

    // declare WebDriver as global


    // initialize parameterized constructor

    // initialize LogInOrRegister with cssLocator
    By logInOrRegister = By.cssSelector("span[class*='login']");
    By menu = By.xpath("//div[@id=\"left\"]/div/div/button");
    By payeeMenu = By.xpath("//li/a[@href=\"/client/payees\"]");
    By payOrTransferMenu = By.xpath("//ul/li[1]/button");
    // method to send WebElement of LogIn or Register
    public WebElement getMenuBtn() {
        return driver.findElement(menu);
    }
    public WebElement getPayeeBtn() {
        return driver.findElement(payeeMenu);
    }
    public WebElement getPayOrTransferBtn() {
        return driver.findElement(payOrTransferMenu);
    }


}
