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
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners({ io.qameta.allure.testng.AllureTestNg.class })
@Epic("Product Page")
@Feature("Product Page Functionality")
public class ProductTest extends BaseClass {
	private SignInPage signInPage;
	private ValidateHelper validateHelper;

//	@BeforeClass
//	public void Login() {
//		signInPage = new SignInPage(driver);
//		driver.get("https://www.saucedemo.com/");
//		signInPage.signin("standard_user", "secret_sauce");
//	}
    @BeforeMethod
    public void setUp() {
		signInPage = new SignInPage(driver);
		driver.get("https://www.saucedemo.com/");
		signInPage.signin("standard_user", "secret_sauce");
	}
	@Story("Check list product is displayed")
	@Test(priority = 0)
	@Severity(SeverityLevel.CRITICAL)
	public void ProductListisDisplayed() throws Exception {
		List<WebElement> productlist = driver.findElements(By.className("inventory_item"));

		assertTrue(productlist.size() > 0, "No product found after loggin in");
		Thread.sleep(2000);
	}

	@Test(priority = 1)
	public void verifyProductNameMatchBetweenDetailPage() throws Exception {
		int productCount = driver.findElements(By.className("inventory_item_name")).size();
		for (int i = 0; i < productCount; i++) {
			List<WebElement> productList = driver.findElements(By.className("inventory_item_name"));
			WebElement product = productList.get(i);

			String productNameList = product.getText();

			product.click();
			String productNameDetail = driver.findElement(By.className("inventory_details_name")).getText();
            assertEquals(productNameList, productNameDetail,"Product name not match");
//			if (productNameList.equals(productNameDetail)) {
//				System.out.println("Product name match: " + productNameList);
//			} else {
//				System.out.println(
//						"Product name not match. List: " + productNameList + " | Detail: " + productNameDetail);
//			}
			driver.findElement(By.id("back-to-products")).click();

		}
		Thread.sleep(2000);
	}

	@Test(priority = 2)
	public void verifyProductDescriptionMatchBetweenDetailPage() {
		int productCount = driver.findElements(By.className("inventory_item_desc")).size();
		for (int i = 0; i < productCount; i++) {
			List<WebElement> productList = driver.findElements(By.className("inventory_item_desc"));
			List<WebElement> productListC = driver.findElements(By.className("inventory_item_name"));
			WebElement product = productList.get(i);
			WebElement productC = productListC.get(i);

			String productDsc = product.getText();
			productC.click();
			String productDscDetail = driver.findElement(By.className("inventory_details_desc")).getText();
			assertEquals(productDsc, productDscDetail,"Product description not match");
//			if (productDsc.equals(productDscDetail)) {
//				System.out.println("Product Description Match:  " + productDsc);
//			} else {
//				System.out.println(
//						"Product Description Not Match. List:" + productDsc + " | Detail: " + productDscDetail);
//			}
			driver.findElement(By.id("back-to-products")).click();
		}
	}

	@Test(priority = 3)
	public void verifyProductPriceMatchBetweenDetailPage() {
		int productCount = driver.findElements(By.className("inventory_item_price")).size();
		for (int i = 0; i < productCount; i++) {
			List<WebElement> productListPrice = driver.findElements(By.className("inventory_item_price"));
			List<WebElement> productListC = driver.findElements(By.className("inventory_item_name"));
			WebElement productPrice = productListPrice.get(i);
			WebElement productC = productListC.get(i);

			String productP = productPrice.getText();
			productC.click();

			String productPriceDetail = driver.findElement(By.className("inventory_details_price")).getText();
			assertEquals(productP, productPriceDetail,"Product price not match");
//			if (productP.equals(productPriceDetail)) {
//				System.out.println("Product Price match: " + productP);
//			} else {
//				System.out.println("Prodcut Price not Match. List:" + productP + " | Detail: " + productPriceDetail);
//			}
			driver.findElement(By.id("back-to-products")).click();
		}
	}

	@Test(priority = 4)
	public void verifyProductImageMatchBetweenDetailPage() {
		int productCount = driver.findElements(By.cssSelector(".inventory_item_img img")).size();
		for (int i = 0; i < productCount; i++) {
			List<WebElement> productList = driver.findElements(By.cssSelector(".inventory_item_img img"));
			WebElement product = productList.get(i);

			String productImageList = product.getAttribute("src");

			product.click();
			String productImageDetail = driver.findElement(By.className("inventory_details_img")).getAttribute("src");
			assertEquals(productImageList, productImageDetail,"Product price not match");
//			if (productImageList.equals(productImageDetail)) {
//				System.out.println("Product image match: " + productImageList);
//			} else {
//				System.out.println(
//						"Product image not match. List: " + productImageList + " | Detail: " + productImageDetail);
//			}
			driver.findElement(By.id("back-to-products")).click();

		}
	}

	@Test(priority = 5)
	public void verifyAddtoCartFromProductPage()
	{
		int productCount = driver.findElements(By.xpath("//button[contains(text(),'Add to cart')]")).size();
		for(int i = 0 ; i < productCount ; i++)
		{
			List<WebElement> addButtons = driver.findElements(By.xpath("//button[contains(text(),'Add to cart')]"));
			
			addButtons.get(0).click();// quan trọng 
			
			WebElement badge = driver.findElement(By.className("shopping_cart_badge"));
			int badgeNumber = Integer.parseInt(badge.getText());
            assertTrue(badgeNumber == i + 1,"Badge sai");
            Allure.step("Badge đúng sau lần thêm thứ: " + (i + 1));
//            if(badgeNumber == i + 1 ) {
//            	System.out.println("Badge đúng sau lần thêm thứ: " + (i+1));
//            }else {
//            	System.out.println("Badge sai. expected: " + (i + 1) + " | Actual:" + badgeNumber);
//            }
		}
	}
	
	@Test(priority = 6)
	public void verifyAddtoCartFromDetailPage() throws Exception
	{
		List<WebElement> removeButtons = driver.findElements(By.xpath("//button[contains(text(),'Remove')]"));
		for(WebElement removebtn : removeButtons)
		{
			removebtn.click();
		}
		int productCount = driver.findElements(By.className("inventory_item_name")).size();
		for (int i = 0; i < productCount; i++) {
			List<WebElement> productClick = driver.findElements(By.className("inventory_item_name"));
			productClick.get(i).click();
			
			WebElement addButtons = driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]"));
			addButtons.click();
			
			driver.findElement(By.id("back-to-products")).click();
			
			WebElement badge = driver.findElement(By.className("shopping_cart_badge"));
			int badgeNumber = Integer.parseInt(badge.getText());
			assertTrue(badgeNumber == i + 1,"Badge sai");
			Allure.step("Badge đúng sau lần thêm thứ: " + (i + 1));
//			if (badgeNumber == i + 1) {
//				System.out.println("Badge đúng sau lần thêm thứ: " + (i + 1));
//			} else {
//				System.out.println("Badge sai. expected: " + (i + 1) + " | Actual: " + badgeNumber);
//			}
		}

	}
	@Test(priority = 7)
	public void verifyRemoveFromProductPage() throws Exception {
		List<WebElement> removeButtons = driver.findElements(By.xpath("//button[contains(text(),'Remove')]"));
		for(WebElement removeBtn : removeButtons)
		{
			removeBtn.click();
		}
		int productCount = driver.findElements(By.className("inventory_item_name")).size();
		for (int i = 0; i < productCount; i++) {
			List<WebElement> addBtns = driver.findElements(By.xpath("//button[contains(text(),'Add to cart')]"));
			addBtns.get(0).click();
		}
		for (int i = 0; i < productCount; i++) {
			List<WebElement> removeBtns = driver.findElements(By.xpath("//button[contains(text(),'Remove')]"));
			removeBtns.get(0).click();

			List<WebElement> badgeElements = driver.findElements(By.className("shopping_cart_badge"));
			int badgeNumber = badgeElements.size() > 0 ? Integer.parseInt(badgeElements.get(0).getText()) : 0;
			// Kiểm tra cái badge này có tồn tại không nếu có thì số text từ badge gán vào
			// badgenumber, ngược lại thì gán 0 vào badge\
			
			assertTrue(badgeNumber == productCount - (i + 1),"Badge sai");
//			if (badgeNumber == productCount - (i + 1)) {
//				System.out.println("Badge đúng sau lần remove thứ: " + (productCount - badgeNumber));
//			} else {
//				System.out.println("Badge sai. expected: " + (badgeNumber - i) + " | Actual: " + badgeNumber);
//			}
		}
	}

	@Test(priority = 8)
	public void verifyRemoveProductFromDetailPage() {
		int productCount = driver.findElements(By.className("inventory_item_name")).size();
		for (int i = 0; i < productCount; i++) {
			List<WebElement> addBtns = driver.findElements(By.xpath("//button[contains(text(),'Add to cart')]"));
			addBtns.get(0).click();
		}
		for (int i = 0; i < productCount; i++) {
			List<WebElement> productClick = driver.findElements(By.className("inventory_item_name"));
			productClick.get(i).click();

			WebElement removeBtns = driver.findElement(By.xpath("//button[contains(text(),'Remove')]"));
			removeBtns.click();

			List<WebElement> badgeElements = driver.findElements(By.className("shopping_cart_badge"));
			int badgeNumber = badgeElements.size() > 0 ? Integer.parseInt(badgeElements.get(0).getText()) : 0;
			assertTrue(badgeNumber == productCount - (i + 1),"Badge sai");
//			if (badgeNumber == productCount - (i + 1)) {
//				System.out.println("Badge đúng sau lần remove thứ: " + (productCount - badgeNumber));
//			} else {
//				System.out.println("Badge sai. expected: " + (badgeNumber - i) + " | Actual: " + badgeNumber);
//			}

			driver.findElement(By.id("back-to-products")).click();
		}
	}

	@Test(priority = 9)
	public void verifyNavigationFromTileToDetailPage() throws Exception {
		int productCount = driver.findElements(By.className("inventory_item_name")).size();
		for (int i = 0; i < productCount; i++) {
        List<WebElement> productName = driver.findElements(By.className("inventory_item_name"));
        productName.get(i).click();
        String url = driver.getCurrentUrl(); 
        assertTrue(url.contains("inventory-item.html"),"❌ Fail Page. Expected URL have contains 'inventory-item.html'");
        driver.findElement(By.id("back-to-products")).click(); 
        Thread.sleep(2000);
		}
	}
	
	@Test(priority = 10)
	public void verifyNavigationImgFromToDetailPage() throws Exception {
		int productCount = driver.findElements(By.cssSelector(".inventory_item_img img")).size();
		for (int i = 0; i < productCount; i++) {
        List<WebElement> productImg = driver.findElements(By.cssSelector(".inventory_item_img img"));
        productImg.get(i).click();
        String url = driver.getCurrentUrl(); 
        assertTrue(url.contains("inventory-item.html"),"❌ Fail Page. Expected URL have contains 'inventory-item.html'");
        driver.findElement(By.id("back-to-products")).click();
 
		}
	}
//	
	@Test(priority = 11)
	public void verifyButtonBackToProductFromDetailPage()
	{
		int productCount = driver.findElements(By.className("inventory_item_name")).size();
		for(int i = 0 ; i  < productCount ;  i++)
		{
			List<WebElement> productC = driver.findElements(By.className("inventory_item_name"));
			productC.get(i).click();
			driver.findElement(By.id("back-to-products")).click();
			String url = driver.getCurrentUrl();
			assertTrue(url.contains("inventory.html"),"Button Back Product Page Not Working");
		    Allure.step("✅ URL is correct after navigating to the Product Page!");
		}
			
	}

	@Test(priority = 12)
	public void verifyMenuProduct()
	{
		ValidateHelper validateHelper = new ValidateHelper(driver);
	    driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();   
	    validateHelper.ClickElement(driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")));
	    String url = driver.getCurrentUrl();
	    assertEquals(url, "https://www.saucedemo.com/","Logout Failed");
	    Allure.step("✅ URL is correct after navigating to the SignIn Page!");
	}
    @Test(priority = 13)
    public void verifyCartIcon()
    {
   	  ValidateHelper validateHelper = new ValidateHelper(driver);
   	  validateHelper.ClickElement(driver.findElement(By.className("shopping_cart_link")));
   	  String url = driver.getCurrentUrl();
   	  assertEquals(url, "https://www.saucedemo.com/cart.html", "❌ URL is incorrect after navigating to the cart page.");
   	  Allure.step("✅ URL is correct after navigating to the cart page!");
    }
	

}
