package cn.web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import cn.entity.Evaluate;
import cn.uitl.CRequest;
import cn.uitl.ContentUtil;
import cn.uitl.DBUtils;
import cn.uitl.JsonUtil;

public class Taobao {

	public static void main(String[] args) throws Exception {
//		getDetail1("https://detail.tmall.com/item.htm?id=557939518214&rn=0b36c1eb57b16e08a480dd5619a368e2&abbucket=5");
//		getSource();
		
		getDetail();
		
//		String url = "https://detail.tmall.com/item.htm?spm=a1z10.3-b-s.w4011-14572899161.86.10bd6158e4b1kE&id=560172873032&rn=80f2070c4efb99d784716f17b1890870&abbucket=13";
//		WebDriver driver = null;
//		try {
//			System.setProperty("phantomjs.binary.path", "F:/selenium/phantomjs.exe");
//			driver = new PhantomJSDriver();
//			driver.get(url);
//			Thread.sleep(5000);
//			String pageSource = driver.getPageSource();
//			Document doc = Jsoup.parse(pageSource);
//			Thread.sleep(2000);
//			getDetail(doc,url);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			driver.quit();
//		}
		
//		getPingLun("560172873032");
		
//		
//		String detailurl="https://detail.tmall.com/item.htm?spm=a1z10.3-b-s.w4011-14572899161.86.10bd6158e4b1kE&id=560172873032&rn=80f2070c4efb99d784716f17b1890870&abbucket=13";
//		String plurl="https://rate.tmall.com/list_detail_rate.htm?itemId=560172873032&spuId=889839487&sellerId=112394247&order=3&currentPage=2&append=0&content=1&tagId=&posi=&picture=&ua=098%23E1hvG9vkv3vvjQCkvvvvvjiPPLdwljEHPsdW1jD2PmPytjr8RLLU1jrbPFqWgjkCvpvLhv3Fcnr4z%2Fw9jkjwSwDbJQnb4IhCvvsNMQtG7DdNt13AMYACvpvWzM%2FsNEt4z%2FwO%2Facw9phv2nMMijAX7rM5VhyszUhCvvsNMTBGexdNt191HaACvpvWzMYbNEi4z%2FwpYuMwRphvCvvvphvjvpvjzYMwzHKwXsyCvvpvvhCv9phv2nMz6BsV7rMNtg5Kz86Cvvyv9Nf61pmveIVrvpvEvvbO0XhvvC24dphvmpvm47k8vvvVCu6Cvvyv9XkXrpvv3H85vpvhvvCCB2yCvvpvvhCv2QhvCvvvMMGCvpvVvUCvpvvvmphvLUH9WLhaz8TJ%2Bul1B57%2BhLItrVERiNoOjEAffCuYiLUpVbgYHf0QntwXVFQWiRQ1Rf8Q81mn3feAhE3tpgoXKFJ1RkDVK4vTHdJZR6ktvpvIvvCvpvvvvvvvvhNjvvmC0pvvBGwvvvUwvvCj1Qvvv99vvhNcvvvmh8yCvv9vvhh9DbPSNOyCvv4CvhE2lnAjvpvhvUCvp86Cvvyv9L9tAvvvvGervpvEvvUxHzyvvU3gdphvmpvmP2TfvvmPFu6Cvvyv9k9unpmvDtmrvpvEvUV5ixZv9CFXdphvmpvmohd3vUvX4IhCvvsNMQDG5DdNt12VlrQtvpvhvvCvp2yCvvpvvhCv9phv2nMMG5Aa7rM5VbmozUhCvvsNMYXGexdNt1kCmYQ%3D&isg=BHR0oxW9ks-lbQaNUNC3QgmPRjEm5YxF6h6FGA7ch_-SeR7DOF_exjtb_TGhmtCP&needFold=0&_ksTS=1516777229042_9485&callback=jsonp9486";
	}

	private static void getSource() {
		WebDriver driver = null;
		try {
			System.setProperty("phantomjs.binary.path", "F:/selenium/phantomjs.exe");
			driver = new PhantomJSDriver();
			
			List<String> urlList = new ArrayList<String>();
			
			for (int i = 0; i < 19; i++) {
				//太平鸟女装
				String url ="https://pb89.tmall.com/search.htm?spm=a1z10.3-b-s.w4011-14572899161.442.60db65779TVhZJ&search=y&orderType=hotsell_desc&scene=taobao_shop&pageNo="+i+"&tsearch=y#anchor";
				//Topshop 
//				String url ="https://topshop.tmall.com/category.htm?spm=a1z10.3-b-s.w4011-16219636585.534.140abb3bZMMQ73&search=y&scene=taobao_shop&pageNo="+i+"#anchor";
				//Monki
//				String url ="https://monki.tmall.com/search.htm?spm=a1z10.3-b-s.w4011-14524252003.439.1b7b2c66iRPbro&search=y&orderType=newOn_desc&scene=taobao_shop&pageNo="+i+"&tsearch=y#anchor";
				//MO&CO
//				String url ="https://moco.tmall.com/category.htm?spm=a1z10.5-b-s.w4011-14818315653.490.77339b5PvHuHN&scene=taobao_shop&pageNo="+i+"#anchor";
				//hdys
//				String url ="https://handuyishe.tmall.com/search.htm?spm=a1z10.3-b-s.w4011-14593428692.215.2a3b5755lkgIY&search=y&pageNo="+i+"&tsearch=y#anchor";
				Thread.sleep(5000);
				System.out.println(url);
				driver.get(url);
				
				Thread.sleep(10000);
				String pageSource = driver.getPageSource();
				Thread.sleep(10000);
				Document doc = Jsoup.parse(pageSource);
				Thread.sleep(10000);
				Elements photos = doc.getElementsByClass("detail");
				String pages = doc.getElementsByClass("ui-page-s-len").text();
				System.out.println(pages);
				Thread.sleep(2000);
				System.out.println("列表页数据="+photos.size());
				for (int j = 0; j < photos.size(); j++) {
					Thread.sleep(200);
					Element element2 = photos.get(j);
					String link = element2.getElementsByTag("a").attr("href");
					System.out.println(link);
					Map<String,String> map = new HashMap<String,String>();
					map.put("link", "https:"+link);
					map.put("pagenum", i+"");
					map.put("website", "tpnnz");
					DBUtils dBUtils = new DBUtils();
					dBUtils.saveProductUrl(map);
				}
				if(photos.size()<1){
					urlList.add(url);
					DBUtils dBUtils = new DBUtils();
					Map<String,String> map = new HashMap<String,String>();
					map.put("url", url);
					map.put("flag", "false");
					map.put("website", "tpnnz");
					dBUtils.saveFailProduct(map);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			driver.quit();
		}
	}
	
	public static void getDetail(){
		Map<String,String> map = new HashMap<String,String>();
		WebDriver driver = null;
		
		try {
			System.setProperty("phantomjs.binary.path", "F:/selenium/phantomjs.exe");
			driver = new PhantomJSDriver();
			DBUtils dBUtils = new DBUtils();
			for (int i = 1; i < 19; i++) {
				
				List<String> list = dBUtils.findByNumber(i , "tpnnz");
				System.out.println("size="+list.size());
				for (String url : list) {
					System.out.println(url);
					if(!(url.indexOf("abbucket") != -1)){
						System.out.println("No");
						continue;
					}
//					System.out.println("Yes");
//					driver.get(url);
//					Thread.sleep(10000);
//					String result = driver.getPageSource();
//					Thread.sleep(2000);
					
					//设置cookie的方式=======================================
//					URL upT = new URL(url);
//					HttpURLConnection cpT = (HttpURLConnection) upT.openConnection();
//					cpT.addRequestProperty("Host", "suggest.taobao.com");
//					cpT.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");
//					cpT.addRequestProperty("Accept", "Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
//					cpT.addRequestProperty("Referer", "https://detail.tmall.com/");
//					cpT.addRequestProperty("Cookie", "enc=7ab8CIb3PuEgbkYZ86WLUBm2aldA80%2BcB%2Fc4el5lvKfyfBa24VnDizybL9pIcMwajWBsvbdz9zwPPxvgYfahIQ%3D%3D; cna=8Sn0En+Iyn8CAXVk6XhV7EMV; isg=BHJyqfnvfODmPkDcV0kU1eUWwLuUq2L3mGjDojxLmSUQzxLJJJPGrXgvu-vzpO41; um=2BA477700510A7DF5C558FD72F749D767A5DE821F727B25449950A3FE01EE172D33D196FC4AB7AFBCD43AD3E795C914C59C3DF733CCAA709E7C14A3C8D2024C9; cookie2=1342f5f23bb0bcfe644d7e01c42e3210; _tb_token_=5106688310eed; t=f2a00e9213567821f075aa0ecf60cf65; v=0; uc1=cookie14=UoTdfDcJD4zzfQ%3D%3D&lng=zh_CN&cookie16=W5iHLLyFPlMGbLDwA%2BdvAGZqLg%3D%3D&existShop=false&cookie21=V32FPkk%2FgihF%2FS5nr3O5&tag=8&cookie15=UtASsssmOIJ0bQ%3D%3D&pas=0; uc3=nk2=rU4CI3%2FOzZ8%3D&id2=Uone%2BXRDsJEgoQ%3D%3D&vt3=F8dBzLljDt8dzZkcBXY%3D&lg2=WqG3DMC9VAQiUQ%3D%3D; existShop=MTUxNzEwODgwOA%3D%3D; lgc=%5Cu5DEB%5Cu5E08%5Cu8715%5Cu53D8; tracknick=%5Cu5DEB%5Cu5E08%5Cu8715%5Cu53D8; sg=%E5%8F%9807; mt=np=; cookie1=WvTNHJYL%2FK5kM6enWbIwgjXi4QdC%2FJzmqBrTIreR6nc%3D; unb=1859127600; skt=4887c1f0dd67bbde; _cc_=VFC%2FuZ9ajQ%3D%3D; tg=0; _l_g_=Ug%3D%3D; _nk_=%5Cu5DEB%5Cu5E08%5Cu8715%5Cu53D8; cookie17=Uone%2BXRDsJEgoQ%3D%3D");
//					cpT.addRequestProperty("Connection", "keep-alive");
//					cpT.connect();
//					Scanner scanner = new Scanner(cpT.getInputStream(),"GBK");
//					
//					String result = "";
//					while(scanner.hasNext()){
//						result += scanner.nextLine() +"\n";
//					}
					//=========================================================
//					Document pageDetail = Jsoup.parse(result);
//					Thread.sleep(5000);
//					
//					Elements title = pageDetail.getElementsByClass("tb-detail-hd");
//					if(title.size()<1){
//						Map<String,String> map1 = new HashMap<String,String>();
//						map1.put("url", url);
//						map1.put("flag", "详情采集失败");
//						map1.put("website", "tpn");
//						dBUtils.saveFailProduct(map1);
//						System.out.println("详情采集失败");
//						continue;
//					}
//					Thread.sleep(2000);
//					Elements prices = pageDetail.getElementsByClass("tm-price");
//					Thread.sleep(2000);
//					Elements count = pageDetail.getElementsByClass("tm-count");
//					Thread.sleep(2000);
//					String kucl = pageDetail.getElementById("J_EmStock").text();
//					map.put("productStock", kucl);
//					Thread.sleep(2000);
//					String productDetail = pageDetail.getElementById("J_AttrUL").text();
//					System.out.println(productDetail);
//					map.put("productDetail", productDetail);
//					Thread.sleep(2000);
//					
//					if(title.size()>0){
//						String biaot = title.get(0).getElementsByTag("h1").text();
//						map.put("productName", biaot);
//					}
//					if(prices.size()>1){
//						String zhuangj = prices.get(0).text();
//						String nianhj = prices.get(1).text();
//						map.put("productShppePrice", zhuangj);
//						map.put("productPromotionPrice", nianhj);
//					}
//					if(count.size()>2){
//						String yuexl = count.get(0).text();
//						String leijipj = count.get(1).text();
//						String tianmaojf = count.get(2).text();
//						map.put("productMonthlySales", yuexl);
//						map.put("productAppraise", leijipj);
//						map.put("productIntegral", tianmaojf);
//					}
					
//					scanner.close();
//					cpT.disconnect();
					
//					System.out.println(map.toString());
					
					Map<String, String> urlRequest = CRequest.URLRequest(url);
					String id = urlRequest.get("id");
					map.put("itemid", id);
					
//					dBUtils.saveProductDetail(map);
					
					System.out.println(id);
					getPingLun(id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
//			driver.quit();
//			cpT.disconnect();
		}
		
	}
	
	public static void getPingLun(String id) throws Exception{
		int i=0;
		while(true) {
			i++;
			String result = "";
			String link = "https://rate.tmall.com/list_detail_rate.htm?itemId="+id+"&sellerId=112394247&order=3&currentPage="+i+"&callback=jsonp2346";
			System.out.println(link);
			URL ul = new URL(link);
			HttpURLConnection conn = (HttpURLConnection) ul.openConnection();
			conn.connect();
			InputStream in = conn.getInputStream();
			
			StringBuffer sb = new StringBuffer();
			String readLine = new String();
			InputStreamReader res = new InputStreamReader(in,"GBK");
			BufferedReader responseReader = new BufferedReader(res);
			while ((readLine = responseReader.readLine()) != null) {
				sb.append(readLine);
			}
			result = sb.toString();
			System.out.println("result"+result);
			if(result==""){
				System.out.println("跳出循环");
				break;
			}
			result = result.substring(10, result.length()-1);
			System.out.println("new_result="+result);
			
			try {
				boolean page = JsonUtil.getPage(result);
				if(page){
					break;
				}
				
				List<Evaluate> data = JsonUtil.getData(result);
				//save
				DBUtils dBUtils = new DBUtils();
				for (Evaluate evaluate : data) {
					System.out.println(evaluate.getRateContent());
//					ContentUtil.writeFileData("用户名="+evaluate.getName()+"||评论="+evaluate.getRateContent());
					dBUtils.saveInfo(evaluate,id);
				}
			} catch (Exception e) {
				Thread.sleep(30*1000);
			}
			
//			if(i>=3){
//				break;
//			}
			
			responseReader.close();
			res.close();
			in.close();
			conn.disconnect();
			int random = JsonUtil.getRandom();
			Thread.sleep(random*1000);
		}
		System.out.println("end");
	}
	
	
	public static void getDetail1(String url){
		Map<String,String> map = new HashMap<String,String>();
		WebDriver driver = null;
		
		try {
//			System.setProperty("phantomjs.binary.path", "F:/selenium/phantomjs.exe");
//			driver = new PhantomJSDriver();
			DBUtils dBUtils = new DBUtils();
			System.out.println(url);
			System.out.println("Yes");
//			driver.get(url);
			Thread.sleep(10000);
			URL upT = new URL(url);
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
//			String pageDetail = driver.getPageSource();
			Thread.sleep(10000);
			Document PageDetail = Jsoup.parse(result);
			Thread.sleep(5000);
			
			Elements title = PageDetail.getElementsByClass("tb-detail-hd");
			if(title.size()<1){
				Map<String,String> map1 = new HashMap<String,String>();
				map1.put("url", url);
				map1.put("flag", "详情采集失败");
				map1.put("website", "tpn");
				dBUtils.saveFailProduct(map1);
				System.out.println("详情采集失败");
			}
			
			Thread.sleep(2000);
			Elements prices = PageDetail.getElementsByClass("tm-price");
			Thread.sleep(2000);
			Elements count = PageDetail.getElementsByClass("tm-count");
			Thread.sleep(2000);
			String kucl = PageDetail.getElementById("J_EmStock").text();
			map.put("productStock", kucl);
			Thread.sleep(2000);
			String productDetail = PageDetail.getElementById("J_AttrUL").text();
			System.out.println(productDetail);
			map.put("productDetail", productDetail);
			Thread.sleep(2000);
			
			if(title.size()>0){
				String biaot = title.get(0).getElementsByTag("h1").text();
				map.put("productName", biaot);
			}
			if(prices.size()>1){
				String zhuangj = prices.get(0).text();
				String nianhj = prices.get(1).text();
				map.put("productShppePrice", zhuangj);
				map.put("productPromotionPrice", nianhj);
			}
			if(count.size()>2){
				String yuexl = count.get(0).text();
				String leijipj = count.get(1).text();
				String tianmaojf = count.get(2).text();
				map.put("productMonthlySales", yuexl);
				map.put("productAppraise", leijipj);
				map.put("productIntegral", tianmaojf);
			}
			
			System.out.println(map.toString());
			
			Map<String, String> urlRequest = CRequest.URLRequest(url);
			String id = urlRequest.get("id");
			map.put("itemid", id);
			
			dBUtils.saveProductDetail(map);
			
			System.out.println(id);
			getPingLun(id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			driver.quit();
		}
		
	}
}
