
import PageObjects.LandingPage;
import PageObjects.MenuOnPage;
import PageObjects.PayeePage;
import PageObjects.PaymentPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Resources.Base;




public class PayeesPageTest extends Base{
    //public static Logger log = LogManager.getLogger(Base.class.getName());

    @Test(invocationCount = 3)
    public void payeesPageNavigation()throws IOException, InterruptedException
             {
        // initialize driver
        WebDriver driver = initializeDriver();

        // navigate to the demoBNZ WebSite URL
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();

        MenuOnPage menu=new MenuOnPage(driver);
        PayeePage payee=new PayeePage(driver);
        menu.getMenuBtn().click();
        menu.getPayeeBtn().click();
        Assert.assertEquals(payee.getMenuBtn(),"Payees");
    }

    @Test(invocationCount = 3)
    public void addNewPayeeInPayeePage()throws IOException, InterruptedException
    {
        // initialize driver
        WebDriver driver = initializeDriver();

        // navigate to the demoBNZ WebSite URL
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();

        MenuOnPage menu=new MenuOnPage(driver);
        PayeePage payeePage=new PayeePage(driver);

        menu.getMenuBtn().click();
        menu.getPayeeBtn().click();
        payeePage.clickAddPayeeBtn();
        payeePage.setAddPayeeName("Mathew");
        payeePage.setAddPayeeAcNo("0276670228009087");
        payeePage.clickAddNameAcNoBtn();
        String message=payeePage.getSuccessMessage(driver);

        Assert.assertEquals(payeePage.getSuccessMessage(driver),"Payee added");

        List<String> payeesNames = new ArrayList<String>();
        payeesNames=payeePage.getPayeeNameList(driver);
        Boolean flag = false;
        for(int i=0;i<payeesNames.size();i++){
            if(payeesNames.get(i).equalsIgnoreCase("Mathew")) {
                flag=true;
                break;
            }
        }
        Assert.assertTrue(flag);
    }
    @Test(invocationCount = 3)
    public void verifyPayeeNameRequiredInPayeePage()throws IOException, InterruptedException
    {
        // initialize driver
        WebDriver driver = initializeDriver();

        // navigate to the demoBNZ WebSite URL
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();

        MenuOnPage menu=new MenuOnPage(driver);
        PayeePage payeePage=new PayeePage(driver);
        menu.getMenuBtn().click();
        menu.getPayeeBtn().click();
        payeePage.clickAddPayeeBtn();
        payeePage.clickAddNameAcNoBtn();

        Assert.assertTrue(payeePage.getPayeeNameReqErrMsg(driver).isDisplayed());
        Assert.assertEquals(payeePage.getPayeeNameReqErrMsg(driver).getText(),"Payee Name is a required field. Please complete to continue.");

        payeePage.setAddPayeeName("Mathew");
        Assert.assertFalse(payeePage.getPayeeNameReqErrMsg(driver).isDisplayed());

    }

    @Test(invocationCount = 3)
    public void verifyNameDispyAscInPayeePage()throws IOException, InterruptedException
    {
        // initialize driver
        WebDriver driver = initializeDriver();

        // navigate to the demoBNZ WebSite URL
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();

        MenuOnPage menu=new MenuOnPage(driver);
        PayeePage payeePage=new PayeePage(driver);
        menu.getMenuBtn().click();
        menu.getPayeeBtn().click();
        payeePage.clickAddPayeeBtn();
        payeePage.setAddPayeeName("Sam");
        payeePage.setAddPayeeAcNo("0276670228009087");
        payeePage.clickAddNameAcNoBtn();
        List<String> payeesNames = new ArrayList<String>();
        payeesNames=payeePage.getPayeeNameList(driver);
        List sortedList = new ArrayList(payeesNames);
        Collections.sort(sortedList);
        Assert.assertTrue(payeesNames.equals(sortedList));

        payeePage.getPayeeNamebyDescOrder(driver);
        List<String> payeesNamesListDesc = new ArrayList<String>();
        payeesNamesListDesc=payeePage.getPayeeNameList(driver);
        List sortedNameListDesc = new ArrayList(payeesNamesListDesc);
        Collections.sort(sortedNameListDesc);
        Collections.reverse(sortedNameListDesc);
        Assert.assertTrue(payeesNamesListDesc.equals(sortedNameListDesc));
    }
    @Test(invocationCount = 3)
    public void navigateToPaymentPage()throws IOException, InterruptedException
    {
        // initialize driver
        WebDriver driver = initializeDriver();
        // navigate to the demoBNZ WebSite URL
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();

        MenuOnPage menu=new MenuOnPage(driver);
        PayeePage payee=new PayeePage(driver);
        PaymentPage paymentPage=new PaymentPage(driver);
        LandingPage landingPage=new LandingPage(driver);

        List<String> balanceList = new ArrayList<String>();
        List<String> accountList = new ArrayList<String>();
        balanceList=landingPage.getBalanceList();
        accountList=landingPage.getAccountList();
        Double everydayAcBalanceBeforeTrns = null;
        Double billsAcBalanceBeforeTrns= null;

        for(int i=0;i<balanceList.size();i++){
            if(accountList.get(i).equalsIgnoreCase("Everyday")){
                everydayAcBalanceBeforeTrns=Double.parseDouble(balanceList.get(i).replace(",",""));
                System.out.println("everyday balance before transfer"+everydayAcBalanceBeforeTrns);
            }
            else if (accountList.get(i).equalsIgnoreCase("Bills")) {
                billsAcBalanceBeforeTrns=Double.parseDouble(balanceList.get(i).replace(",",""));
                System.out.println("bills balance before transfer"+billsAcBalanceBeforeTrns);
            }
        }

        menu.getMenuBtn().click();
        menu.getPayOrTransferBtn().click();


        paymentPage.setFromAccount("Everyday");
        paymentPage.setToAccount("Bills");
        paymentPage.setAmountTransfer("500");
        paymentPage.transferAmount();
        String successMessage = paymentPage.getSuccessfulMessage();
        Assert.assertEquals(successMessage,"Transfer successful");

        balanceList=landingPage.getBalanceList();
        accountList=landingPage.getAccountList();
        Double everydayAcBalanceAfterTrns = null;
        Double billsAcBalanceAfterTrns = null;
        for(int i=0;i<balanceList.size();i++){
            if(accountList.get(i).equalsIgnoreCase("Everyday")){
                everydayAcBalanceAfterTrns=Double.parseDouble(balanceList.get(i).replace(",",""));
                System.out.println(everydayAcBalanceAfterTrns);
            }
            else if (accountList.get(i).equalsIgnoreCase("Bills")) {
                billsAcBalanceAfterTrns=Double.parseDouble(balanceList.get(i).replace(",",""));
                    System.out.println((billsAcBalanceAfterTrns));


            }
        }
       Assert.assertEquals(everydayAcBalanceAfterTrns,(everydayAcBalanceBeforeTrns-500));
        Assert.assertEquals(billsAcBalanceAfterTrns,(billsAcBalanceBeforeTrns+500));


    }


}
