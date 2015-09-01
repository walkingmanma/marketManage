package bean;

import java.io.Serializable;

public class OrderListBean implements Serializable 
{
	private int orderlistid;
	private String commodityname;
	private int commodityid;
	private int orderformid;
	private int amount;
	
	public OrderListBean(){}
	public OrderListBean(int orderlistid, String commodityname,
			int commodityid, int orderformid, int amount) 
	{
		this.orderlistid = orderlistid;
		this.commodityname = commodityname;
		this.commodityid = commodityid;
		this.orderformid = orderformid;
		this.amount = amount;
	}
	public int getOrderlistid() 
	{
		return orderlistid;
	}
	public void setOrderlistid(int orderlistid) 
	{
		this.orderlistid = orderlistid;
	}
	public String getCommodityname() 
	{
		return commodityname;
	}
	public void setCommodityname(String commodityname) 
	{
		this.commodityname = commodityname;
	}
	public int getCommodityid()
	{
		return commodityid;
	}
	public void setCommodityid(int commodityid) 
	{
		this.commodityid = commodityid;
	}
	public int getOrderformid() 
	{
		return orderformid;
	}
	public void setOrderformid(int orderformid) 
	{
		this.orderformid = orderformid;
	}
	public int getAmount() 
	{
		return amount;
	}
	public void setAmount(int amount) 
	{
		this.amount = amount;
	}
	
	
}
