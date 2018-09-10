package pageclass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class BranchCreation {
	WebDriver driver;
	Actions ac;
public BranchCreation(WebDriver driver) {
	this.driver= driver;
}
@FindBy (xpath="//img[@src='images/Branches_but.jpg']") WebElement Click_Branches;
@FindBy (xpath="//input[@id='BtnNewBR']") WebElement Click_New_Branches;
@FindBy (xpath="//input[@id='txtbName']") WebElement Enter_Branch_name;
@FindBy (xpath="//input[@id='txtAdd1']") WebElement Enter_address;
@FindBy (xpath="//input[@id='Txtadd2']") WebElement Enter_address2;
@FindBy (xpath="//input[@id='txtadd3']") WebElement Enter_address3;
@FindBy (xpath="//input[@id='txtArea']") WebElement Enter_area;
@FindBy (xpath="//input[@id='txtZip']") WebElement Enter_ZipCode;
@FindBy (xpath="//select[@id='lst_counrtyU']") WebElement Select_Country;
@FindBy (xpath="//select[@id='lst_stateI']") WebElement Select_state;
@FindBy (xpath="//select[@id='lst_cityI']") WebElement Select_City;
@FindBy (xpath="//input[@id='btn_insert']") WebElement Click_Submit;
public void verfy_Branch_Creation(String Bname, String Add1, String Add2, String Add3,String zip, String country,String state,String city)  {
	ac= new Actions(driver);
	ac.moveToElement(Click_Branches).click().perform();
	ac.moveToElement(Click_New_Branches).click().perform();
	this.Enter_Branch_name.sendKeys(Bname);
	this.Enter_address.sendKeys(Add1);
	this.Enter_address2.sendKeys(Add2);
	this.Enter_address3.sendKeys(Add3);
	this.Enter_ZipCode.sendKeys(zip);
	Select countrylist= new Select(Select_Country);
	countrylist.selectByVisibleText(country);
	Select statelist= new Select(Select_state);
	statelist.selectByVisibleText(state);
	Select citylist= new Select(Select_City);
	citylist.selectByVisibleText(city);
	ac.moveToElement(Click_Submit).click().perform();
	 driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
	String message= driver.switchTo().alert().getText();
	Reporter.log(message,true);
	
	 driver.switchTo().alert().accept();
	 
	 
	
	
	
}

}
