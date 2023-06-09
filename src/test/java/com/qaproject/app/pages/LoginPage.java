package com.qaproject.app.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    protected WebDriver driver;

    @FindBy(xpath = "//button[@data-name='SwitchToEmailAuthBtn']")
    WebElement loginButton;

    @FindBy(xpath = "//input[@name='username']")
    WebElement userNameInput;

    @FindBy(xpath = "//input[@name='password']")
    WebElement passwordInput;

    @FindBy(xpath = "//div[@data-name='AuthenticationModal']/div/div[2]/div/form/div/div/span")
    WebElement wrongLoginPassText;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public LoginPage switchToEmailAuth() {
        loginButton.click();
        return this;
    }

    public LoginPage enterUserName(String str){
        userNameInput.sendKeys(str, Keys.ENTER);
        return this;
    }

    public StartPage enterPassword(String str){
        passwordInput.sendKeys(str, Keys.ENTER);
        return new StartPage(driver);
    }

    public LoginPage enterWrongPasswordLogin(String str){
        passwordInput.sendKeys(str, Keys.ENTER);
        return this;
    }
    public Boolean checkWrongLoginPass() {
        return wrongLoginPassText.isDisplayed();
    }
}
