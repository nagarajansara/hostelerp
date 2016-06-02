package hostelerp.com.model;

import java.io.Serializable;

public class Menu implements Serializable
{
	private int menu_id;
	private String menu_name;
	private int menu_level;
	private int slno;
	private String is_parent;
	private int parent_id;
	private String menu_url;
	private String menu_icon;
	private String is_admin_menu;
	private String is_projectmanager_menu;
	private String status;

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

	public void setDelete_access(String delete_access)
	{
		this.delete_access = delete_access;
	}

	private String save_access;
	private String edit_access;
	private String delete_access;


	public final static String SAVE_ACCESS = "save_access";
	public final static String EDIT_ACCESS = "edit_access";
	public final static String DELETE_ACCESS = "delete_access";

	public Menu()
	{

	}

	public int getMenu_id()
	{
		return menu_id;
	}

	public void setMenu_id(int menu_id)
	{
		this.menu_id = menu_id;
	}

	public String getMenu_name()
	{
		return menu_name;
	}

	public void setMenu_name(String menu_name)
	{
		this.menu_name = menu_name;
	}

	public int getMenu_level()
	{
		return menu_level;
	}

	public void setMenu_level(int menu_level)
	{
		this.menu_level = menu_level;
	}

	public int getSlno()
	{
		return slno;
	}

	public void setSlno(int slno)
	{
		this.slno = slno;
	}

	public String getIs_parent()
	{
		return is_parent;
	}

	public void setIs_parent(String is_parent)
	{
		this.is_parent = is_parent;
	}

	public int getParent_id()
	{
		return parent_id;
	}

	public void setParent_id(int parent_id)
	{
		this.parent_id = parent_id;
	}

	public String getMenu_url()
	{
		return menu_url;
	}

	public void setMenu_url(String menu_url)
	{
		this.menu_url = menu_url;
	}

	public String getMenu_icon()
	{
		return menu_icon;
	}

	public void setMenu_icon(String menu_icon)
	{
		this.menu_icon = menu_icon;
	}

	public String getIs_admin_menu()
	{
		return is_admin_menu;
	}

	public void setIs_admin_menu(String is_admin_menu)
	{
		this.is_admin_menu = is_admin_menu;
	}

	public String getIs_projectmanager_menu()
	{
		return is_projectmanager_menu;
	}

	public void setIs_projectmanager_menu(String is_projectmanager_menu)
	{
		this.is_projectmanager_menu = is_projectmanager_menu;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}


}
