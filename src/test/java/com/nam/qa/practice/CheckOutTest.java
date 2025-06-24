package com.nam.qa.practice;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.nam.qa.POM.BaseClass;
import com.nam.qa.POM.ValidateHelper;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Listeners({ io.qameta.allure.testng.AllureTestNg.class })
@Epic("Cart Page")
@Feature("Cart Page Functionality")
public class CheckOutTest extends BaseClass {
	private SignInPage  signInPage;
	private ValidateHelper validateHelper;
	
	@BeforeMethod
	public void setup() {
		signInPage = new SignInPage(driver);
		driver.get("https://www.saucedemo.com/");
		signInPage.signin("standard_user", "secret_sauce");
	} 
	@Test(priority = 0)
	public void verifyCancelButtonNavigatesBackToCartPage()
	{
		ValidateHelper validateHelper = new ValidateHelper(driver);
		validateHelper.ClickElement(driver.findElement(By.xpath("//a[@class='shopping_cart_link']")));
		validateHelper.ClickElement(driver.findElement(By.xpath("//button[@id='checkout']")));
		validateHelper.ClickElement(driver.findElement(By.xpath("//button[@id='cancel']")));
		String url = driver.getCurrentUrl();
		assertEquals(url, "https://www.saucedemo.com/cart.html");	
	}
	@Test(priority = 1)
	public void verifyErrorMessageWhenFirstNameIsEmpty()
	{
		ValidateHelper validateHelper = new ValidateHelper(driver);
		CartPage cartPage = new CartPage(driver);
		validateHelper.ClickElement(driver.findElement(By.xpath("//a[@class='shopping_cart_link']")));
		validateHelper.ClickElement(driver.findElement(By.xpath("//button[@id='checkout']")));
		validateHelper.setText(By.xpath("//input[@id='last-name']"), "tadaa");
		validateHelper.setText(By.xpath("//input[@id='postal-code']"), "123");
		validateHelper.ClickElement(driver.findElement(By.xpath("//input[@id='continue']")));
		assertEquals(cartPage.getErrorMessage(), "Error: First Name is required");
		
	}
	@Test(priority = 2)
	public void verifyErrorMessageWhenLastNameIsEmpty()
	{
		ValidateHelper validateHelper = new ValidateHelper(driver);
		CartPage cartPage = new CartPage(driver);
		validateHelper.ClickElement(driver.findElement(By.xpath("//a[@class='shopping_cart_link']")));
		validateHelper.ClickElement(driver.findElement(By.xpath("//button[@id='checkout']")));
		validateHelper.setText(By.xpath("//input[@id='first-name']"), "tadaa");
		validateHelper.setText(By.xpath("//input[@id='postal-code']"), "123");
		validateHelper.ClickElement(driver.findElement(By.xpath("//input[@id='continue']")));
		assertEquals(cartPage.getErrorMessage(), "Error: Last Name is required");
		
	}
	@Test(priority = 3)
	public void verifyErrorMessageWhenPostalCodeIsEmpty()
	{
		ValidateHelper validateHelper = new ValidateHelper(driver);
		CartPage cartPage = new CartPage(driver);
		validateHelper.ClickElement(driver.findElement(By.xpath("//a[@class='shopping_cart_link']")));
		validateHelper.ClickElement(driver.findElement(By.xpath("//button[@id='checkout']")));
		validateHelper.setText(By.xpath("//input[@id='first-name']"), "tadaa");
		validateHelper.setText(By.xpath("//input[@id='last-name']"), "tadaưea");
		validateHelper.ClickElement(driver.findElement(By.xpath("//input[@id='continue']")));
		assertEquals(cartPage.getErrorMessage(), "Error: Postal Code is required");
	}
//	@Test(priority = 4)
//	public void  verifyCannotCheckoutWithEmptyCart()
//	{
//		ValidateHelper validateHelper = new ValidateHelper(driver);
//		validateHelper.ClickElement(driver.findElement(By.xpath("//a[@class='shopping_cart_link']")));
//		int cartItems = driver.findElements(By.className("cart_item")).size();
//		 assertEquals(cartItems, 0, "❌ Cart is not empty!");
//		
//		validateHelper.ClickElement(driver.findElement(By.xpath("//button[@id='checkout']")));
//		
//		validateHelper.setText(By.xpath("//input[@id='first-name']"), "Nam");
//		
//		validateHelper.setText(By.xpath("//input[@id='last-name']"), "Le");
//		
//		validateHelper.setText(By.xpath("//input[@id='postal-code']"), "500");
//		
//		validateHelper.ClickElement(driver.findElement(By.xpath("//input[@id='continue']")));
//		
//	    String url = driver.getCurrentUrl();
//	    assertEquals(url, "https://www.saucedemo.com/checkout-step-one.html","❌ Checkout proceeded even with empty cart!");
//		
//		
//		
//		String getPrice = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']")).getText().replace("$","").trim();
//		
//		Double itemTotal = Double.parseDouble(getPrice);
//		
//		Double tax = Double.parseDouble(driver.findElement(By.xpath("//div[@class='summary_tax_label']")).getText().replace("$","").trim());
//		Double Total = itemTotal + tax;
//		assertEquals(Total, 0);
//	}
	@Test(priority = 5)
	public void verifyTotalAmountWithSingleProduct()
	{
		ValidateHelper validateHelper = new ValidateHelper(driver);
		driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]")).click();
		
		validateHelper.ClickElement(driver.findElement(By.xpath("//a[@class='shopping_cart_link']")));

		
		validateHelper.ClickElement(driver.findElement(By.xpath("//button[@id='checkout']")));
		
		validateHelper.setText(By.xpath("//input[@id='first-name']"), "Nam");
		
		validateHelper.setText(By.xpath("//input[@id='last-name']"), "Le");
		
		validateHelper.setText(By.xpath("//input[@id='postal-code']"), "500");
		
		validateHelper.ClickElement(driver.findElement(By.xpath("//input[@id='continue']")));
		
		Double itemTotal = Double.parseDouble(driver.findElement(By.xpath("//div[@class='summary_subtotal_label']")).getText().replace("Item total: $","").trim());
		
		Double tax = Double.parseDouble(driver.findElement(By.xpath("//div[@class='summary_tax_label']")).getText().replace("Tax: $","").trim());
		Double displayedTotal = Double.parseDouble(driver.findElement(By.xpath("//div[@class='summary_total_label']")).getText().replace("Total: $","").trim());
		Double ExpectTotal = itemTotal + tax;
		assertEquals(displayedTotal, ExpectTotal,"Price not match");
	}
	@Test(priority = 6)
	public void verifyTotalAmountWithMutipleProduct()
	{
		driver.findElement(By.xpath("//button[contains(text(),'Remove')]")).click();
		ValidateHelper validateHelper = new ValidateHelper(driver);
		int productCount = driver.findElements(By.className("inventory_item_name")).size();
		for(int i = 0 ; i  < productCount ; i++)
		{
			List<WebElement> itemProduct = driver.findElements(By.xpath("//button[contains(text(),'Add to cart')]"));
			itemProduct.get(0).click();
		}
		
		validateHelper.ClickElement(driver.findElement(By.xpath("//a[@class='shopping_cart_link']")));

		
		validateHelper.ClickElement(driver.findElement(By.xpath("//button[@id='checkout']")));
		
		validateHelper.setText(By.xpath("//input[@id='first-name']"), "Nam");
		
		validateHelper.setText(By.xpath("//input[@id='last-name']"), "Le");
		
		validateHelper.setText(By.xpath("//input[@id='postal-code']"), "500");
		
		validateHelper.ClickElement(driver.findElement(By.xpath("//input[@id='continue']")));
		
		Double itemTotal = Double.parseDouble(driver.findElement(By.xpath("//div[@class='summary_subtotal_label']")).getText().replace("Item total: $","").trim());
		
		Double tax = Double.parseDouble(driver.findElement(By.xpath("//div[@class='summary_tax_label']")).getText().replace("Tax: $","").trim());
		Double displayedTotal = Double.parseDouble(driver.findElement(By.xpath("//div[@class='summary_total_label']")).getText().replace("Total: $","").trim());
		Double ExpectTotal = itemTotal + tax;
		assertEquals(displayedTotal, ExpectTotal,"Price not match");
		
		validateHelper.ClickElement(driver.findElement(By.xpath("//button[@id='cancel']")));
		
		for(int i = 0 ; i  < productCount ; i++)
		{
			List<WebElement> itemProduct = driver.findElements(By.xpath("//button[contains(text(),'Remove')]"));
			itemProduct.get(0).click();
		}
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
	@Test(priority = 8)
	public void verifyButtonBackHomeNavigatesBackToProductPage()
	{
		ValidateHelper validateHelper = new ValidateHelper(driver);
		driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]")).click();
		
		validateHelper.ClickElement(driver.findElement(By.xpath("//a[@class='shopping_cart_link']")));
		validateHelper.ClickElement(driver.findElement(By.xpath("//button[@id='checkout']")));

		validateHelper.setText(By.xpath("//input[@id='first-name']"), "Nam");

		validateHelper.setText(By.xpath("//input[@id='last-name']"), "Le");

		validateHelper.setText(By.xpath("//input[@id='postal-code']"), "500");

		validateHelper.ClickElement(driver.findElement(By.xpath("//input[@id='continue']")));
		
		validateHelper.ClickElement(driver.findElement(By.xpath("//button[@id='finish']")));
		
		validateHelper.ClickElement(driver.findElement(By.xpath("//button[@id='back-to-products']")));
		
		String getUrl = driver.getCurrentUrl();
		
		assertEquals(getUrl,"https://www.saucedemo.com/inventory.html","Button BackHome Fail");
		
		
	}
    
}
