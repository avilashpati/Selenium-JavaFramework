package Avilassh.Utility;

import Avilassh.PageObjects.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UtilMethods {

    WebDriver driver;

    public UtilMethods(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "button[routerlink*='cart']")
    WebElement cartButton;

    private int getWaitTimeout() {
        return System.getenv("CI") != null ? 15 : 5;
    }

    public void WaitForElementToAppear(By findBy){
        System.out.println("[WAIT] Waiting for element to appear: " + findBy);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(getWaitTimeout()));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
        System.out.println("[WAIT] Element visible: " + findBy);
    }

    public void WaitForWebElementToAppear(WebElement findBy){
        System.out.println("[WAIT] Waiting for WebElement to appear: " + findBy);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(getWaitTimeout()));
        wait.until(ExpectedConditions.visibilityOf(findBy));
        System.out.println("[WAIT] WebElement visible");
    }

    public void WaitForElementToDisappear(By findBy){
        System.out.println("[WAIT] Waiting for element to disappear: " + findBy);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(getWaitTimeout()));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
        System.out.println("[WAIT] Element gone: " + findBy);
    }

    public void jsClick(WebElement element){
        System.out.println("[CLICK] JS click on element: " + element);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        System.out.println("[CLICK] JS click executed");
    }

    public CartPage clickOnCart(){
        cartButton.click();
        return new CartPage(driver);
    }

}
