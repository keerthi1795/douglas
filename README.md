# Automated Testing of Douglas Website

## Overview
This project contains automated test scripts designed to verify the product filtering functionality on the Douglas website. It also includes the count of products after filtering the required product. The tests are implemented using Selenium WebDriver and TestNG.

## Prerequisites
Ensure you have the following software and tools installed:
- **Java**: JDK 8 or later
- **Eclipse IDE**: For Java development
- **Maven**: For dependency management (if applicable)
- **Selenium WebDriver**: For browser automation
- **TestNG**: For test execution
- **ChromeDriver**: For Chrome browser
- **GeckoDriver**: For Firefox browser

## Setup Instructions

### 1. Clone the Repository
Clone the repository to your local machine using Git:
git clone https://github.com/your-repository-url.git

2. Import the Project into Eclipse
Open Eclipse IDE.
Go to File > Import > Existing Projects into Workspace.
Select the directory of the cloned project and click Finish.

3. Add Dependencies
Ensure your pom.xml file is correctly configured if using Maven. Include dependencies for Selenium and TestNG:

4. Configure Browser Drivers
ChromeDriver: Download from ChromeDriver.
GeckoDriver: Download from GeckoDriver.
Set the system property for the WebDriver in your test scripts:
System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
System.setProperty("webdriver.gecko.driver", "/path/to/geckodriver");

### Running the Tests
1. Open TestNG XML Configuration
Locate the testng.xml file in the project. This file configures different browser tests.

2. Run the TestNG Suite
Right-click on testng.xml.
Select Run As > TestNG Suite.

### Test Script Details
CountProductsTest
Description: This test script verifies the number of products displayed after applying various filters on the Douglas website.
Parameters:
	browser: Specifies which browser to use for the test (e.g., chrome, firefox).
Data Provider: perfumeData - Fetches test data from an Excel file located at /home/keerthana/Documents/eclipse-installer/douglas/Douglas items.xlsx.

LandingPage
Description: This testscript verifies the cookies are accepted after displaying the popup while entering the Douglas website.
			 In clickPerfumeMenu(), required menu is passed as a String and Verifying the page with the help of BreadCrumb.
			 
PerfumePage
Description: This testscript verifies the filter selecting and applying of filter to get the list of products and its count. The test data are fetched from Data Provider.
Data Provider: perfumeData - Fetches test data from an Excel file located at /home/keerthana/Documents/eclipse-installer/douglas/Douglas items.xlsx.

ExcelUtil
Description: ExcelUtil is a utility class designed to simplify the process of reading data from Excel files. It generally includes methods for:

Reading Data from Excel Files:
Reading data from specific sheets within an Excel workbook.
Converting rows and columns of data into suitable formats (e.g., lists or maps) for use in tests.

Handling Different Excel Formats:
Supporting various Excel file formats (such as .xlsx and .xls).
Using libraries like Apache POI or JExcelAPI to interact with Excel files.


### Troubleshooting
Common Issues

Error: while clicking perfume menu: element click intercepted
Solution: Ensure to include Thread.sleep(5000) in accept cookies

Error: java.lang.IllegalStateException: Cannot get a STRING value from a NUMERIC cell
Solution: Ensure the data type of cells in the Excel file is correctly defined (string vs numeric).

Error:Browser Driver Not Found
Solution: Verify that the path to the browser driver executable is correct and that it has the necessary execution permissions.

Error: Main method not found in class com.assessment.CountProductsTest
Solution: Ensure you are running the test as a TestNG suite via testng.xml, not directly as a Java application.

###Contact
Author: Keerthana Ganesan
Email: keerthihari000@gmail.com







