package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentPage {
    public WebDriver driver;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    By fromAccount = By.xpath("//button[@data-testid=\"from-account-chooser\"]");
    By toAccount = By.xpath("//button[@data-testid=\"to-account-chooser\"]");
    By fromandToSearch = By.xpath("//input[@data-monitoring-label=\"Transfer Form Search\"]");
    By transferFromAcCard = By.xpath("//button[@data-monitoring-label=\"Transfer Form Account Card\"]");

    By enterAmount = By.xpath("//input[@data-monitoring-label=\"Transfer Form Amount\"]");
    By paymentBtn = By.xpath("//button[@data-monitoring-label=\"Transfer Form Submit\"]");
    By successfulMessage = By.xpath("//span[@class=\"message\"]");
    // method to send WebElement of LogIn or Register
    public void setFromAccount(String fromAc) throws InterruptedException {
         driver.findElement(fromAccount).click();
         Thread.sleep(1000);
         driver.findElement(fromandToSearch).click();
         driver.findElement(fromandToSearch).sendKeys(fromAc);
         driver.findElement(fromandToSearch).sendKeys(Keys.TAB);
         driver.findElement(transferFromAcCard).sendKeys(Keys.ENTER);
    }
    public void setToAccount(String toAc) throws InterruptedException {
        driver.findElement(toAccount).click();
        Thread.sleep(1000);
        driver.findElement(fromandToSearch).click();
        driver.findElement(fromandToSearch).sendKeys(toAc);
        driver.findElement(fromandToSearch).sendKeys(Keys.TAB);
        driver.findElement(fromandToSearch).sendKeys(Keys.TAB);
        driver.findElement(transferFromAcCard).sendKeys(Keys.ENTER);
    }
    public void setAmountTransfer(String amountTransfer) {
        driver.findElement(enterAmount).click();
        driver.findElement(enterAmount).sendKeys(amountTransfer);

    }
    public void transferAmount() {

        driver.findElement(paymentBtn).submit();
    }
    public String getSuccessfulMessage() throws InterruptedException {
        Thread.sleep(2000);
        if(driver.findElement(successfulMessage).isDisplayed()){
            return driver.findElement(successfulMessage).getText();
        }
        return null;
    }

    //ul/li[1]/button/span[1]
}
