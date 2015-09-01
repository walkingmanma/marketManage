package action;

import java.sql.Date;
import java.util.List;

import service.UserService;
import bean.UserBean;

import com.opensymphony.xwork2.ActionSupport;

import dao.Imp.UserDaoImp;

public class UserAction extends ActionSupport 
{
	//业务逻辑组件
	private UserService userService;
	//封装查询结果集
	private List<UserBean> data;
	//以下封装用户信息数据
	private int userid;
	private String username;
	private String password;
	private String name;
	private String sex;
	private String address;
	private String postaddress;
	private String email;
	private String phone;
	private Date tagtime;
	//以下封装相关前端反馈信息内容
	private String msg;
	private boolean success;
	private int  total;
	private String query;
	//以下封装分页参数
	private int page;
	private int limit;
	//相关属性的getter、setter方法
	public void setUserService(UserService userService) 
	{
		this.userService = userService;
	}

	public void setData(List<UserBean> data) 
	{
		this.data = data;
	}
	public List<UserBean> getData()
	{
		return this.data;
	}

	public void setUserid(int userid)
	{
		this.userid = userid;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}
	public String getSex()
	{
		return this.sex;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public void setPostaddress(String postaddress) 
	{
		this.postaddress = postaddress;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public void setTagtime(Date tagtime) 
	{
		this.tagtime = tagtime;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}
	public String getMsg()
	{
		return this.msg;
	}

	public void setSuccess(boolean success) 
	{
		this.success = success;
	}
	public boolean getSuccess()
	{
		return this.success;
	}


	public void setTotal(int total) 
	{
		this.total = total;
	}
	public int getTotal()
	{
		return this.total;
	}
	
	public void setPage(int page) 
	{
		this.page = page;
	}

	public void setLimit(int limit)
	{
		this.limit = limit;
	}

    public void setQuery(String query)
    {
    	this.query=query;
    }
    
	//查询用户相关信息，根据用户名/电话号码
	public String findUserByNameOPhone() throws Exception
	{
		data=userService.findByNamePhone(name, phone, page, limit);
		this.total=data.size();
		this.success=true;
		this.msg="查询完成";
		return SUCCESS;
	}
	
	//添加用户信息
	public String addUser() throws Exception
	{
		userService.addUser(username, password, name
				, sex, address, postaddress
				, phone, email, tagtime);
		this.success=true;
		this.msg="新增完成";
		return SUCCESS;
	}
	
	//修改用户信息
	public String updateUser() throws Exception
	{
		userService.updateUser(userid, username, password
				, name, sex, address
				, postaddress, phone, email
				, tagtime);
		this.success=true;
		this.msg="修改成功";
		return SUCCESS;
	}
	
}
