package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.ElementActions;
import utils.Log;
import utils.TestBase;


public class Amazon_Home_Page {

    WebDriver driver;
    ElementActions perform;;
    private static Logger log = LogManager.getLogger(Amazon_Home_Page.class);

    @FindBy(xpath = "//a[@href='https://www.amazon.in/']")
    WebElement amazonLinkElement;

    @FindBy(xpath = "//span[text()='Hello, sign in']")
    WebElement signInElement;

    @FindBy(id = "searchDropdownBox")
    WebElement searchDropDownElement;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement submitButtonElement;

    public Amazon_Home_Page(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void navigateToAmazon()
    {
        //Click on the link which takes to the amazon home page.
        perform.clickOn(amazonLinkElement);
        log.info("Open Amazon home page");

        //Validate Amazon application
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains("Amazon.in"), "Failed to open the browser or incorrect webpage.");

        //Click on Sign In
        perform.clickOn(signInElement);
    }


    public void navigateToElectronics()
    {
        //click on all buttons on search & select Electronics
        perform.sleep(5000);
//        perform.waitTillElementIsClickable(10,searchDropDownElement);
        Select select = new Select(searchDropDownElement);
        select.selectByVisibleText("Electronics");
        log.info("Click on All buttons on search & select Electronics");
        //Click on Search button
        perform.clickOn(submitButtonElement);
    }

}
