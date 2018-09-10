package pageclass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class Roles {
	WebDriver driver;
	Actions ac;
	Robot r;
public Roles(WebDriver driver) {
	this.driver= driver;
}
@FindBy(xpath="//img[@src='images/Roles_but.jpg']") WebElement roles;
@FindBy(xpath="//input[@id='btnRoles']") WebElement newrole;
@FindBy(xpath="//input[@id='txtRname']") WebElement rolename;
@FindBy(xpath="//input[@id='txtRDesc']") WebElement roledesc;
@FindBy(xpath="//select[@id='lstRtypeN']") WebElement roletype;
@FindBy(xpath="//input[@id='btninsert']") WebElement submit;
@FindBy(xpath="//*//tr[2]/td[5]") WebElement roledelete;

public void verifyRoleCreation(String name,String desc,String I) throws InterruptedException {
	ac =new Actions(driver);
	ac.moveToElement(roles).click().perform();
	ac.moveToElement(newrole).click().perform();
	this.rolename.sendKeys(name);
	this.roledesc.sendKeys(desc);
	new Select(roletype).selectByValue(I);;
	ac.moveToElement(submit).click().perform();
	String Message1= driver.switchTo().alert().getText();
	Reporter.log(Message1,true);
	Thread.sleep(5000);
	driver.switchTo().alert().accept();
}
public void  verifyRoleDeletion() throws InterruptedException, AWTException {
	ac= new Actions(driver);
	r= new Robot();
	ac.moveToElement(roles).click().perform();
	ac.moveToElement(roledelete).click().perform();
	//Thread.sleep(5000);
	driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
	r.keyPress(KeyEvent.VK_ENTER);
	//driver.switchTo().alert().accept();
	
	driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
	/*String Message2= driver.switchTo().alert().getText();
	Reporter.log(Message2,true);
	driver.switchTo().alert().accept();	*/

}
}