package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class ElementActions extends TestBase{
    private static Logger log = LogManager.getLogger(TestBase.class);
    static WebDriver driver = TestBase.driver;

    public static void clickOn(WebElement element){
        try{
            element.click();
        }catch (NoSuchElementException e){
            log.error("Unable to find the element !! ", element);
        }
    }

    public static void enterText(WebElement element, String text){
        try{
            element.sendKeys(text);
        }catch (NoSuchElementException e){
            log.error("Unable to find the element !! ", element);
        }
    }

    public static String getText(WebElement element){
        String text = "";
        try{
           text = element.getText();
        }catch (NoSuchElementException e){
            log.error("Unable to find the element !! ", element);
        }
        return text;
    }

    public static void scrollToElement(WebElement element)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void backOperation()
    {
        driver.navigate().back();
    }

    public static void switchToNewWindow()
    {
        //Multiple window handle
        Set<String> windows = driver.getWindowHandles();
        String mainWindow = driver.getWindowHandle();

        for (String childWindow:windows)
        {
            if(!(childWindow == mainWindow))
            {
                //Switch to child window
                driver.switchTo().window(childWindow);
            }
        }
    }

    public static void waitTillElementIsVisible(int duration, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitTillElementIsClickable(int duration, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitTillAllElementsAreVisible(int duration, List<WebElement> element)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public static void sleep(int duration)
    {
        try{
            Thread.sleep(duration);
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
