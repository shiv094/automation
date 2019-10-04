package testScripts;


import org.testng.annotations.Test;

import com.ezops.applicationInTest.pageObject.HomePage;

import baseClass.TestBase;
public class LoginTest extends TestBase {

HomePage hm =new HomePage();

	@Test
	public void test1() {
		hm.openContactUsPage();
	}

		
	
}
