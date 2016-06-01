package hostelerp.com.dao;

import java.util.List;

import hostelerp.com.model.*;

public interface MenuDAO
{
	List<Menu> getMenus(String userRole, int userId) throws Exception;

}
