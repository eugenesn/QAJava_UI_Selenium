package com.qaproject.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StartPage {

    @FindBy(xpath = "//*[@id='header-frontend']//div[@data-name='UserRelated']/a")
    WebElement loginButton;

    @FindBy(xpath = "//span[@data-name='FiltersSearchButtons']/span/a[@data-mark='FiltersSearchButton']")
    WebElement topSearchButton;

    @FindBy(xpath = "//div[@data-mark='FilterRoomsCount']/button")
    WebElement roomSelectButton;

    @FindBy(xpath = "//div[@data-mark='FilterRoomsCount']/div/ul/li/button[contains(@class,'active')]")
    List<WebElement> activeRoomSelect;

    @FindBy(xpath = "//div[@data-mark='FilterRoomsCount']/div/ul/li[3]/button")
    WebElement room3Select;

    protected WebDriver driver;

    public StartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public StartPage openStartPage(String url) {
        driver.get(url);
        return this;
    }

    public StartPage roomSelectBtn() {
        roomSelectButton.click();
        return this;
    }

    public StartPage activeRoomSelectBtn() {
        for(WebElement e : activeRoomSelect) {
            e.click();
        }
        room3Select.click();
        return this;
    }

    public LoginPage openLoginPage() {
        loginButton.click();
        return new LoginPage(driver);
    }

    public Boolean checkLoggedIn() {
        String userAvatarLocator = "//*[@id='header-frontend']//div[@data-name='UserRelated']/a[@data-name='UserAvatar']";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(userAvatarLocator)));
        return driver.findElement(By.xpath(userAvatarLocator)).isDisplayed();
    }

    public TopSearchResult openSearchPage(){
        topSearchButton.click();
        return new TopSearchResult(driver);
    }

    public Boolean getLoginButtonDisplayed(){
        return loginButton.isDisplayed();
    }
}
