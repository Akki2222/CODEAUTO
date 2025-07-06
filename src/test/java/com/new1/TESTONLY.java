package com.new1;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.onlytest.Base;

public class TESTONLY extends Base {

    @Test
    public void methodFirst() {
          
    	new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));


        // Find all links
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Found " + links.size() + " links");

        // Loop through each link
        for (WebElement link : links) {
            @SuppressWarnings("deprecation")
			String url = link.getAttribute("href");
            if (url == null || url.isBlank()
                    || url.startsWith("javascript:")
                    || url.startsWith("mailto:")
                    || url.startsWith("tel:")) {
                continue;
            }

            try {
                @SuppressWarnings("deprecation")
				HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("HEAD");
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                conn.connect();

                int code = conn.getResponseCode();
                if (code >= 400) {
                    System.out.printf("[BROKEN] %-3d -> %s%n", code, url);
                } else {
                    System.out.printf("[OK]     %-3d -> %s%n", code, url);
                }

            } catch (Exception e) {
                System.out.printf("[ERROR]       -> %s (%s)%n", url, e.getClass().getSimpleName());
            }
        }
    }
}
