package PageObjects;

import Util.Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Selenium Behavior Driven-Development Example
//Author: Jhonatan Pereira
public class LoginPage {

    private final WebDriver driver;
    WebDriverWait wait;

    /* PageFactory & Extra Configs*/
    public LoginPage() {
        /*Edge Configs - No Need To Download Drivers*/
        /*Change the methods to other drivers if needed*/
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Utils.getMaxWaitTime());
        PageFactory.initElements(driver, this);
    }

  /* WebElements - Locators */
    @FindBy(id = "email")
    WebElement inputNameOrEmail;

    @FindBy(id = "passwd")
    WebElement inputPassword;

    @FindBy(id = "SubmitLogin")
    WebElement loginButton;

    @FindBy(className = "logout")
    WebElement logoutButton;

    @FindBy(className =  "alert")
     WebElement alertMessage;

    @FindBy(linkText = "Forgot your password?")
    WebElement forgotPassword;

    @FindBy(xpath = "//button/span[text()='Retrieve Password']")
    WebElement retrievePassword;


    /* Background Methods */
    public void userOpenLoginScreen(){
        driver.get("http://automationpractice.pl/index.php?controller=authentication&back=my-account");
    }
    public void userCanSeeLoginFields(){
        wait.until(ExpectedConditions.visibilityOf(inputNameOrEmail));
        wait.until(ExpectedConditions.visibilityOf(inputPassword));
        wait.until(ExpectedConditions.visibilityOf(loginButton));
    }

    /* User Interactions Methods */
    public void insertUsername(String username){
        inputNameOrEmail.sendKeys(username);
    }

    public void insertPassword(String password){
        inputPassword.sendKeys(password);
    }

    public void clickOnLogin(){
        loginButton.click();
    }

    public void clickOnForgotPassword(){
        wait.until(ExpectedConditions.visibilityOf(forgotPassword));
        forgotPassword.click();
    }
    public void clickOnLogOut(){
        wait.until(ExpectedConditions.visibilityOf(logoutButton));
        logoutButton.click();
    }

    public void checkProfilePage() {
        Assert.assertEquals("http://automationpractice.pl/index.php?controller=authentication&back=my-account", driver.getCurrentUrl());
    }
    public void checkIncorrectLoginMessage(){
        wait.until(ExpectedConditions.visibilityOf(alertMessage));
        String expected = "There is 1 error\n" +
                "Authentication failed." ;
        Assert.assertEquals(expected, alertMessage.getText());
    }

    public void checkResetPasswordMessage(){
        wait.until(ExpectedConditions.visibilityOf(alertMessage));
        String expected = "There is 1 error\n" +
                "You can regenerate your password only every 360 minute(s)";
        Assert.assertEquals(expected, alertMessage.getText());
    }

    public void userCanSeeLostPasswordFields(){
        wait.until(ExpectedConditions.visibilityOf(inputNameOrEmail));
        //wait.until(ExpectedConditions.visibilityOf(retrievePassword));
    }
    public void userInsertValidUserOnLostPasswordInput(String user){
        inputNameOrEmail.sendKeys(user);
    }
    public void userClicksOnRetrievePasswordButton(){
        retrievePassword.click();
    }
    public void closeBrowser(){
        driver.quit();
    }

    public void checkAccountPage() {
        Assert.assertEquals("http://automationpractice.pl/index.php?controller=my-account", driver.getCurrentUrl());
    }
}
