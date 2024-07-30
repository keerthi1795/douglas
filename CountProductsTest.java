package com.assessment;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CountProductsTest {

    private WebDriver driver;
    private PerfumePage perfumePage;

    @BeforeClass
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "/snap/bin/geckodriver");
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
        }

        driver.get("https://www.douglas.de");
        driver.manage().window().maximize();

        try {
            LandingPage mainPage = new LandingPage(driver);
            mainPage.acceptCookies();
            mainPage.clickPerfumeMenu("PARFUM");
            perfumePage = new PerfumePage(driver);
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @DataProvider(name = "perfumeData")
    public Object[][] perfumeData() {
        String filePath = "/home/keerthana/Documents/eclipse-installer/douglas/Douglas items.xlsx";
        String sheetName = "Sheet1";
        List<Map<String, String>> testData = ExcelUtil.getTestData(filePath, sheetName);
        Object[][] data = new Object[testData.size()][1];
        for (int i = 0; i < testData.size(); i++) {
            data[i][0] = testData.get(i);
        }
        return data;
    }

    @Test(dataProvider = "perfumeData")
    public void testPerfumeFilter(Map<String, String> filters) {
        perfumePage.applyFilters(filters);
        int actualProductCount = perfumePage.getProductCount();
        System.out.println("Filters: " + filters + ", Product Count: " + actualProductCount);
        Assert.assertTrue(actualProductCount > 0, "No products found for filters: " + filters);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

