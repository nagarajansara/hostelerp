package hostelerp.com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
@SuppressWarnings(
{ "unused", "unchecked" })
public class AdminController
{
	private static final Logger logger = Logger.getLogger(AdminController.class
			.getName());

	@RequestMapping(value = "/ping", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String ping(HttpServletRequest request, HttpServletResponse res,
			ModelMap model)
	{
		try
		{

		} catch (Exception ex)
		{
			logger.error("ping :" + ex.getMessage());

		}
		return "response";
	}

	@RequestMapping(value = "/get", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String get(HttpServletRequest request, HttpServletResponse res,
			ModelMap model)
	{
		try
		{

		} catch (Exception ex)
		{
			logger.error("get :" + ex.getMessage());

		}
		return "home";
	}

}
