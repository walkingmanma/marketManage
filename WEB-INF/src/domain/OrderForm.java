package domain;

/**
 * OrderForm是持久化层的关于订单信息实体类
 */
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.sql.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ORDERFORM",schema="SCOTT")
public class OrderForm implements Serializable 
{
	//对应主键
	@Id
	@GeneratedValue(generator="orderformPkGenerator")
	@GenericGenerator(name="orderformPkGenerator",strategy="increment")
	@Column(name="ORDERFORMID")
	private Integer orderformid;
	//定义该订单实体关联的用户实体
	@ManyToOne(targetEntity=domain.User.class)
	//指定外键列，不允许为空
	@JoinColumn(name="USERID_REF",nullable=false)
	private User user;
	//订单提交时间
	@Column(name="SUBMITTIME",nullable=false)
	private Date submittime;
	//总价格
	@Column(name="TOTALPRICE",nullable=false)
	private Double totalprice;

	@Column
	private Double ispayoff;

	@OneToMany(targetEntity=domain.OrderList.class)
	@JoinColumn(name="ORDERFORMID")
	private Set<OrderList> orderlist=new HashSet<OrderList>();
	
	public Integer getOrderformid() 
	{
		return orderformid;
	}
	public void setOrderformid(Integer orderformid)
	{
		this.orderformid = orderformid;
	}
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	public Date getSubmittime()
	{
		return submittime;
	}
	public void setSubmittime(Date submittime)
	{
		this.submittime = submittime;
	}
	public Double getTotalprice()
	{
		return totalprice;
	}
	public void setTotalprice(Double totalprice)
	{
		this.totalprice = totalprice;
	}
	public Double getIspayoff()
	{
		return ispayoff;
	}
	public void setIspayoff(Double ispayoff)
	{
		this.ispayoff = ispayoff;
	}
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((orderformid == null) ? 0 : orderformid.hashCode());
		result = prime * result
				+ ((submittime == null) ? 0 : submittime.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (!(obj instanceof OrderForm)) 
		{
			return false;
		}
		OrderForm other = (OrderForm) obj;
		if (orderformid == null) {
			if (other.orderformid != null) 
			{
				return false;
			}
		} else if (!orderformid.equals(other.orderformid)) 
		{
			return false;
		}
		if (submittime == null) 
		{
			if (other.submittime != null)
			{
				return false;
			}
		} else if (!submittime.equals(other.submittime))
		{
			return false;
		}
		if (user == null) 
		{
			if (other.user != null) 
			{
				return false;
			}
		} else if (!user.equals(other.user)) 
		{
			return false;
		}
		return true;
	}
	
	
	
}
