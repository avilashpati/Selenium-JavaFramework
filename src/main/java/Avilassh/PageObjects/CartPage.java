package Avilassh.PageObjects;

import Avilassh.Utility.UtilMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends UtilMethods {
    WebDriver driver;

    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[contains(text(),'Checkout')]")
    WebElement checkoutButton;

    @FindBy(css = ".infoWrap h3")
    List<WebElement> itemsInCart;

    public void clickOnCheckout(){
        checkoutButton.click();
    }

    public List<WebElement> getItemsInCart(){
        WaitForWebElementToAppear(checkoutButton);
        return itemsInCart;
    }

    public void checkPresenceofSelectedItemInCart(List<WebElement> items, String productName){
        for(WebElement item : items){
            if(item.getText().equalsIgnoreCase(productName)){
                System.out.println("Item is present in the cart");
            }
        }
    }

}
