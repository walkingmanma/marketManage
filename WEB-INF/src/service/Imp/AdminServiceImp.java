package service.Imp;

import dao.Imp.AdminDaoImp;
import domain.Admin;
import domain.User;
import service.AdminService;

import java.sql.Date;
import java.util.*;

import bean.AdminBean;
import bean.UserBean;

public class AdminServiceImp implements AdminService 
{
	private AdminDaoImp adminDaoImp;
	public AdminDaoImp getAdmindaoimp() 
	{
		return adminDaoImp;
	}
	public void setAdminDaoImp(AdminDaoImp admindaoimp) 
	{
		this.adminDaoImp = admindaoimp;
	}
	
	@Override
	public boolean isAdmin(String username,String password) throws Exception
	{
		List<Admin> list=adminDaoImp.findByNameAndPW(username, password);
		if(list.size()>0)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public void addAdmin(String username, String sex, Date hireday,String password) throws Exception
	{
		Admin ad=new Admin();
		ad.setHireday(hireday);
		ad.setPassword(password);
		ad.setSex(sex);
		ad.setUsername(username);
		adminDaoImp.addAdmin(ad);	
	}
	
	public void updateAdmin(int adminid, String username, String sex,
			Date hireday,String password) 
	{
		Admin ad=adminDaoImp.findById(adminid);
		ad.setHireday(hireday);
		ad.setPassword(password);
		ad.setSex(sex);
		ad.setUsername(username);
		adminDaoImp.update(ad);
	}
	
	public List<AdminBean> findallAdmin() throws Exception
	{
		List<Admin> result= adminDaoImp.findAllAdmin();
		List<AdminBean> list=new ArrayList<AdminBean>();
		if(result.size()>0)
		{
			for (Admin a : result) 
			{
				list.add(new AdminBean(a.getAdminid(), a.getUsername()
						, a.getPassword(), a.getHireday(), a.getSex()));
			}
		}else
		{
			throw new Exception("没有查询到数据");
		}
		return list;
	}

	public List<AdminBean> findByName(String username) throws Exception {
		List<Admin> result= adminDaoImp.findByName(username);
		List<AdminBean> list=new ArrayList<AdminBean>();
		if(result.size()>0)
		{
			for (Admin a : result) 
			{
				list.add(new AdminBean(a.getAdminid(), a.getUsername()
						, a.getPassword(), a.getHireday(), a.getSex()));
			}
		}else
		{
			throw new Exception("没有查询到数据");
		}
		return list;
	}
	
	
	public void deleteAdmin(int id)
	{
		Admin admin=adminDaoImp.findById(id);
		adminDaoImp.delete(admin);
	}

}
