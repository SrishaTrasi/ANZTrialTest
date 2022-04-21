package Objects;

import Pages.Loans;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {

    private WebDriver driver;
    private Loans loans;

    public PageObjectManager(WebDriver driver){
        this.driver = driver;
    }

    public Loans getLoans(){
        loans = new Loans(driver);
        return loans;
    }
}
