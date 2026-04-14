package Avilassh.PageObjects;

import Avilassh.Utility.UtilMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends UtilMethods {
    WebDriver driver;

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement userPassword;

    @FindBy(id = "login")
    WebElement loginButton;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    public void goTo(){
        System.out.println("[NAV] Navigating to application URL");
        driver.get("https://rahulshettyacademy.com/client");
        System.out.println("[NAV] Page loaded: " + driver.getTitle());
    }

    public String fetchErrorMessage(){
        System.out.println("[LOGIN] Fetching error message");
        WaitForWebElementToAppear(errorMessage);
        String msg = errorMessage.getText();
        System.out.println("[LOGIN] Error message: " + msg);
        return msg;
    }

    public ProductCatalogue loginApplication(String email, String password){
        System.out.println("[LOGIN] Attempting login with email: " + email);
        WaitForWebElementToAppear(userEmail);
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        jsClick(loginButton);
        System.out.println("[LOGIN] Login button clicked");
        return new ProductCatalogue(driver);
    }

}
