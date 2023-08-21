package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementActions;
import utils.Log;
import utils.TestBase;

public class Login_Page {

    WebDriver driver;
    ElementActions perform ;
    private static Logger log = LogManager.getLogger(Login_Page.class);

    @FindBy(id = "ap_email")
    WebElement emailTextBoxElement;

    @FindBy(id = "ap_password")
    WebElement passwordTextBoxElement;

    @FindBy(id = "continue")
    WebElement continueButtonElement;

    @FindBy(id= "signInSubmit")
    WebElement signInButtonElement;

    public Login_Page(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void signInToAmazon()
    {
        //Enter email
        perform.enterText(emailTextBoxElement,TestBase.EMAIL);
        //Click on Continue
        perform.clickOn(continueButtonElement);
        //Enter password
        perform.enterText(passwordTextBoxElement,TestBase.PASSWORD);
        //Click on Sign In
        perform.clickOn(signInButtonElement);

        log.info("Successfully sign in into amazon.com");
    }
}
