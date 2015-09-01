package service.Imp;


import java.util.*;
import java.sql.*;
import java.sql.Date;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import bean.CommodityBean;
import dao.Imp.CommodityClassDaoImp;
import dao.Imp.CommodityDaoImp;
import domain.Commodity;
import service.CommodityService;

public class CommodityServiceImp implements CommodityService 
{
	private CommodityDaoImp commodityDaoImp;
	private CommodityClassDaoImp commodityclassDaoimp;
	
	
	public CommodityClassDaoImp getCommodityclassDaoimp() 
	{
		return commodityclassDaoimp;
	}
	
	public void setCommodityclassDaoimp(CommodityClassDaoImp commodityclassimp) 
	{
		this.commodityclassDaoimp = commodityclassimp;
	}
	
	public CommodityDaoImp getCommodityDaoImp() 
	{
		return commodityDaoImp;
	}
	
	
	public void setCommodityDaoImp(CommodityDaoImp commodityDaoImp) 
	{
		this.commodityDaoImp = commodityDaoImp;
	}

	//分页，并按照name,typeid,manu查询商品信息
	public List<CommodityBean> findByNameTypeManu(String name, int typeid,
			String manu, int page, int limit) throws Exception 
	{
		List<CommodityBean> list=new ArrayList<CommodityBean>();
		List<Commodity> result;
		if( name!=null && typeid==0 && manu == null) //名称
		{
			 result=commodityDaoImp.findByName(name, page, limit);
		}else if(name != null && typeid!=0 && manu == null)  //名称种类
		{
			 result=commodityDaoImp.findByNameType(name, typeid, page, limit);
		}else if((name != null) && typeid != 0 && manu != null) //名称种类生厂商
		{
			 result=commodityDaoImp.findByNameTypeManu(name, typeid,manu, page, limit);
		}else if(name == null && typeid != 0 && manu == null)  //种类
		{
			 result=commodityDaoImp.findByType(typeid, page, limit);
		}else if(name == null && typeid != 0 && manu != null) //种类和生产商 
		{
			 result=commodityDaoImp.findByTypeManu(typeid, manu,page, limit);
		}else if(name == null && typeid == 0 && manu != null) //生产商
		{
			 result=commodityDaoImp.findByManu(manu,page, limit);
		}else if((name != null) && typeid == 0 && manu != null) //名称和生产商
		{
			 result=commodityDaoImp.findByNameManu(name,manu,page, limit);
		}else if(name == null && typeid == 0 && manu == null)
		{
			result=commodityDaoImp.findAllCommByPaging(page, limit);
		}else
		{
			result=new ArrayList<Commodity>();
		};
		if(result.size()>0)
		{
			for (Commodity commodity : result) 
			{
				list.add(new CommodityBean(commodity.getCommodityid(), commodity.getType().getTypename(),
						commodity.getCommodityname(), commodity.getManufacture(),
						commodity.getCommoditydesc(), commodity.getCommodityprice(), commodity.getFcprice(),
						commodity.getCommodityamount(), commodity.getCommodityleavenum(), commodity.getTagtime()
						,commodity.getType().getTypeid()));
			}
		}else
		{
			throw new Exception("没有查询到数据");
		}
		
		return list;
	}
	
	//新增产品信息，
	public void addCommodity(String commodityname,String commoditydesc,String manufacture
			,int commodityamount,int typeid,double commodityprice, double fcprice
			,Date tagtime)
	{
		Commodity comm=new Commodity();
		comm.setCommodityamount(commodityamount);
		comm.setCommodityleavenum(commodityamount);
		comm.setCommoditydesc(commoditydesc);
		comm.setCommodityname(commodityname);
		comm.setCommodityprice(commodityprice);
		comm.setFcprice(fcprice);
		comm.setManufacture(manufacture);
		comm.setTagtime(tagtime);
		comm.setType(commodityclassDaoimp.findById(typeid));
		commodityDaoImp.addComm(comm);
	}
	
	public void updateCommodity(int commodityid, int typeid,
			String commodityname, String manufacture, String commoditydesc,
			int commodityamount, double commodityprice, double fcprice,
			Date tagtime)
	{
		Commodity comm=commodityDaoImp.findById(commodityid);
		comm.setCommodityamount(comm.getCommodityamount()-commodityamount);
		comm.setCommodityleavenum(comm.getCommodityleavenum()-commodityamount);
		comm.setCommoditydesc(commoditydesc);
		comm.setCommodityname(commodityname);
		comm.setCommodityprice(commodityprice);
		comm.setFcprice(fcprice);
		comm.setManufacture(manufacture);
		comm.setTagtime(tagtime);
		comm.setType(commodityclassDaoimp.findById(typeid));
		commodityDaoImp.update(comm);
		
	}
	
	//根据id删除商品信息
	public void deleteCommodity(int id)
	{
		Commodity comm=commodityDaoImp.findById(id);
		commodityDaoImp.delete(comm);
	}
}
