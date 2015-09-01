package service.Imp;

import java.util.*;

import bean.CommodityClassBean;
import dao.CommodityClassDao;
import dao.Imp.CommodityClassDaoImp;
import domain.Admin;
import domain.CommodityClass;
import service.CommodityClassService;

public class CommodityClassServiceImp implements CommodityClassService 
{
	
    private CommodityClassDao typeDaoImp;
   
	public void setTypeDaoImp(CommodityClassDao typeDaoImp) 
	{
		this.typeDaoImp = typeDaoImp;
	}

	//查询所有的商品种类实体，返回id,name,desc属性的bean
	public List<CommodityClassBean> findAllTypes() 
	{	
		List<CommodityClassBean> result=new ArrayList<CommodityClassBean>();
		List<CommodityClass>  list;
		list=typeDaoImp.findAll();
		for (CommodityClass type : list) {
			result.add(new CommodityClassBean(type.getTypeid(),type.getTypename(),type.getTypedesc()));
		}
		return result;
	}
	//分页查询所有商品种类信息
	public List<CommodityClassBean> findAllTypeByPaging(int page, int limit) 
	{
		List<CommodityClassBean> result=new ArrayList<CommodityClassBean>();
		List<CommodityClass>  list;
		list=typeDaoImp.findAllByPaging(page, limit);
		for (CommodityClass type : list) {
			result.add(new CommodityClassBean(type.getTypeid(),type.getTypename(),type.getTypedesc()));
		}
		return result;
	}
	//通过商品类型名称查找所有数据
	public List<CommodityClassBean> findTypeByName(String typename)
	{
		List<CommodityClassBean> result=new ArrayList<CommodityClassBean>();
		List<CommodityClass>  list;
		list=typeDaoImp.findByName(typename);
		for (CommodityClass it : list) {
			result.add(new CommodityClassBean(it.getTypeid(),it.getTypename(),it.getTypedesc()));
		}
		return result;
	}
	
	//通过商品类型名称查找所有数据并分页显示
	public List<CommodityClassBean> findTypeByNamePaging(String typename,
			int page, int limit) 
	{
		
		List<CommodityClassBean> result=new ArrayList<CommodityClassBean>();
		List<CommodityClass>  list;
		list=typeDaoImp.findAllByNamePaging(typename, page, limit);
		for (CommodityClass it : list) {
			result.add(new CommodityClassBean(it.getTypeid(),it.getTypename(),it.getTypedesc()));
		}
		return result;
	}
	
	//更新商品种类信息，要求不能有相同的typename名称
	public void updateType(int typeid,String typename,String typedesc) throws Exception
	{
		
		CommodityClass type=typeDaoImp.findById(typeid);
		if(isExisted(typename))
		{
			type.setTypedesc(typedesc);
			typeDaoImp.update(type);
			
		}else
		{
			
			type.setTypename(typename);
			type.setTypedesc(typedesc);
			typeDaoImp.update(type);
		}
	} 
	
	//通过name属性判断是否已经存在某商品种类
	private boolean isExisted(String typename)
	{
		List<CommodityClass> list=typeDaoImp.findByNameAccurate(typename);
		boolean result=false;
		if (list.size()>0) 
		{
			result=true;
		}else
		{
			result=false;
		}
		return result;
	}
	
	//添加商品种类
	public void addType(String typename,String typedesc) throws Exception
	{	
		
		if(isExisted(typename))
		{	
			throw new Exception("该类商品已经存在");
		}else
		{	
			CommodityClass type=new CommodityClass();
			type.setTypename(typename);
			type.setTypedesc(typedesc);
			typeDaoImp.save(type);
		}	
	}
	
	//删除商品种类新消息
	public void deleteTypes(int id,String typename) throws Exception 
	{
		if(!isExisted(typename))
		{	
			throw new Exception("对不起，不存在该商品种类信息");
		}else
		{	
			CommodityClass type=typeDaoImp.findById(id);
			typeDaoImp.delete(type);
		}	
	}	
}
