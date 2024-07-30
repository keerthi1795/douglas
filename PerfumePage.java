package com.assessment;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PerfumePage extends LandingPage {
    public PerfumePage(WebDriver driver) {
        super(driver); 
        PageFactory.initElements(driver, this);
    }
    
    public int getProductCount() {
        try {
            WebElement countElement = driver.findElement(By.xpath("//div[@class='product-overview__headline-wrapper']"));
            String countText = countElement.getText();
            String numberString = countText.replaceAll("\\D", ""); // Extract numeric part
            return Integer.parseInt(numberString);
        } catch (Exception e) {
            System.err.println("Error retrieving product count: " + e.getMessage());
            e.printStackTrace();
            return 0; // Return 0 in case of an error
        }
    }
    
    public void applyFilters(Map<String, String> filters) {
        for (Map.Entry<String, String> filter : filters.entrySet()) {
        	String filterCategory = filter.getKey();
            String filterValue = filter.getValue();
            selectFilter(filterCategory, filterValue);
        }
    }

    public void selectFilter(String filterCategory, String filterValue) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 250);");            
            WebElement filterCategoryElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='facet']//div[text()='" + filterCategory + "']")));
            filterCategoryElement.click();            
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("rc-scrollbars-container")));            
            WebElement filterValueElement = driver.findElement(By.xpath("//a[@role='checkbox' and .//div[text()='" + filterValue + "']]//input[@type='checkbox']"));
            if (filterValueElement != null && !filterValueElement.isSelected()) {
                filterValueElement.click();
                wait.until(ExpectedConditions.elementToBeSelected(filterValueElement));
                System.out.println("Filter applied: " + filterValue);
            } else {
                System.out.println("Filter value not found or already selected: " + filterValue);
            }
             filterCategoryElement.click(); 
        } catch (Exception e) {
            System.err.println("Error selecting filter: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
