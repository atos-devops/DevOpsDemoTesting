package net.atos.Testing1;

import junit.framework.TestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DemoTest {
	static HtmlUnitDriver driver = new HtmlUnitDriver();

	//static WebDriver driver = new FirefoxDriver();
	String env=System.getProperty("env.server");
	@Test(priority = 2)
	public void checkLogIn() {
		
		WebElement username = driver
				.findElement(By
						.xpath(".//*[@id='command']/div/table/tbody/tr[1]/td[2]/input"));
		WebElement password = driver
				.findElement(By
						.xpath(".//*[@id='command']/div/table/tbody/tr[2]/td[2]/input"));
		username.sendKeys("admin");
		password.sendKeys("admin123");
		WebElement btn = driver
				.findElement(By
						.xpath(".//*[@id='command']/div/table/tbody/tr[3]/td[2]/input"));
		btn.click();
		WebElement adminName = driver.findElement(By.xpath("html/body/h2"));
		AssertJUnit.assertEquals("Welcome admin", adminName.getText());

	} // end of checkin method

	@Test(priority = 1)
	public void formValidation() {
		WebElement username = driver
				.findElement(By
						.xpath(".//*[@id='command']/div/table/tbody/tr[1]/td[2]/input"));
		username.sendKeys("admin");
		WebElement password = driver
				.findElement(By
						.xpath(".//*[@id='command']/div/table/tbody/tr[2]/td[2]/input"));
		WebElement btn = driver
				.findElement(By
						.xpath(".//*[@id='command']/div/table/tbody/tr[3]/td[2]/input"));
		btn.click();
		WebElement errorMessage = driver
				.findElement(By
						.xpath(".//*[@id='command']/div/div"));
		
		AssertJUnit.assertEquals("Invalid user. Please enter details again.", errorMessage.getText());
	}//end
	
	@Test (priority=3)
	public void checkValidEmail()
	{
		WebElement email = driver
				.findElement(By
						.xpath("html/body/form/table/tbody/tr/td[1]/input"));
		email.sendKeys("sumit.2.jaiswal@atos.net");
		WebElement btn = driver
				.findElement(By
						.xpath("html/body/form/table/tbody/tr/td[2]/input"));
		btn.click();
		WebElement validEmail = driver
				.findElement(By
						.xpath("html/body/form/table/tbody/tr[2]/td/font"));

		AssertJUnit.assertEquals("[sumit.2.jaiswal@atos.net] Valid email address!", validEmail.getText());
	}// end
	
	
	@Test (priority=4)
	public void checkInvalidEmail()
	{
		WebElement email = driver
				.findElement(By
						.xpath("html/body/form/table/tbody/tr/td[1]/input"));
		email.sendKeys("sumit.2.jaiswal");
		WebElement btn = driver
				.findElement(By
						.xpath("html/body/form/table/tbody/tr/td[2]/input"));
		btn.click();
		WebElement invalidEmail = driver
				.findElement(By
						.xpath("html/body/form/table/tbody/tr[2]/td/font"));

		AssertJUnit.assertEquals("[sumit.2.jaiswal] Invalid email address!", invalidEmail.getText());
	}//
	
	@Test(priority=5)
	public void checkVersion()
	{
		WebElement bodyText=driver.findElement(By.xpath("//body[contains(.,'Version: 1.0-SNAPSHOT')]"));
		AssertJUnit.assertTrue("Version: 1.0-SNAPSHOT", bodyText.getText().contains("Version: 1.0-SNAPSHOT"));
		//Assert.assertTrue("Version: 1.0-SNAPSHOT", bodyText.contains(bodyText));
	}
	
	/*@Test (priority=4)
	public void checkUserCounter()
	{
		WebElement link = driver
				.findElement(By
						.xpath("html/body/a[1]"));
		//email.sendKeys("sumit.2.jaiswal");
		link.click();
		WebElement counter = driver
				.findElement(By
						.xpath("html/body/h3[1]"));
		//btn.click();
		AssertJUnit.assertEquals("Message : Welcome", counter.getText());
	}*/
	
	/*@Test (priority=5)
	public void checkCalculator()
	{
		driver.navigate().back();
		WebElement link = driver
				.findElement(By
						.xpath("html/body/a[2]"));
		//email.sendKeys("sumit.2.jaiswal");
		link.click();
		WebElement calculator = driver
				.findElement(By
						.xpath("html/body/h3[1]"));
		//btn.click();
		AssertJUnit.assertEquals("Message : Welcome calculator", calculator.getText());
	}*/

	@BeforeTest
	@Parameters({"DEV","QA","PROD"})
	@Test(priority=0)
	public void beforeTest(String DEV,String QA,String PROD) {
		 System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
		 String baseUrl="";
		 if(env.contains("DEV"))
		 {
			 baseUrl = "http://esegsd517.emea.nsn-net.net:"+DEV+"/DevOpsDemo/login";	 
		 }
		 else if(env.contains("QA"))
		 {
			 baseUrl = "http://esegsd517.emea.nsn-net.net:"+QA+"/DevOpsDemo/login";
		 }
		 else if(env.contains("PROD"))
		 {
			 baseUrl = "http://esegsd517.emea.nsn-net.net:"+PROD+"/DevOpsDemo/login";
		 }
		/*System.out.println("URL + "+baseUrl);*/
		try {
			driver.get(baseUrl);
		} catch (Throwable e) {
			System.out.println("Application DOWN : PLEASE CHECK "
					+ e.getMessage());
			System.exit(0);
		}
	}//

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
