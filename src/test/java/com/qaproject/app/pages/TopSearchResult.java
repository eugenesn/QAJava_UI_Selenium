package com.qaproject.app.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TopSearchResult {

    protected WebDriver driver;

    @FindBy(xpath = "//*[@id='mainFilter_advancedFilters']/div")
    WebElement advancedFiltersButton;

    @FindBy(xpath = "//*[@id='mainFilter_price']")
    WebElement price;

    @FindBy(xpath = "//*[@id='mainFilter_price']//div[1]/div/input")
    WebElement priceFrom;

    @FindBy(xpath = "//*[@id='mainFilter_price']//div[2]/div/input")
    WebElement priceTo;

    @FindBy(xpath = "//article[@data-name='CardComponent']//span[@data-mark='OfferTitle']/span")
    List<WebElement> titleResult;

    @FindBy(xpath = "//article[@data-name='CardComponent']//span[@data-mark='MainPrice']/span")
    List<WebElement> priceResult;

    private List<Integer> summa = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    
    public TopSearchResult(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public TopSearchResult filterPrice() {
        price.click();
        return this;
    }

    public TopSearchResult filterPriceFrom(String str){
        priceFrom.sendKeys(str);
        return this;
    }

    public TopSearchResult filterPriceTo(String str){
        priceTo.sendKeys(str);
        return this;
    }

    public AdvancedSearchPage openAdvancedFilters() {
        advancedFiltersButton.click();
        return new AdvancedSearchPage(driver);
    }


    public TopSearchResult waitResult() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'preloadOverlay')]")));
        getResult();

        return this;
    }

    public TopSearchResult getResult() {
        priceResult.forEach(x->summa.add(Integer.valueOf(x.getText().replaceAll("[^\\d.]", ""))));
        titleResult.forEach(x->titles.add(x.getText()));
        return this;
    }

    public List<Integer> getSumma() {
        return summa;
    }

    public List<String> getTitles() {
        return titles;
    }
}
