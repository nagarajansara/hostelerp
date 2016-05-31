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

	String GET_MENU_MASS = "SELECT ms.*,  " + ":accessstatus AS  save_access, "
			+ " :accessstatus AS  edit_access, "
			+ " :accessstatus AS  delete_access " + "FROM " + "menu_mas ms "
			+ "LEFT OUTER JOIN " + "user_vs_menu um " + "ON "
			+ "ms.menu_id = um.menu_id ";

	@Override
	public List<Menu> getMenus(String userRole) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("accessstatus", "yes");
		String tempQuery = "";
		if (userRole.equals(USER_ROLE_PROJECTMANAGER))
		{
			tempQuery = " ORDER BY slno,menu_id";
			tempQuery = GET_MENU_MASS + tempQuery;
		} else
		{
			tempQuery =
					" WHERE is_projectmanager_menu <>:status ORDER BY slno,menu_id";
			tempQuery = GET_MENU_MASS + tempQuery;
			paramMap.put("status", "yes");
		}
		return namedParameterJdbcTemplate.query(tempQuery, paramMap,
				new BeanPropertyRowMapper(Menu.class));

	}

}
