package Steps;

import PageObjects.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {



    LoginPage login = new LoginPage();

    @Given("user is at the Login Page")
    public void userIsAtTheLoginPage() {
        login.userOpenLoginScreen();
    }

    @And("user can observe login fields and login button")
    public void userCanObserveLoginFieldsAndLoginButton() {
        login.userCanSeeLoginFields();
    }

    @When("the user inserts a {string} into the username textbox")
    public void userInsertsAnUsername(String username) {
        login.insertUsername(username);
    }

    @And("the user inserts a {string} into the password textbox")
    public void userInsertsAPassword(String password) {
        login.insertPassword(password);
    }

    @And("the user clicks on LOGIN button")
    public void userClicksOnLoginButton() {
        login.clickOnLogin();
    }


    @Then("the user should navigate to the Profile Page")
    public void theUserShouldNavigateToTheProfilePage() {
        login.checkAccountPage();
    }

    @Given("user has valid credentials: {string} and {string} and is logged in")
    public void userIsAtTheUserPage(String username, String password) {
        login.userCanSeeLoginFields();
        login.insertUsername(username);
        login.insertPassword(password);
    }

    @When("the user clicks on LOGOUT button")
    public void theUserClicksOnLOGOUTButton() {
        login.clickOnLogOut();
    }

    @Then("the user should navigate to Login Page")
    public void theUserShouldNavigateToAccountPage() {
        login.checkProfilePage();
    }

    @Then("system displays an error message indicating incorrect credentials")
    public void systemDisplaysAnErrorMessageIndicatingIncorrectCredentials() {
        login.checkIncorrectLoginMessage();
    }

    @And("user clicks on FORGOT YOUR PASSWORD button")
    public void userClicksOnFORGOTYOURPASSWORDButton() {
        login.clickOnForgotPassword();
    }

    @Then("user navigate to lost password page")
    public void userNavigateToLostPasswordPage() {
        login.userCanSeeLostPasswordFields();
    }

    @And("user insert valid credential: {string}")
    public void userInsertValidCredential(String user) {
        login.userInsertValidUserOnLostPasswordInput(user);
    }

    @And("user clicks on RETRIEVE NEW PASSWORD")
    public void userClicksOnRetrievePasswordButton() {
        login.userClicksOnRetrievePasswordButton();
    }

    @Then("the system displays a recover password message")
    public void theSystemDisplaysARecoverPasswordMessage() {
        login.checkResetPasswordMessage();
    }

    @After(order = 0)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            //Code an action that your testing automation would take if a specific scenario has just failed.
            String scenarioThatFailed = scenario.getName();
        }
    }

    @After(order = 1)
    public void tearDown() {
        login.closeBrowser();
    }

}

