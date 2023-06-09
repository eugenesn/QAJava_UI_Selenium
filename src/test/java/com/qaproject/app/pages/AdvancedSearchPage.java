package com.qaproject.app.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdvancedSearchPage {
    protected WebDriver driver;
    JavascriptExecutor js;

    @FindBy(xpath = "//div[@aria-labelledby='advancedFilter_repair']/following::div[1]/div[2]/div/div[4]//span[1]")
    WebElement floor;

    @FindBy(xpath = "//div/div[2]/div[3]/div/button[2]")
    WebElement buttonSearch;

    public AdvancedSearchPage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) this.driver;
        PageFactory.initElements(this.driver, this);
    }

    public TopSearchResult setLastFloor() {
        js.executeScript("arguments[0].click();", floor);
        js.executeScript("arguments[0].click();", buttonSearch);
        return new TopSearchResult(driver);
    }
}
