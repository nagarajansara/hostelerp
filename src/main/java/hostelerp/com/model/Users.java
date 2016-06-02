package hostelerp.com.model;

import java.io.Serializable;

public class Users implements Serializable
{
	private int userid;
	private String username;
	private String password;
	private String firstname;
	private String usertype;
	private String status;
	private String editBtn;

	public String getEditBtn()
	{
		return editBtn;
	}

	public void setEditBtn(String editBtn)
	{
		this.editBtn = editBtn;
	}

	public int getUserid()
	{
		return userid;
	}

	public void setUserid(int userid)
	{
		this.userid = userid;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public String getUsertype()
	{
		return usertype;
	}

	public void setUsertype(String usertype)
	{
		this.usertype = usertype;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getCreated_on()
	{
		return created_on;
	}

	public void setCreated_on(String created_on)
	{
		this.created_on = created_on;
	}

	private String created_on;

	public Users()
	{

	}

	public Users(String username, String password, String firstname,
			String usertype)
	{
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.usertype = usertype;
	}

	public Users(int userId, String username, String firstname, String usertype)
	{
		this.userid = userId;
		this.username = username;
		this.firstname = firstname;
		this.usertype = usertype;
	}

}
