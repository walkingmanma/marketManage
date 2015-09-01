package dao.Imp;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import dao.BaseDaoHibernate4;
import dao.OrderFormDao;
import domain.OrderForm;

public class OrderFormDaoImp extends BaseDaoHibernate4<OrderForm> implements
		OrderFormDao 
{

	@Override
	public void addOrderForm(OrderForm of) 
	{
		save(of);
	}

	@Override
	public List<OrderForm> findAllOrderForm() 
	{
		return findAll(OrderForm.class);
	}

	@Override
	public List<OrderForm> findBySubmittime(Date time) 
	{
		String submittime=time.toString();
		String hql="select en from "
				+ OrderForm.class.getSimpleName() + " as en"
				+ " where TO_CHAR(en.submittime,'YYYY-MM-DD') like " +"'%"+submittime+"%'" ;
		return find(hql);
		
	}

	@Override
	public List<OrderForm> findByPhone(String phone)
	{
		String hql="select en from "
				+ OrderForm.class.getSimpleName() + " as en"
				+ " inner join en.user u"
				+ " where u.phone like "+"'%"+phone+"%'"; 
		return find(hql);
	}

	@Override
	public List<OrderForm> findByTimePhone(Date time, String phone) 
	{
		String submittime=time.toString();
		String hql="select en from "
				+ OrderForm.class.getSimpleName() + " as en"
				+ " inner join en.user u"
				+ " where u.phone like "+"'%"+phone+"%'"
				+ " and TO_CHAR(en.submittime,'YYYY-MM-DD') like " +"'%"+submittime+"%'" ; 
		return find(hql);
	}
	
	public OrderForm findByid(int orderformid)
	{
		return get(OrderForm.class, orderformid);
	}
}
