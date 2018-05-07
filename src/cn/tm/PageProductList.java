package cn.tm;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PageProductList {

	public static void login(){
		try {
			URL upT = new URL("https://detail.tmall.com/item.htm?id=557939518214&rn=0b36c1eb57b16e08a480dd5619a368e2&abbucket=5");
			HttpURLConnection cpT = (HttpURLConnection) upT.openConnection();
			cpT.addRequestProperty("Host", "suggest.taobao.com");
			cpT.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");
			cpT.addRequestProperty("Accept", "Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
			cpT.addRequestProperty("Referer", "https://detail.tmall.com/");
			cpT.addRequestProperty("Cookie", "7ab8CIb3PuEgbkYZ86WLUBm2aldA80%2BcB%2Fc4el5lvKfyfBa24VnDizybL9pIcMwajWBsvbdz9zwPPxvgYfahIQ%3D%3D; cna=8Sn0En+Iyn8CAXVk6XhV7EMV; isg=BHJyqfnvfODmPkDcV0kU1eUWwLuUq2L3mGjDojxLmSUQzxLJJJPGrXgvu-vzpO41; um=2BA477700510A7DF5C558FD72F749D767A5DE821F727B25449950A3FE01EE172D33D196FC4AB7AFBCD43AD3E795C914C59C3DF733CCAA709E7C14A3C8D2024C9; cookie2=1342f5f23bb0bcfe644d7e01c42e3210; _tb_token_=5106688310eed; t=f2a00e9213567821f075aa0ecf60cf65; v=0; uc1=cookie14=UoTdfDcJD4zzfQ%3D%3D&lng=zh_CN&cookie16=W5iHLLyFPlMGbLDwA%2BdvAGZqLg%3D%3D&existShop=false&cookie21=V32FPkk%2FgihF%2FS5nr3O5&tag=8&cookie15=UtASsssmOIJ0bQ%3D%3D&pas=0; uc3=nk2=rU4CI3%2FOzZ8%3D&id2=Uone%2BXRDsJEgoQ%3D%3D&vt3=F8dBzLljDt8dzZkcBXY%3D&lg2=WqG3DMC9VAQiUQ%3D%3D; existShop=MTUxNzEwODgwOA%3D%3D; lgc=%5Cu5DEB%5Cu5E08%5Cu8715%5Cu53D8; tracknick=%5Cu5DEB%5Cu5E08%5Cu8715%5Cu53D8; sg=%E5%8F%9807; mt=np=; cookie1=WvTNHJYL%2FK5kM6enWbIwgjXi4QdC%2FJzmqBrTIreR6nc%3D; unb=1859127600; skt=4887c1f0dd67bbde; _cc_=VFC%2FuZ9ajQ%3D%3D; tg=0; _l_g_=Ug%3D%3D; _nk_=%5Cu5DEB%5Cu5E08%5Cu8715%5Cu53D8; cookie17=Uone%2BXRDsJEgoQ%3D%3D");
			cpT.addRequestProperty("Connection", "keep-alive");
			cpT.connect();
			Scanner scanner = new Scanner(cpT.getInputStream(),"GBK");
			
			String result = "";
			while(scanner.hasNext()){
				result += scanner.nextLine() +"\n";
			}
			Document doc = Jsoup.parse(result);
			Elements title = doc.getElementsByClass("tb-detail-hd");
			if(title.size()<1){
				Map<String,String> map1 = new HashMap<String,String>();
				map1.put("url", "123");
				map1.put("flag", "详情采集失败");
				map1.put("website", "tpn");
				System.out.println("详情采集失败");
			}
			Elements prices = doc.getElementsByClass("tm-price");
			System.out.println(prices);
			Elements count = doc.getElementsByClass("tm-count");
			System.out.println(count);
			Thread.sleep(2000);
			String kucl = doc.getElementById("J_EmStock").text();
			System.out.println(kucl);
			Thread.sleep(2000);
			String productDetail = doc.getElementById("J_AttrUL").text();
			System.out.println(productDetail);
			System.out.println(title);
			PrintWriter pw1 = new PrintWriter("detail.txt");
			pw1.print(result);
			pw1.flush();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		login();
	}

}
