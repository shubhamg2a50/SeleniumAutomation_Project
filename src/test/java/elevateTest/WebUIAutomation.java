package elevateTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebUIAutomation {
    public static void main(String[] args) {
       
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.amazon.in/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));

        searchBox.sendKeys("Laptop");

        WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-component-type='s-search-results']")));

        List<WebElement> results = driver.findElements(By.cssSelector("[data-component-type='s-search-results']"));

        boolean found = false;
        for (WebElement result : results) {
            String title = result.getText();
            System.out.println("Product Title: " + title);
            if (title.toLowerCase().contains("laptop")) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Test Passed: Laptop found in search results.");
        } else {
            System.out.println("Test Failed: No Laptop found.");
        }

        driver.quit();
    }
}
