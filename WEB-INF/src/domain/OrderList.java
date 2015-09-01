package domain;

/**
 * OrderList是持久层关于订单项详情的实体类
 */
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ORDERLIST",schema="SCOTT")
public class OrderList implements Serializable
{
	
	//对应主键
	@Id
	@GeneratedValue(generator="orderlistPkGenerator")
	@GenericGenerator(name="orderlistPkGenerator",strategy="increment")
	@Column(name="ORDERLISTID")
	private Integer orderlistid;
	
	//定义该订单列表实体关联的订单实体
		@ManyToOne(targetEntity=domain.Commodity.class)
		//指定外键列，不允许为空
		@JoinColumn(name="COMMODITYID_REF",nullable=false)
	private Commodity commodity;
		
		//定义该订单列表实体关联的商品实体
		@ManyToOne(targetEntity=domain.OrderForm.class)
		//指定外键列，不允许为空
		@JoinColumn(name="ORDERFORMID_REF",nullable=false)
	private OrderForm orderformid;
	@Column
	private Integer amount;
	
	public Integer getOrderlistid() 
	{
		return orderlistid;
	}
	public void setOrderlistid(Integer orderlistid)
	{
		this.orderlistid = orderlistid;
	}
	public Commodity getCommodity()
	{
		return commodity;
	}
	public void setCommodity(Commodity commodity)
	{
		this.commodity = commodity;
	}
	public OrderForm getOrderformid()
	{
		return orderformid;
	}
	public void setOrderformid(OrderForm orderform)
	{
		this.orderformid = orderform;
	}
	public Integer getAmount()
	{
		return amount;
	}
	public void setAmount(Integer amount)
	{
		this.amount = amount;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((commodity == null) ? 0 : commodity.hashCode());
		result = prime * result
				+ ((orderformid == null) ? 0 : orderformid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof OrderList))
			return false;
		OrderList other = (OrderList) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (commodity == null) {
			if (other.commodity != null)
				return false;
		} else if (!commodity.equals(other.commodity))
			return false;
		if (orderformid == null) {
			if (other.orderformid != null)
				return false;
		} else if (!orderformid.equals(other.orderformid))
			return false;
		return true;
	}
	
}	
