package com.nam.qa.practice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.nam.qa.POM.BaseClass;
import com.nam.qa.POM.ValidateHelper;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Listeners({ io.qameta.allure.testng.AllureTestNg.class })
@Epic("Cart Page")
@Feature("Cart Page Functionality")
public class CartTest extends BaseClass {
	private SignInPage signInPage;
	private ValidateHelper validateHelper;

	@BeforeMethod
	public void setup() {
		signInPage = new SignInPage(driver);
		driver.get("https://www.saucedemo.com/");
		signInPage.signin("standard_user", "secret_sauce");
	}

	@Test(priority = 0)
	public void verifyMenuCartPage()
	{
		 ValidateHelper validateHelper = new ValidateHelper(driver);
		 driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();   
		 validateHelper.ClickElement(driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")));
		 String url = driver.getCurrentUrl();
		 assertEquals(url, "https://www.saucedemo.com/","Logout Failed");
	}

	@Test(priority = 1)
	public void verifyButtonContinueShopping() {
		ValidateHelper validateHelper = new ValidateHelper(driver);
		validateHelper.ClickElement(driver.findElement(By.className("shopping_cart_link")));
		validateHelper.ClickElement(driver.findElement(By.id("continue-shopping")));
		String url = driver.getCurrentUrl();
		assertEquals(url, "https://www.saucedemo.com/inventory.html",
				"❌ URL is incorrect after navigating to the productpage.");
		Allure.step("✅ URL is correct after navigating to the product page!");
	}


	@Test(priority = 2)
	public void verifyButtonCheckout() {
		ValidateHelper validateHelper = new ValidateHelper(driver);
		validateHelper.ClickElement(driver.findElement(By.className("shopping_cart_link")));
		validateHelper.ClickElement(driver.findElement(By.id("checkout")));
		String url = driver.getCurrentUrl();
		assertEquals(url, "https://www.saucedemo.com/checkout-step-one.html",
				"❌ URL is incorrect after navigating to the checkout page.");
		Allure.step("✅ URL is correct after navigating to the checkout page!");
	}

	@Test(priority = 3)
	public void verifyProductIsAddedToCartSuccessfully() {
		ValidateHelper validateHelper = new ValidateHelper(driver);
		int productCount = driver.findElements(By.className("inventory_item_name")).size();
		for (int i = 0; i < productCount; i++) {
			List<WebElement> addBtns = driver.findElements(By.xpath("//button[contains(text(),'Add to cart')]"));
			addBtns.get(0).click();
			validateHelper.ClickElement(driver.findElement(By.className("shopping_cart_link")));
			List<WebElement> productCart = driver.findElements(By.className("cart_item"));
			String productName = driver.findElement(By.className("inventory_item_name")).getText();
			assertTrue(productCart.size() > 0, "Product is not added To Cart");
			Allure.step("Product: " + i + " is Added To Cart Successfully");
			validateHelper.ClickElement(driver.findElement(By.id("continue-shopping")));
		}
		for (int i = 0; i < productCount; i++) {
			List<WebElement> removeBtns = driver.findElements(By.xpath("//button[contains(text(),'Remove')]"));
			removeBtns.get(0).click();
		}
	}

	@Test(priority = 4)
	public void verifyProductNameMatchesBetweenProductAndCartPages() {

		ValidateHelper validateHelper = new ValidateHelper(driver);
		int productCount = driver.findElements(By.className("inventory_item_name")).size();

		for (int i = 0; i < productCount; i++) {
			List<WebElement> ProductName = driver.findElements(By.className("inventory_item_name"));

			String productName = ProductName.get(i).getText();

			Allure.step("📦 Name Product selected on Product Page: " + productName);

			List<WebElement> addBtns = driver.findElements(By.xpath("//button[contains(text(),'Add to cart')]"));

			addBtns.get(0).click();

			validateHelper.ClickElement(driver.findElement(By.className("shopping_cart_link")));

			List<WebElement> ProductNameCart = driver.findElements(By.className("inventory_item_name"));

			String productNameCart = ProductNameCart.get(i).getText();

			Allure.step("🛒 Name Product shown in Cart Page: " + productNameCart);

			assertEquals(productName, productNameCart);

			validateHelper.ClickElement(driver.findElement(By.id("continue-shopping")));

		}
		for (int i = 0; i < productCount; i++) {
			List<WebElement> removeBtns = driver.findElements(By.xpath("//button[contains(text(),'Remove')]"));
			removeBtns.get(0).click();
		}
	}

	@Test(priority = 5)
	public void verifyProductDescriptionMatchesBetweenProductAndCartPages() {

		ValidateHelper validateHelper = new ValidateHelper(driver);
		int productCount = driver.findElements(By.className("inventory_item_name")).size();

		for (int i = 0; i < productCount; i++) {
			List<WebElement> ProductDesciption = driver.findElements(By.className("inventory_item_desc"));

			String productDescription = ProductDesciption.get(i).getText();

			Allure.step("📦 Description Product selected on Product Page: " + productDescription);

			List<WebElement> addBtns = driver.findElements(By.xpath("//button[contains(text(),'Add to cart')]"));

			addBtns.get(0).click();

			validateHelper.ClickElement(driver.findElement(By.className("shopping_cart_link")));

			List<WebElement> ProductDescriptionCart = driver.findElements(By.className("inventory_item_desc"));

			String productDescriptionCart = ProductDescriptionCart.get(i).getText();

			Allure.step("🛒 Description Product shown in Cart Page: " + productDescriptionCart);

			assertEquals(productDescription, productDescriptionCart);

			validateHelper.ClickElement(driver.findElement(By.id("continue-shopping")));

		}
		for (int i = 0; i < productCount; i++) {
			List<WebElement> removeBtns = driver.findElements(By.xpath("//button[contains(text(),'Remove')]"));
			removeBtns.get(0).click();
		}
	}

	@Test(priority = 6)
	public void verifyProductPriceMatchesBetweenProductAndCartPages() {

		ValidateHelper validateHelper = new ValidateHelper(driver);
		int productCount = driver.findElements(By.className("inventory_item_name")).size();

		for (int i = 0; i < productCount; i++) {
			List<WebElement> ProductPrice = driver.findElements(By.className("inventory_item_price"));

			String productPrice = ProductPrice.get(i).getText();

			Allure.step("📦 Price Product selected on Product Page: " + productPrice);

			List<WebElement> addBtns = driver.findElements(By.xpath("//button[contains(text(),'Add to cart')]"));

			addBtns.get(0).click();

			validateHelper.ClickElement(driver.findElement(By.className("shopping_cart_link")));

			List<WebElement> ProductPriceCart = driver.findElements(By.className("inventory_item_price"));

			String productPriceCart = ProductPriceCart.get(i).getText();

			Allure.step("🛒 Price Product shown in Cart Page: " + productPriceCart);

			assertEquals(productPrice, productPriceCart);

			validateHelper.ClickElement(driver.findElement(By.id("continue-shopping")));

		}
		for (int i = 0; i < productCount; i++) {
			List<WebElement> removeBtns = driver.findElements(By.xpath("//button[contains(text(),'Remove')]"));
			removeBtns.get(0).click();
		}
	}
	@Test(priority = 7)
	public void verifyQuantityProduct() {
		ValidateHelper validateHelper = new ValidateHelper(driver);
		int productCount = driver.findElements(By.className("inventory_item_name")).size();
		for (int i = 0; i < productCount; i++) {
			List<WebElement> addBtns = driver.findElements(By.xpath("//button[contains(text(),'Add to cart')]"));
			addBtns.get(0).click();
			validateHelper.ClickElement(driver.findElement(By.className("shopping_cart_link")));
			List<WebElement> QuantityProduct = driver.findElements(By.className("cart_quantity"));
			int quantityProduct = Integer.parseInt(QuantityProduct.get(i).getText());
			Allure.step("Số lượng sản phẩm thứ " + (i + 1) + " : " + quantityProduct);
			assertTrue(quantityProduct > 0, "quantity must > 0 ");
			validateHelper.ClickElement(driver.findElement(By.id("continue-shopping")));
		}
		for (int i = 0; i < productCount; i++) {
			List<WebElement> removeBtns = driver.findElements(By.xpath("//button[contains(text(),'Remove')]"));
			removeBtns.get(0).click();
		}
	}
	
	@Test(priority = 8)
	public void verifyRemoveProductFromCartPage() throws Exception {
		ValidateHelper validateHelper = new ValidateHelper(driver);
		int productCount = driver.findElements(By.className("inventory_item_name")).size();
		for (int i = 0; i < productCount; i++) {
			List<WebElement> addBtns = driver.findElements(By.xpath("//button[contains(text(),'Add to cart')]"));
			addBtns.get(i).click();
			validateHelper.ClickElement(driver.findElement(By.className("shopping_cart_link")));
			List<WebElement> removeBtns = driver.findElements(By.xpath("//button[contains(text(),'Remove')]"));
			removeBtns.get(0).click();
//			List<WebElement> badgeElements = driver.findElements(By.className("shopping_cart_badge"));
//			int badgeNumber = badgeElements.size() > 0 ? Integer.parseInt(badgeElements.get(0).getText()) : 0;
			List<WebElement> badgeCart = driver.findElements(By.className("shopping_cart_badge"));
			int badgeNumber = badgeCart.size() > 0 ? Integer.parseInt(badgeCart.get(i).getText()) : 0;
			
			assertEquals(badgeNumber, 0, "Pass");
			validateHelper.ClickElement(driver.findElement(By.id("continue-shopping")));
//					
//			validateHelper.ClickElement(driver.findElement(By.id("continue-shopping")));
		}
	}
	

}
