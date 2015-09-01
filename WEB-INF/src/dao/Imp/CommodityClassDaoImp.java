package dao.Imp;


import java.util.List;

import dao.BaseDaoHibernate4;
import dao.CommodityClassDao;
import domain.CommodityClass;

public class CommodityClassDaoImp extends BaseDaoHibernate4<CommodityClass>
		implements CommodityClassDao 
{
	@Override
	public List<CommodityClass> findByName(String name)
	{
		String hql="select en from "+CommodityClass.class.getSimpleName()+" as en "
				+" where en.typename like "+ "'%" + name + "%'";
		return find(hql);
	}
	
	public List<CommodityClass> findByNameAccurate(String name)
	{
		String hql="select en from "+CommodityClass.class.getSimpleName()+" as en "
				+" where en.typename= "+"'"+name+"'";
		return find(hql);
	}
	
	@Override
	public List<CommodityClass> findAllByNamePaging(String name,int page, int pageSize) 
	{
		String hql="select en from "+CommodityClass.class.getSimpleName()+" as en "
				+" where en.typename like "+ "'%" + name + "%'";
		return findByPage(hql, page, pageSize);
	}
	
	@Override
	public List<CommodityClass> findAll() 
	{
		return findAll(CommodityClass.class);
	}

	@Override
	public List<CommodityClass> findAllByPaging(int page, int pageSize) {
		String hql="select en from "+CommodityClass.class.getSimpleName()+" as en ";
		return findByPage(hql, page, pageSize);
	}

	@Override
	public CommodityClass findById(int id) {
		return get(CommodityClass.class, id);
	}

	
}
