package com.new1;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.onlytest.Base;

public class TESTONLY extends Base{

	@Test
	public void methodFirst() {

      List<WebElement> links = driver.findElements(By.tagName("a"));
      System.out.println("Found " + links.size() + " links");

      for (WebElement link : links) {
          String url = link.getAttribute("href");
          if (url == null || url.isBlank()
                  || url.startsWith("javascript:")
                  || url.startsWith("mailto:")
                  || url.startsWith("tel:")) {
              continue;
          }
          checkUrl(url);
      }

      driver.quit();
  }
  private static void checkUrl(String urlString) {
      try {
          HttpURLConnection conn = (HttpURLConnection) new URL(urlString).openConnection();
          conn.setRequestMethod("HEAD");
          conn.setConnectTimeout(5000);
          conn.setReadTimeout(5000);
          conn.connect();

          int code = conn.getResponseCode();
          if (code >= 400) {
              System.out.printf("[BROKEN] %-3d -> %s%n", code, urlString);
          } else {
              System.out.printf("[OK]     %-3d -> %s%n", code, urlString);
          }
      } catch (Exception e) {
          System.out.printf("[ERROR]       -> %s (%s)%n", urlString, e.getClass().getSimpleName());
      }
  }
  }

