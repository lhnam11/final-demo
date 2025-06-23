package com.nam.qa.practice;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nam.qa.POM.BaseClass;
import com.nam.qa.POM.ValidateHelper;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;

public class EndToEndTest extends BaseClass{
	public SignInPage signInPage;
	@BeforeMethod
	public void setup() {
		signInPage = new SignInPage(driver);
		driver.get("https://www.saucedemo.com/");
		signInPage.signin("standard_user", "secret_sauce");
	}
	@Epic("End-to-End Flow")
	@Description("This test verifies that the user can complete the checkout flow from adding to cart to seeing confirmation message.")
	@Test(priority = 7, groups = "end-to-end")
	public void verifyMessageWhenCheckOutSuccessfully() {
		ValidateHelper validateHelper = new ValidateHelper(driver);
		driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]")).click();
		
		validateHelper.ClickElement(driver.findElement(By.xpath("//a[@class='shopping_cart_link']")));
		validateHelper.ClickElement(driver.findElement(By.xpath("//button[@id='checkout']")));

		validateHelper.setText(By.xpath("//input[@id='first-name']"), "Nam");

		validateHelper.setText(By.xpath("//input[@id='last-name']"), "Le");

		validateHelper.setText(By.xpath("//input[@id='postal-code']"), "500");

		validateHelper.ClickElement(driver.findElement(By.xpath("//input[@id='continue']")));
		
		validateHelper.ClickElement(driver.findElement(By.xpath("//button[@id='finish']")));
		
	    String checkOutComplete = driver.findElement(By.className("complete-header")).getText();
	    assertEquals(checkOutComplete, "Thank you for your order!");
	}

}
