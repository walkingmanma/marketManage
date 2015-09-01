package dao.Imp;

import java.io.Serializable;
import java.util.List;

import dao.BaseDaoHibernate4;
import dao.CommodityDao;
import domain.Commodity;

public class CommodityDaoImp extends BaseDaoHibernate4<Commodity> implements
		CommodityDao 
{

	//查询所有的商品信息
	public List<Commodity> findAllComm() 
	{
		return findAll(Commodity.class);
	}

	//分页查询所有的商品信息
	public List<Commodity> findAllCommByPaging(int page, int limit) 
	{
		String hql="select en from "
					+ Commodity.class.getSimpleName() + " as en ";
		return findByPage(hql, page, limit);
	}

	@Override
	public List<Commodity> findByType(int typeid, int page, int limit) 
	{
		String hql="select en from "
				+ Commodity.class.getSimpleName() + " as en"
				+ " join en.type t"
				+ " where t.typeid="+typeid; 
		return findByPage(hql, page, limit);
	}
	//分页，按名称，种类，生产厂家查询所需商品
	public List<Commodity> findByNameTypeManu(String name, int typeid,
			String manu,int page,int limit) 
	{
		String hql="select en from "
				+ Commodity.class.getSimpleName() + " as en"
				+ " join en.type t"
				+ " where en.commodityname like "+"'%"+name+"%'"
				+ " and en.manufacture like "+"'%"+manu+"%'"
				+ "	and t.typeid= "+ typeid; 
		return findByPage(hql, page, limit);
	}

	@Override
	public List<Commodity> findByName(String name, int page, int limit) 
	{
		String hql="select en from "
				+ Commodity.class.getSimpleName() + " as en"
				+ " where en.commodityname like "+"'%"+name+"%'"; 
		return findByPage(hql, page, limit);
	}

	@Override
	public List<Commodity> findByNameType(String name, int typeid, int page,
			int limit) 
	{
		String hql="select en from "
				+ Commodity.class.getSimpleName() + " as en"
				+ " inner join en.type t"
				+ " where en.commodityname like "+"'%"+name+"%'"
				+ "	and t.typeid= "+ typeid; 
		return findByPage(hql, page, limit);
	}
	
	@Override
	public List<Commodity> findByTypeManu(int typeid, String manu, int page,
			int limit) 
	{

		String hql="select en from "
				+ Commodity.class.getSimpleName() + " as en"
				+ " join en.type t"
				+ " where en.manufacture like "+"'%"+manu+"%'"
				+ "	and t.typeid= "+ typeid; 
		return findByPage(hql, page, limit);
	}

	@Override
	public List<Commodity> findByNameManu(String name, String manu, int page,
			int limit) 
	{
		String hql="select en from "
				+ Commodity.class.getSimpleName() + " as en"
				+ " join en.type t"
				+ " where en.commodityname like "+"'%"+name+"%'"
				+ " and en.manufacture like "+"'%"+manu+"%'";
		return findByPage(hql, page, limit);
	}

	@Override
	public List<Commodity> findByManu(String manu, int page, int limit) 
	{
		String hql="select en from "
				+ Commodity.class.getSimpleName() + " as en"
				+ " join en.type t"
				+ " where en.manufacture like "+"'%"+manu+"%'"; 
		return findByPage(hql, page, limit);
	}
	
	//判断实体是否已经存在,
	public  int isExist(Commodity comm)
	{
		int result=0;
		List<Commodity> list=findAllComm();
		for (Commodity commodity : list) 
		{
			if(commodity.equals(comm))
			{
				result=commodity.getCommodityid();
				break;
			}
		}
		return result;
	}
	
	//增加商品（本方法用于给库存增加新的商品，或者更改原有的库存数量）
	public void addComm(Commodity commodity) 
	{
		int id=isExist(commodity);
		if(id==0 )
		{
			save(commodity);
		}else
		{
			Commodity c=get(Commodity.class, id);
			c.setCommodityleavenum(c.getCommodityamount()+commodity.getCommodityamount());
			c.setCommodityamount(c.getCommodityamount()+commodity.getCommodityamount());
		}
	}

	@Override
	public Commodity findById(int id)
	{
		return get(Commodity.class, id);
	}
	
}
