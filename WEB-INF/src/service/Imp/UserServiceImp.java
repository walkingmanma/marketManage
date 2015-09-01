package service.Imp;

import dao.Imp.UserDaoImp;
import domain.Commodity;
import domain.User;
import service.UserService;

import java.sql.Date;
import java.util.*;

import bean.CommodityBean;
import bean.UserBean;
public class UserServiceImp implements UserService 
{
	//UserDao的注入
	private UserDaoImp userdaoimp;

	public UserDaoImp getUserdaoimp()
	{
		return userdaoimp;
	}
	public void setUserdaoimp(UserDaoImp userdaoimp) 
	{
		this.userdaoimp = userdaoimp;
	}
	
	//查询所有用户信息
	public 	List<UserBean> findAllUser() throws Exception
	{
		List<User> result= userdaoimp.findAllUser();
		List<UserBean> list=new ArrayList<UserBean>();
		if(result.size()>0)
		{
			for (User u : result) 
			{
				list.add(new UserBean(u.getUserid(), u.getUsername(),u.getName()
						, u.getSex(), u.getAddress(), u.getPost()
						, u.getPhone(),u.getEmail(),u.getRegtime(),u.getPassword()));
			}
		}else
		{
			throw new Exception("没有查询到数据");
		}
		return list;
	}
	
	//按名称或者电话查找相关用户信息
	public List<UserBean> findByNamePhone(String name,String phone,int page,int limit) throws Exception
	{

		List<UserBean> list=new ArrayList<UserBean>();
		List<User> result=new ArrayList<User>();
		if((name != null) && phone == null) //名称
		{
			result=userdaoimp.findByName(name);
		}else if(name != null && phone != null)
		{
			result=userdaoimp.findByNamePhone(name, phone);
		}else if(name == null  && phone != null)
		{
			result=userdaoimp.findByPhone(phone);
		}else if(name == null && phone == null)
		{
			result=userdaoimp.findAllUser();
		};
		if(result.size()>0)
		{
			for (User u : result) 
			{
				list.add(new UserBean(u.getUserid(), u.getUsername(),u.getName()
						, u.getSex(), u.getAddress(), u.getPost()
						, u.getPhone(),u.getEmail(),u.getRegtime(),u.getPassword()));
			}
		}else
		{
			throw new Exception("没有查询到数据");
		};
		return list;
	}

	//增加用户信息的方法
	public 	void addUser(String username,String password,String name
				,String sex,String address,String postaddress
				,String phone,String email,Date tagtime) throws Exception
	{
		User u=new User();
		u.setAddress(address);
		u.setEmail(email);
		u.setName(name);
		u.setPassword(password);
		u.setPhone(phone);
		u.setPost(postaddress);
		u.setRegtime(tagtime);
		u.setUsername(username);
		u.setSex(sex);
		userdaoimp.addUser(u);
	}
		//根据传入的参数修改用户信息
	public void updateUser(int userid,String username,String password,String name
				,String sex,String address,String postaddress
				,String phone,String email,Date tagtime) throws Exception
	{
		User u=userdaoimp.findById(userid);
		u.setAddress(address);
		u.setEmail(email);
		u.setName(name);
		u.setPassword(password);
		u.setPhone(phone);
		u.setPost(postaddress);
		u.setRegtime(tagtime);
		u.setUsername(username);
		u.setSex(sex);
		userdaoimp.update(u);
	}
		//根据id删除商品信息
	public void deleteCommodity(int id)
	{
		User user=userdaoimp.findById(id);
		userdaoimp.delete(user);
	}
}
