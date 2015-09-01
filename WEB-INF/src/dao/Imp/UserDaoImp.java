package dao.Imp;


import java.util.List;

import dao.BaseDaoHibernate4;
import dao.UserDao;
import domain.Commodity;
import domain.User;

public class UserDaoImp extends BaseDaoHibernate4<User> implements UserDao 
{
		//查询所有的用户信息
	public List<User> findAllUser()
	{
		return findAll(User.class);
	}
	
	//查询所有的用户并分页
	public 	List<User> findAllUserByPaging(int page,int limit)
	{
		String hql="select en from "
				+ User.class.getSimpleName() + " as en ";
		return findByPage(hql, page, limit);
	}
	
	//按照name查询用户信息
	public List<User> findByName(String name)
	{
		String hql="select en from "
				+ User.class.getSimpleName() + " as en"
				+ " where en.name like "+"'%"+name+"%'"; 
				return find(hql);
	}
	
	//按照phone查询用户信息
	public 	List<User> findByPhone(String phone)
	{
		String hql="select en from "
				+ User.class.getSimpleName() + " as en"
				+ " where en.phone like "+"'%"+phone+"%'"; 
		return find(hql);
	}

	//按照name，phone查询用户信息
	public 	List<User> findByNamePhone(String name,String phone)
	{
		String hql="select en from "
				+ User.class.getSimpleName() + " as en"
				+ " where en.phone like "+"'%"+phone+"%'"
				+ " and en.name like "+"'%"+name+"%'"; 
		return find(hql);
	}
	
	//判断实体是否已经存在,返回该实体的id,如果不存在返回0
	public int isExist(User user)
	{
		int result=0;
		List<User> list=findAllUser();
		for (User u : list) 
		{
			if(u.equals(user))
			{
				result=u.getUserid();
				break;
			}
		}
		return result;
	}
	//增加用户信息
	public void addUser(User user) throws Exception
	{
		int id=isExist(user);
		if(id==0 )
		{
			save(user);
		}else
		{
			throw new Exception("该用户已经注册！！");
		};
	}
	
	//依据产品id查询产品实体
	public User findById(int id)
	{
		return get(User.class, id);
	}
	
}
