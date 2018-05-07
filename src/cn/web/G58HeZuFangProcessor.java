package cn.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cn.uitl.ContentUtil;

public class G58HeZuFangProcessor {

	public static String pageUrl = "";
	
	public static void getLinkList(){
		for (int i = 3; i < 30; i++) {
//			String url = "http://bj.58.com/hezu/sub/l2/s1983/pn"+i;
			String url = "http://bj.58.com/chuzu/1/pn"+i;
			System.out.println(url);
		
			WebDriver driver = null;
			try {
				System.setProperty("webdriver.firefox.bin", "F:/selenium/ff/firefox.exe");
				driver = new FirefoxDriver();
				driver.get(url);
				Thread.sleep(5000);
				String pageSource = driver.getPageSource();
				Document doc = Jsoup.parse(pageSource);
				Elements elementsByClass = doc.getElementsByClass("listUl");
				Thread.sleep(2000);
				Element element = elementsByClass.get(0);
				Elements elementsByTag = element.getElementsByTag("li");
				Thread.sleep(2000);
				for (int j = 0; j < elementsByTag.size(); j++) {
					Element element2 = elementsByTag.get(j);
					Elements elementsByTag2 = element2.getElementsByTag("h2");
					if(elementsByTag2.size()>0){
						Element element3 = elementsByTag2.get(0);
						String link = element3.getElementsByTag("a").attr("href");
						getContent(link);
					}
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
			Elements elementsByClass = doc.getElementsByClass("agent-name");
			Elements elementsByClass2 = doc.getElementsByClass("house-chat-txt");
			Thread.sleep(2000);
			String name = elementsByClass.text();
			String phone = elementsByClass2.text();
			System.out.println(name);
			System.out.println(phone);
			ContentUtil.writeFileData("ÐÕÃû:"+name+"  µç»°ºÅÂë:"+phone);
			
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
