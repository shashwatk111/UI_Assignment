package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementActions;
import utils.Log;
import utils.TestBase;

import java.util.List;

public class Google_Home_Page {

    WebDriver driver;
    ElementActions perform;

    private static Logger log = LogManager.getLogger(Google_Home_Page.class);
    @FindBy(name = "q")
    WebElement googleSearchBarElement;

    @FindBy(xpath = "//li//div[@role='presentation' and not(@style)]/span")
    List<WebElement> searchListElement;

    @FindBy(xpath = "//li//div[@role='presentation' and not(@style)]/span[text()='Amazon']")
    WebElement amazonTextElement;

    public Google_Home_Page(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void printGoogleSearchResults()
    {
        //Enter the keyword "amazon" in the search bar
        perform.enterText(googleSearchBarElement, "amazon");
        log.info("Enter keyword 'amazon' in the search bar");

        //Print all the search results
        googleSearchResults();

        //Click on the link which takes to the amazon login page
        navigateToAmazon();
    }

    public void googleSearchResults() throws StaleElementReferenceException
    {
        int searchListCount = searchListElement.size();
        log.info("Google Search Results :: ");
        for(int i=0; i<searchListCount; i++)
        {
            log.info(searchListElement.get(i).getText());
        }
    }

    public void navigateToAmazon()
    {
        //Click on "Amazon" from search results
        perform.clickOn(amazonTextElement);
        log.info("Click on link which takes to amazon login page");
    }
}
