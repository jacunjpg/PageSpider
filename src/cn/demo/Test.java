package cn.demo;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;

public class Test {

	public static void main(String[] args) {
//		Spider spider = 
		Spider.create(new GithubRepoPageProcessor())
        //��https://github.com/code4craft��ʼץ    
//        .addUrl("https://github.com/code4craft")
//        .addUrl("https://www.baidu.com")
//        .test("https://www.baidu.com")
        //����Scheduler��ʹ��Redis������URL����
//        .setScheduler(new RedisScheduler("localhost"))
        //����Pipeline���������json��ʽ���浽�ļ�
        .addPipeline(new JsonFilePipeline("D:\\data\\webmagic"))
        //����5���߳�ͬʱִ��
        .thread(5);
        //��������
//		spider.run();
	}
}
