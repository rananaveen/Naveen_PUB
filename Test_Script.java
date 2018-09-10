package maintestcase;



import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pageclass.AdminLoginPage;
import pageclass.Adminlogout;
import pageclass.BranchCreation;
import pageclass.Roles;

public class Test_Script {
WebDriver driver;
ExtentReports report;
ExtentTest logger;
Workbook wb;
Sheet ws;
Row r;
FileInputStream fi;
FileOutputStream fo;
String inputpath="F:\\personal\\STC\\testNG\\INPUT\\testdata.xlsx";
String outputpath="F:\\personal\\STC\\testNG\\OUTPUTXL\\testresults.xlsx";
@BeforeTest
public void Generate() {
	report= new ExtentReports("F:\\personal\\STC\\testNG\\Output\\OutputpomReport.html");
	}
@BeforeMethod
public void loginpage() throws InterruptedException {
	driver = new ChromeDriver();
	Thread.sleep(5000);
	driver.manage().window().maximize();
	Thread.sleep(5000);
	
	driver.navigate().to("http://primusbank.qedgetech.com:9090/home.aspx");
	//driver.switchTo().alert().wait();
	//driver.switchTo().alert().wait();
	
	AdminLoginPage login = PageFactory.initElements(driver, AdminLoginPage.class);
	login.verify_Login("Admin", "Admin");
}
@Test(description= " Branch creation verification",priority=1, enabled= true)
public void Branch_creation() {
	logger= report.startTest("Branch creation verification");
	BranchCreation branch= PageFactory.initElements(driver, BranchCreation.class);
	branch.verfy_Branch_Creation("kukatpallyy", "kphbb", "jntuu", "hyderabadd", "50005", "INDIA", "Andhra Pradesh", "Hyderabad");
	String ActVal=driver.getCurrentUrl();
	String Expval="admin_banker_master";
	if(ActVal.contains(Expval)) {
		logger.log(LogStatus.PASS, "Branch created successfuly");
		Reporter.log("Branch created successfully");
		}
	else {
		logger.log(LogStatus.FAIL,"Branch created successfuly");
		Reporter.log("Branch created successfuly");
	}
	
}
@Test(description= " Role creation verification",priority=0, enabled= true)
public void Role_cration() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
	logger= report.startTest("Role Creation Verification");
	fi= new FileInputStream(inputpath);
	wb= WorkbookFactory.create(fi);
	ws= wb.getSheet("Roles");
	int rc= ws.getLastRowNum();
	//Reporter.log("executed upto here"+rc,true);
	//int cc= ws.getRow(0).getLastCellNum();
	for (int i=1; i<=rc; i++) {
		String name=ws.getRow(i).getCell(0).getStringCellValue();
		String desc=ws.getRow(i).getCell(1).getStringCellValue();
		String J= ws.getRow(i).getCell(2).getStringCellValue();	
	Roles role= PageFactory.initElements(driver, Roles.class);
	//role.verifyRoleCreation("ranaas", "testnn", 1);
	//role.verifyRoleCreation(name, desc, J);
	role.verifyRoleCreation(name, desc, J);
	String Actval= driver.getCurrentUrl();
	String Expval= "Admin_Roles_details";
	if(Actval.contains(Expval)) {
		logger.log(LogStatus.PASS, "Role created successfully");
		Reporter.log("Role created successfully",true);
		ws.getRow(i).createCell(3).setCellValue("Role created successfully");
		//ws.getRow(i).getCell(3).setCellStyle(wb.createCellStyle().setFillBackgroundColor(IndexedColors.AQUA.getIndex()));
	}
	else {
		logger.log(LogStatus.FAIL, "Role creation Unsuccessfull");
		Reporter.log("Role creation Unsuccessfull",true);
		ws.getRow(i).createCell(3).setCellValue("Role creation unsuccessfull");
		
	}
	fo= new FileOutputStream(new File(outputpath));
	wb.write(fo);
	}
	fi.close();
	
	fo.close();
	wb.close();
}
@Test(description= " Role deletion verification",priority=2, enabled= true)
public void VerifyRoleDeletion() throws InterruptedException, AWTException {
	logger =report.startTest("Role Deletion Verification");
	Roles role= PageFactory.initElements(driver, Roles.class);
	role.verifyRoleDeletion();
	String Actval= driver.getCurrentUrl();
	String Expval= "Admin_Roles_details";
	if(Actval.contains(Expval)) {
		logger.log(LogStatus.PASS, "Role Deleted successfully");
		Reporter.log("Role Deleted successfully",true);
	}
	else {
		logger.log(LogStatus.FAIL, "Role Deletion Unsuccessfull");
		Reporter.log("Role deletion Unsuccessfull",true);
		
		
	}
}
@AfterMethod
public void logoutpage() {
	Adminlogout logout =PageFactory.initElements(driver, Adminlogout.class);
	logout.verifyLogout();
	report.flush();
	report.endTest(logger);
	driver.close();
}

}
