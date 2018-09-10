package pageclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AdminLoginPage {
	WebDriver driver;
	Actions ac;
public AdminLoginPage(WebDriver driver) {
this.driver= driver;		
	}
@FindBy(name="txtuId")
WebElement Ente_username;
@FindBy(name="txtPword") WebElement Enter_Password;
@FindBy(name="login") WebElement Click_login;

public void verify_Login(String username, String password) {
	ac= new Actions(driver);
	this.Ente_username.sendKeys(username);
	this.Enter_Password.sendKeys(password);
	ac.moveToElement(Click_login).click().build().perform();
}
}