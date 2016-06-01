package hostelerp.com.service;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

import hostelerp.com.util.*;
import hostelerp.com.model.*;

@SuppressWarnings(
{ "unused", "unchecked" })
public interface LoginService
{

	List<Login> validate(String email, String password, String sTATUS_ACTIVE)
			throws Exception;

	List<Menu> getMenus(String userRole, int userId) throws Exception;

}
