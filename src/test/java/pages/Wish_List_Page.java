package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.ElementActions;
import utils.Log;
import utils.TestBase;

public class Wish_List_Page extends TestBase {

    WebDriver driver;
    ElementActions perform;
    private static Logger log = LogManager.getLogger(Wish_List_Page.class);

    @FindBy(xpath = "//span[contains(text(),'item added to')]")
    WebElement itemAddedTextElement;

    @FindBy(xpath = "//a[text()='View Your List']")
    WebElement wishListElement;

    @FindBy(name = "submit.deleteItem")
    WebElement deleteButtonElement;

    public Wish_List_Page(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void validateProductInWishList()
    {
        //Validate product is added to the wish list
        String itemAddedText = perform.getText(itemAddedTextElement);
        Assert.assertTrue(itemAddedText.contains("item added"),"Item not added in wish list");
        log.info("Validated product is added to the wish list");
        deleteProductFromWishList();
    }

    private void deleteProductFromWishList()
    {
        //Go to wish list
        perform.clickOn(wishListElement);

        perform.sleep(2000);
        //Delete product in wish list if exist
        if(deleteButtonElement.isDisplayed())
        {
            perform.clickOn(deleteButtonElement);
        }
    }

}
