package service;

import java.sql.Date;
import java.util.List;

import bean.UserBean;

public interface UserService 
{
	//分页查询所有用户信息
	List<UserBean> findAllUser() throws Exception;
	//按名称或者电话查找相关用户信息
	List<UserBean> findByNamePhone(String name,String phone,int page,int limit) throws Exception;
	//增加用户信息的方法
	void addUser(String username,String password,String name
			,String sex,String address,String postaddress
			,String phone,String email,Date tagtime) throws Exception;
	//根据传入的参数修改用户信息
	void updateUser(int userid,String username,String password,String name
			,String sex,String address,String postaddress
			,String phone,String email,Date tagtime) throws Exception;
	//根据id删除商品信息
	void deleteCommodity(int id);
}
