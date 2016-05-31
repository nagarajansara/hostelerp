package hostelerp.com.controller;

import hostelerp.com.model.Login;
import hostelerp.com.model.Menu;
import hostelerp.com.service.LoginService;
import hostelerp.com.util.AppProp;
import hostelerp.com.util.ConstException;
import hostelerp.com.util.Response;
import hostelerp.com.util.Utilities;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
@SuppressWarnings(
{ "unused", "unchecked" })
public class LoginController extends BaseController
{

	private static final Logger logger = Logger.getLogger(LoginController.class
			.getName());

	@Autowired
	@Qualifier("loginService")
	LoginService loginService;

	@Autowired
	@Qualifier("utilities")
	Utilities utilities;

	@Autowired
	@Qualifier("response")
	Response response;

	@Autowired
	@Qualifier("appProp")
	AppProp appProp;

	@RequestMapping(value = "/ping", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String ping(HttpServletRequest request, HttpServletResponse res,
			ModelMap model) throws Exception
	{
		return "response";
	}

	@RequestMapping(value = "/getlogin", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getlogin(HttpServletRequest request, HttpServletResponse res,
			ModelMap model) throws Exception
	{
		try
		{
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("getlogin :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		return "login";
	}

	@RequestMapping(value = "/validate", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String validate(HttpServletRequest request, HttpServletResponse res,
			@RequestParam("userName") String email,
			@RequestParam("password") String password, ModelMap model)
			throws Exception
	{
		try
		{

			String STATUS_ACTIVE = "active";
			{
				List<Login> list =
						loginService.validate(email, password, STATUS_ACTIVE);
				if (list != null && list.size() > 0)
				{
					Login login = (Login) list.get(0);
					setUserSession(request, login);
					List<Menu> menuList =
							loginService.getMenus(login.getUsertype());
					setUserSessionMenuMas(request, menuList);
					utilities.setSuccessResponse(response);
				} else
				{
					throw new ConstException("Invalid Login");
				}
			}

		} catch (Exception ex)
		{
			logger.error("validate :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "home";
	}

	@RequestMapping(value = "/logout", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpServletRequest request, HttpServletResponse res,
			ModelMap model) throws Exception
	{
		try
		{
			HttpSession session = request.getSession(false);
			session.invalidate();
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("logout :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "login";
	}

}
