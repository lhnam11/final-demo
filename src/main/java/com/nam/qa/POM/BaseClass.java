package com.nam.qa.POM;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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
//		System.setProperty("webdriver.gecko.driver", "D:\\LeHoaiNam\\Selenium\\geckodriver.exe");
//
//		FirefoxOptions options = new FirefoxOptions();
//		options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe"); // chỉnh đúng đường dẫn
//
//		driver = new FirefoxDriver(options);
//		driver.manage().window().maximize();
////
		System.setProperty("webdriver.edge.driver", "D:\\LeHoaiNam\\Selenium\\msedgedriver.exe");

		driver = new EdgeDriver();
		driver.manage().window().maximize();

//
//		EdgeOptions options = new EdgeOptions();
//		options.addArguments("--headless");        // chạy không giao diện (cho Jenkins)
//		options.addArguments("--disable-gpu");     // bắt buộc cho Windows
//		options.addArguments("--window-size=1920,1080"); // giúp layout đúng
//
//		driver = new EdgeDriver(options);
//		driver.manage().window().maximize();
	}

	@AfterClass
	public void TearDown() throws Exception {
		Thread.sleep(2000);
		driver.quit();
	}

}
