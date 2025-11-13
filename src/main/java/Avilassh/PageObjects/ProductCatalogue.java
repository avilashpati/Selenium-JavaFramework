package Avilassh.PageObjects;

import Avilassh.Utility.UtilMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductCatalogue extends UtilMethods {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".mb-3 b")
    List<WebElement> items;

    @FindBy(css = ".mb-3 button:last-of-type")
    WebElement addToCart;

    @FindBy(css = "div[aria-label='Product Added To Cart']")
    WebElement cartAdditionNotification;

    @FindBy(css = "div[aria-label='Login Successfully']")
    WebElement loginSuccessToast;


    By ItemsList = By.cssSelector(".mb-3");
    By toast = By.id("toast-container");
    By cart = By.cssSelector("button[routerlink*='cart']");


    public List<WebElement> getItemList(){
        WaitForElementToAppear(ItemsList);
        return items;
    }

    public void addProductToCart(List<WebElement> itemsSet,String desiredItem){
        for(WebElement selectedItem:itemsSet){
            if(selectedItem.getText().equalsIgnoreCase(desiredItem)){
                addToCart.click();
            }
        }
    }

    public String fetchProductAddedToCartSuccessToast(){
        WaitForWebElementToAppear(cartAdditionNotification);
        return cartAdditionNotification.getText();
    }

    public String fetchLoginSuccessToast(){
        WaitForWebElementToAppear(loginSuccessToast);
        return loginSuccessToast.getText();
    }


    public void waitForCartIconToBeClickable(){
        WaitForElementToDisappear(toast);
        WaitForElementToAppear(cart);
    }
}
