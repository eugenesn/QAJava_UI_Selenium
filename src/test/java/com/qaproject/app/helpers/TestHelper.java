package com.qaproject.app.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;

public class TestHelper {

    private static final Properties properties = new Properties();
    protected static WebDriver driver;
    protected static String baseUrl;
    protected static String username;
    protected static String password;
    protected static String priceFrom;
    protected static String priceTo;
    protected static String rooms;

    private static Map<String, Object> getDataFromJSON(String jsonFullPath) throws IOException {
        return new ObjectMapper().readValue(
                new File(jsonFullPath),
                Map.class);
    }

    @BeforeAll
    static void setProperty() throws IOException {

        properties.load(TestHelper.class.getClassLoader().getResourceAsStream("appTest.properties"));
        baseUrl = properties.getProperty("baseUrl");
        Map<String, Object> authData = getDataFromJSON("src/test/java/com/qaproject/app/config/authData.json");
        Map<String, Object> searchData = getDataFromJSON("src/test/java/com/qaproject/app/config/searchData.json");

        username = (String) authData.get("username");
        password = (String) authData.get("password");

        priceFrom = (String) searchData.get("priceFrom");
        priceTo = (String) searchData.get("priceTo");
        rooms = (String) searchData.get("rooms");
    }

    @BeforeEach
    public void setChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        if(Boolean.parseBoolean(properties.getProperty("headless"))){
            options.addArguments("--headless");
        }
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(properties.getProperty("waitSeconds"))));
    }

    @AfterEach
    public void endTest() {
        driver.quit();
    }
}