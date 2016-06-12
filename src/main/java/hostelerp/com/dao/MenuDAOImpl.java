package hostelerp.com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import hostelerp.com.model.Menu;

@SuppressWarnings(
{ "unchecked", "unused" })
public class MenuDAOImpl implements MenuDAO
{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	final String USER_ROLE_PROJECTMANAGER = "projectmanager";
	final String USER_ROLE_ADMIN = "admin";

	String GET_MENU_MASS = "SELECT ms.*,  "
			+ "IFNULL(save_access, 'no') AS  save_access, "
			+ "IFNULL(edit_access, 'no') AS  edit_access, "
			+ "IFNULL(delete_access, 'no') AS  delete_access " + "FROM "
			+ "menu_mas ms " + "LEFT OUTER JOIN "
			+ "(SELECT * FROM user_vs_menu WHERE userid =:userid) AS um "
			+ "ON " + "ms.menu_id = um.menu_id ";

	@Override
	public List<Menu> getMenus(String userRole, int userId) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("accessstatus", "yes");
		paramMap.put("userid", userId);
		String tempQuery = "";
		if (userRole.equals(USER_ROLE_PROJECTMANAGER))
		{
			tempQuery = " WHERE ms.status =:status ORDER BY slno,menu_id";
			tempQuery = GET_MENU_MASS + tempQuery;
			paramMap.put("status", "active");
		} else
		{
			tempQuery =
					" WHERE is_projectmanager_menu <>:status AND ms.status =:menustatus ORDER BY slno,menu_id";
			tempQuery = GET_MENU_MASS + tempQuery;
			paramMap.put("status", "yes");
			paramMap.put("menustatus", "active");
		}
		return namedParameterJdbcTemplate.query(tempQuery, paramMap,
				new BeanPropertyRowMapper(Menu.class));

	}

}
