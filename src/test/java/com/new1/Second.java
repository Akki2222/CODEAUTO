package com.new1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.onlytest.Base;

public class Second extends Base{
 
 @Test
  public void test2() {
	 driver.findElement(By.xpath("//a[text()='Funds']")).click();
  }
}
