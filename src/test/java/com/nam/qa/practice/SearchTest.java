package com.nam.qa.practice;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.Desktop.Action;
import java.security.KeyStoreSpi;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nam.qa.POM.BaseClass;
import com.nam.qa.POM.ValidateHelper;

public class SearchTest extends BaseClass {
	@BeforeClass
	public void setUp() throws Exception {
		ValidateHelper validateHelper = new ValidateHelper(driver);
		Actions action = new Actions(driver);

		driver.get("https://tiki.vn/");
		action.click().build().perform();

		validateHelper.ClickElement(driver.findElement(By.xpath("//span[contains(text(),'Tài khoản')]")));

		validateHelper.setText(By.xpath("//input[@placeholder='Số điện thoại']"), "0786990226");

		validateHelper.ClickElement(driver.findElement(By.xpath("//button[contains(text(),'Tiếp Tục')]")));

		validateHelper.setText(By.xpath("//input[@placeholder='Mật khẩu']"), "Nam023432657.");

		validateHelper.ClickElement(driver.findElement(By.xpath("//button[contains(text(),'Đăng Nhập')]")));

		Thread.sleep(10000);

		action.click().build().perform();

	}

//	Mục tiêu kiểm thử	Tên test case đề xuất
//	Tìm kiếm rỗng	verifySearchWithEmptyKeyword()
//	Tìm kiếm rất dài	verifySearchWithLongKeyword()
//	Tìm kiếm ký tự Unicode	verifySearchWithUnicodeCharacters()
//	Tìm kiếm với Enter	verifySearchByPressingEnter()
//	Tìm kiếm với nút tìm kiếm	verifySearchByClickingSearchButton()
	@Test(priority = 0)
	public void verifySearchWithValidKeyword() throws Exception {
		ValidateHelper validateHelper = new ValidateHelper(driver);
		WebElement searchInput = driver.findElement(By.cssSelector("input[data-view-id='main_search_form_input']"));
		validateHelper.ClickElement(driver.findElement(By.xpath("//input[@data-view-id='main_search_form_input']")));
		searchInput.clear();
		validateHelper.setText(By.cssSelector("input[data-view-id='main_search_form_input']"), "iphone");
		searchInput.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		int productCount = driver.findElements(By.cssSelector("a[data-view-id='product_list_item']")).size();
		assertTrue(productCount > 0, "No products are displayed");
		for (int i = 0; i < productCount; i++) {
			List<WebElement> productList = driver.findElements(By.cssSelector(".sc-68e86366-8.dDeapS"));
			String productTile = productList.get(i).getText();
			assertTrue(validateHelper.normalizeText(productTile).contains("iphone"),
					"Products do not contain the keywordIphone");
		}

	}

	@Test(priority = 1)
	public void verifySearchWithEmptyKeyword() throws Exception {
		ValidateHelper validateHelper = new ValidateHelper(driver);
		WebElement searchInput = driver.findElement(By.cssSelector("input[data-view-id='main_search_form_input']"));
		validateHelper.ClickElement(driver.findElement(By.xpath("//input[@data-view-id='main_search_form_input']")));
		searchInput.sendKeys(Keys.CONTROL + "a");
		searchInput.sendKeys(Keys.BACK_SPACE);
		validateHelper.setText(By.cssSelector("input[data-view-id='main_search_form_input']"), "      ");
		searchInput.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String messages = driver.findElement(By.cssSelector("div[class='sc-d31d4d01-0 gdLxPY'] div")).getText();
		assertEquals(messages, "Rất tiếc, không tìm thấy sản phẩm phù hợp với lựa chọn của bạn");
//		
	}

	@Test(priority = 2)
	public void verifySearchWithLongKeyword() throws Exception {
		ValidateHelper validateHelper = new ValidateHelper(driver);
		WebElement searchInput = driver.findElement(By.cssSelector("input[data-view-id='main_search_form_input']"));
		validateHelper.ClickElement(driver.findElement(By.xpath("//input[@data-view-id='main_search_form_input']")));
		searchInput.sendKeys(Keys.CONTROL + "a");
		searchInput.sendKeys(Keys.BACK_SPACE);
		validateHelper.setText(By.cssSelector("input[data-view-id='main_search_form_input']"),
				"“Burger của chúng tôi có gì khác biệt so với những thương hiệu trên thị trường? Đó là ở phần vỏ bánh brioche mềm mại, sẵn sàng tan chảy trong miệng cùng phần thịt bò Mỹ được nướng chín tới đậm đà, mọng nước!”");
		searchInput.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String messages = driver.findElement(By.cssSelector("div[class='sc-d31d4d01-0 gdLxPY'] div")).getText();
		assertEquals(messages, "Rất tiếc, không tìm thấy sản phẩm phù hợp với lựa chọn của bạn");

	}

	@Test(priority = 3)
	public void verifySearchWithUnicodeCharacters() throws Exception {
		ValidateHelper validateHelper = new ValidateHelper(driver);
		WebElement searchInput = driver.findElement(By.cssSelector("input[data-view-id='main_search_form_input']"));
		validateHelper.ClickElement(driver.findElement(By.xpath("//input[@data-view-id='main_search_form_input']")));
		searchInput.sendKeys(Keys.CONTROL + "a");
		searchInput.sendKeys(Keys.BACK_SPACE);
		validateHelper.setText(By.cssSelector("input[data-view-id='main_search_form_input']"), "#iphone");
		searchInput.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		int productCount = driver.findElements(By.cssSelector("a[data-view-id='product_list_item']")).size();
		for(int i = 0 ; i < productCount ; i++)
		{
			List<WebElement> productList = driver.findElements(By.cssSelector(".sc-68e86366-8.dDeapS"));
			String productTitle = productList.get(i).getText();
			assertTrue(validateHelper.normalizeText(productTitle).contains("iphone"),
					"Products do not contain the keywordIphone");
		}

	}
	@Test(priority = 4)
    public void verifySearchByClickingSearchButton()
    {
		ValidateHelper validateHelper = new ValidateHelper(driver);
		WebElement searchInput = driver.findElement(By.cssSelector("input[data-view-id='main_search_form_input']"));
		validateHelper.ClickElement(driver.findElement(By.xpath("//input[@data-view-id='main_search_form_input']")));
		searchInput.sendKeys(Keys.CONTROL + "a");
		searchInput.sendKeys(Keys.BACK_SPACE);
		validateHelper.setText(By.cssSelector("input[data-view-id='main_search_form_input']"), "kẹo");
		driver.findElement(By.cssSelector("button[data-view-id='main_search_form_button']")).click();
		String getUrl = driver.getCurrentUrl();
		assertTrue(getUrl.contains("https://tiki.vn/search?"));
    }
}
