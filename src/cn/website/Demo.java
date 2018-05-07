package cn.website;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class Demo {

	@Test
	public void test() {
		WebDriver driver = null;
		try {
			
			
			System.setProperty("phantomjs.binary.path", "F:/selenium/phantomjs.exe");
			driver = new PhantomJSDriver();
			
			driver.get("http://www.xici.net/d245590240.htm");
			
			
			String pageSource = driver.getPageSource();
			Document title = Jsoup.parse(pageSource);
			
			Element element = title.getElementById("doc_tit");
			
			String ss = element.getElementsByTag("h1").text();
			
			System.out.println(ss);
			String text = title.body().text();
			
			System.out.println(title);
			System.out.println("============================");
			System.out.println(text);
			
			   try {
					PrintWriter pw = new PrintWriter(new File("code.txt"));
					pw.print(title);
					pw.flush();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2() {
		WebDriver driver = null;
		try {
			
			
			System.setProperty("phantomjs.binary.path", "F:/selenium/phantomjs.exe");
			driver = new PhantomJSDriver();
			
			driver.get("http://www.linquan.info/category/luojituili/");
			
			
			
			String pageSource = driver.getPageSource();
			
			   try {
					PrintWriter pw = new PrintWriter(new File("code.txt"));
					pw.print(pageSource);
					pw.flush();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
