package dao;

import java.util.List;

import domain.OrderList;

public interface OrderListDao extends BaseDao<OrderList> 
{
	//定义按照orderformid查询订单项
	List<OrderList> findListByformid(int orderformid);
	//定义增加订单列表的方法
	void addList(OrderList list);
	//定义修改的方法
	void updateList(OrderList list);
	//定义删除掉额方法
	void deleteList(OrderList list);
}
