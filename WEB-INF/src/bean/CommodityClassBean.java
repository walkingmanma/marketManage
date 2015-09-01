package bean;

import java.io.Serializable;

public class CommodityClassBean implements Serializable 
{

	private int typeid;
	private String typename;
	private String typedesc;
	private int id;
	
	public CommodityClassBean()
	{	
	}
	public CommodityClassBean(int typeid,String typename,String typedesc)
	{
		this.typeid=typeid;
		this.typedesc=typedesc;
		this.typename=typename;
	}
	public int getTypeid() 
	{
		return typeid;
	}
	public void setTypeid(int typeid)
	{
		this.typeid = typeid;
	}
	public String getTypename() 
	{
		return typename;
	}
	public void setTypename(String typename) 
	{
		this.typename = typename;
	}
	public String getTypedesc() 
	{
		return typedesc;
	}
	public void setTypedesc(String typedesc) 
{
		this.typedesc = typedesc;
	}
	
}
