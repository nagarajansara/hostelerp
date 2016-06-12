package hostelerp.com.controller;

import hostelerp.com.datatable.CommonDataTableJsonObj;
import hostelerp.com.model.Block;
import hostelerp.com.model.CityState;
import hostelerp.com.model.Hostel;
import hostelerp.com.model.Menu;
import hostelerp.com.model.Student;
import hostelerp.com.model.UserMenu;
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

import org.apache.catalina.Host;
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
								ConstException.ERR_CODE_DUPLICATE_ENTERY,
								ConstException.ERR_MSG_DUPLICATE_ENTERY);
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
		return "studentdetailspage";
	}

	@RequestMapping(value = "/updateStudensViaId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String updateStudensViaId(HttpServletRequest request,
			HttpServletResponse res, @RequestParam("name") String name,
			@RequestParam("id") int id, @RequestParam("batch") String batch,
			@RequestParam("rollno") String rollno,
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
							city, country, mobileno, id, rollno);
			projectManagerService.updateStudensViaId(student);
			utilities.setSuccessResponse(response);

		} catch (Exception ex)
		{
			logger.error("updateStudensViaId  :" + ex.getMessage());
			if (ex.getMessage().indexOf("Duplicate entry") >= 0)
			{
				ConstException constException =
						new ConstException(
								ConstException.ERR_CODE_DUPLICATE_ENTERY,
								ConstException.ERR_MSG_DUPLICATE_ENTERY);
				utilities.setErrResponse(constException, response);
			} else
			{
				utilities.setErrResponse(ex, response);
			}
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

	// Add management users
	@RequestMapping(value = "/getHostels", method =
	{ RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	public @ResponseBody String getHostels(HttpServletRequest request,
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

			List<Hostel> hostelList = new ArrayList<Hostel>();
			int numEntries = 0;

			if (null != searchParameter && !searchParameter.equals(""))
			{
				hostelList =
						projectManagerService.getHostelsViaSearchParam(
								startIndx, maxIndx, STATUS_ACTIVE,
								searchParameter);
				numEntries =
						projectManagerService
								.getHostelsNumEntriesViaSearchParam(
										STATUS_ACTIVE, searchParameter);
			} else
			{
				hostelList =
						projectManagerService.getHostels(startIndx, maxIndx,
								STATUS_ACTIVE);
				numEntries =
						projectManagerService
								.getHostelsNumEntries(STATUS_ACTIVE);
			}

			CommonDataTableJsonObj<List<Hostel>> employeeCategoryJsonObj =
					new CommonDataTableJsonObj<List<Hostel>>();
			// Set Total display record
			employeeCategoryJsonObj.setiTotalDisplayRecords(numEntries);
			// Set Total record
			employeeCategoryJsonObj.setiTotalRecords(numEntries);
			employeeCategoryJsonObj.setAaData(hostelList);
			json2 = gson.toJson(employeeCategoryJsonObj);

		} catch (Exception ex)
		{
			logger.error("getHostels :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute(json2);
		return json2;
	}

	@RequestMapping(value = "/get_hostel", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public
			String get_hostel(HttpServletRequest request,
					HttpServletResponse res)
	{
		return "hostel";
	}

	@RequestMapping(value = "/getCollegeNameApi", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getCollegeNameApi(HttpServletRequest request, @RequestParam(
			value = "locationname") String locationname, ModelMap model)

	{
		Map<Object, Object> map = new HashMap<Object, Object>();
		JSONArray jSONArray = new JSONArray();
		boolean isCity = true;
		try
		{
			String status = "active";
			List<Hostel> cityList =
					projectManagerService.getCollegeNameApi(locationname,
							status);
			if (cityList != null && cityList.size() > 0)
			{
				for (int i = 0; i < cityList.size(); i++)
				{
					Hostel city = (Hostel) cityList.get(i);
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
			logger.error("getCollegeNameApi :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", jSONArray.toString());
		return "hostel";
	}

	@RequestMapping(value = "/addHostel", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String addHostel(HttpServletRequest request,
			HttpServletResponse res, @RequestParam("name") String name,
			@RequestParam("collegename") String collegename,
			@RequestParam("mobileno") String mobileno,
			@RequestParam("address") String address,
			@RequestParam("city") String city,
			@RequestParam("state") String state,
			@RequestParam("country") String country,
			@RequestParam(value = "menuId", required = false,
					defaultValue = "0") int menuId, ModelMap model)
			throws Exception
	{
		try
		{
			if (menuId != 0)
				isMenuAccessDenied(menuId, Menu.SAVE_ACCESS, request);
			Hostel hostel =
					new Hostel(name, collegename, mobileno, address, state,
							city, country);
			projectManagerService.addHostel(hostel);
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("addHostel :" + ex.getMessage());
			if (ex.getMessage().indexOf("Duplicate entry") >= 0)
			{
				ConstException constException =
						new ConstException(
								ConstException.ERR_CODE_DUPLICATE_ENTERY,
								ConstException.ERR_MSG_DUPLICATE_ENTERY);
				utilities.setErrResponse(constException, response);
			} else
			{
				utilities.setErrResponse(ex, response);
			}
		}
		model.addAttribute("model", response);
		return "hostel";
	}

	@RequestMapping(value = "/getHostelViaId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getHostelViaId(HttpServletRequest request,
			HttpServletResponse res, @RequestParam("hostelId") int hostelId,
			@RequestParam(value = "menuId", required = false,
					defaultValue = "0") int menuId, ModelMap model)
			throws Exception
	{
		try
		{
			String STATUS_ACTIVE = "active";
			List<Hostel> hostels =
					projectManagerService.getHostelViaId(hostelId,
							STATUS_ACTIVE);
			utilities.setSuccessResponse(response, hostels);
		} catch (Exception ex)
		{
			logger.error("getHostelViaId :" + ex.getMessage());
			utilities.setErrResponse(ex, response);

		}
		model.addAttribute("model", response);
		return "hosteldetailspage";
	}

	@RequestMapping(value = "/updateHostelViaId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String updateHostelViaId(HttpServletRequest request,
			HttpServletResponse res, @RequestParam("name") String name,
			@RequestParam("id") int id,
			@RequestParam("collegename") String collegename,
			@RequestParam("mobileno") String mobileno,
			@RequestParam("address") String address,
			@RequestParam("city") String city,
			@RequestParam("state") String state,
			@RequestParam("country") String country,
			@RequestParam(value = "menuId", required = false,
					defaultValue = "0") int menuId, ModelMap model)
			throws Exception
	{
		try
		{
			if (menuId != 0)
				isMenuAccessDenied(menuId, Menu.EDIT_ACCESS, request);
			Hostel hostel =
					new Hostel(name, collegename, mobileno, address, state,
							city, country, id);
			projectManagerService.updateHostelViaId(hostel);
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("updateHostelViaId :" + ex.getMessage());
			if (ex.getMessage().indexOf("Duplicate entry") >= 0)
			{
				ConstException constException =
						new ConstException(
								ConstException.ERR_CODE_DUPLICATE_ENTERY,
								ConstException.ERR_MSG_DUPLICATE_ENTERY);
				utilities.setErrResponse(constException, response);
			} else
			{
				utilities.setErrResponse(ex, response);
			}
		}
		model.addAttribute("model", response);
		return "hostel";
	}

	@RequestMapping(value = "/deleteHostelViaId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String deleteHostelViaId(HttpServletRequest request,
			HttpServletResponse res, @RequestParam("hostelId") int hostelId,
			@RequestParam(value = "menuId", required = false,
					defaultValue = "0") int menuId, ModelMap model)
			throws Exception
	{
		try
		{
			if (menuId != 0)
				isMenuAccessDenied(menuId, Menu.DELETE_ACCESS, request);
			String STATUS_DEACTIVE = "deactive";
			projectManagerService.deleteHostelViaId(hostelId, STATUS_DEACTIVE);
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("deleteHostelViaId :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "hostel";

	}

	@RequestMapping(value = "/getBlocks", method =
	{ RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	public @ResponseBody String getBlocks(HttpServletRequest request,
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

			List<Block> blockList = new ArrayList<Block>();
			int numEntries = 0;

			if (null != searchParameter && !searchParameter.equals(""))
			{
				blockList =
						projectManagerService.getBlocksViaSearchParam(
								startIndx, maxIndx, STATUS_ACTIVE,
								searchParameter);
				numEntries =
						projectManagerService
								.getBlocksViaSearchParamNumEntriesViaSearchParam(
										STATUS_ACTIVE, searchParameter);
			} else
			{
				blockList =
						projectManagerService.getBlocks(startIndx, maxIndx,
								STATUS_ACTIVE);
				numEntries =
						projectManagerService
								.getBlocksNumEntries(STATUS_ACTIVE);
			}

			CommonDataTableJsonObj<List<Block>> employeeCategoryJsonObj =
					new CommonDataTableJsonObj<List<Block>>();
			// Set Total display record
			employeeCategoryJsonObj.setiTotalDisplayRecords(numEntries);
			// Set Total record
			employeeCategoryJsonObj.setiTotalRecords(numEntries);
			employeeCategoryJsonObj.setAaData(blockList);
			json2 = gson.toJson(employeeCategoryJsonObj);

		} catch (Exception ex)
		{
			logger.error("getHostels :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute(json2);
		return json2;

	}

	@RequestMapping(value = "/getHostelNameApi", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getHostelNameApi(HttpServletRequest request, @RequestParam(
			value = "locationname") String locationname, ModelMap model)

	{
		Map<Object, Object> map = new HashMap<Object, Object>();
		JSONArray jSONArray = new JSONArray();
		boolean isCity = true;
		try
		{
			String status = "active";
			List<Hostel> cityList =
					projectManagerService
							.getHostelNameApi(locationname, status);
			if (cityList != null && cityList.size() > 0)
			{
				for (int i = 0; i < cityList.size(); i++)
				{
					Hostel city = (Hostel) cityList.get(i);
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
			logger.error("getHostelNameApi :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", jSONArray.toString());
		return "hostel";
	}

	@RequestMapping(value = "/get_block", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public
			String
			get_block(HttpServletRequest request, HttpServletResponse res)
	{
		return "block";
	}

	@RequestMapping(value = "/addBlock", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String addBlock(HttpServletRequest request, HttpServletResponse res,
			@RequestParam("hostelname") String hostelname,
			@RequestParam("blockname") String blockname,
			@RequestParam("nooffloor") int nooffloor,
			@RequestParam(value = "menuId", required = false,
					defaultValue = "0") int menuId, ModelMap model)
			throws Exception
	{
		try
		{
			if (menuId != 0)
				isMenuAccessDenied(menuId, Menu.SAVE_ACCESS, request);
			Block block = new Block(hostelname, blockname, nooffloor);
			projectManagerService.addBlock(block);
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("addBlock :" + ex.getMessage());
			if (ex.getMessage().indexOf("Duplicate entry") >= 0)
			{
				ConstException constException =
						new ConstException(
								ConstException.ERR_CODE_DUPLICATE_ENTERY,
								ConstException.ERR_MSG_DUPLICATE_ENTERY);
				utilities.setErrResponse(constException, response);
			} else
			{
				utilities.setErrResponse(ex, response);
			}
		}
		model.addAttribute("model", response);
		return "block";
	}

	@RequestMapping(value = "/getBlockViaId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getBlockViaId(HttpServletRequest request,
			HttpServletResponse res, @RequestParam("id") int id,
			@RequestParam(value = "menuId", required = false,
					defaultValue = "0") int menuId, ModelMap model)
			throws Exception
	{
		try
		{
			String STATUS_ACTIVE = "active";
			List<Block> list =
					projectManagerService.getBlockViaId(id, STATUS_ACTIVE);
			utilities.setSuccessResponse(response, list);
		} catch (Exception ex)
		{
			logger.error("getBlockViaId :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "block";
	}

	@RequestMapping(value = "/updateBlockViaId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String updateBlockViaId(HttpServletRequest request,
			HttpServletResponse res,
			@RequestParam("hostelname") String hostelname,
			@RequestParam("blockname") String blockname,
			@RequestParam("nooffloor") int nooffloor,
			@RequestParam("id") int id, @RequestParam(value = "menuId",
					required = false, defaultValue = "0") int menuId,
			ModelMap model) throws Exception
	{
		try
		{
			if (menuId != 0)
				isMenuAccessDenied(menuId, Menu.EDIT_ACCESS, request);
			Block block = new Block(hostelname, blockname, nooffloor, id);
			projectManagerService.updateBlockViaId(block);
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("updateBlockViaId :" + ex.getMessage());
			if (ex.getMessage().indexOf("Duplicate entry") >= 0)
			{
				ConstException constException =
						new ConstException(
								ConstException.ERR_CODE_DUPLICATE_ENTERY,
								ConstException.ERR_MSG_DUPLICATE_ENTERY);
				utilities.setErrResponse(constException, response);
			} else
			{
				utilities.setErrResponse(ex, response);
			}
		}
		model.addAttribute("model", response);
		return "block";
	}

	@RequestMapping(value = "/deleteBlockViaId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String deleteBlockViaId(HttpServletRequest request,
			HttpServletResponse res, @RequestParam("id") int id,
			@RequestParam(value = "menuId", required = false,
					defaultValue = "0") int menuId, ModelMap model)
			throws Exception
	{
		try
		{
			if (menuId != 0)
				isMenuAccessDenied(menuId, Menu.DELETE_ACCESS, request);
			String status = "deactive";
			projectManagerService.deleteBlockViaId(id, status);
			utilities.setSuccessResponse(response);

		} catch (Exception ex)
		{
			logger.error("deleteBlockViaId :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "block";
	}

	@RequestMapping(value = "/getUserMenus", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getUserMenus(HttpServletRequest request,
			@RequestParam("userId") int userId, HttpServletResponse res,
			ModelMap model) throws Exception
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			String STATUS_ACTIVE = "active";
			List<Users> list = projectManagerService.getUsers(STATUS_ACTIVE);
			if (userId != 0)
			{
				List<UserMenu> userMenu =
						projectManagerService.getUserMenus(STATUS_ACTIVE,
								userId);
				map.put("userId", userId);
				map.put("userMenu", userMenu);
			}
			map.put("users", list);
			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("getUserMenus :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "usersmenu";
	}

	@RequestMapping(value = "/getUserMenus_index", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getUserMenus_index(HttpServletRequest request,
			HttpServletResponse res, ModelMap model) throws Exception
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			String STATUS_ACTIVE = "active";
			List<Users> list = projectManagerService.getUsers(STATUS_ACTIVE);
			map.put("users", list);
			map.put("userId", 0);
			utilities.setSuccessResponse(response, map);

		} catch (Exception ex)
		{
			logger.error("getUserMenus_index :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "usersmenu";
	}

	@RequestMapping(value = "/addUserMenuRights", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String addUserMenuRights(HttpServletRequest request,
			@RequestParam("isApproved") String isApproved,
			@RequestParam("manageType") String manageType,
			@RequestParam("pk_MenuId") int menuId,
			@RequestParam("status") String status,
			@RequestParam("userId") int userId, HttpServletResponse res,
			ModelMap model) throws Exception
	{
		try
		{
			UserMenu userMenu =
					new UserMenu(isApproved, manageType, menuId, status, userId);
			projectManagerService.addUserMenuRights(userMenu);
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("addUserMenuRights :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "usersmenu";
	}
}
