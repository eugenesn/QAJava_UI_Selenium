package com.qaproject.app;

import com.qaproject.app.helpers.TestHelper;
import com.qaproject.app.pages.LoginPage;
import com.qaproject.app.pages.StartPage;
import com.qaproject.app.pages.TopSearchResult;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.Collections;

class AppApplicationTest extends TestHelper {

    @Test
    public void LoginTest() {

        StartPage startPage = new StartPage(driver)
                .openStartPage(baseUrl)
                .openLoginPage()
                .switchToEmailAuth()
                .enterUserName(username)
                .enterPassword(password);

        Assertions.assertTrue(startPage.checkLoggedIn());

    }

    @Test
    public void LoginTestNegative() {

        LoginPage loginPage = new StartPage(driver)
                .openStartPage(baseUrl)
                .openLoginPage()
                .switchToEmailAuth()
                .enterUserName(username)
                .enterWrongPasswordLogin("incorrect password");

        Assertions.assertTrue(loginPage.checkWrongLoginPass());

    }

    @Test
    public void SearchApartmentsTest() {
        TopSearchResult searchResult = new StartPage(driver)
                .openStartPage(baseUrl)
                .roomSelectBtn()
                .activeRoomSelectBtn()
                .openSearchPage()
                .filterPrice()
                .filterPriceFrom(priceFrom)
                .filterPriceTo(priceTo)
                .openAdvancedFilters()
                .setLastFloor()
                .waitResult();

        Assertions.assertTrue(Collections.max(searchResult.getSumma())<=Integer.parseInt(priceTo));
        Assertions.assertTrue(Collections.min(searchResult.getSumma())>=Integer.parseInt(priceFrom));

        for (String str:searchResult.getTitles()){
            if(str.contains(",")){
                String[] strs = str.substring(str.lastIndexOf(',')+1).trim().replaceAll("[^/\\d.]", "").split("/");
                Assertions.assertTrue(str.substring(0,str.indexOf(',')).contains(rooms));
                Assertions.assertEquals(strs[0],strs[1]);
            }
        }
    }

    @ParameterizedTest
    @CsvSource({"1920,1080", "1366,768", "768,1024"})
    public void ScreenTest(Integer width, Integer height){

        driver.manage().window().setSize(new Dimension(
                width, height
        ));

        StartPage loginPage = new StartPage(driver)
                .openStartPage(baseUrl);

        Allure.getLifecycle().addAttachment("Screenshot", "image/png", "png",
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)  );

        Assertions.assertTrue(loginPage.getLoginButtonDisplayed());
    }

}