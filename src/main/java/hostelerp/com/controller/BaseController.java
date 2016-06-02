package hostelerp.com.controller;

import hostelerp.com.model.Login;
import hostelerp.com.model.Menu;
import hostelerp.com.util.ConstException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SuppressWarnings(
{ "unchecked", "unused" })
public class BaseController
{

	public final String ATTR_USER_ID = "userId";
	public final String ATTR_FNAME = "fName";
	public final String ATTR_LNAME = "lName";
	public final String ATTR_EMAIL = "email";
	public final String ATTR_NAME = "username";
	public final String ATTR_USER_TYPE = "usertype";
	public final String ATTR_ROLE = "role";
	public final String ATTR_EMAIL_VERIFIED = "emailverified";
	public final String ATTR_PROFILE_IMAGE = "profileimage";
	public final String ATTR_COUNTRY = "country";
	public final String ATTR_MOBILE_NO = "mobileno";
	public final String ATTR_MENU = "menulist";

	public int getUserId(HttpServletRequest request) throws ConstException
	{
		int userId = 0;
		HttpSession session = request.getSession();
		if (session != null)
		{
			Object obj = session.getAttribute(ATTR_USER_ID);
			if (obj != null)
			{
				userId = ((Integer) obj).intValue();
			}
		}

		if (userId == 0)
		{
			throw new ConstException(ConstException.ERR_CODE_INVALID_LOGIN,
					ConstException.ERR_MSG_SESSION_EXP);
		}
		return userId;
	}

	public int getUserId_WHOUT_Exception(HttpServletRequest request)
			throws ConstException
	{
		int userId = 0;
		HttpSession session = request.getSession();
		if (session != null)
		{
			Object obj = session.getAttribute(ATTR_USER_ID);
			if (obj != null)
			{
				userId = ((Integer) obj).intValue();
			}
		}
		return userId;
	}

	public String getSessionAttr(HttpServletRequest request, String attr)
	{
		String val = null;
		HttpSession session = request.getSession();
		if (session != null)
		{
			Object obj = session.getAttribute(attr);
			if (obj != null)
			{
				val = ((String) obj);
			}
		}
		return val;
	}

	public Object getSessionAttrMenu(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Object obj = null;
		if (session != null)
		{
			obj = session.getAttribute(ATTR_MENU);
		}
		return obj;
	}

	public String getRole(HttpServletRequest request) throws ConstException
	{
		String role = null;
		HttpSession session = request.getSession();
		if (session != null)
		{
			Object obj = session.getAttribute(ATTR_ROLE);
			if (obj != null)
			{
				role = (String) obj;
			}
		}

		if (role == null)
		{
			throw new ConstException(ConstException.ERR_CODE_INVALID_LOGIN,
					ConstException.ERR_MSG_SESSION_EXP);
		}
		return role;
	}

	public void setUserSession(HttpServletRequest request, Login user)
			throws ConstException
	{
		if (user != null && user.getUserid() > 0)
		{
			HttpSession session = request.getSession();
			session.setAttribute(ATTR_USER_ID, user.getUserid());
			session.setAttribute(ATTR_NAME, user.getUsername());
			session.setAttribute(ATTR_FNAME, user.getFirstname());
			session.setAttribute(ATTR_USER_TYPE, user.getUsertype());

		}
	}

	public void setUserSessionMenuMas(HttpServletRequest request,
			List<Menu> menu) throws ConstException
	{
		if (menu != null && menu.size() > 0)
		{
			HttpSession session = request.getSession();
			session.setAttribute(ATTR_MENU, menu);
		}
	}

	public int getStartIdx(int currPage, int maxEntriesPerPage)
			throws ConstException
	{
		return (currPage * maxEntriesPerPage);
	}

	public void destroySession(HttpServletRequest request)
			throws ConstException
	{
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			session.invalidate();
		}
	}

	public boolean isMenuAccessDenied(int menuId, String accessType,
			HttpServletRequest request) throws Exception
	{
		boolean isAccessDined = false;
		try
		{
			List<Menu> menu = (List<Menu>) getSessionAttrMenu(request);
			if (menu != null && menu.size() > 0)
			{
				for (int i = 0; i < menu.size(); i++)
				{
					Menu tempMenu = (Menu) menu.get(i);
					if (tempMenu.getMenu_id() == menuId)
					{
						if (Menu.SAVE_ACCESS.equals(accessType))
						{
							if (tempMenu.getSave_access().equals("no"))
							{
								throw new ConstException(
										ConstException.ERR_CODE_ACCESS_DENIED,
										ConstException.ERR_MSG_ACCESS_DENIED);
							} else
							{

							}
						}
						if (Menu.EDIT_ACCESS.equals(accessType))
						{
							if (tempMenu.getEdit_access().equals("no"))
							{
								throw new ConstException(
										ConstException.ERR_CODE_ACCESS_DENIED,
										ConstException.ERR_MSG_ACCESS_DENIED);
							}

						}
						if (Menu.DELETE_ACCESS.equals(accessType))
						{
							if (tempMenu.getDelete_access().equals("no"))
							{
								throw new ConstException(
										ConstException.ERR_CODE_ACCESS_DENIED,
										ConstException.ERR_MSG_ACCESS_DENIED);
							}
						}
					}
				}
			}
		} catch (Exception ex)
		{
			throw ex;
		}
		return isAccessDined;
	}
}
