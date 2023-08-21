package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.ElementActions;
import utils.Log;
import utils.TestBase;

import java.util.List;


public class Electronics_Page extends TestBase {
    private static Logger log = LogManager.getLogger(Electronics_Page.class);

    WebDriver driver;
    ElementActions perform;

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchBoxElement;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement submitButtonElement;

    @FindBy(xpath = "//input[@placeholder='Min']")
    WebElement minimumPriceElement;

    @FindBy(xpath = "//input[@placeholder='Max']")
    WebElement maximumPriceElement;

    @FindBy(id = "a-autoid-1")
    WebElement goButtonElement;

    @FindBy(xpath = "//span[contains(text(),'Price')]")
    WebElement priceElement;

    @FindBy(xpath = "//div[@class='a-row a-size-base a-color-base']//span[@class='a-price-whole']")
    List<WebElement> wholeProductPriceElement;

    @FindBy(xpath = "//a[text()='Next']")
    WebElement nextPageElement;

    @FindBy(xpath = "//span[text()='5.0 out of 5 stars']//../../../../../../../div/h2//span")
    List<WebElement> productRatingElements;

    @FindBy(id = "wishListMainButton")
    WebElement addToWishListElement;

    @FindBy(xpath = "(//span[@aria-label='5.0 out of 5 stars'])[1]/parent::div//parent::div/preceding-sibling::div/h2/a")
    WebElement first_product_with_5_star;

    public Electronics_Page(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void searchDellComputers()
    {
        //Search "dell computers"
        perform.enterText(searchBoxElement,"dell computers");

        perform.clickOn(submitButtonElement);
        log.info("Search dell computers");
        //Scroll down to enter price range
        perform.scrollToElement(priceElement);
    }

    public void applyPriceRangeFilter()
    {
        //Apply the filter of range Rs 30000 to 50000
        perform.enterText(minimumPriceElement,"30000");
        perform.enterText(maximumPriceElement,"50000");

        //Click on Go button
        perform.clickOn(goButtonElement);

        log.info("Apply the filter of range Rs 30000 to 50000");
    }

    public void validateProductPricesAndRatings()
    {
        //validate product price range on page 1
        validatePriceRange();

        //validate Product Rating on page 1
        printProductNameWith5StarRating(1);

        //Navigate to page 2
        navigateToSecondPage();
        log.info("Navigate to Page 2");

        //validate product price range on page 2
        validatePriceRange();
        //validate Product Rating
        printProductNameWith5StarRating(2);
        log.info("Validate products with 5 star rating");

        //Navigate back to page 1
        perform.backOperation();
        log.info("Navigate back to Page 1");
    }

    private void printProductNameWith5StarRating(int pageNumber)
    {
        //Print product names with 5 star rating
        for(WebElement product : productRatingElements)
        {
            log.info("Product with 5 star rating on page "+pageNumber+" : "+product.getText());
        }

    }

    public void navigateToSecondPage()
    {
        //Scroll down till next page element is located
        perform.scrollToElement(nextPageElement);
        //Click on next page
        perform.clickOn(nextPageElement);
    }

    public void validatePriceRange()
    {
            //Validate product price range is between Rs. 30000 and 50000
            int productCount = wholeProductPriceElement.size();

            for (int j = 0; j < productCount; j++) {
                String price = wholeProductPriceElement.get(j).getText();
                int wholePrice = convertOffScreenPriceToWholePrice(price);

                Assert.assertTrue(wholePrice >= 30000 && wholePrice <= 50000,"Product price is not within the range");
            }

    }

    public void addFirstFiveStarProductToWishList()
    {
        perform.waitTillElementIsVisible(10,first_product_with_5_star);

        perform.scrollToElement(first_product_with_5_star);

        //Click on first product
        perform.clickOn(first_product_with_5_star);

        //Switch to new window
        perform.switchToNewWindow();
        log.info("Click on first product which has 5 star rating");

        //Scroll down till "Add to wish list" element is visible
        perform.scrollToElement(addToWishListElement);

        //Click on "Add to wish list"
        perform.clickOn(addToWishListElement);
        log.info("Product added to wish list");
    }

}
