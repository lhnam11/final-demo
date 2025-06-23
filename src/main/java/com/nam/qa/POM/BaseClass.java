package com.nam.qa.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	public WebDriver driver;

	@BeforeClass
	public void SetUp() {
//		System.setProperty("webdriver.chrome.driver", "D:\\LeHoaiNam\\Selenium\\chromedriver.exe");
//		driver = new ChromeDriver();
//		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();

		System.setProperty("webdriver.edge.driver", "D:\\LeHoaiNam\\Selenium\\msedgedriver.exe");

		driver = new EdgeDriver();
		driver.manage().window().maximize();
	}

	@AfterClass
	public void TearDown() throws Exception {
		Thread.sleep(2000);
		driver.quit();
	}

}
