package bean;

import java.io.Serializable;
import java.sql.Date;

public class CommodityBean implements Serializable 
{
	
	private Integer commodityid;
	private int typeid;
	private String typename;
	private String commodityname;
	private String manufacture;
	private String commoditydesc;
	private Double commodityprice;
	private Double fcprice;
	private Integer commodityamount;
	private Integer commodityleavenum;
	private Date tagtime;
	
	public CommodityBean(){}
	
	public CommodityBean(int commodityid, String typename, String commodityname,
			String manufacture, String commoditydesc, double commodityprice,
			double fcprice, int commodityamount, int commodityleavenum,
			Date tagtime,int typeid) 
	{
		this.commodityid = commodityid;
		this.typename = typename;
		this.commodityname = commodityname;
		this.manufacture = manufacture;
		this.commoditydesc = commoditydesc;
		this.commodityprice = commodityprice;
		this.fcprice = fcprice;
		this.commodityamount = commodityamount;
		this.commodityleavenum = commodityleavenum;
		this.tagtime = tagtime;
		this.typeid=typeid;
	}
	
	public Integer getCommodityid() 
	{
		return commodityid;
	}
	public void setCommodityid(Integer commodityid) 
	{
		this.commodityid = commodityid;
	}
	public int getTypeid()
	{
		return this.typeid;
	}
	public void setTypeid(int typeid)
	{
		this.typeid=typeid;
	}
	public String getTypename()
	{
		return typename;
	}
	public void setTypename(String typename)
	{
		this.typename = typename;
	}
	public String getCommodityname()
	{
		return commodityname;
	}
	public void setCommodityname(String commodityname) 
	{
		this.commodityname = commodityname;
	}
	public String getManufacture()
	{
		return manufacture;
	}
	public void setManufacture(String manufacture) 
	{
		this.manufacture = manufacture;
	}
	public String getCommoditydesc() 
	{
		return commoditydesc;
	}
	public void setCommoditydesc(String commoditydesc) 
	{
		this.commoditydesc = commoditydesc;
	}
	public Double getCommodityprice() 
	{
		return commodityprice;
	}
	public void setCommodityprice(Double commodityprice) 
	{
		this.commodityprice = commodityprice;
	}
	public Double getFcprice() 
	{
		return fcprice;
	}
	public void setFcprice(Double fcprice) 
	{
		this.fcprice = fcprice;
	}
	public Integer getCommodityamount() 
	{
		return commodityamount;
	}
	public void setCommodityamount(Integer commodityamount)
	{
		this.commodityamount = commodityamount;
	}
	public Integer getCommodityleavenum() 
	{
		return commodityleavenum;
	}
	public void setCommodityleavenum(Integer commodityleavenum) 
	{
		this.commodityleavenum = commodityleavenum;
	}
	public Date getTagtime() 
	{
		return tagtime;
	}
	public void setTagtime(Date tagtime) 
	{
		this.tagtime = tagtime;
	}

	
	
	
}
