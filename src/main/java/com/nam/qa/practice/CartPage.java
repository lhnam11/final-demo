package com.nam.qa.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	public WebDriver driver;
	private By errorMessage = By.cssSelector("h3[data-test='error']");
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}
	public String getErrorMessage() {
		return driver.findElement(errorMessage).getText();
	}

}
