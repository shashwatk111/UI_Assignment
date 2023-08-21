package testCases;

import org.testng.annotations.Listeners;
import pages.*;
import utils.TestBase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Listeners(utils.Listeners.class)
public class Amazon_Test_Cases extends TestBase {

    Login_Page amazonLoginPage;
    Amazon_Home_Page amazonHomePage;
    Google_Home_Page googleHomePage;
    Electronics_Page electronicsPage;
    Wish_List_Page wishListPage;

    @BeforeClass
    public void initializeDriver() {
        amazonLoginPage = new Login_Page(driver);
        amazonHomePage = new Amazon_Home_Page(driver);
        googleHomePage = new Google_Home_Page(driver);
        electronicsPage = new Electronics_Page(driver);
        wishListPage = new Wish_List_Page(driver);
    }

    @Test(description = "Validate the product is added to the wish list")
    public void validate_product_is_added_to_the_wish_list(){
        googleHomePage.printGoogleSearchResults();
        amazonHomePage.navigateToAmazon();
        amazonLoginPage.signInToAmazon();
        amazonHomePage.navigateToElectronics();
        electronicsPage.searchDellComputers();
        electronicsPage.applyPriceRangeFilter();
        electronicsPage.validateProductPricesAndRatings();
        electronicsPage.addFirstFiveStarProductToWishList();
        wishListPage.validateProductInWishList();
    }
}
