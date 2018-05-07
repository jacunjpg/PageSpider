package cn.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class GTaoBao {
	public static void login(){
		try {
//			URL upT = new URL("https://detail.tmall.com/item.htm?spm=a1z10.3-b-s.w4011-14572899161.86.10bd6158e4b1kE&id=560172873032&rn=80f2070c4efb99d784716f17b1890870&abbucket=13");
			URL upT = new URL("https://pb89.tmall.com/search.htm?spm=a1z10.1-b-s.w5001-16886321023.6.4fc672e76xac4T&search=y&orderType=hotsell_desc&tsearch=y&scene=taobao_shop");
			HttpURLConnection cpT = (HttpURLConnection) upT.openConnection();
			cpT.addRequestProperty("Host", "suggest.taobao.com");
			cpT.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");
			cpT.addRequestProperty("Accept", "Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
//			cpT.addRequestProperty("Accept-Encoding", "gzip, deflate, br");
			cpT.addRequestProperty("Referer", "https://detail.tmall.com/");
			cpT.addRequestProperty("Cookie", "enc=ehHq3L2oNPTK9tWq9wOqwr24b0OZJ%2B%2B5GyXT%2F4m15Ai6KfBhMeVLb3BgjpWAj8%2Ff6cPcRfAeZeOoy6ovAa%2BE0g%3D%3D; cna=x9LlElbamXECAXVk6z5BcLbH; isg=BKamDdVLwFYNkpTzNC2fr-5F9B_oL_4zjKS3xpBPj0mkE0Yt-Bc6UYzjb4-foOJZ; um=0823A424438F76AB8D16A720B78753DEB214B700F2107C1D7F333630B5F8FB6B773F21165CD3A6A9CD43AD3E795C914C556FD0227F3BA351D2E36D381C67D317; t=57515e03383cbb8b7d8094a9a299ced4; mt=np=&ci=54_1; _cc_=VT5L2FSpdA%3D%3D; tg=0; thw=cn; _m_user_unitinfo_=unit|unsz; _m_unitapi_v_=1508566261407; _m_h5_tk=9338e52b6067768b4e41a351170aca0b_1516702097462; _m_h5_tk_enc=9e9c768e4880e3df5ce7510cdcfe9c77; cookie2=19efc6fd53167cebef617d3df8517c9a; v=0; _tb_token_=bb7b5d88ebde; uc1=cookie14=UoTdfYK5w7jinQ%3D%3D&lng=zh_CN&cookie16=Vq8l%2BKCLySLZMFWHxqs8fwqnEw%3D%3D&existShop=false&cookie21=UIHiLt3xThH8t7YQoFNq&tag=8&cookie15=WqG3DMC9VAQiUQ%3D%3D&pas=0; uc3=sg2=BYS%2B8bkggmCgNY8rvgvhEd5SzyzFDmsVmQDD3jf%2F3sc%3D&nk2=rU4CI3%2FOzZ8%3D&id2=Uone%2BXRDsJEgoQ%3D%3D&vt3=F8dBzLlnC94pC9Dz6T0%3D&lg2=V32FPkk%2Fw0dUvg%3D%3D; existShop=MTUxNjc1NzQxNg%3D%3D; lgc=%5Cu5DEB%5Cu5E08%5Cu8715%5Cu53D8; tracknick=%5Cu5DEB%5Cu5E08%5Cu8715%5Cu53D8; skt=bf97aac835f0f40e; uss=UoH7K1l3nevLB2bV3%2F%2Bek3Qr7WwdpalVVSjfgICYmy7%2BDxDqg5OA3CamRQ%3D%3D; sg=%E5%8F%9807; cookie1=WvTNHJYL%2FK5kM6enWbIwgjXi4QdC%2FJzmqBrTIreR6nc%3D; unb=1859127600; _l_g_=Ug%3D%3D; _nk_=%5Cu5DEB%5Cu5E08%5Cu8715%5Cu53D8; cookie17=Uone%2BXRDsJEgoQ%3D%3D");
			cpT.addRequestProperty("Connection", "keep-alive");
			
			cpT.connect();
			
			Scanner scanner = new Scanner(cpT.getInputStream(),"GBK");
			
			String result = "";
			while(scanner.hasNext()){
				
				result += scanner.nextLine() +"\n";
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
