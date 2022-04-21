package StepDefs;

import Cucumber.TestContext;
import Pages.Loans;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class LoansStepDef {

    TestContext testContext;
    Loans loans;
    String infoMessage = "Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 035 500.";

    public LoansStepDef(TestContext context){
        testContext = context;
        loans = testContext.getPageObjectManager().getLoans();
    }


    @Given("I have landed on the personal home loan page")
    public void i_have_landed_on_the_personal_home_loan_page() throws IOException {
        loans.navigateToUrl();
        loans.verifyLandingPageTitle("Home loan borrowing power calculator");
    }

    @When("I select below values in {string} for borrow estimate")
    public void i_select_below_values_in_for_borrow_estimate(String section, DataTable dataTable) {
        if(section.equalsIgnoreCase("Your details")) {
            loans.selectDetails(dataTable);
        }else{
            loans.inputDetails(dataTable);
        }
    }

    @Then("I should see the estimate as {string}")
    public void i_should_see_the_estimate_as(String expEstimate) throws InterruptedException {
        loans.verifyTheEstimates(expEstimate);
    }

    @When("I click on Start Over")
    public void i_click_on_start_over() {
        loans.clickOnStartOver();
    }

    @Then("I should see all the fields been reset to zero")
    public void i_should_see_all_the_fields_been_reset_to_zero() throws InterruptedException {
        loans.verifyZeroEstimates();
    }

    @When("I click on calculate estimate")
    public void i_click_on_calculate_estimate() {
        loans.clickOnEstimate();
    }

    @Then("I can verify an informative message is displayed")
    public void i_can_verify_an_informative_message_is_displayed() {
        loans.verifyMessage(infoMessage);
    }
}
