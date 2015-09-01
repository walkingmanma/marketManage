package domain;

/**
 * CommodityClass类是持久化曾的商品种类信息实体类
 * @author Ma
 */
import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="COMMODITYCLASS",schema="SCOTT")
public class CommodityClass implements Serializable 
{

	//商品种类的编号
	@Id
	@GeneratedValue(generator="typePkGenerator")
	@GenericGenerator(name="typePkGenerator",strategy="increment")
	@Column(name="COMMODITYCLASSID")
	private Integer typeid;
	
	//商品种类名称
	@Column(name="COMMODITYCLASSNAME",length=30,nullable=false)
	private String typename;
	
	//商品种类描述
	@Column(name="COMMODITYCLASSDESC",length=50,nullable=true)
	private String typedesc;
	
	//定义该商品种类实体所关联的所有的商品实体
	@OneToMany(targetEntity=domain.Commodity.class)
	//定义外键列
	@JoinColumn(name="COMMODITYCLASS")
	private Set<Commodity> commoditys=new HashSet<Commodity>();
	
	public Integer getTypeid()
	{
		return typeid;
	};
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
	public Set<Commodity> getCommoditys() 
	{
		return commoditys;
	}
	public void setCommoditys(Set<Commodity> commoditys) 
	{
		this.commoditys = commoditys;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((typename == null) ? 0 : typename.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CommodityClass)) {
			return false;
		}
		CommodityClass other = (CommodityClass) obj;
		if (typename == null) {
			if (other.typename != null) {
				return false;
			}
		} else if (!typename.equals(other.typename)) {
			return false;
		}
		return true;
	}
	
}
