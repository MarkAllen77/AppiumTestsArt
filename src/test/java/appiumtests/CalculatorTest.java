package appiumtests;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;

public class CalculatorTest {
	
	//Web driver driver;
	static AppiumDriver driver;
	static DesiredCapabilities dc = new DesiredCapabilities();
	//AndroidDriver driver;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//startAppiumServer();
			openCalculator();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getCause();
			e.getMessage();
			e.printStackTrace();
		}

	}
	
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
	
	public static void startAppiumServer() throws Exception {
		
		//        ProcessBuilder builder = new ProcessBuilder(
		//            "cmd.exe", "/c", "appium");
		//        builder.redirectErrorStream(true);
		//        Process p = builder.start();
		//        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		//        String line;
		//        while (true) {
		//            line = r.readLine();
		//            if (line == null) { break; }
		//            System.out.println(line);
		//        }
		Runtime rt = Runtime.getRuntime();
		rt.exec("cmd.exe /c start appium");
		Thread.sleep(5000);
		/*---- Run appium ----
		 * 1. Start Appium Server GUI
		 * 2. Verify cmd > adb devices
		*/
    }
	
	public static void loadSamsungCapabilities() {		
		dc.setCapability("deviceName", "Unknown");
		dc.setCapability("platformName", "Android");
		dc.setCapability("platformVersion", "11");
		dc.setCapability("udid", "ce071717d8f158360c7e");
		dc.setCapability("appPackage", "com.sec.android.app.popupcalculator");
		dc.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");
		dc.setCapability("automationName", "UiAutomator2");
		dc.setCapability("appWaitForLaunch", "false");		
	}

	public static void loadOppoCapabilities() {
		dc.setCapability("deviceName", "Unknown");
		dc.setCapability("platformName", "Android");
		dc.setCapability("platformVersion", "11");
		dc.setCapability("udid", "922bd3a7");
		//dc.setCapability("udid", "192.168.1.5:5555");
		dc.setCapability("appPackage", "com.coloros.calculator");
		dc.setCapability("appActivity", "com.android.calculator2.Calculator");
		dc.setCapability("automationName", "UiAutomator2");
		dc.setCapability("appWaitForLaunch", "false");			
	}
		
	public static void loadEmulatorCapabilities() {		
		dc.setCapability("deviceName", "Unknown");
		dc.setCapability("platformName", "Android");
		dc.setCapability("platformVersion", "8.1");
		dc.setCapability("udid", "emulator-5554");
		dc.setCapability("appPackage", "com.android.calculator2");
		dc.setCapability("appActivity", "com.android.calculator2.Calculator");
		dc.setCapability("automationName", "UiAutomator2");
		dc.setCapability("appWaitForLaunch", "false");		
	}

	
	public static void openCalculator() throws MalformedURLException{
		//samsung = 1
		//oppo = 2
		//emulator = 3
		
		int loadPhone = 1;
		
		WebElement one = null;
		WebElement two = null;
		WebElement plus = null;
		WebElement equals = null;
		
		System.out.println("Loading Capabilities...");
				
		switch (loadPhone)
		{
			case 1:
				loadSamsungCapabilities();
				break;
			case 2:
				loadOppoCapabilities();
				break;
			case 3:
				loadEmulatorCapabilities();
				break;
		}
			 
		
		System.out.println("Connecting to Device... " + loadPhone);	

		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AppiumDriver(url, dc);

		
		System.out.println("Start Application");

		if (loadPhone == 1)
		{
			one = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_01"));
			two = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_02"));
			plus = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_add"));
			equals = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_equal"));
		}
		if (loadPhone == 2)
		{
			infoBox("Is the calculator ready?", "Java Messagebox");
			
			one = driver.findElement(By.id("com.coloros.calculator:id/digit_1"));
			two = driver.findElement(By.id("com.coloros.calculator:id/digit_2"));
			plus = driver.findElement(By.id("com.coloros.calculator:id/op_add"));
			equals = driver.findElement(By.id("com.coloros.calculator:id/eq"));
		}
		if (loadPhone == 3)
		{		
			one = driver.findElement(By.id("com.android.calculator2:id/digit_1"));
			two = driver.findElement(By.id("com.android.calculator2:id/digit_7"));
			plus = driver.findElement(By.id("com.android.calculator2:id/digit_8"));
			equals = driver.findElement(By.id("com.android.calculator2:id/digit_9"));
		}
					
		one.click();
		plus.click();
		two.click();
		equals.click();
		
		
		System.out.println("Appium Complete");
	}

}
