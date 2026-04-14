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

    @FindBy(css = ".mb-3")
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
    By addToCartBtn = By.cssSelector("button:last-of-type");


    public List<WebElement> getItemList(){
        System.out.println("[CATALOGUE] Waiting for product list to load");
        WaitForElementToAppear(ItemsList);
        System.out.println("[CATALOGUE] Products loaded: " + items.size() + " items found");
        return items;
    }

    public void addProductToCart(List<WebElement> itemsSet, String desiredItem){
        System.out.println("[CATALOGUE] Waiting for toast to disappear before clicking Add to Cart");
        WaitForElementToDisappear(toast);
        System.out.println("[CATALOGUE] Searching for product: " + desiredItem);
        for(WebElement selectedItem : itemsSet){
            String itemName = selectedItem.findElement(By.cssSelector("b")).getText();
            if(itemName.equalsIgnoreCase(desiredItem)){
                System.out.println("[CATALOGUE] Product found: " + itemName + " - clicking Add to Cart");
                WebElement btn = selectedItem.findElement(addToCartBtn);
                WaitForWebElementToAppear(btn);
                jsClick(btn);
                System.out.println("[CATALOGUE] Add to Cart clicked successfully");
                break;
            }
        }
    }

    public String fetchProductAddedToCartSuccessToast(){
        System.out.println("[CATALOGUE] Waiting for cart addition toast");
        WaitForWebElementToAppear(cartAdditionNotification);
        String msg = cartAdditionNotification.getText();
        System.out.println("[CATALOGUE] Cart toast message: " + msg);
        return msg;
    }

    public String fetchLoginSuccessToast(){
        System.out.println("[CATALOGUE] Waiting for login success toast");
        WaitForWebElementToAppear(loginSuccessToast);
        String msg = loginSuccessToast.getText();
        System.out.println("[CATALOGUE] Login toast message: " + msg);
        return msg;
    }


    public void waitForCartIconToBeClickable(){
        WaitForElementToDisappear(toast);
        WaitForElementToAppear(cart);
    }
}
