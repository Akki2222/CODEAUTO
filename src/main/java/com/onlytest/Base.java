package com.onlytest;

import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class Base {
  protected  WebDriver driver;
 @BeforeClass
  public void setBrowser() {
	 WebDriverManager.chromedriver().setup();

	   driver = new ChromeDriver();
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	   // change to your target URL
  }
 
 @BeforeMethod
 public void beforeMethod() {
	  driver.get("https://www.hdfcfund.com/");
 }
 
 @AfterMethod
 public void clearCookies() {
	 driver.manage().deleteAllCookies();
 }
 
 @AfterClass
 public void afterClass() {
	 driver.close();
 }
}
