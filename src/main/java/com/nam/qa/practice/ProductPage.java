package com.nam.qa.practice;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	public WebDriver driver;
    public ProductPage(WebDriver driver)
    {
    	this.driver = driver;
    	PageFactory.initElements(driver, this);
    }
	public List<String> getAllProductsNames(By nameLocator) {
		List<WebElement> productElements = driver.findElements(nameLocator);
		List<String> names = new ArrayList<>();
		for (WebElement e : productElements) {
			names.add(e.getText().trim());
		}
		return names;
	}

	public List<Double> getAllProductPrices(By priceLocator) {
		List<WebElement> productElements = driver.findElements(priceLocator);
		List<Double> prices = new ArrayList<>();
		for (WebElement e : productElements) {
			String priceText = e.getText().replace("$", "").trim();
			prices.add(Double.parseDouble(priceText));
		}
		return prices;
	}
	
	// add Tiki Website 
	
	
}
