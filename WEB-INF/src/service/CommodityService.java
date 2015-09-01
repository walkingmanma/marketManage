package service;

import java.sql.Date;
import java.util.List;



import bean.CommodityBean;

public interface CommodityService 
{
	
	//分页，并按照name,typeid,manu查询商品信息
	List<CommodityBean> findByNameTypeManu(String typename,int typeid,String manu,int page,int limit) throws Exception;
	//增加商品信息的方法，当商品信息相同是，增加数量
	void addCommodity(String commodityname,String commoditydesc,String manufacture
			,int commodityamount,int typeid,double commodityprice, double fcprice
			,Date tagtime);
	//根据传入的参数修改产品信息
	void updateCommodity(int commodityid,int typeid,String commodityname,String manufacture
			,String commoditydesc,int commodityamount,double commodityprice,double fcprice
			,Date tagtime);
	//根据id删除商品信息
	void deleteCommodity(int id);
}
