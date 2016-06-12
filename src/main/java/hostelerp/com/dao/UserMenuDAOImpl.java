package hostelerp.com.dao;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import hostelerp.com.model.*;

@SuppressWarnings("unused")
public class UserMenuDAOImpl implements UserMenuDAO
{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	final String GET_USER_MENU =
			"SELECT m.menu_id, m.menu_name, IFNULL(um.save_access, 'norecord') AS save_access, "
					+ "IFNULL(um.edit_access, 'norecord') AS edit_access, IFNULL(um.delete_access, 'norecord') AS delete_access  FROM menu_mas m "
					+ "LEFT OUTER JOIN (SELECT menu_id, save_access,edit_access, delete_access FROM user_vs_menu WHERE userid =:userid)um "
					+ "ON m.menu_id = um.menu_id WHERE m.status =:status ORDER BY m.menu_id";
	final String ADD_USER_RIGHTS = "INSERT into user_vs_menu ";
	final String UPDATE_USER_RIGHTS = "UPDATE user_vs_menu set ";

	@Override
	public List<UserMenu> getUserMenu(int userId, String status)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("userid", userId);
		paramMap.put("status", status);
		return namedParameterJdbcTemplate.query(GET_USER_MENU, paramMap,
				new BeanPropertyRowMapper<UserMenu>(UserMenu.class));
	}

	@Override
	public void addUserMenuRights(UserMenu userMenu) throws Exception
	{
		Map paramMap = new HashMap();
		if (userMenu.getManageType().equals("save_access"))
		{
			if (userMenu.getStatus().equals("update"))
			{
				String query =
						"UPDATE user_vs_menu set save_access =:isApproved where userid =:userid AND menu_id =:menu_id";
				paramMap.put("userid", userMenu.getUserid());
				paramMap.put("menu_id", userMenu.getMenu_id());
				paramMap.put("isApproved", userMenu.getApprovedType());
				namedParameterJdbcTemplate.update(query, paramMap);
			}
			else
			{
				String query =
						"INSERT into user_vs_menu (menu_id, userid, save_access) values (:menu_id, :userid, :save_access)";
				paramMap.put("userid", userMenu.getUserid());
				paramMap.put("menu_id", userMenu.getMenu_id());
				paramMap.put("save_access", userMenu.getApprovedType());
				namedParameterJdbcTemplate.update(query, paramMap);
			}
		}
		if (userMenu.getManageType().equals("edit_access"))
		{
			if (userMenu.getStatus().equals("update"))
			{
				String query =
						"UPDATE user_vs_menu set edit_access =:isApproved where userid =:userid AND menu_id =:menu_id";
				paramMap.put("userid", userMenu.getUserid());
				paramMap.put("menu_id", userMenu.getMenu_id());
				paramMap.put("isApproved", userMenu.getApprovedType());
				namedParameterJdbcTemplate.update(query, paramMap);
			}
			else
			{
				String query =
						"INSERT into user_vs_menu (menu_id, userid, edit_access) values (:menu_id, :userid, :edit_access)";
				paramMap.put("userid", userMenu.getUserid());
				paramMap.put("menu_id", userMenu.getMenu_id());
				paramMap.put("edit_access", userMenu.getApprovedType());
				namedParameterJdbcTemplate.update(query, paramMap);
			}
		}
		if (userMenu.getManageType().equals("delete_access"))
		{
			if (userMenu.getStatus().equals("update"))
			{
				String query =
						"UPDATE user_vs_menu set delete_access =:isApproved where userid =:userid AND menu_id =:menu_id";
				paramMap.put("userid", userMenu.getUserid());
				paramMap.put("menu_id", userMenu.getMenu_id());
				paramMap.put("isApproved", userMenu.getApprovedType());
				namedParameterJdbcTemplate.update(query, paramMap);
			}
			else
			{
				String query =
						"INSERT into user_vs_menu (menu_id, userid, delete_access) values (:menu_id, :userid, :delete_access)";
				paramMap.put("userid", userMenu.getUserid());
				paramMap.put("menu_id", userMenu.getMenu_id());
				paramMap.put("delete_access", userMenu.getApprovedType());
				namedParameterJdbcTemplate.update(query, paramMap);
			}
		}

	}

}
