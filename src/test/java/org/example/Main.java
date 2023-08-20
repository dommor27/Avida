package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;


import java.io.IOException;


public class Main  {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.setProperty("webdriver.edge.driver",
                "D:\\edgeDriver\\msedgedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //  options.addArguments("headless");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("window-size=1400,800");
        options.addArguments("disable-gpu");
        options.addArguments( "--disable-gpu", "--window-size=1400,800","--ignore-certificate-errors");
        WebDriver driver = new EdgeDriver(options);
        Methods methods = new Methods();

        driver.get("https://user.centrum.sk/");
        Thread.sleep(2000);

        //Cookies
        WebElement cookies = driver.findElement(By.xpath("//button[text()='Nesúhlasím']"));
        cookies.click();

        Thread.sleep(5000);

        methods.loginToMail(driver);
        methods.writeEmail(driver,"Dominik","D:\\Actimize_Projects\\SAM\\sam-test master\\Indra Avitech\\src\\main\\resources\\Ranč TARA cenník.doc","THIS IS A EAIL TEXT");

        //Validation element
        WebElement message = driver.findElement(By.id("message_notification"));
        String ExpectedText = "Sprava bola uspesne odoslana";

        Assert.assertEquals(message,ExpectedText);

    }

}