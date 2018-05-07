package cn.website;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.demo.XiciProcessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class XiCi implements PageProcessor {

	@Test
	public void test() {
		Spider.create(new XiciProcessor()).addUrl("http://www.xici.net")
		.addPipeline(new JsonFilePipeline("D:\\data\\webmagic"))
		.thread(10)
		.run();
	}

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
        page.addTargetRequests(page.getHtml().xpath("//div[@class=\"feed-infinite-wrapper\"]").links().regex(URL_POST).all());
        page.putField("time", page.getHtml().xpath("//div[@class='doc_n']/div[@class='td_r']//b/text()").toString());
        page.putField("title", page.getHtml().xpath("//div[@id='doc_tit']/h1/text()"));
        page.putField("txurl", page.getHtml().xpath("//div[@class='doc_n']/div[@class='td_l']//a/img/@src").toString());
        page.putField("id", page.getHtml().xpath("//div[@class='doc_n']/div[@class='td_l']//span/text()").toString());
        page.putField("name", page.getHtml().xpath("//div[@class='doc_n']/div[@class='td_r']//a/span/text()").toString());
        page.putField("content", page.getHtml().xpath("//div[@class='doc_n']//div[@class='doc_txt']").toString());
        page.putField("url", page.getHtml().xpath("//div[@class=\"feed-infinite-wrapper\"]").links().regex(URL_POST).all());
    }
}
