package cn.website;

import cn.demo.XiciProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class LinQuanProcessor implements PageProcessor {
	
//    public static final String URL_LIST = "http://blog\\.sina\\.com\\.cn/s/articlelist_1487828712_0_\\d+\\.html";
    public static final String URL_LIST = "http://www\\.linquan\\.info/tag/%E5%A5%A5%E6%95%B0%E9%A2%98/page/d+/";

	public static final String URL_POST = "http://www\\.linquan\\.info/archives/\\d+.html";
    
    private Site site = Site
            .me()
            .setDomain("linquan.info")
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
	
	
	public Site getSite() {
		return site;
	}

	public void process(Page page) {
		//¡–±Ì“≥
      if (!page.getUrl().regex(URL_POST).match()) {
          page.addTargetRequests(page.getHtml().xpath("//article/header[@class=\"entry-header\"]").links().regex(URL_POST).all());
          page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
          //Œƒ’¬“≥
      } else {
//          page.putField("time", page.getHtml().xpath("//div[@class='doc_n']/div[@class='td_r']//b/text()").toString());
//          page.putField("title", page.getHtml().xpath("//div[@id='doc_tit']/h1/text()"));
//          page.putField("txurl", page.getHtml().xpath("//div[@class='doc_n']/div[@class='td_l']//a/img/@src").toString());
//          page.putField("id", page.getHtml().xpath("//div[@class='doc_n']/div[@class='td_l']//span/text()").toString());
//          page.putField("name", page.getHtml().xpath("//div[@class='doc_n']/div[@class='td_r']//a/span/text()").toString());
//          page.putField("content", page.getHtml().xpath("//div[@class='doc_n']//div[@class='doc_txt']").toString());
//          page.putField("url", page.getHtml().xpath("//div[@class=\"feed-infinite-wrapper\"]").links().regex(URL_POST).all());
          page.putField("title", page.getHtml().xpath("//article/header[@class=\"entry-header\"]/h1/text()").toString());
          page.putField("content", page.getHtml().xpath("//article/div[@class=\"entry-content\"]/div[@class=\"single-content\"]").toString());
//          page.putField("url", page.getHtml().xpath("//div[@class=\"feed-infinite-wrapper\"]").links().regex(URL_POST).all());
      }
		
		
	}
	
	public static void main(String[] args) {
		//http://www.linquan.info/tag/%E5%A5%A5%E6%95%B0%E9%A2%98/page/3/
		Spider.create(new LinQuanProcessor()).addUrl("http://www.linquan.info/tag/%E5%A5%A5%E6%95%B0%E9%A2%98/")
		.addPipeline(new JsonFilePipeline("D:\\data\\webmagic"))
		.thread(10)
		.run();
	}

}
