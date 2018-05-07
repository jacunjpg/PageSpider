package cn.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import cn.uitl.ConfigInfo;
import cn.uitl.ContentUtil;

public class GXingaochunGaochunwenhuaProcesspr {
	
	//http://www.xingaochun.com/forum-44-1.html
	public static String TitleUrl= "http://www.xingaochun.com/forum-44-";
	
	public static String Link="http://www.xingaochun.com/";

	/**
	 * 获取总的页数
	 * @return
	 */
	public static int GetPage() {
		int page = 1;
		WebDriver driver = null;
		try {
			System.setProperty("phantomjs.binary.path", "F:/selenium/phantomjs.exe");
			driver = new PhantomJSDriver();
			driver.get(ConfigInfo.url);
			
			String pageSource = driver.getPageSource();
			Document title = Jsoup.parse(pageSource);
			
			Elements elementsByClass = title.getElementsByClass("pg");
			Element element2 = elementsByClass.get(0);
			String text = element2.getElementsByTag("span").text();
			if(text.length()>2){
				String num = text.substring(text.indexOf("/")+1, text.length()-2);
				page = Integer.parseInt(num.trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			driver.quit();
		}
		
		return page;
	}
	
	//获取url
	public static void GetUrl(int pageNum){
		for (int i = 1; i <= pageNum; i++) {
			String url = TitleUrl+i+".html";
			System.out.println(url);
			WebDriver driver = null;
			try {
				System.setProperty("phantomjs.binary.path", "F:/selenium/phantomjs.exe");
				driver = new PhantomJSDriver();
				driver.get(url);
				
				String pageSource = driver.getPageSource();
				Document title = Jsoup.parse(pageSource);
				
//				Elements links = title.select("a[href]");
//				Elements elementsByClass = title.getElementsByClass("by");
//				for (int j = 0; j < links.size(); j++) {
//					Element element = links.get(j);
//					System.out.println(element);
//				}
				
				Element element = title.getElementById("threadlisttableid");
				Elements allElements = element.getElementsByTag("th");
				for (int j = 0; j < allElements.size(); j++) {
					Element e = allElements.get(j);
					Elements elementsByClass = e.getElementsByClass("xst");
//					for (int k = 0; k < 1; k++) {
					for (int k = 0; k < elementsByClass.size(); k++) {
						Element element2 = elementsByClass.get(k);
						String hrefs = element2.attr("href");
						String text = element2.text();
						System.out.println(hrefs+"==="+text);
						GetContent(hrefs);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				driver.quit();
			}
		}
	}
	
	public static void GetContent(String href){
		String url = Link+href;
		WebDriver driver = null;
		try {
			System.setProperty("phantomjs.binary.path", "F:/selenium/phantomjs.exe");
			driver = new PhantomJSDriver();
			driver.get(url);
			
			String pageSource = driver.getPageSource();
			Document doc = Jsoup.parse(pageSource);
			
			String title = doc.getElementById("thread_subject").text();
//			System.out.println("标题："+title);
			System.out.println("================================");
			Elements elementsByClass = doc.getElementsByClass("plhin");
			for (int i = 0; i < elementsByClass.size(); i++) {
				Element element = elementsByClass.get(i);
				Elements elementsByClass2 = element.getElementsByClass("t_f");
				for (int j = 0; j < elementsByClass2.size(); j++) {
					Element element2 = elementsByClass2.get(j);
//					System.out.println(element2);
					String text = element2.text();
//					System.out.println(text);
					ContentUtil.writeFileData(title);
					ContentUtil.writeFileData(text);
				}
			}
			
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
//		System.out.println(GetPage());
		GetUrl(GetPage());
	}

}
