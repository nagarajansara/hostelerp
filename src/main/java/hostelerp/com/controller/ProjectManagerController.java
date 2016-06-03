package hostelerp.com.controller;

import hostelerp.com.datatable.CommonDataTableJsonObj;
import hostelerp.com.model.CityState;
import hostelerp.com.model.Menu;
import hostelerp.com.model.Student;
import hostelerp.com.model.Users;
import hostelerp.com.service.LoginService;
import hostelerp.com.service.ProjectManagerService;
import hostelerp.com.util.AppProp;
import hostelerp.com.util.ConstException;
import hostelerp.com.util.Response;
import hostelerp.com.util.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
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
			@RequestParam("usertype") String usertype,
			@RequestParam(value = "menuId", required = false,
					defaultValue = "0") int menuId, ModelMap model)
			throws Exception
	{
		try
		{
			if (menuId != 0)
				isMenuAccessDenied(menuId, Menu.SAVE_ACCESS, request);

			Users user = new Users(userName, password, firstname, usertype);
			projectManagerService.addUsers(user);
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("addUsers :" + ex.getMessage());
			if (ex.getMessage().indexOf("Duplicate entry") >= 0)
			{
				ConstException constException =
						new ConstException(
								ConstException.ERR_CODE_INVALID_LOGIN,
								ConstException.ERR_MSG_INVALID_LOGIN);
				utilities.setErrResponse(constException, response);
			} else
			{
				utilities.setErrResponse(ex, response);
			}

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

	// Add management users
	@RequestMapping(value = "/deleteUsers", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String deleteUsers(HttpServletRequest request,
			HttpServletResponse res, @RequestParam("userId") int userId,
			@RequestParam(value = "menuId", required = false,
					defaultValue = "0") int menuId, ModelMap model)
			throws Exception
	{
		String STATUS_INACTIVE = "inactive";
		try
		{
			if (menuId != 0)
				isMenuAccessDenied(menuId, Menu.DELETE_ACCESS, request);

			projectManagerService.deleteUsers(userId, STATUS_INACTIVE);
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("addUsers :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "users";
	}

	@RequestMapping(value = "/getUsersViaId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getUsersViaId(HttpServletRequest request,
			HttpServletResponse res, @RequestParam("userId") int userId,
			ModelMap model) throws Exception
	{
		try
		{
			List<Users> list = projectManagerService.getUsersViaId(userId);
			utilities.setSuccessResponse(response, list);
		} catch (Exception ex)
		{
			logger.error("getUsersViaId :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "users";
	}

	@RequestMapping(value = "/updateUsersViaId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String updateUsersViaId(HttpServletRequest request,
			HttpServletResponse res, @RequestParam("userId") int userId,
			@RequestParam("userName") String userName,
			@RequestParam("firstname") String firstname,
			@RequestParam("usertype") String usertype,
			@RequestParam(value = "menuId", required = false,
					defaultValue = "0") int menuId, ModelMap model)
			throws Exception
	{
		try
		{
			if (menuId != 0)
				isMenuAccessDenied(menuId, Menu.EDIT_ACCESS, request);

			Users users = new Users(userId, userName, firstname, usertype);
			projectManagerService.updateUsersViaId(users);
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("updateUsersViaId :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "users";
	}

	/****************************************** Student ***********************************************************/

	@RequestMapping(value = "/get_student", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String updateUsersViaId(HttpServletRequest request,
			HttpServletResponse res)
	{
		return "student";
	}

	@RequestMapping(value = "/addStudent", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String addStudent(HttpServletRequest request,
			HttpServletResponse res, @RequestParam("name") String name,
			@RequestParam("rollno") String rollno,
			@RequestParam("batch") String batch,
			@RequestParam("course") String course,
			@RequestParam("messtype") String messtype,
			@RequestParam("address") String address,
			@RequestParam("state") String state,
			@RequestParam("city") String city,
			@RequestParam("country") String country,
			@RequestParam("mobileno") String mobileno,
			@RequestParam(value = "menuId", required = false,
					defaultValue = "0") int menuId, ModelMap model)
			throws Exception
	{
		try
		{
			if (menuId != 0)
				isMenuAccessDenied(menuId, Menu.SAVE_ACCESS, request);

			Student student =
					new Student(name, rollno, batch, course, messtype, address,
							state, city, country, mobileno);
			projectManagerService.addStudent(student);
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("addStudent :" + ex.getMessage());
			if (ex.getMessage().indexOf("Duplicate entry") >= 0)
			{
				ConstException constException =
						new ConstException(
								ConstException.ERR_CODE_INVALID_LOGIN,
								ConstException.ERR_MSG_INVALID_LOGIN);
				utilities.setErrResponse(constException, response);
			} else
			{
				utilities.setErrResponse(ex, response);
			}

		}
		model.addAttribute("model", response);
		return "student";
	}

	@RequestMapping(value = "/getStudentsViaId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getStudentsViaId(HttpServletRequest request,
			HttpServletResponse res, @RequestParam("studentId") int studentId,
			@RequestParam(value = "menuId", required = false,
					defaultValue = "0") int menuId, ModelMap model)
			throws Exception
	{
		try
		{
			String status = "active";
			List<Student> list =
					projectManagerService.getStudentsViaId(studentId, status);
			utilities.setSuccessResponse(response, list);
		} catch (Exception ex)
		{
			logger.error("getStudentsViaId :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "student";
	}

	@RequestMapping(value = "/updateStudensViaId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String updateStudensViaId(HttpServletRequest request,
			HttpServletResponse res, @RequestParam("name") String name,
			@RequestParam("id") int id, @RequestParam("batch") String batch,
			@RequestParam("course") String course,
			@RequestParam("messtype") String messtype,
			@RequestParam("address") String address,
			@RequestParam("state") String state,
			@RequestParam("city") String city,
			@RequestParam("country") String country,
			@RequestParam("mobileno") String mobileno,
			@RequestParam(value = "menuId", required = false,
					defaultValue = "0") int menuId, ModelMap model)
			throws Exception
	{
		try
		{
			if (menuId != 0)
				isMenuAccessDenied(menuId, Menu.EDIT_ACCESS, request);

			Student student =
					new Student(name, batch, course, messtype, address, state,
							city, country, mobileno, id);
			projectManagerService.updateStudensViaId(student);
			utilities.setSuccessResponse(response);

		} catch (Exception ex)
		{
			logger.error("updateStudensViaId  :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "student";
	}

	@RequestMapping(value = "/deleteStudentViaId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String deleteStudentViaId(HttpServletRequest request,
			HttpServletResponse res, @RequestParam("studentId") int studentId,
			@RequestParam(value = "menuId", required = false,
					defaultValue = "0") int menuId, ModelMap model)
			throws Exception
	{
		try
		{

			if (menuId != 0)
				isMenuAccessDenied(menuId, Menu.DELETE_ACCESS, request);
			projectManagerService.deleteStudentViaId(studentId);
			utilities.setSuccessResponse(response);

		} catch (Exception ex)
		{
			logger.error("deleteStudentViaId :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "student";
	}

	// Add management users
	@RequestMapping(value = "/getStudent", method =
	{ RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	public @ResponseBody String getStudent(HttpServletRequest request,
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

			List<Student> studentList = new ArrayList<Student>();
			int numEntries = 0;

			if (null != searchParameter && !searchParameter.equals(""))
			{
				studentList =
						projectManagerService.getStudentViaSearchParam(
								startIndx, maxIndx, STATUS_ACTIVE,
								searchParameter);
				numEntries =
						projectManagerService
								.getStudentNumEntriesViaSearchParam(
										STATUS_ACTIVE, searchParameter);
			} else
			{
				studentList =
						projectManagerService.getStudent(startIndx, maxIndx,
								STATUS_ACTIVE);
				numEntries =
						projectManagerService
								.getStudentNumEntries(STATUS_ACTIVE);
			}

			CommonDataTableJsonObj<List<Student>> employeeCategoryJsonObj =
					new CommonDataTableJsonObj<List<Student>>();
			// Set Total display record
			employeeCategoryJsonObj.setiTotalDisplayRecords(numEntries);
			// Set Total record
			employeeCategoryJsonObj.setiTotalRecords(numEntries);
			employeeCategoryJsonObj.setAaData(studentList);
			json2 = gson.toJson(employeeCategoryJsonObj);

		} catch (Exception ex)
		{
			logger.error("getStudent :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute(json2);
		return json2;
	}

	@RequestMapping(value = "/getCityApi", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getCityApi(HttpServletRequest request, @RequestParam(
			value = "locationname") String locationname, ModelMap model)

	{
		Map<Object, Object> map = new HashMap<Object, Object>();
		JSONArray jSONArray = new JSONArray();
		boolean isCity = true;
		try
		{
			String status = "active";
			List<CityState> cityList =
					projectManagerService.getCityAndState(locationname, status,
							isCity);
			if (cityList != null && cityList.size() > 0)
			{
				for (int i = 0; i < cityList.size(); i++)
				{
					CityState city = (CityState) cityList.get(i);
					String cityName = (String) city.getName();

					int ID = (Integer) (city.getId());
					JSONObject jSONObject = new JSONObject();

					jSONObject.put("id", ID);
					jSONObject.put("text", cityName);
					jSONArray.put(jSONObject);

				}
				utilities.setSuccessResponse(response, jSONArray.toString());
			} else
			{
				throw new ConstException(ConstException.ERR_CODE_NO_DATA,
						ConstException.ERR_MSG_NO_DATA);
			}
		} catch (Exception ex)
		{
			logger.error("getCity :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", jSONArray.toString());
		return "student";
	}

	@RequestMapping(value = "/getStateApi", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getStateApi(HttpServletRequest request, @RequestParam(
			value = "locationname") String locationname, ModelMap model)

	{
		Map<Object, Object> map = new HashMap<Object, Object>();
		JSONArray jSONArray = new JSONArray();
		boolean isCity = false;
		try
		{
			String status = "active";
			List<CityState> cityList =
					projectManagerService.getCityAndState(locationname, status,
							isCity);
			if (cityList != null && cityList.size() > 0)
			{
				for (int i = 0; i < cityList.size(); i++)
				{
					CityState city = (CityState) cityList.get(i);
					String cityName = (String) city.getName();

					int ID = (Integer) (city.getId());
					JSONObject jSONObject = new JSONObject();

					jSONObject.put("id", ID);
					jSONObject.put("text", cityName);
					jSONArray.put(jSONObject);

				}
				utilities.setSuccessResponse(response, jSONArray.toString());
			} else
			{
				throw new ConstException(ConstException.ERR_CODE_NO_DATA,
						ConstException.ERR_MSG_NO_DATA);
			}
		} catch (Exception ex)
		{
			logger.error("getStateApi :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", jSONArray.toString());
		return "student";
	}

}
