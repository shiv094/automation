package utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseClass.TestBase;

public class UserActions extends TestBase {

	WebDriverWait wait = new WebDriverWait(driver, 10);

	public void acceptAlert(int waitTime) {
		try {
			// WebDriverWait wait=new WebDriverWait(driver, waitTime);
			// wait.until(ExpectedConditions.alertIsPresent());
			// wait.until(ExpectedConditions.alertIsPresent());
			wait.until(ExpectedConditions.alertIsPresent());
			// wait.until
			Alert alt = driver.switchTo().alert();
			alt.accept();
		} catch (Exception e) {
			System.out.println("No Alert");
		}
	}

	public static void writeProperty(String fileName, String key, String data) {
		OutputStream out = null;
		try {
			Properties prop = new Properties();
			File f = new File("src//test//resoures//configFiles//" + fileName + ".properties");
			if (f.exists()) {
				prop.load(new FileReader(f));
				prop.setProperty(key, data);
			}
			out = new FileOutputStream(f);
			prop.store(out, key + "updated");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException ex) {
					System.out.println(ex.getMessage());
				}
			}
		}
	}

	public boolean isDisplayedAndEnabled(WebElement ele) {
		if (ele.isDisplayed() && ele.isEnabled()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isDisplayedAndDisabled(WebElement ele) {
		if (ele.isDisplayed() && !(ele.isEnabled())) {
			return true;
		} else {
			return false;
		}
	}

	public String getText(WebElement ele) {
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(ele));
		String text = null;
		try {
			text = ele.getText();
		} catch (Exception e) {
			e.getMessage();
		}
		return text;
	}

	public void select(WebElement ele, String option) {
		try {
			new WebDriverWait(driver, 40).until(ExpectedConditions.elementToBeClickable(ele));
			new Select(ele).selectByVisibleText(option.trim());
			if (!getSelectedOption(ele).equalsIgnoreCase(option.trim())) {
				new Select(ele).selectByVisibleText(option.trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getSelectedOption(WebElement ele) {
		String option = null;
		try {
			new WebDriverWait(driver, 40).until(ExpectedConditions.elementToBeClickable(ele));
			Select sel = new Select(ele);
			WebElement first = sel.getFirstSelectedOption();
			option = first.getText().trim();
		} catch (Exception e) {
			e.getMessage();

		}
		if (option.contains("please select")) {
			option = "";
		}
		return option;
	}

	public String checkSelectedOption(WebElement ele) {
		String optionName = null;
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(ele));
			if (getSelectedOption(ele).equalsIgnoreCase("please select")) {
				optionName = "";
			} else {
				optionName = getSelectedOption(ele);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return optionName;
	}

	public List<WebElement> getSelectFieldOptions(WebElement ele) {
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(ele));
		Select sel = new Select(ele);
		return sel.getOptions();
	}

	public String getTextBoxValue(WebElement ele) {
		if (ele.isEnabled()) {
			new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(ele));

		}
		return ele.getAttribute("value");
	}

	public void swithToFrameByName(String frameId) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String currentFrameName = (String) js.executeScript("return.self.name");
		if (!(currentFrameName.contains(frameId))) {
			WebElement frameToSwitch = driver.findElement(By.name(frameId));
			driver.switchTo().frame(frameToSwitch);
		}
	}

	public void swithToFrameById(String frameId) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String currentFrameName = (String) js.executeScript("return.self.name");
		if (!(currentFrameName.contains(frameId))) {
			WebElement frameToSwitch = driver.findElement(By.id(frameId));
			driver.switchTo().frame(frameToSwitch);
		}
	}

	public void selevtByIndex(WebElement ele, int index) {
		try {
			new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(ele));
			new Select(ele).selectByIndex(index);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void click(WebElement ele) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(ele));
		ele.click();
	}

	public void setText(WebElement element, String text) {
		try {
			new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(element));
			element.clear();
			element.sendKeys(text);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public String isTextDisplayed(WebElement element, int waitTime) {
		String text = null;
		try {
			new WebDriverWait(driver, waitTime).until(ExpectedConditions.elementToBeClickable(element));
			text = element.getText();
		} catch (Exception e) {
			e.getMessage();
		}
		return text;
	}

	public void pause(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clearText(WebElement element) {
		try {
			new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(element));
			element.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectRadioButton(List<WebElement> radio, String radioValue) {
		WebElement ele = null;
		for (WebElement element : radio) {
			if (element.getAttribute("vale").equals(radioValue)) {
				ele = element;
				break;
			}
		}
		try {
			if (ele != null) {
				new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(ele));
				ele.click();
			} else {
				System.out.println("not matching radio value:");
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void switchIFrame(String frameName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 200);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
		} catch (InvalidSelectorException e) {
			e.getMessage();
		}
	}

	public void switchPreviousFrame(String frame) {
		driver.switchTo().defaultContent();
		new WebDriverWait(driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
	}

	public void switchChildWindow() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		String parentWindow = driver.getWindowHandle();
		Set<String> child = driver.getWindowHandles();
		System.out.println("window handeles");
		for (String window : child) {
			if (!parentWindow.equals(child)) {
				driver.switchTo().window(window);
				driver.close();
				wait.until(ExpectedConditions.numberOfWindowsToBe(1));
				break;
			}
		}
		driver.switchTo().window(parentWindow);
	}

	public String switchToWindow() {
		new WebDriverWait(driver, 30).until(ExpectedConditions.numberOfWindowsToBe(2));
		String parentWindow = driver.getWindowHandle();
		Set<String> child = driver.getWindowHandles();
		System.out.println("window handeles");
		for (String window : child) {
			if (!parentWindow.equals(child)) {
				driver.switchTo().window(window);
				break;
			}
		}
		return parentWindow;
	}

	public String getTodayDate() {
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyy-HHMM");
		Date date = new Date();
		String todate = format.format(date);
		return todate;
	}

	public void switchAlternativeFrame(String frame, String alternateFrame) {
		driver.switchTo().defaultContent();
		try {
			new WebDriverWait(driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));

		} catch (Exception e) {
			new WebDriverWait(driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(alternateFrame));
		}
	}

	public void takeScreenShot() {
		TakesScreenshot ts = (TakesScreenshot) driver;
		try {
			File srcFile = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File("target\\screenshot\\screenshot.png"));
		} catch (Exception e) {
			System.out.println("screenshot not saved:");
		}
	}

	public void switchFrame(String frameName) {
		driver.switchTo().defaultContent();
		try {
			new WebDriverWait(driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));

		} catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}

	public void switchFrames(String frameName, String Iframe) {
		switchFrame(frameName);
		switchIFrame(Iframe);
	}

}
