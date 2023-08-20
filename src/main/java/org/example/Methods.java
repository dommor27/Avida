package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Methods {


    public void loginToMail(WebDriver driver) throws IOException, InterruptedException {
        WebElement emailID =  driver.findElement(By.id("ego_user"));
        WebElement passwordId =  driver.findElement(By.id("ego_secret"));
        WebElement prihlasitBtn =  driver.findElement(By.id("ego_submit"));


        emailID.click();
        emailID.sendKeys(getProperties("email"));
        passwordId.click();
        passwordId.sendKeys(getProperties("password"));
        prihlasitBtn.click();
        Thread.sleep(2000);
    }


    public void navigateToWriteEmailButton(WebDriver driver) throws IOException, InterruptedException {
        WebElement writeNewEmail =  driver.findElement(By.id("compose_button"));
        writeNewEmail.click();
    }
    public void sendButton(WebDriver driver) throws IOException, InterruptedException {
        WebElement sendButton =  driver.findElement(By.id("qa_email_send_uppe"));
        sendButton.click();
    }

    public void addRecepients(WebDriver driver,String recepientsToSearch) throws IOException, InterruptedException {
        driver.findElement(By.id("quick_contact_search")).click();
        driver.findElement(By.id("quick_contact_search")).sendKeys(recepientsToSearch);

        List<WebElement> searchReciepents = driver.findElements(By.id("quickabook_div"));

        for ( WebElement searchContact : searchReciepents) {
            WebElement contact = searchContact.findElement(By.tagName("ul"));
            if (contact.getText().equalsIgnoreCase(recepientsToSearch)) {
                contact.click();
            }
        }
    }
    public void addAttachment(WebDriver driver,String path) throws IOException, InterruptedException {
        driver.findElement(By.xpath("//span[text()='Pridať prílohu']")).sendKeys(path);

    }
    public void writeTextContent(WebDriver driver,String text) throws IOException, InterruptedException {
        driver.findElement(By.id("tinymce")).click();
        driver.findElement(By.id("tinymce")).sendKeys(text);
    }
    public void writeEmail(WebDriver driver,String reciepient, String attachmentPath,String plainText) throws IOException, InterruptedException {
        navigateToWriteEmailButton(driver);
        addRecepients(driver,reciepient);
        addAttachment(driver, attachmentPath);

        driver.findElement(By.id("subject_input")).click();
        driver.findElement(By.id("subject_input")).sendKeys("object");

        writeTextContent(driver,plainText);
        sendButton(driver);
        Thread.sleep(2000);

    }


    public CharSequence getProperties(String parameter) throws IOException {
    FileReader reader=new FileReader("D:\\Actimize_Projects\\SAM\\sam-test master\\Indra Avitech\\src\\TestData.properties");
    Properties props = new Properties();
    props.load(reader);

    return props.getProperty(parameter);


    }
}
