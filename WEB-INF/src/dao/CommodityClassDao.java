package dao;

import java.util.*;

import domain.CommodityClass;

public interface CommodityClassDao extends BaseDao<CommodityClass> {
	
	//查询所有的实体
	List<CommodityClass> findAll();
	//查询所有产品种类实体并且分页显示
	List<CommodityClass> findAllByPaging(int page,int pageSize);
	//实现通过名称查询商品种类信息，模糊查询
	List<CommodityClass> findByName(String name);
	//实现通过名称查询商品种类信息,精确查询
	List<CommodityClass> findByNameAccurate(String name);
	//通过种类名称查询所有产品种类实体并且分页显示
	List<CommodityClass> findAllByNamePaging(String name,int page,int pageSize);
	//按照id查询实体
	CommodityClass findById(int id);
}
