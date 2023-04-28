package Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.apache.commons.io.FileUtils;
import PageObjects.LandingPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.testng.annotations.*;

public class Base {
    //Declare WebDriver as global variable
    WebDriver driver;


    //Declare Properties as global variable
    public Properties prop;

    //Declare FileInputStream as global variable
    public FileInputStream propFile;

    public WebDriver initializeDriver() throws IOException {
        prop = new Properties();

        //get data.properties file
        propFile = new FileInputStream(
                "C:\\Shany's work\\Datacom Automation engineer\\UI Automation\\DemoBnzE2EWebAutomation\\src\\main\\java\\Resources\\data.properties");
        //load data.properties file
        prop.load(propFile);

        //get value of browser key from data.properties file
        String browserName = prop.getProperty("browser");


        if (browserName.equals("chrome")) {
            //chrome driver information
            System.setProperty("webdriver.chrome.driver", "C:\\Shany's work\\Datacom Automation engineer\\UI Automation\\DemoBnzE2EWebAutomation\\chromedriver.exe");

            //initialize Chrome driver
            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            File file = new File("C:\\Shany's work\\Datacom Automation engineer\\UI Automation\\DemoBnzE2EWebAutomation\\geckodriver.exe");
            System.setProperty("webdriver.firefox.marionette", file.getAbsolutePath());
            //initialize Firefox driver
            driver = new FirefoxDriver();
        } else if (browserName.equals("ie")) {
            File file = new File("C:\\Shany's work\\Datacom Automation engineer\\UI Automation\\DemoBnzE2EWebAutomation\\IEDriverServer.exe");
            System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
            driver = new InternetExplorerDriver();
        }
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        return driver;
    }


    public void tearDown()
    {
        driver.close();
    }

}
