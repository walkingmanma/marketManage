package dao;

import domain.Admin;

import java.util.*;
public interface AdminDao extends BaseDao<Admin> 
{
	//根据员工姓名查询员工实体的方法List<Admin> findByName(Admin.class entityClass,String name);
	List<Admin> findByName(String name);
	//根据员工的姓名和密码查询员工实体的方法
	List<Admin> findByNameAndPW(String name,String password);
	//查找所有员工的信息记录
	List<Admin> findAllAdmin();
	//按照adminid查找员工记录
	Admin findById(int id);
	//注册新用户
	void addAdmin(Admin admin) throws Exception;
}
