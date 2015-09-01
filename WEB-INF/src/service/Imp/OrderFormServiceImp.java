package service.Imp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.ObjectNotFoundException;

import bean.OrderFormBean;
import bean.UserBean;
import dao.OrderFormDao;
import dao.UserDao;
import domain.OrderForm;
import domain.User;
import service.OrderFormService;

public class OrderFormServiceImp implements OrderFormService
{

	private OrderFormDao orderformdaoimp;
	private UserDao userdaoimp;
	
	
	public UserDao getUserdaoimp()
	{
		return userdaoimp;
	}
	public void setUserdaoimp(UserDao userdaoimp) 
	{
		this.userdaoimp = userdaoimp;
	}
	public OrderFormDao getOrderformdaoimp() 
	{
		return orderformdaoimp;
	}
	public void setOrderformdaoimp(OrderFormDao orderformdaoimp) 
	{
		this.orderformdaoimp = orderformdaoimp;
	}

	@Override
	public void addOrderForm(int userid, Date submittime, double totalprice,
			double ispayoff) 
	{
		OrderForm of=new OrderForm();
		of.setIspayoff(ispayoff);
		of.setSubmittime(submittime);
		of.setTotalprice(totalprice);
		of.setUser(userdaoimp.findById(userid));
		orderformdaoimp.addOrderForm(of);
	}
	@Override
	public List<OrderFormBean> findByTimeOPhone(Date time, int userid) throws Exception 
	{
		List<OrderFormBean> list=new ArrayList<OrderFormBean>();
		List<OrderForm> result=new ArrayList<OrderForm>();
		try
		{
			User user=userdaoimp.findById(userid);
			String phone=user.getPhone();
			if((time == null)) //名称
			{
				result=orderformdaoimp.findByPhone(phone);
			}else if(time != null)
			{
				result=orderformdaoimp.findByTimePhone(time, phone);
			}
		}catch(ObjectNotFoundException e)
		{
			if((time == null))
			{
				result=orderformdaoimp.findAllOrderForm();
			}else if(time != null)
			{
				result=orderformdaoimp.findBySubmittime(time);
			}
		};
		
		if(result.size()>0)
		{
			for (OrderForm u : result) 
			{
				list.add(new OrderFormBean(u.getOrderformid(), u.getUser().getName()
						, u.getSubmittime(), u.getTotalprice(), u.getIspayoff()));
			}
		}else
		{
			throw new Exception("没有查询到数据");
		};
		return list;
	}
	@Override
	public void updateOrderForm(int orderformid, double ispayoff)
	{
		OrderForm of=orderformdaoimp.findByid(orderformid);
		of.setIspayoff(ispayoff);
		orderformdaoimp.update(of);
	}
	
}
