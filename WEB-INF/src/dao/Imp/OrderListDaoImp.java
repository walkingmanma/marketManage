package dao.Imp;

import java.util.List;

import dao.BaseDaoHibernate4;
import dao.OrderListDao;
import domain.OrderList;

public class OrderListDaoImp extends BaseDaoHibernate4<OrderList> implements
		OrderListDao 
{
	@Override
	public List<OrderList> findListByformid(int orderformid) 
	{
		String hql="select en from "
				+ OrderList.class.getSimpleName() + " as en"
				+ " inner join en.orderform of "
				+ " where of.orderformid= "+ orderformid;
			return find(hql);		 
	}

	@Override
	public void addList(OrderList list) 
	{
		save(list);
	}

	@Override
	public void updateList(OrderList list) 
	{
		update(list);
	}

	@Override
	public void deleteList(OrderList list) 
	{
		delete(list);
	}

}
