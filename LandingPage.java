package com.assessment;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }
    
    public void acceptCookies() {
        try {
        	Thread.sleep(5000);
            WebElement shadowHost = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("usercentrics-root")));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement acceptAllButton = (WebElement) js.executeScript(
                "return arguments[0].shadowRoot.querySelector('button[data-testid=\"uc-accept-all-button\"]')",
                shadowHost
            );

            if (acceptAllButton != null && acceptAllButton.isDisplayed()) {
            	acceptAllButton.click();
                wait.until(ExpectedConditions.invisibilityOf(acceptAllButton));
                System.out.println("Accepted cookies");
            } else {
                System.out.println("Accept Cookies button not displayed");
            }
        } catch (Exception e) {
            System.err.println("Error while accepting cookies: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void clickPerfumeMenu(String menu) {
        try {
            WebElement menuItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[a[text()='" + menu + "']]")));
            menuItem.click();
            String formattedMenu = menu.substring(0, 1).toUpperCase() + menu.substring(1).toLowerCase();
            By breadCrumb = By.xpath("//span[text()='" + formattedMenu + "']");
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(breadCrumb));
            
            if (element != null && element.isDisplayed()) {
                System.out.println("Navigated to: " + formattedMenu);
            } else {
                System.out.println("Parfum page is not displayed");
            }
        } catch (Exception e) {
            System.err.println("Error while clicking perfume menu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
