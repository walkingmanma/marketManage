package bean;

import java.io.Serializable;
import java.sql.Date;

public class UserBean implements Serializable
{
	private int userid;
	private String username;
	private String password;
	private String name;
	private String sex;
	private String address;
	private String postaddress;
	private String phone;
	private String email;
	private Date tagtime;
	
	public UserBean(){}
	
	public UserBean(int userid,String username,String name
			,String sex,String address,String postaddress
			,String phone,String email,Date tagtime
			,String password)
	{
		this.userid=userid;
		this.name=name;
		this.address=address;
		this.email=email;
		this.phone=phone;
		this.sex=sex;
		this.tagtime=tagtime;
		this.username=username;
		this.postaddress=postaddress;
		this.password=password;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostaddress() {
		return postaddress;
	}
	public void setPostaddress(String postaddress) {
		this.postaddress = postaddress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getTagtime() {
		return tagtime;
	}
	public void setTagtime(Date tagtime) {
		this.tagtime = tagtime;
	}
	public void setPassword(String password)
	{
		this.password=password;
	}
	public String getPassword()
	{
		return this.password;
	}
	
}
