package com.ezops.applicationInTest.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClass.TestBase;
import utility.UserActions;

public class HomePage extends TestBase {
	//public WebDriver driver;
	UserActions userActions = new UserActions();

	@FindBy(xpath = "//a[contains(text(),'Sign in')]")
	public WebElement signIn;

	@FindBy(xpath = "(//a[contains(text(),'Contact us')])[1]")
	public WebElement contactUs;

	public HomePage() {
		
		PageFactory.initElements(driver, this);

	}

	public void openSignInPage() {
		if (userActions.isDisplayedAndEnabled(signIn))
			signIn.click();
		//return new SignInPage();
	}

	public ContactUsPage openContactUsPage() {
		if (userActions.isDisplayedAndEnabled(contactUs))
			contactUs.click();
		return new ContactUsPage();
	}

}
