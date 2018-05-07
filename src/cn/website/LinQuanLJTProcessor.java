package cn.website;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.demo.XiciProcessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class LinQuanLJTProcessor implements PageProcessor {
	
	//œÍ«È“≥
	public static final String URL_LIST = "http://www\\.linquan\\.info/archives/\\d+.html";
	//¡–±Ì“≥
	public static final String URL_POST = "http://www.linquan.info/category/zhiliyouxi/page/\\d+";
	
	 private Site site = Site
	            .me()
	            .setDomain("http://www.linquan.info/category/zhiliyouxi/")
	            .setSleepTime(3000)
	            .setUserAgent(
	                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
		

//	@Test
//	public void test() {
////		Spider.create(new LinQuanLJTProcessor()).addUrl("http://www.linquan.info/category/luojituili/page/1/")
//		Spider.create(new LinQuanLJTProcessor()).addUrl("http://www.linquan.info/category/zhiliyouxi/page/1/")
//		.addPipeline(new JsonFilePipeline("D:\\data\\webmagic"))
//		.thread(10)
//		.run();
//
//	}

	public Site getSite() {
		return site;
	}

	public void process(Page page) {
		System.out.println(page.getUrl());
		if (page.getUrl().regex(URL_POST).match()) {
			page.addTargetRequests(page.getHtml().xpath("//div[@class=\"pagenav-clear\"]").links().regex(URL_POST).all());
			page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
		} else {
//          page.putField("title", page.getHtml().xpath("//div[@class='entry-header']/h1/text()").toString());
//          page.putField("content", page.getHtml().xpath("//div[@class='single-content']/p/text()").toString());
			
			
			
          System.out.println(page.getHtml().xpath("//div[@class='single-content']/p/text()").toString());
      }
		
		
	}
	
	public static void main(String[] args) {
		Spider.create(new LinQuanLJTProcessor()).addUrl("http://www.linquan.info/category/zhiliyouxi/page/1")
		.addPipeline(new JsonFilePipeline("D:\\data\\webmagic"))
		.thread(10)
		.run();
	}

}
