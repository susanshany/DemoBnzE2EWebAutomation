package PageObjects;

import Resources.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LandingPage extends Base {
    WebDriver driver;

    By balances = By.xpath("//div[@class=\"account-info\"]/span[@class=\"account-balance\"]");
    By accountNames = By.xpath("//div[@class=\"account-info\"]/span/h3");

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goTo()
    {
        driver.get(prop.getProperty("url"));
    }
    public List<String> getBalanceList() {
        List<WebElement> balanceListElements = new ArrayList<WebElement>();
        balanceListElements = driver.findElements(balances);

        List<String> balanceList = new ArrayList<String>();

        for (int i = 0; i < balanceListElements.size(); i++) {
            balanceList.add(i, balanceListElements.get(i).getText());
        }

        return balanceList;
    }
    public List<String> getAccountList() {
        List<WebElement> accountListElements = new ArrayList<WebElement>();
        accountListElements = driver.findElements(accountNames);

        List<String> accountList = new ArrayList<String>();

        for (int i = 0; i < accountListElements.size(); i++) {
            accountList.add(i, accountListElements.get(i).getText());
            System.out.println(accountList);
        }

        return accountList;
    }

}
