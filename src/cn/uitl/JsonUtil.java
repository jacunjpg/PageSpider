package cn.uitl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.entity.Evaluate;

public class JsonUtil {
	
	public static int getRandom(){
		int[] nums = { 10, 15, 16, 18, 20 };
		int i = (int) (Math.random() * nums.length);
		System.out.println(nums[i]);
		return nums[i];
	}
	
	public static boolean getPage(String json) throws Exception{
		boolean flag = false;
		JSONObject jo = JSON.parseObject(json);
		JSONObject obj = (JSONObject) jo.get("rateDetail");
		JSONObject pages = (JSONObject) obj.get("paginator");
		String lastPage = pages.get("lastPage").toString();
		String page = pages.get("page").toString();
		System.out.println("���ҳ��"+lastPage);
		System.out.println("��ǰҳ��"+page);
		int mp = Integer.parseInt(lastPage);
		int p = Integer.parseInt(page);
		if(mp<=p){
			flag = true;
		}
		return flag;
	}

	public static List<Evaluate> getData(String json) throws Exception{
		List<Evaluate> evaluateList = new ArrayList<Evaluate>();
		JSONObject jo = JSON.parseObject(json);
//		JSONArray ja = JSONArray.parseArray(jo.getString("rateDetail").toString());
//		System.out.println(ja.toString());
		JSONObject obj = (JSONObject) jo.get("rateDetail");
		JSONObject pages = (JSONObject) obj.get("paginator");
		JSONArray ja = JSONArray.parseArray(obj.getString("rateList").toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (Iterator iterator = ja.iterator(); iterator.hasNext();) {
			JSONObject rate = (JSONObject) iterator.next();
			Evaluate evaluate = new Evaluate();
			evaluate.setId(rate.get("id").toString());
			evaluate.setName(rate.get("displayUserNick").toString());
			evaluate.setRatedate(sdf.parse(rate.get("rateDate").toString()));
			evaluate.setRateContent(rate.get("rateContent").toString());
			evaluate.setAuctionSku(rate.get("auctionSku").toString());
			evaluate.setCmsSource(rate.get("cmsSource").toString());
			evaluate.setTradeEndTime(rate.get("tradeEndTime").toString());
			evaluate.setCreatetime(new Date());
			evaluateList.add(evaluate);
		}
		return evaluateList;
	}
	
	public static void main(String[] args) {
		String json = "{\"rateDetail\": {" +
				"\"paginator\": {" +
				"\"items\": 3219,\"lastPage\": 99,\"page\": 1},\"rateCount\": {" +
				"\"picNum\": 981,\"shop\": 0,\"total\": 3219,\"used\": 239},\"rateDanceInfo\": {" +
				"\"currentMilles\": 1516780423453,\"intervalMilles\": 13540988060,\"showChooseTopic\": false,\"storeType\": 4},\"rateList\": [" +
				"{\"auctionSku\": \"��ɫ����:��ɫ������;����:S\"," +
				"\"cmsSource\": \"��è\"," +
				"\"displayUserNick\": \"С***��\"," +
				"\"id\": 338286782364," +
				"\"rateContent\": \"������ÿ���ů��\"," +
				"\"rateDate\": \"2018-01-18 23:27:05\"," +
				"\"tradeEndTime\": 1516289160000" +
				"}," +
				"{\"auctionSku\": \"��ɫ����:��ɫ��Ԥ�� Ԥ��12.22����;����:XS\"," +
				"\"cmsSource\": \"��è\"," +
				"\"displayUserNick\": \"С***��\"," +
				"\"id\": 336423973901," +
				"\"rateContent\": \"����������ײ���ģ���Ҷ�ϲ��������Ү��ů�ͣ�ƫ��һ����С�ž͹���\"," +
				"\"rateDate\": \"2018-01-02 16:42:56\"," +
				"\"tradeEndTime\": 1514880528000}]," +
				"\"searchinfo\": \"\",\"tags\": \"\"}}";
		try {
			List<Evaluate> data = getData(json);
			for (Evaluate evaluate : data) {
				
				System.out.println(evaluate.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
