package domain;

/**
 * User是持久化层关于前台用户实体的类
 */
import java.io.Serializable;
import java.util.*;
import java.sql.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="USERS",schema="SCOTT")
public class User implements Serializable 
{
	
	//对应主键
	@Id
	@GeneratedValue(generator="userPkGenerator")
	@GenericGenerator(name="userPkGenerator",strategy="increment")
	@Column(name="USERID")
	private Integer userid;
	
	//注册的用户名
	@Column(name="USERNAME",length=30,nullable=false)
	private String username;
	
	//注册用户名对应的密码
	@Column(name="PASSWORD",length=30,nullable=false)
	private String password;
	
	//用户的姓名
	@Column(name="NAME",length=30,nullable=false)
	private String name;
	
	//用户的性别
	@Column(name="SEX")
	private String sex;
	
	//用户的住址
	@Column(name="ADDRESS",length=50)
	private String address;
	
	//用户的电话号码
	@Column(name="PHONE",length=30,nullable=false)
	private String phone;
	
	//用户的邮寄地址
	@Column(name="POSTADDRESS",length=50,nullable=false)
	private String post;
	
	//用户的email地址
	@Column(name="EMAIL",length=30)
	private String email;
	
	//用户的注册时间
	@Column(name="REGTIME")
	private java.sql.Date regtime;
	
	//用于存放该用户的订单集合
	//定义该商品种类实体所关联的所有的商品实体
	@OneToMany(targetEntity=domain.OrderForm.class)
		//定义外键列
	@JoinColumn(name="USERID")
	private Set<OrderForm> orders=new HashSet<OrderForm>();
	
	
	public Set<OrderForm> getOrders() 
	{
		return orders;
	}
	public void setOrders(Set<OrderForm> orders)
	{
		this.orders = orders;
	}
	public Integer getUserid()
	{
		return userid;
	}
	public void setUserid(Integer userid)
	{
		this.userid = userid;
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
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getSex()
	{
		return sex;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getPost()
	{
		return post;
	}
	public void setPost(String post)
	{
		this.post = post;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public java.sql.Date getRegtime()
	{
		return regtime;
	}
	public void setRegtime(java.sql.Date regtime)
	{
		this.regtime = regtime;
	}
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (phone == null) {
			if (other.phone != null) {
				return false;
			}
		} else if (!phone.equals(other.phone)) {
			return false;
		}
		return true;
	}
	
	
	
}	
