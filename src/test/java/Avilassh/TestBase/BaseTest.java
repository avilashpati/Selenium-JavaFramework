package Avilassh.TestBase;

import Avilassh.PageObjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir")
                        + "//src//main//java//Avilassh//Resources//GlobalData.properties");

        prop.load(fis);

        String browserName = System.getProperty("browser") != null ?
                System.getProperty("browser") :
                prop.getProperty("browser");

        System.out.println("[DRIVER] Initializing browser: " + browserName);

        if (browserName.contains("chrome")) {

            ChromeOptions options = new ChromeOptions();

            WebDriverManager.chromedriver().setup();

            if (browserName.contains("headless") || System.getenv("CI") != null) {
                options.addArguments("--headless=new");
                System.out.println("[DRIVER] Headless mode enabled");
            }

            // Required for Linux CI environments
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            driver = new ChromeDriver(options);

        } else if (browserName.equalsIgnoreCase("firefox")) {

            System.out.println("Firefox setup not implemented yet");

        } else if (browserName.equalsIgnoreCase("edge")) {

            System.out.println("Edge setup not implemented yet");

        } else {

            System.out.println("Please enter valid browser name");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        if (System.getenv("CI") != null) {
            driver.manage().window().setSize(new Dimension(1920, 1080));
            System.out.println("[ENV] Running in CI mode - window size set to 1920x1080");
        } else {
            driver.manage().window().maximize();
            System.out.println("[ENV] Running in LOCAL mode - window maximized");
        }

        return driver;
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;

        File source = ts.getScreenshotAs(OutputType.FILE);

        File file = new File(System.getProperty("user.dir")
                + "//reports//" + testCaseName + ".png");

        FileUtils.copyFile(source, file);

        return System.getProperty("user.dir")
                + "//reports//" + testCaseName + ".png";
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

        String jsonContent = FileUtils.readFileToString(
                new File(filePath), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();

        List<HashMap<String, String>> data =
                mapper.readValue(jsonContent,
                        new TypeReference<List<HashMap<String, String>>>() {});

        return data;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        System.out.println("[TEST] Launching application...");
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        System.out.println("[TEST] Application launched successfully");
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void TearDown() {
        System.out.println("[TEST] Tearing down driver...");
        if (driver != null) {
            driver.quit();
        }
        System.out.println("[TEST] Driver closed");
    }
}
