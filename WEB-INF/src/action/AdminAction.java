package action;

import java.io.BufferedReader;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import service.AdminService;
import bean.AdminBean;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import domain.Admin;

public class AdminAction extends ActionSupport
{
	//封装业务逻辑组件
	private AdminService adminService;
	private List<AdminBean> data;
	//保存服务器得到的来自客户端的json字符串
	private String params;
	//保存传入的种类姓名参数，用于按名称查询
	private int adminid;
	private String username;
	private String password;
	private String sex;
	private Date hireday;
	//封装反馈数据的属性
	private boolean success;
	private String msg;
	public List<AdminBean> getData()
	{
		return data;
	}
	public boolean isSuccess()
	{
		return success;
	}
	public String getMsg() 
	{
		return msg;
	}
	public void setAdminService(AdminService adminService)
	{
		this.adminService = adminService;
	}
	
	public String getParams() 
	{
		return params;
	}
	public void setParams(String params) 
	{
		this.params = params;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String name) 
	{
		this.username = name;
	}
	
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public String getSex() 
	{
		return sex;
	}
	public void setSex(String sex) 
	{
		this.sex = sex;
	}
	public Date getHireday() 
	{
		return hireday;
	}
	public void setHireday(Date hireday) 
	{
		this.hireday = hireday;
	}
	public void setAdminid(int adminid)
	{
		this.adminid=adminid;
	}
	public int getAdminid()
	{
		return this.adminid;
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
	//登录验证的方法
	public String login() throws Exception
	{
		boolean out=adminService.isAdmin(username, password);
		if(out)
		{
			this.success=true;
			this.msg="您已成功登录";
			return Action.SUCCESS;
		}else
		{
			this.success=false;
			this.msg="用户名和密码不匹配";
			return Action.SUCCESS;
		}
	}
	
	//注册的方法
	public  String addAdmin() throws Exception
	{
		adminService.addAdmin(username, sex, hireday, password);
		this.success=true;
		this.msg="添加成功";
		return SUCCESS;
	}
	
	//按名称查找员工信息
	public  String findadminByName() throws Exception
	{
		data=adminService.findByName(username);
		this.success=true;
		this.msg="添加成功";
		return SUCCESS;
	}
	
	//查找所有的员工信息
	public  String findAlladmin() throws Exception
	{
		data=adminService.findallAdmin();
		this.success=true;
		this.msg="添加成功";
		return SUCCESS;
	}
	
	//更新员工信息
	public String updateAdmin() throws Exception
	{
		adminService.updateAdmin(adminid, username, sex, hireday, password);
		this.success=true;
		this.msg="更新成功";
		return SUCCESS;
	}
	
	//删除员工信息
	public String deleteAdmin() throws Exception
		{
			adminService.deleteAdmin(adminid);
			this.success=true;
			this.msg="删除成功";
			return SUCCESS;
		}
}
