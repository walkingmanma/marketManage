package service;

import java.util.List;

import bean.CommodityClassBean;
import domain.CommodityClass;

public interface CommodityClassService 
{
	//添加商品种类信息
	void addType(String typename,String typedesc) throws Exception;
	//查询所有商品种类信息
	List<CommodityClassBean> findAllTypes();
	//查询所有商品种类信息并且分页显示
	List<CommodityClassBean> findAllTypeByPaging(int page,int limit);
	//根据商品种类名称模糊查询商品种类信息
	List<CommodityClassBean> findTypeByName(String typename);
	//根据商品种类名称模糊查询商品种类信息并分页显示
	List<CommodityClassBean> findTypeByNamePaging(String typename,int page,int limit);
	//更新商品种类信息
	void updateType(int typeid,String typename,String typedesc) throws Exception;
	//删除商品种类信息
	void deleteTypes(int id,String typename) throws Exception;
}
