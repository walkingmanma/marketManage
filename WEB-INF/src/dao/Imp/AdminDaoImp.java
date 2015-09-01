package dao.Imp;

import java.util.List;

import dao.AdminDao;
import dao.BaseDaoHibernate4;
import domain.Admin;

public class AdminDaoImp extends BaseDaoHibernate4<Admin> implements AdminDao {


	@Override
	public List<Admin> findByName(String name) {
		String like = "'%" + name + "%'";  
		String hql="select en from "
				+ Admin.class.getSimpleName() + " as en"
				+ " where en.username like"+like;
		List<Admin> admins=find(hql);
		return admins;
	}

	@Override
	public List<Admin> findByNameAndPW(String name, String password) {  
		String hql="select en from "
				+ Admin.class.getSimpleName() + " as en"
				+ " where en.username="+"'"+name+"'"
				+ " and en.password="+"'"+password+"'";
		List<Admin> admins=find(hql);
		return admins;	
	}

	@Override
	public List<Admin> findAllAdmin() 
	{
		return findAll(Admin.class);
	}

	@Override
	public Admin findById(int id) 
	{
		return get(Admin.class, id);
	}
	
	private boolean isExisted(Admin admin)
	{
		List<Admin> list=findAllAdmin();
		boolean result=false;
		for (Admin adm : list) 
		{
			if (adm.equals(admin)) 
			{
				result=adm.equals(admin);
				break;
			}
		}
		return result;
	}
	
	@Override
	public void addAdmin(Admin admin) throws Exception 
	{	
		if(isExisted(admin))
		{	
			throw new Exception("该用户已经注册，请登录或重新注册");
		}else
		{	
			save(admin);
		}			
	}
}
