package pageclass;

//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class Adminlogout {
	WebDriver dr;
	Actions ac;
	public Adminlogout(WebDriver dr) {
		this.dr =dr;
	}
	@FindBy(xpath="//*[@id=\"Table_01\"]/tbody/tr/td[3]/a/img") WebElement Logout;
public void verifyLogout() {
	ac = new Actions(dr);
	ac.moveToElement(Logout).click().perform();
	//this.Logout.sendKeys(Keys.ENTER);
	
}

}
