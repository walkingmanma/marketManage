package action;

import java.io.BufferedReader;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import service.OrderFormService;
import bean.OrderFormBean;

import com.opensymphony.xwork2.ActionSupport;

public class OrderFormAction extends ActionSupport 
{
	//业务逻辑组件
	private OrderFormService orderformservice;
	//返回数据的封装
	private List<OrderFormBean> data;
	//参数值的封装
	private int userid;
	private Date submittime;
	private double totalprice;
	private double ispayoff;
	//返回响应状态的参数
	private boolean success;
	private String msg;
	//jsonString params
	private String params;
	
	//getter/setter方法
	public void setParams(String params)
	{
		this.params=params;
	}
	public String getParams()
	{
		return this.params;
	}
	public List<OrderFormBean> getData()
	{
		return this.data;
	}
	public void setData(List<OrderFormBean> data) 
	{
		this.data = data;
	}
	public void setOrderformservice(OrderFormService orderformservice) 
	{
		this.orderformservice = orderformservice;
	}
	public void setUserid(int userid) 
	{
		this.userid = userid;
	}
	public void setSubmittime(Date submittime) 
	{
		this.submittime = submittime;
	}
	public void setTotalprice(double totalprice) 
	{
		this.totalprice = totalprice;
	}
	public void setIspayoff(double ispayoff) 
	{
		this.ispayoff = ispayoff;
	}
	
	public boolean isSuccess() 
	{
		return success;
	}
	public void setSuccess(boolean success) 
	{
		this.success = success;
	}
	public String getMsg() 
	{
		return msg;
	}
	public void setMsg(String msg) 
	{
		this.msg = msg;
	}
	
	//若浏览器传入数据为json格式，gerJsonString()为读取json字符串方法
	private String getJsonString()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		StringBuffer jsonString=new StringBuffer();
		char[] ch=new char[2048];
		int length=-1;
		try
		{
			BufferedReader br=request.getReader();
			while((length=br.read(ch))!= -1)
			{
				jsonString.append(new String(ch, 0, length));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return jsonString.toString();
	}
	
	//添加订单
	public String addOrderForm() throws Exception
	{
		orderformservice.addOrderForm(userid, submittime, totalprice, ispayoff);
		this.success=true;
		this.msg="添加成功";
		return SUCCESS;
	}
	
	//查询订单
	public String findOrderForm() throws Exception
	{
		data=orderformservice.findByTimeOPhone(submittime, userid);
		this.success=true;
		this.msg="查询完成";
		return SUCCESS;
	}
	
	//更新订单
	public String updateOrderForm() throws Exception
	{
		
		params=getJsonString();
		JSONObject jsonObject = JSONObject.fromObject(params);
		int id=jsonObject.getInt("orderformid");
		double payoff=jsonObject.getDouble("ispayoff");
		orderformservice.updateOrderForm(id, payoff);
		this.success=true;
		this.msg="更新完成";
		return SUCCESS;
	}
}
