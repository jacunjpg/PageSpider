package cn.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class XiciProcessor implements PageProcessor {

//	public static final String URL_LIST = "http://blog\\.sina\\.com\\.cn/s/articlelist_1487828712_0_\\d+\\.html";
	
	public static final String URL_POST = "http://www\\.xici\\.net/d\\d+\\.htm";
    
    private Site site = Site
            .me()
            .setDomain("xici.net")
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
	
	public Site getSite() {
		return site;
	}

	public void process(Page page) {
		 //¡–±Ì“≥
//        if (!page.getUrl().regex(URL_POST).match()) {
            page.addTargetRequests(page.getHtml().xpath("//div[@class=\"feed-infinite-wrapper\"]").links().regex(URL_POST).all());
//            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
            //Œƒ’¬“≥
//        } else {
            page.putField("time", page.getHtml().xpath("//div[@class='doc_n']/div[@class='td_r']//b/text()").toString());
            page.putField("title", page.getHtml().xpath("//div[@id='doc_tit']/h1/text()"));
            page.putField("txurl", page.getHtml().xpath("//div[@class='doc_n']/div[@class='td_l']//a/img/@src").toString());
            page.putField("id", page.getHtml().xpath("//div[@class='doc_n']/div[@class='td_l']//span/text()").toString());
            page.putField("name", page.getHtml().xpath("//div[@class='doc_n']/div[@class='td_r']//a/span/text()").toString());
            page.putField("content", page.getHtml().xpath("//div[@class='doc_n']//div[@class='doc_txt']").toString());
            page.putField("url", page.getHtml().xpath("//div[@class=\"feed-infinite-wrapper\"]").links().regex(URL_POST).all());
//        }
		
            
//            try {
//				String rawText = page.getRawText();
//				PrintWriter pw = new PrintWriter(new File("code.txt"));
//				pw.print(rawText);
//				pw.flush();
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
            
            //======================================================
            
//            List<String> all = page.getHtml().xpath("//div[@class=\"feed-infinite-wrapper\"]").links().regex(URL_POST).all();
//            for (String url : all) {
//            	System.setProperty("phantomjs.binary.path", "F:/selenium/phantomjs.exe");
//            	WebDriver driver = null;
//            	try {
//            		driver = new PhantomJSDriver();
//            		driver.get(url);
//            		String pageSource = driver.getPageSource();
//            		Document title_p = Jsoup.parse(pageSource);
//        			Element element_t = title_p.getElementById("doc_tit");
//        			Element element_a = title_p.getElementById("td_r");
//        			Element element_c = title_p.getElementById("text_1_0");
//        			String title = element_t.getElementsByTag("h1").text();
//        			String author = element_a.getElementsByTag("span").text();
//        			String time = element_a.getElementsByTag("b").text();
//        			String content = element_c.getElementsByTag("div").text();
//        			System.out.println(title);
//        			System.out.println(author);
//        			System.out.println(time);
//        			System.out.println(content);
        			
        			
//            		try {
//            			PrintWriter pw = new PrintWriter(new File("code.txt"));
//            			pw.print(pageSource);
//            			pw.flush();
//            		} catch (FileNotFoundException e) {
//            			e.printStackTrace();
//            		}
            		
//            	} catch (Exception e) {
//            		e.printStackTrace();
//            	}finally{
////            		driver.close();
//            		driver.quit();
//            	}
//			}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Spider.create(new XiciProcessor()).addUrl("http://www.xici.net")
		.addPipeline(new JsonFilePipeline("D:\\data\\webmagic"))
//		.setDownloader(new cn.selenium.SeleniumDownloader())
		.thread(10)
		.run();

	}

}
