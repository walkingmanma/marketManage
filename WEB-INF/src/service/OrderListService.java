package service;

import java.util.List;

import bean.OrderListBean;

public interface OrderListService 
{
	//根据orderlistid查找所有的订单详情
	List<OrderListBean> findByFormid(int orderformid) throws Exception;
	//增加订单详情
	void addOrderList(int orderformid, int commodityid, int amount);
	//更新订单详情
	void updateList(int orderlistid,int orderformid, int commodityid, int amount);
	//删除订单详情的方法
	void deleteList(int orderlistid);
}
