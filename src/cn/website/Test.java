package cn.website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class Test {

	public void getParam(){
		
		WebDriver driver = null;
		try {
			java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("config\\properties\\paramsConfig");

			String phantomjsUrl = bundle.getString("phantomjsurl");
			
			
			System.setProperty("phantomjs.binary.path", phantomjsUrl);
			driver = new PhantomJSDriver();
			
			driver.get("http://www.xici.net/d245590240.htm");
			
			String pageSource = driver.getPageSource();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
