package com.nam.qa.POM;

import java.text.Normalizer;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ValidateHelper {
	public WebDriver driver;
	public WebDriverWait wait;
	public JavascriptExecutor js;
	// hàm trùng với tên class là hàm xây dựng
	public ValidateHelper(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		js = (JavascriptExecutor) driver;
	}

	public void setText(By element, String value) // 1 là webelement 2 là by
	{
		wait.until(ExpectedConditions.elementToBeClickable(element));
		driver.findElement(element).clear();
		// sendkeys 1 giá trị cho element truyền vào
		driver.findElement(element).sendKeys(value);
	}

	public void ClickElement(WebElement element)
    {
       //Click của một phần tử element truyền vào 
       wait.until(ExpectedConditions.elementToBeClickable(element)).click();
     
       //Click của của Js
//       js.executeScript("arguments[0].click()", driver.findElement(element));
    }
	
	public void selectOptionsort(By element, int index) throws Exception
	{
		wait.until(ExpectedConditions.elementToBeClickable(element));
		Select select = new Select(driver.findElement(element));
		select.selectByIndex(index);
		Thread.sleep(2000);
	}
     
	// hàm bỏ dấu tiếng việt
	public String normalizeText(String str)
	{
		return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
		
	}
	
	

}
