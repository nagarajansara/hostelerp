package hostelerp.com.controller;

import hostelerp.com.datatable.CommonDataTableJsonObj;
import hostelerp.com.model.Users;
import hostelerp.com.service.LoginService;
import hostelerp.com.service.ProjectManagerService;
import hostelerp.com.util.AppProp;
import hostelerp.com.util.Response;
import hostelerp.com.util.Utilities;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/manager")
@SuppressWarnings(
{ "unused", "unchecked" })
public class ProjectManagerController extends BaseController
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

	@Autowired
	@Qualifier("projectManagerService")
	ProjectManagerService projectManagerService;

	@RequestMapping(value = "/ping", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String ping(HttpServletRequest request, HttpServletResponse res,
			ModelMap model) throws Exception
	{
		return "response";
	}

	@RequestMapping(value = "/users_index", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String usersIndex(HttpServletRequest request,
			HttpServletResponse res, ModelMap model) throws Exception
	{
		return "users";
	}

	// Add management users
	@RequestMapping(value = "/addUsers", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String addUsers(HttpServletRequest request, HttpServletResponse res,
			@RequestParam("userName") String userName,
			@RequestParam("password") String password,
			@RequestParam("firstname") String firstname,
			@RequestParam("usertype") String usertype, ModelMap model)
			throws Exception
	{
		try
		{
			Users user = new Users(userName, password, firstname, usertype);
			projectManagerService.addUsers(user);
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("addUsers :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "users";
	}

	// Add management users
	@RequestMapping(value = "/getUsers", method =
	{ RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	public @ResponseBody String getUsers(HttpServletRequest request,
			HttpServletResponse res, ModelMap model) throws Exception
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = "";
		String STATUS_ACTIVE = "active";
		try
		{
			Integer pageNumber = 0;
			if (null != request.getParameter("iDisplayStart"))
				pageNumber =
						(Integer.valueOf(request.getParameter("iDisplayStart")) / utilities
								.getDefaultMaxIndx()) + 1;

			// Fetch search parameter;
			String searchParameter = request.getParameter("sSearch");
			// Fetch Page display length
			Integer pageDisplayLength =
					Integer.valueOf(request.getParameter("iDisplayLength"));

			int startIndx = pageNumber - 1;
			int maxIndx = pageDisplayLength;

			startIndx = getStartIdx(startIndx, maxIndx);

			List<Users> usersList = new ArrayList<Users>();
			int numEntries = 0;

			if (null != searchParameter && !searchParameter.equals(""))
			{
				usersList =
						projectManagerService.getUsersViaSearchParam(startIndx,
								maxIndx, STATUS_ACTIVE, searchParameter);
				numEntries =
						projectManagerService.getUsersNumEntriesViaSearchParam(
								STATUS_ACTIVE, searchParameter);
			} else
			{
				usersList =
						projectManagerService.getUsers(startIndx, maxIndx,
								STATUS_ACTIVE);
				numEntries =
						projectManagerService.getUsersNumEntries(STATUS_ACTIVE);
			}

			CommonDataTableJsonObj<List<Users>> employeeCategoryJsonObj =
					new CommonDataTableJsonObj<List<Users>>();
			// Set Total display record
			employeeCategoryJsonObj.setiTotalDisplayRecords(numEntries);
			// Set Total record
			employeeCategoryJsonObj.setiTotalRecords(numEntries);
			employeeCategoryJsonObj.setAaData(usersList);
			json2 = gson.toJson(employeeCategoryJsonObj);

		} catch (Exception ex)
		{
			logger.error("getUsers :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute(json2);
		return json2;

	}
}
