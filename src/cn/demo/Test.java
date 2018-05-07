package cn.demo;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;

public class Test {

	public static void main(String[] args) {
//		Spider spider = 
		Spider.create(new GithubRepoPageProcessor())
        //从https://github.com/code4craft开始抓    
//        .addUrl("https://github.com/code4craft")
//        .addUrl("https://www.baidu.com")
//        .test("https://www.baidu.com")
        //设置Scheduler，使用Redis来管理URL队列
//        .setScheduler(new RedisScheduler("localhost"))
        //设置Pipeline，将结果以json方式保存到文件
        .addPipeline(new JsonFilePipeline("D:\\data\\webmagic"))
        //开启5个线程同时执行
        .thread(5);
        //启动爬虫
//		spider.run();
	}
}
