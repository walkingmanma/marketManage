package bean;

import java.io.Serializable;
import java.sql.Date;

public class OrderFormBean implements Serializable 
{
	private int orderformid;
	private String name;
	private Date submittime;
	private double totalprice;
	private double ispayoff;
	
	public OrderFormBean(){}
	public OrderFormBean(int orderformid, String name, Date submittime,
			double totalprice, double ispayoff) 
	{
		this.orderformid = orderformid;
		this.name = name;
		this.submittime = submittime;
		this.totalprice = totalprice;
		this.ispayoff = ispayoff;
	}
	public int getOrderformid()
	{
		return orderformid;
	}
	public void setOrderformid(int orderformid)
	{
		this.orderformid = orderformid;
	}
	public String getName()
	{
		return name;
	}
	public void setname(String name) 
	{
		this.name = name;
	}
	public Date getSubmittime() 
	{
		return submittime;
	}
	public void setSubmittime(Date submittime)
	{
		this.submittime = submittime;
	}
	public double getTotalprice()
	{
		return totalprice;
	}
	public void setTotalprice(double totalprice)
	{
		this.totalprice = totalprice;
	}
	public double getIspayoff() 
	{
		return ispayoff;
	}
	public void setIspayoff(double ispayoff) 
	{
		this.ispayoff = ispayoff;
	}
	
	
	
}
