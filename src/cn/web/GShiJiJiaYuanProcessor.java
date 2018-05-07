package cn.web;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cn.uitl.ContentUtil;

public class GShiJiJiaYuanProcessor {

	
	
	public static void toLogin(){
		WebDriver driver = null;
		try {
//			System.setProperty("phantomjs.binary.path", "F:/selenium/phantomjs.exe");
//			System.setProperty("webdriver.chrome.driver", "F:/selenium/chromedriver.exe");
			System.setProperty("webdriver.firefox.bin", "F:/selenium/ff/firefox.exe");
			String url = "http://login.jiayuan.com/";
			String urls = "http://search.jiayuan.com/v2/";
			driver = new FirefoxDriver();
			
			System.out.println(url);
			driver.get(url);
			
			driver.findElement(By.id("login_email")).sendKeys("18813155934");
			driver.findElement(By.id("login_password")).sendKeys("zhujiacun5717");
			driver.findElement(By.id("login_btn")).click();
			Thread.sleep(5000);
			String currentUrl = driver.getCurrentUrl();
			if(currentUrl.contains("login")){
				System.out.println(currentUrl);
				System.out.println("登录成功");
			}
			
			driver.get(urls);
			Thread.sleep(2000);
			
			//初次执行&&&点击下一页执行
			
			String pageSource = driver.getPageSource();
			Document doc = Jsoup.parse(pageSource);
			Elements elementsByClass = doc.getElementsByClass("hy_box");
			System.out.println("sleep..."+elementsByClass.size());
			Thread.sleep(2000);
			for (int i = 0; i < elementsByClass.size(); i++) {
				Element element = elementsByClass.get(i);
				Thread.sleep(2000);
				Elements elementsByClass2 = element.getElementsByClass("user_name");
				for (int j = 0; j < elementsByClass2.size(); j++) {
					Thread.sleep(2000);
					Element element2 = elementsByClass2.get(0);
					String link = element2.getElementsByTag("a").attr("href");
					String name = element2.getElementsByTag("a").text();
					System.out.println(link);
					System.out.println(name);
					ContentUtil.writeFileData(name);
					//=======================
					driver.get(link);
					String pageContent = driver.getPageSource();
					Document page = Jsoup.parse(pageContent);
					Elements es = page.getElementsByClass("member_info_r");
					Thread.sleep(2000);
					for (int k = 0; k < es.size(); k++) {
						Element e = es.get(0);
						Elements es2 = e.getElementsByClass("pr20");
						Elements es3 = e.getElementsByClass("member_dj");
						Elements es4 = e.getElementsByClass("member_name");
						Elements es5 = e.getElementsByTag("li");
//						Map<String, String> nameMap = getName(es2.text());
						System.out.println(es2.text());
						System.out.println(es3.text());
						System.out.println(es4.text());
						System.out.println(es5.text());
						ContentUtil.writeFileData(es2.text());
						ContentUtil.writeFileData(es3.text());
						ContentUtil.writeFileData(es4.text());
						ContentUtil.writeFileData(es5.text());
						ContentUtil.writeFileData("=============================");
					}
					System.out.println("===============================");
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			driver.quit();
		}
	}
	
	public static Map<String,String> getName(String str){
		Map<String,String> map = new HashMap<String, String>();
		if(str.indexOf("ID") != -1){
			String name = str.substring(0, str.indexOf("ID"));
			String id = str.substring(str.indexOf("ID")+3,str.length()-1);
			System.out.println(name);
			System.out.println(id);
			map.put("name", name);
			map.put("id", id);
		}
		return map;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		toLogin();
//		Map<String, String> nameMap = getName("jacunID:172892390");

	}

}
