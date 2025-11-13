package Avilassh.TestCases;

import Avilassh.PageObjects.CartPage;
import Avilassh.PageObjects.LandingPage;
import Avilassh.PageObjects.ProductCatalogue;
import Avilassh.TestBase.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderFlow extends BaseTest {

    // Testing QA 
    @Test(dataProvider = "getData",groups = {"Products","Smoke"})
    public void OrderAdditionToCart(HashMap<String,String> input) throws IOException {
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
        String loginSuccess = productCatalogue.fetchLoginSuccessToast();
        Assert.assertEquals(loginSuccess,"Login Successfully");
        List<WebElement> productsList = productCatalogue.getItemList();
        productCatalogue.addProductToCart(productsList, input.get("product"));
        String cartAdditionSuccess = productCatalogue.fetchProductAddedToCartSuccessToast();
        Assert.assertEquals(cartAdditionSuccess, "Product Added To Cart");
    }

    /**********Approach -1 : Hardcoded Data*************/
//    @DataProvider
//    public Object[][] getData(){
//        return new Object[][]{{"avilashpatirkl@gmail.com","Avilash@496","ZARA COAT 3"}};
//    }

    /**********Approach -2 : Data Passed as Hashmap*************/
//    @DataProvider
//    public Object[][] getData(){
//        HashMap<String,String> map = new HashMap<String,String>();
//        map.put("email","avilashpatirkl@gmail.com");
//        map.put("password", "Avilash@496");
//        map.put("product", "ZARA COAT 3");
//        return new Object[][]{{map}};
//    }

    /**********Approach -3 : Data Passed from Json*************/
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Avilassh//TestData//Order.json");
        return new Object[][]{{data.get(0)}};
    }

//    @Test(dependsOnMethods = {"OrderAdditionToCart"})
//    public void validateProductDetailsInCart(){
//        ProductCatalogue productCatalogue = landingPage.loginApplication("avilashpatirkl@gmail.com", "Avilash@496");
//        String loginSuccess = productCatalogue.fetchLoginSuccessToast();
//        System.out.println(loginSuccess);
//        productCatalogue.waitForCartIconToBeClickable();
//        CartPage cartPage = productCatalogue.clickOnCart();
//        List<WebElement> selectedItems = cartPage.getItemsInCart();
//        cartPage.checkPresenceofSelectedItemInCart(selectedItems, "ZARA COAT 3");
//        cartPage.clickOnCheckout();
//    }
}
