package cn.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import cn.uitl.ContentUtil;

public class GTMTianPingNiaoGirlProcessor {
	
	
	public static void getLinkList(){
		WebDriver driver = null;
		try {
			String urls = "https://login.taobao.com/member/login.jhtml";
			System.setProperty("webdriver.firefox.bin", "F:/selenium/ff/firefox.exe");
			driver = new FirefoxDriver();
			
			System.out.println(urls);
			driver.get(urls);
			
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div/div/div[2]/div[4]/div/div[5]/a[1]")).click();
			
			driver.findElement(By.id("TPL_username_1")).sendKeys("巫师蜕变");
			driver.findElement(By.id("TPL_password_1")).sendKeys("zhujiacun5717!");
			driver.findElement(By.id("J_SubmitStatic")).click();
			Thread.sleep(5000);
			String currentUrl = driver.getCurrentUrl();
			if(currentUrl.contains("login")){
				System.out.println(currentUrl);
				System.out.println("登录成功");
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}finally{
			driver.quit();
		}
		
		
		for (int i = 1; i < 21; i++) {
//			String url = "http://bj.58.com/chuzu/1/pn"+i;
			String url = "https://pb89.tmall.com/search.htm?spm=a1z10.1-b-s.w5001-16886321023.6.4fc672e76xac4T&search=y&orderType=hotsell_desc&tsearch=y&scene=taobao_shop";
//			String url = "https://pb89.tmall.com/search.htm?spm=a1z10.3-b-s.w4011-14572899161.445.16488963MCo32i&search=y&orderType=hotsell_desc&scene=taobao_shop&pageNo="+i+"&tsearch=y#anchor";
			System.out.println(url);
		
			
			try {
				System.setProperty("webdriver.firefox.bin", "F:/selenium/ff/firefox.exe");
//				System.setProperty("webdriver.chrome.driver", "F:/selenium/chromedriver.exe");
//				System.setProperty("phantomjs.binary.path", "F:/selenium/phantomjs.exe");
				driver = new FirefoxDriver();
				driver.get(url);
				Thread.sleep(5000);
				String pageSource = driver.getPageSource();
				Document doc = Jsoup.parse(pageSource);
				Elements elementsByClass = doc.getElementsByClass("item");
				Thread.sleep(2000);
				for (int j = 0; j < elementsByClass.size(); j++) {
					Element element2 = elementsByClass.get(j);
					String link = element2.getElementsByTag("a").attr("href");
					getContent(link);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				driver.quit();
			}
			
		}
	}
	
	public static void getContent(String link){
	
		WebDriver driver = null;
		try {
			System.setProperty("webdriver.firefox.bin", "F:/selenium/ff/firefox.exe");
			driver = new FirefoxDriver();
			driver.get(link);
			Thread.sleep(5000);
			String pageSource = driver.getPageSource();
			Document doc = Jsoup.parse(pageSource);
			Elements h1 = doc.getElementsByTag("h1");
			Elements jg = doc.getElementsByClass("tm-fcs-panel");
			Elements yxl = doc.getElementsByClass("tm-ind-panel");
			Elements jbxx = doc.getElementsByClass("tm-sale-prop");
			Element kucun = doc.getElementById("J_EmStock");
//			Elements elementsByClass2 = doc.getElementsByClass("house-chat-txt");
			Thread.sleep(2000);
			System.out.println(h1.text());
			System.out.println(jg.text());
			System.out.println(yxl.text());
			System.out.println(jbxx.text());
			System.out.println(kucun.text());
//			ContentUtil.writeFileData("姓名:"+name+"  电话号码:"+phone);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			driver.quit();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		getLinkList();
	}

}
