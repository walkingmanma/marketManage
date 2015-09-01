package service;

import java.sql.Date;
import java.util.List;

import domain.OrderForm;
import bean.OrderFormBean;


public interface OrderFormService 
{
	//增加orderform的方法
	void addOrderForm(int userid,Date submittime,double totalprice,double ispayoff);
	//根据电话号码或者日期查找订单
	List<OrderFormBean> findByTimeOPhone(Date time,int userid) throws Exception;
	//更新订单状况
	void updateOrderForm(int orderform,double ispayoff);
}
