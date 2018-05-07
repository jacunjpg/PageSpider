package cn.demo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class GithubRepoPageProcessor implements PageProcessor {

	// ����һ��ץȡ��վ��������ã��������롢ץȡ��������Դ�����
    private Site site = Site.me().setRetryTimes(1).setSleepTime(1000);

    
    // process�Ƕ��������߼��ĺ��Ľӿڣ��������д��ȡ�߼�
    public void process(Page page) {
        // ���ֶ���������γ�ȡҳ����Ϣ������������
//        page.putField("author_bak", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//        page.putField("name", page.getHtml().xpath("//h1[@class='vcard-names']/span/text()").toString());
//        if (page.getResultItems().get("name") == null) {
//            //skip this page
//            page.setSkip(true);
//        }
//        page.putField("picurl", page.getHtml().xpath("//a[@class='u-photo d-block position-relative']/img/@src").toString());
//        page.putField("addr", page.getHtml().xpath("//span[@class='p-label']/text()").toString());
//        page.putField("link", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());

        // ����������ҳ�淢�ֺ�����url��ַ��ץȡ
//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
    	
        page.putField("author_bak", page.getUrl().regex("http://www\\.xici.net").toString());
        page.putField("title", page.getHtml().xpath("//div[@class='title']/a/text()").toString());
        if (page.getResultItems().get("title") == null) {
            //skip this page
            page.setSkip(true);
        }
        page.putField("picurl", page.getHtml().xpath("//div[@class='thumb-1']/a/img/@src").toString());
        page.putField("addr", page.getHtml().xpath("//div[@class='meta-info']/div/a/text()").toString());
    	
    	// ����������ҳ�淢�ֺ�����url��ַ��ץȡ
        page.addTargetRequests(page.getHtml().links().regex("(http://www\\.xici.net)").all());
    	
    }
    
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new GithubRepoPageProcessor())
                //��"https://github.com/code4craft"��ʼץ
//                .addUrl("https://github.com/code4craft")
                .addUrl("http://www.xici.net")
                .addPipeline(new JsonFilePipeline("D:\\data\\webmagic"))
                //����5���߳�ץȡ
                .thread(5)
                //��������
                .run();
    }

}
