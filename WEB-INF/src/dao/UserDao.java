package dao;

import java.util.List;

import domain.Commodity;
import domain.User;

public interface UserDao extends BaseDao<User>
{
	//查询所有的用户信息
	List<User> findAllUser();
	//查询所有的用户并分页
	List<User> findAllUserByPaging(int page,int limit);
	//按照name查询用户信息并分页
	List<User> findByName(String name);
	//按照phone查询用户信息
	List<User> findByPhone(String phone);
	//按照name，phone查询用户信息
	List<User> findByNamePhone(String name,String phone);
	
	void addUser(User user) throws Exception;
	//判断实体是否已经存在,返回该实体的id,如果不存在返回0
	int isExist(User user);
	//依据产品id查询产品实体
	User findById(int id);
}
