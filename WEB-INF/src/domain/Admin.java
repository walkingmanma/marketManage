package domain;
/**
 * Admin是持久层关于管理员信息的实体类
 * @author Ma
 */
import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ADMINS",schema="SCOTT")  
public class Admin implements Serializable {
	//管理员编码号
	@Id
	@GeneratedValue(generator="adminPkGenerator")
	@GenericGenerator(name="adminPkGenerator",strategy="increment")
	private Integer adminid;
	
	//管理员用户名
	@Column(length=30,nullable=false)
	private String username;
	
	//管理员密码
	@Column(length=30,nullable=false)
	private String password;
	
	@Column
	private String sex;
	
	@Column
	private Date hireday;

	public Integer getAdminid() {
		return adminid;
	}

	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getHireday() {
		return hireday;
	}

	public void setHireday(Date hireday) {
		this.hireday = hireday;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hireday == null) ? 0 : hireday.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Admin)) {
			return false;
		}
		Admin other = (Admin) obj;
		if (hireday == null) {
			if (other.hireday != null) {
				return false;
			}
		} else if (!hireday.equals(other.hireday)) {
			return false;
		}
		if (sex == null) {
			if (other.sex != null) {
				return false;
			}
		} else if (!sex.equals(other.sex)) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}

	
	
	
	
}
