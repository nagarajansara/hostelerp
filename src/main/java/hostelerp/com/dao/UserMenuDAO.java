package hostelerp.com.dao;

import java.io.*;
import java.util.*;

import hostelerp.com.model.*;

@SuppressWarnings("unused")
public interface UserMenuDAO
{
	List<UserMenu> getUserMenu(int userId, String status) throws Exception;

	void addUserMenuRights(UserMenu userMenu) throws Exception;
}
