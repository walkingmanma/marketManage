package dao;

import java.sql.Date;
import java.util.List;

import domain.OrderForm;
import domain.User;

public interface OrderFormDao extends BaseDao<OrderForm>
{
	//增加订单的方法
	void addOrderForm(OrderForm of);
	
	//查询所有的订单信息
	List<OrderForm> findAllOrderForm();
	//按照tagtime查询用户信息
	List<OrderForm> findBySubmittime(Date time);
	//按照phone查询用户信息
	List<OrderForm> findByPhone(String phone);
	//按照name，phone查询用户信息
	List<OrderForm> findByTimePhone(Date time,String phone);
	OrderForm findByid(int orderformid);
}
