package com.aog;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ObjectGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\soura\\workspace\\ObjectGenerator\\driver\\chromedriver.exe"); 
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.google.com/");
		//driver.get("https://www.wikipedia.org");
		List<WebElement> allElements = driver.findElements(By.xpath("//*"));
		System.out.println(allElements.size());
		for (WebElement webElement : allElements) {
			
			if (webElement.getAttribute("id").length() > 0)
			{
				System.out.println("By " + webElement.getTagName() + "_" + webElement.getAttribute("id") + " = By.id(\"" + webElement.getAttribute("id") + "\");");
			}
			else
			{
				if (!(webElement.getAttribute("name") == null))
				{
					System.out.println("By " + webElement.getTagName() + "_" + webElement.getAttribute("name") + " = By.name(\"" + webElement.getAttribute("name") + "\");");
				}
				else
				{
					if (!(webElement.getAttribute("class") == null))
					{
						System.out.println("By " + webElement.getTagName() + "_" + webElement.getAttribute("class") + " = By.xpath(\"" + "//" + webElement.getTagName() +  "[@class='" +  webElement.getAttribute("class") + "']" +"\");");
					}
					else
					{
						System.out.println("By " + webElement.getTagName() + "_" + "TagName" + " = By.tagName(\"" + webElement.getTagName() + "\");");
					}
				}
			}
		}
		driver.quit();
	}

}
