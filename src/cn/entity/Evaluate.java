package cn.entity;

import java.util.Date;

public class Evaluate {

	private String id;
	private String name;
	private Date ratedate;
	private String rateContent;
	private String auctionSku;
	private String cmsSource;
	private String tradeEndTime;
	private Date createtime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRatedate() {
		return ratedate;
	}
	public void setRatedate(Date ratedate) {
		this.ratedate = ratedate;
	}
	public String getRateContent() {
		return rateContent;
	}
	public void setRateContent(String rateContent) {
		this.rateContent = rateContent;
	}
	public String getAuctionSku() {
		return auctionSku;
	}
	public void setAuctionSku(String auctionSku) {
		this.auctionSku = auctionSku;
	}
	public String getCmsSource() {
		return cmsSource;
	}
	public void setCmsSource(String cmsSource) {
		this.cmsSource = cmsSource;
	}
	public String getTradeEndTime() {
		return tradeEndTime;
	}
	public void setTradeEndTime(String tradeEndTime) {
		this.tradeEndTime = tradeEndTime;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}
