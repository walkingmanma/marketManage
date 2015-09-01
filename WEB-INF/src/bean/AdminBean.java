package bean;

import java.io.Serializable;
import java.sql.Date;

public class AdminBean implements Serializable
{
	private int adminid;
	private String username;
	private String password;
	private String sex;
	private Date hireday;
	
	public AdminBean(){}
	public AdminBean(int adminid, String username
			, String password, Date hireday
			,String sex)
	{
		this.adminid = adminid;
		this.username = username;
		this.password = password;
		this.hireday = hireday;
		this.sex=sex;
	}
	public int getAdminid() 
	{
		return adminid;
	}
	public void setAdminid(int adminid)
	{
		this.adminid = adminid;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public Date getHireday() 
	{
		return hireday;
	}
	public void setHireday(Date hireday)
	{
		this.hireday = hireday;
	}
	public String getSex() 
	{
		return sex;
	}
	public void setSex(String sex) 
	{
		this.sex = sex;
	}
}
