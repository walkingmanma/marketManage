package action;

import java.io.BufferedReader;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import service.CommodityClassService;
import bean.CommodityClassBean;

import com.opensymphony.xwork2.ActionSupport;



public class CommodityClassAction extends ActionSupport 
{
	//业务逻辑组件
	private CommodityClassService commodityClassService;
	//封装CommodityClassBean
	private List<CommodityClassBean> data=new ArrayList<CommodityClassBean>();
	//保存服务器反馈结果
	private boolean success;
	//保存服务器反馈信息
	private String msg;
	//保存客户端传入的当前页面信息
	private int page;
	//保存客户端传入的页面显示条目信息
	private int limit;
	//保存服务器查询反馈的total条目
	private int total;
	//保存服务器得到的来自客户端的json字符串
	private String params;
	//保存传入的种类姓名参数，用于按名称查询
	private String name;
	
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setParams(String params)
	{
		this.params=params;
	}
	
	public List<CommodityClassBean> getData() {
		return data;
	}
	public void setData(List<CommodityClassBean> data) {
		this.data = data;
	}
	
	public String getMsg() 
	{
		return msg;
	}
	
	public int getTotal() 
	{
		return total;
	}
	public void setTotal(int total)
	{
		this.total = total;
	}
	
	public void setPage(int page) 
	{
		this.page = page;
	}
	
	public void setLimit(int limit) 
	{
		this.limit = limit;
	}
	
	public boolean getSuccess()
	{
		return this.success;
	}
	
	public void setCommodityClassService(
			CommodityClassService commodityClassService) 
	{
		this.commodityClassService = commodityClassService;
	}
	
	//查询所有商品种类信息分页显示
	public String allTypes() throws Exception{
		this.total=commodityClassService.findAllTypes().size();
		data=commodityClassService.findAllTypeByPaging(page, limit);
		if(data.size()>0)
		{
			this.success=true;
			this.msg="查询成功";
		}else
		{
			this.success=false;
			this.msg="抱歉，没有查询的数据";
		}
		return SUCCESS;
	}
	
	//按照商品名称查询商品种类信息分页显示
	public String allTypesByName() throws Exception
	{
		data=commodityClassService.findTypeByName(name);
		this.total=data.size();
		if(data.size()>0)
		{
			this.success=true;
			this.msg="查询成功";
		}else
		{
			this.success=false;
			this.msg="抱歉，没有查到需要的数据";
		}
		return SUCCESS;
	}
	
	//更新商品信息
	public String updateType() throws Exception
	{
		
		params=getJsonString();
		JSONObject jsonObject = JSONObject.fromObject(params);
		int typeid=jsonObject.getInt("typeid");
		String typename=jsonObject.getString("typename");
		String typedesc=jsonObject.getString("typedesc");
		commodityClassService.updateType(typeid, typename, typedesc);
		this.success=true;
		this.msg="修改成功";
		return SUCCESS;
	}
	
	//新增产品种类信息的方法
	public String addType() throws Exception
	{
		params=getJsonString();
		JSONObject jsonObject = JSONObject.fromObject(params);
		String typename=jsonObject.getString("typename");
		String typedesc=jsonObject.getString("typedesc");
		commodityClassService.addType(typename,typedesc);
		this.success=true;
		this.msg="新增成功";
		return SUCCESS;
	}
	
	//删除商品信息
	public String deleteType() throws Exception
	{
		params=getJsonString();
		JSONObject jsonObject = JSONObject.fromObject(params);
		String typename=jsonObject.getString("typename");
		int typeid=jsonObject.getInt("typeid");
		commodityClassService.deleteTypes(typeid, typename);
		this.success=true;
		this.msg="删除成功";
		return SUCCESS;
	}
	public String execute() throws Exception
	{
		this.success=true;
		return SUCCESS;
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
}
