package com.nam.qa.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.nam.qa.POM.BaseClass;
import com.nam.qa.POM.ValidateHelper;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Epic("DropdownSortProduct")
@Feature("Product sorting function")
public class SortProductTest extends BaseClass {
	private ValidateHelper validateHelper;
	private SignInPage signInPage;
	private By Sort = By.className("product_sort_container");
    private ProductPage productPage;
	@BeforeClass
	public void LoginSauceDemo() throws Exception {
		signInPage = new SignInPage(driver);
		productPage = new ProductPage(driver);
		validateHelper = new ValidateHelper(driver);
		driver.get("https://www.saucedemo.com/");
		signInPage.signin("standard_user", "secret_sauce");
	}

	@Test(priority = 0)
	public void verifyProductSortingByNameAscending() throws Exception {
		validateHelper.selectOptionsort(Sort, 0);

		By productName = By.className("inventory_item_name");
		List<String> uiList = productPage.getAllProductsNames(productName);

		List<String> StoreList = new ArrayList<>(uiList);
		Collections.sort(StoreList);

		if (uiList.equals(StoreList)) {
			System.out.println("✅ PASS: Danh sách đã được sắp xếp đúng từ A → Z");
		} else {
			System.out.println("❌ FAIL: Danh sách KHÔNG được sắp xếp đúng A → Z");
			System.out.println("UI: " + uiList);
			System.out.println("Expected: " + StoreList);
		}
	}

	@Test(priority = 1)
	public void verifyProductSortingByNameDescending() throws Exception {
		validateHelper.selectOptionsort(Sort, 1);
		
		By productName = By.className("inventory_item_name");
		List<String> uiList = productPage.getAllProductsNames(productName);
		
		List<String> StoreList = new ArrayList<>(uiList);
		Collections.sort(StoreList, Collections.reverseOrder());
		
		if(uiList.equals(StoreList)){
			System.out.println("✅ PASS: Danh sách đã được sắp xếp đúng từ Z → A");
		} else {
			System.out.println("❌ FAIL: Danh sách KHÔNG được sắp xếp đúng A → Z");
			System.out.println("UI: " + uiList);
			System.out.println("Expected: " + StoreList);
		}
		
	}

	@Test(priority = 2)
	public void verifyProductSortingByPriceLowToHigh() throws Exception {
		validateHelper.selectOptionsort(Sort, 2);
		
		By productName = By.className("inventory_item_price");
		List<Double> uiList = productPage.getAllProductPrices(productName);
		
		List<Double> priceList = new ArrayList<>(uiList);
		Collections.sort(priceList);
		
		if(uiList.equals(priceList)) {
			System.out.println("✅ PASS: Danh sách đã được sắp xếp đúng giá từ thấp đến cao");
		}else {
			System.out.println("❌ FAIL: Danh sách KHÔNG được sắp xếp đúng giá từ thấp đến");
			System.out.println("UI: " + uiList);
			System.out.println("Expected: " + priceList);
		}
	}

	@Test(priority = 3)
	public void verifyProductSortingByPriceHighToLow() throws Exception {
		validateHelper.selectOptionsort(Sort, 3);
		
		By productName = By.className("inventory_item_price");
		List<Double> uiList = productPage.getAllProductPrices(productName);
		
		List<Double> priceList = new ArrayList<>(uiList);
		Collections.sort(priceList,Collections.reverseOrder());
		
		if(uiList.equals(priceList)) {
			System.out.println("✅ PASS: Danh sách đã được sắp xếp đúng giá từ cao đến thấp");
		}else {
			System.out.println("❌ FAIL: Danh sách KHÔNG được sắp xếp đúng giá từ cao đến thấp");
			System.out.println("UI: " + uiList);
			System.out.println("Expected: " + priceList);
		}
	}
}
