package hostelerp.com.model;

import java.io.*;
import java.util.*;

import hostelerp.com.model.*;

@SuppressWarnings("unused")
public class UserMenu
{
	private int id;
	private int userid;
	private int menu_id;
	private String save_access;
	private String edit_access;
	private String delete_access;
	private String menu_name;
	private String status;
	private String approvedType;
	private String manageType;

	public String getManageType()
	{
		return manageType;
	}

	public void setManageType(String manageType)
	{
		this.manageType = manageType;
	}

	public UserMenu()
	{

	}

	public String getMenu_name()
	{
		return menu_name;
	}

	public void setMenu_name(String menu_name)
	{
		this.menu_name = menu_name;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getUserid()
	{
		return userid;
	}

	public void setUserid(int userid)
	{
		this.userid = userid;
	}

	public int getMenu_id()
	{
		return menu_id;
	}

	public void setMenu_id(int menu_id)
	{
		this.menu_id = menu_id;
	}

	public String getSave_access()
	{
		return save_access;
	}

	public void setSave_access(String save_access)
	{
		this.save_access = save_access;
	}

	public String getEdit_access()
	{
		return edit_access;
	}

	public void setEdit_access(String edit_access)
	{
		this.edit_access = edit_access;
	}

	public String getDelete_access()
	{
		return delete_access;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getApprovedType()
	{
		return approvedType;
	}

	public void setApprovedType(String approvedType)
	{
		this.approvedType = approvedType;
	}

	public void setDelete_access(String delete_access)
	{
		this.delete_access = delete_access;
	}

	public UserMenu(String isApproved, String manageType, int menuId,
			String status, int userId)
	{
		this.userid = userId;
		this.manageType = manageType;
		this.menu_id = menuId;
		this.approvedType = isApproved;
		this.status = status;
	}

}
