package cn.demo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class GithubRepoPageProcessor implements PageProcessor {

	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(1).setSleepTime(1000);

    
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
//        page.putField("author_bak", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//        page.putField("name", page.getHtml().xpath("//h1[@class='vcard-names']/span/text()").toString());
//        if (page.getResultItems().get("name") == null) {
//            //skip this page
//            page.setSkip(true);
//        }
//        page.putField("picurl", page.getHtml().xpath("//a[@class='u-photo d-block position-relative']/img/@src").toString());
//        page.putField("addr", page.getHtml().xpath("//span[@class='p-label']/text()").toString());
//        page.putField("link", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());

        // 部分三：从页面发现后续的url地址来抓取
//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
    	
        page.putField("author_bak", page.getUrl().regex("http://www\\.xici.net").toString());
        page.putField("title", page.getHtml().xpath("//div[@class='title']/a/text()").toString());
        if (page.getResultItems().get("title") == null) {
            //skip this page
            page.setSkip(true);
        }
        page.putField("picurl", page.getHtml().xpath("//div[@class='thumb-1']/a/img/@src").toString());
        page.putField("addr", page.getHtml().xpath("//div[@class='meta-info']/div/a/text()").toString());
    	
    	// 部分三：从页面发现后续的url地址来抓取
        page.addTargetRequests(page.getHtml().links().regex("(http://www\\.xici.net)").all());
    	
    }
    
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new GithubRepoPageProcessor())
                //从"https://github.com/code4craft"开始抓
//                .addUrl("https://github.com/code4craft")
                .addUrl("http://www.xici.net")
                .addPipeline(new JsonFilePipeline("D:\\data\\webmagic"))
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }

}
