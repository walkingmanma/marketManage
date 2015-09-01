package dao;

import java.util.*;

import domain.Commodity;

public interface CommodityDao extends BaseDao<Commodity> 
{	
	//查询所有商品信息
	List<Commodity> findAllComm();
	//分页查询所有商品
	List<Commodity> findAllCommByPaging(int page,int limit);
	//分页，按名称，
	List<Commodity> findByName(String name,int page,int limit);
	//分页，按名称，种类
	List<Commodity> findByNameType(String name, int typeid,int page,int limit);
	//分页，按名称，种类，生产厂家查询所需商品
	List<Commodity> findByNameTypeManu(String name, int typeid,String manu,int page,int limit);
	//分页种类查询
	List<Commodity> findByType(int typeid,int page,int limit);
	//分页，按种类，生产厂家查询所需商品
	List<Commodity> findByTypeManu(int typeid,String manu,int page,int limit);
	//分页生产厂查询
	List<Commodity> findByManu(String manu,int page,int limit);
	//分页 。名称和生产厂家查询
	List<Commodity> findByNameManu(String name, String manu,int page,int limit);
	//增加商品（本方法用于给库存增加新的商品，或者更改原有的库存数量）
	void addComm(Commodity commodity) throws Exception;
	//判断实体是否已经存在,返回该实体的id,如果不存在返回0
	int isExist(Commodity commodity);
	//依据产品id查询产品实体
	Commodity findById(int id);
}
