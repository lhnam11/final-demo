package com.nam.qa.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SignInPage {
	public WebDriver driver;

	@FindBy(id = "user-name")
	private WebElement inputUserNam;

	@FindBy(id = "password")
	private WebElement inputPassWord;

	private By errorMessage = By.cssSelector("h3[data-test='error']");

	@FindBy(id = "login-button")
	private WebElement loginBtn;

	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public ProductPage signin(String username, String password) {
		inputUserNam.sendKeys(username);
		inputPassWord.sendKeys(password);
		loginBtn.click();
		return new ProductPage(driver); 

	}

	public String getErrorMessage() {
		return driver.findElement(errorMessage).getText();
	}

}
