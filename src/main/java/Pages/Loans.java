package Pages;

import CommonFunctions.BasePage;
import CommonFunctions.PropertyReader;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Loans extends BasePage {

    private WebDriver driver;

    //Locators
    private static String fieldSelect = "//label[normalize-space(text())='{FIELD}']/..//li/label[normalize-space(text())='{VALUE}']";
    private static String fieldInput = "//label[normalize-space(text())='{FIELD}']/..//input";
    private static String dropdown = "//select[@title='{FIELD}']";
    private static By btnStartOver = By.xpath("(//button[@aria-label='Start over'])[1]");
    private static By message = By.xpath("//div[@class='borrow__error__text']");

    public Loans(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToUrl() throws IOException {
        String url = getURL();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MICROSECONDS);
    }

    public String getURL() throws IOException {
        String master = PropertyReader.getProperty("MASTER");
        String landingPage = PropertyReader.getProperty("LANDING_PAGE");
        String intendedURL = PropertyReader.getProperty(landingPage);
        String finalURL = "https://"+master+intendedURL;
        return finalURL;
    }

    public void verifyLandingPageTitle(String title){
    Assert.assertTrue("Landing page is incorrect",driver.getTitle().contains(title));
    }

    public void selectDetails(DataTable dataTable){
        List<List<String>> fieldValues = dataTable.asLists();
        for(int i=0;i<fieldValues.get(0).size();i++){
            if(fieldValues.get(0).get(i).equalsIgnoreCase("Number of dependants")){
                By elem = By.xpath(dropdown.replace("{FIELD}",fieldValues.get(0).get(i)));
                waitForElementVisibility(driver,elem);
                Select select = new Select(driver.findElement(elem));
                select.selectByVisibleText(fieldValues.get(1).get(i));
            }else {
                WebElement elems = driver.findElement(By.xpath(fieldSelect.replace("{FIELD}", fieldValues.get(0).get(i)).replace("{VALUE}", fieldValues.get(1).get(i))));
                System.out.println("elem:"+fieldSelect.replace("{FIELD}", fieldValues.get(0).get(i)).replace("{VALUE}", fieldValues.get(1).get(i)));
                elems.click();
            }

        }
    }

    public void inputDetails(DataTable dataTable){
        List<List<String>> fieldValues = dataTable.asLists();
        for(int i=0;i<fieldValues.get(0).size();i++){
            WebElement elems = driver.findElement(By.xpath(fieldInput.replace("{FIELD}",fieldValues.get(0).get(i))));
            elems.sendKeys(fieldValues.get(1).get(i));
        }
    }

    public void verifyTheEstimates(String expEstimate) throws InterruptedException {
        driver.findElement(By.id("btnBorrowCalculater")).click();
        Thread.sleep(3000);
        String actEstimate = driver.findElement(By.id("borrowResultTextAmount")).getText();
        Assert.assertEquals("BorrowEstimation is calculated incorrectly",expEstimate,actEstimate);
    }

    public void clickOnStartOver(){
        driver.findElement(btnStartOver).click();
    }

    public void verifyZeroEstimates() throws InterruptedException {
        Thread.sleep(1000);
        String actEstimate = driver.findElement(By.id("borrowResultTextAmount")).getText();
        Assert.assertEquals("BorrowEstimation is calculated incorrectly","$0",actEstimate);
    }

    public void clickOnEstimate(){
        driver.findElement(By.id("btnBorrowCalculater")).click();
    }

    public void verifyMessage(String expectedMsg){
        String actualMsg = driver.findElement(message).getText().trim();
        Assert.assertEquals("Required info message not been displayed",expectedMsg,actualMsg);
    }
}
