package PageObjects;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PayeePage {
    public WebDriver driver;
    public PayeePage(WebDriver driver) {
        this.driver=driver;
    }

    By header = By.xpath( "//header[@class=\"CustomPage-header\"]/h1/span");
    By addPayeeBtn = By.xpath("//div[@class=\"Row\"]/div/button");
    By addPayeeName = By.xpath("//div[@class=\"combobox\"]/div[@class=\"inputs\"]/input[1]");
    By addNameAcBtn = By.xpath("//form[@id=\"apm-form\"]/div/button[3]");
    By payeeAddedMessage = By.xpath("//div[@id=\"notification\"]//span");
    By addAcNo = By.id("apm-bank");
    By payeeNameList = By.xpath("//p[@class=\"Avatar-title\"]/span[@class=\"js-payee-name\"]");
    By payeeAcNoList = By.xpath("//div[contains(@id, 'js-payee-item')]/div/div/p[2]");
    By payeeNameReqErrMsg = By.xpath("//p[@class=\"text js-tooltip-text\"]");
    By nameInDescOrderBtn = By.xpath(("//h3/span[contains(text(), \"Name\" )]"));
        //(""//li/a[@href=\"/client/payees\"]");
    // method to send WebElement of LogIn or Register
    public String getHeader() {

        return driver.findElement(header).getText();
    }
    public void clickAddPayeeBtn() {
         driver.findElement(addPayeeBtn).click();
    }
    public void setAddPayeeName(String payeeName) {
        driver.findElement(addPayeeName).click();
        //driver.findElement(addPayeeName).sendKeys(payeeName);
        driver.findElement(addPayeeName).sendKeys(payeeName);
        driver.findElement(addPayeeName).sendKeys(Keys.TAB);
        driver.findElement(addPayeeName).sendKeys(Keys.ENTER);

    }
    public void setAddPayeeAcNo(String payeeAcNo) {

        driver.findElement(addAcNo).click();
        driver.findElement(addAcNo).sendKeys(payeeAcNo);
    }

    public void clickAddNameAcNoBtn() {
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
       // wait.until(ExpectedConditions.elementToBeClickable(addNameAcBtn)).submit();

        driver.findElement(addNameAcBtn).click();

    }

    public String getSuccessMessage(WebDriver driver) throws InterruptedException {
        Duration timeout=Duration.ofMillis(30) ;

        WebDriverWait wait;
        //wait = new WebDriverWait(driver ,timeout, timeout);
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(payeeAddedMessage));
        Thread.sleep(2000);
        String message = driver.findElement(payeeAddedMessage).getText();
        System.out.println(message);

        return message;
    }
    public List<String> getPayeeNameList(WebDriver driver) throws InterruptedException {

        List<WebElement> payeesNameList = new ArrayList<WebElement>();
        payeesNameList = driver.findElements(payeeNameList);
        int count=payeesNameList.size();
        System.out.println(("PAyee count: "+count));
        List<String> payeesNames = new ArrayList<String>();

        for(int i=0;i<count;i++){
            payeesNames.add(i,payeesNameList.get(i).getText());
                    //set(i,payeesNameList.get(i).getText());
            //System.out.println(payeesNames.get(i));
        }

        return payeesNames;
    }
    public WebElement getPayeeNameReqErrMsg(WebDriver driver) throws InterruptedException {

        WebElement errMessage = driver.findElement(payeeNameReqErrMsg);
        System.out.println((errMessage));

        return errMessage;
    }
    public void getPayeeNamebyDescOrder(WebDriver driver) throws InterruptedException {

        driver.findElement(nameInDescOrderBtn).click();

    }
    //header[@class="CustomPage-header"]
}
