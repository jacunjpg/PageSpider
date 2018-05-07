package cn.website;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import cn.uitl.ContentUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class SinaProcess implements PageProcessor {


	private Site site = Site
			.me()
			.setDomain("")
//			.setDomain("https://weibo.com/1095240537/GdGXgwn7p?refer_flag=1001030106_&type=comment#_loginLayer_1525678497046")
			.setSleepTime(3000)
			.setUserAgent(
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

	public Site getSite() {
		return site;
	}

	public void process(Page page) {
		System.out.println(page.getUrl());
		String url = page.getUrl().toString();
		System.setProperty("phantomjs.binary.path", "F:/selenium/phantomjs.exe");
//		System.setProperty("webdriver.firefox.bin", "F:/selenium/ff/firefox.exe");
		WebDriver driver = null;
		try {
			driver = new PhantomJSDriver();
//			driver = new FirefoxDriver();
			driver.get(url);
			Thread.sleep(1000*20);
			((JavascriptExecutor)driver).executeScript("document.documentElement.scrollTop=2000"); 
			Thread.sleep(1000*10);
			((JavascriptExecutor)driver).executeScript("document.documentElement.scrollTop=5000"); 
			Thread.sleep(1000*10);
			((JavascriptExecutor)driver).executeScript("document.documentElement.scrollTop=8000"); 
			Thread.sleep(1000*10);
			String pageSource = driver.getPageSource();
			Document title = Jsoup.parse(pageSource);
			Elements elementsByClass = title.getElementsByClass("WB_text");
			for (Element element : elementsByClass) {
				String text = element.text();
				System.out.println(text);
				if(text.indexOf("£º")!=-1){
					text = text.substring(text.indexOf("£º")+1);
					ContentUtil.writeFileData(text);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

	public static void main(String[] args) {
		Spider.create(new SinaProcess())
				.addUrl("https://weibo.com/1095240537/GdGXgwn7p?refer_flag=1001030106_&type=comment")
				.addPipeline(new JsonFilePipeline("D:\\data\\sina")).thread(1)
				.run();
	}

}
