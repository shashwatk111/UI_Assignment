package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class TestBase extends ConfigDataReader{
    private static Logger log = LogManager.getLogger(TestBase.class);

//    ExtentTest logger;
    public static WebDriver driver;

    @BeforeSuite
    public void setUpSuite()
    {
        configFileReader();
        excelFileReader();
    }

    @BeforeTest
    public void browserInitialization()
    {

        switch (getConfigValue("browser")) {

            case "chrome":
                driver = WebDriverManager.chromedriver().create();
                break;
            case "firefox":
                driver = WebDriverManager.firefoxdriver().create();
                break;
            case "edge":
                driver = WebDriverManager.edgedriver().create();
                break;
            default:
                break;
        }

        log.info("Launch the browser");
        driver.manage().window().maximize();
        String application_url = getConfigValue("applicationUrl");
        driver.get(application_url);
        validateApplicationOpening();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void validateApplicationOpening(){
        String expectedTitle = "Google";
        String actualTitle = driver.getTitle();

        if(actualTitle.equals(expectedTitle)){
            log.info("Open Google home page");
        }else{
            Assert.fail("Failed to open the browser or incorrect webpage.");
        }
    }

    public int convertOffScreenPriceToWholePrice(String price) throws NumberFormatException
    {
        String wholePrice = price.replaceAll(",","");
        return Integer.parseInt(wholePrice);
    }

    public String takeScreenShot(String testMethodName)
    {
        File sourceFilePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String filePath = "target/Screenshots/"+testMethodName+".jpg";
        File destinationFilePath = new File(filePath);
        try {
            FileUtils.copyFile(sourceFilePath, destinationFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filePath;
    }

    @AfterTest
  public void closeBrowser()
    {
        driver.quit();
    }

}
