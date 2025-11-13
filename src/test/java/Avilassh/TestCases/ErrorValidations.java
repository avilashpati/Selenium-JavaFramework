package Avilassh.TestCases;

import Avilassh.PageObjects.ProductCatalogue;
import Avilassh.TestBase.BaseTest;
import Avilassh.TestBase.RetryTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidations extends BaseTest {

    @Test(retryAnalyzer = RetryTest.class)
    public void loginWithInvalidCredentials(){
        landingPage.loginApplication("avilashpatirkl@gmail.com", "Avilash@495");
        Assert.assertEquals("Incorrect email or password.", landingPage.fetchErrorMessage());
    }

    @Test(groups={"Smoke"})
    public void loginWithValidCredentials(){
        ProductCatalogue productCatalogue = landingPage.loginApplication("avilashpatirkl@gmail.com", "Avilash@496");
        Assert.assertEquals("Login Successfully", productCatalogue.fetchLoginSuccessToast());
    }

}
