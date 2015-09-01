package action;

import java.io.BufferedReader;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;

import service.OrderListService;
import bean.OrderListBean;

import com.opensymphony.xwork2.ActionSupport;

public class OrderListAction extends ActionSupport
{
	//业务逻辑组件
	private OrderListService orderlistservice;
	//封装bean
	List<OrderListBean> data;
	//封装json格式的参数
	private String params;
	//封装响应状态的参数
	private boolean success;
	private String msg;
	
	
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
	public void setOrderlistservice(OrderListService orderlistservice)
	{
		this.orderlistservice = orderlistservice;
	}
	public List<OrderListBean> getData() 
	{
		return data;
	}
	public void setData(List<OrderListBean> data)
	{
		this.data = data;
	}
	public String getParams()
	{
		return params;
	}
	public void setParams(String params) 
	{
		this.params = params;
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
		
		//新增订单详情信息的方法
	public String addOrderList() throws Exception
		{
			//获取json数据
			params=getJsonString();
			JSONArray jsonArray=null;
			try
			{
				jsonArray=JSONArray.fromObject(params);
				for(int i=0;i<jsonArray.size();i++)
				{
					JSONObject jsonobject=jsonArray.getJSONObject(i);
					int commodityid=jsonobject.getInt("commodityid");
					int orderformid=jsonobject.getInt("orderformid");
					int amount=jsonobject.getInt("amount");
					orderlistservice.addOrderList(orderformid, commodityid, amount);
				}
			}catch(JSONException e)
			{
				e.printStackTrace();
			}
			
			
			this.success=true;
			this.msg="新增成功";
			return SUCCESS;
		}
}
