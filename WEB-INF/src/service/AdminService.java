package service;

import java.sql.Date;
import java.util.List;

import bean.AdminBean;
import domain.Admin;

public interface AdminService
{
	//增加新的员工信息
	void addAdmin(String username,String sex,Date hireday,String password) throws Exception;
	//判断是否可登录
	boolean isAdmin(String username,String password) throws Exception;
	//更新员工信息
	void updateAdmin(int adminid,String username,String sex,Date hireday,String password);
	//查找所有的员工信息
	List<AdminBean> findallAdmin() throws Exception;
	//按照名称查询员工信息
	List<AdminBean> findByName(String username) throws Exception;
	//删除员工信息
	void deleteAdmin(int id);
}
