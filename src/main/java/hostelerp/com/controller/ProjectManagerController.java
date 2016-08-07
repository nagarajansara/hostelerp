package hostelerp.com.controller;

import hostelerp.com.datatable.CommonDataTableJsonObj;
import hostelerp.com.model.Block;
import hostelerp.com.model.CityState;
import hostelerp.com.model.College;
import hostelerp.com.model.Hostel;
import hostelerp.com.model.Menu;
import hostelerp.com.model.Payment;
import hostelerp.com.model.Room;
import hostelerp.com.model.RoomAllocation;
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

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
			HttpServletResponse res, ModelMap model)
	{

		try
		{
			String STATUS_ACTIVE = "active";
			List<College> colleges =
					projectManagerService
							.getAllCollegesViaStatus(STATUS_ACTIVE);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("college", colleges);
			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("get_student :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
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
			@RequestParam("collegeid") int collegeid,
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
							state, city, country, mobileno, collegeid);
			projectManagerService.addStudent(student);
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("addStudent :" + ex.getMessage());
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
			@RequestParam("collegeid") int collegeid,
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
							city, country, mobileno, id, rollno, collegeid);
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
			@RequestParam("collegeid") int collegeid,
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
					new Hostel(name, mobileno, address, state, city, country,
							collegeid);
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
			@RequestParam("collegeid") int collegeid,
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
					new Hostel(name, collegeid, mobileno, address, state, city,
							country, id);
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
			@RequestParam("hostelid") int hostelid,
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
			Block block = new Block(hostelid, blockname, nooffloor);
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
			HttpServletResponse res, @RequestParam("hostelid") int hostelid,
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
			Block block = new Block(hostelid, blockname, nooffloor, id);
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
	{ RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
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

	@RequestMapping(value = "/addColleges", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String addColleges(HttpServletRequest request,
			@RequestParam("name") String name,
			@RequestParam("address") String address,
			@RequestParam("state") String state,
			@RequestParam("city") String city,
			@RequestParam("country") String country,
			@RequestParam("mobileno") String mobileno,
			@RequestParam(value = "menuId", required = false,
					defaultValue = "0") int menuId, HttpServletResponse res,
			ModelMap model) throws Exception
	{
		try
		{
			if (menuId != 0)
				isMenuAccessDenied(menuId, Menu.SAVE_ACCESS, request);

			College college =
					new College(name, address, state, city, country, mobileno);
			projectManagerService.addColleges(college);
			utilities.setSuccessResponse(response);

		} catch (Exception ex)
		{
			logger.error("addColleges :" + ex.getMessage());
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
		return "college";
	}

	@RequestMapping(value = "/getColleges", method =
	{ RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	public @ResponseBody String getColleges(HttpServletRequest request,
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

			List<College> collegeList = new ArrayList<College>();
			int numEntries = 0;

			if (null != searchParameter && !searchParameter.equals(""))
			{
				collegeList =
						projectManagerService.getCollegesViaSearchParam(
								startIndx, maxIndx, STATUS_ACTIVE,
								searchParameter);
				numEntries =
						projectManagerService
								.getCollegeNumEntriesViaSearchParam(
										STATUS_ACTIVE, searchParameter);
			} else
			{
				collegeList =
						projectManagerService.getColleges(startIndx, maxIndx,
								STATUS_ACTIVE);
				numEntries =
						projectManagerService
								.getCollegesNumEntries(STATUS_ACTIVE);
			}

			CommonDataTableJsonObj<List<College>> employeeCategoryJsonObj =
					new CommonDataTableJsonObj<List<College>>();
			// Set Total display record
			employeeCategoryJsonObj.setiTotalDisplayRecords(numEntries);
			// Set Total record
			employeeCategoryJsonObj.setiTotalRecords(numEntries);
			employeeCategoryJsonObj.setAaData(collegeList);
			json2 = gson.toJson(employeeCategoryJsonObj);

		} catch (Exception ex)
		{
			logger.error("getColleges :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute(json2);
		return json2;
	}

	@RequestMapping(value = "/get_college", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String get_college(HttpServletRequest request,
			HttpServletResponse res)
	{
		return "college";
	}

	@RequestMapping(value = "/getCollegeViaId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public
			String getCollegeViaId(HttpServletRequest request,
					@RequestParam("id") int id, HttpServletResponse res,
					ModelMap model)
	{
		try
		{
			String STATUS_ACTIVE = "active";
			List<College> colleges =
					projectManagerService.getCollegeViaId(id, STATUS_ACTIVE);
			utilities.setSuccessResponse(response, colleges);
		} catch (Exception ex)
		{

			logger.error("getCollegeViaId :" + ex.getMessage());
			utilities.setErrResponse(ex, response);

		}
		model.addAttribute("model", response);
		return "college";
	}

	@RequestMapping(value = "/updateCollegesViaId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String updateCollegesViaId(HttpServletRequest request,
			@RequestParam("name") String name,
			@RequestParam("address") String address,
			@RequestParam("state") String state,
			@RequestParam("city") String city,
			@RequestParam("country") String country,
			@RequestParam("mobileno") String mobileno,
			@RequestParam("id") int id, @RequestParam(value = "menuId",
					required = false, defaultValue = "0") int menuId,
			HttpServletResponse res, ModelMap model) throws Exception
	{
		try
		{
			if (menuId != 0)
				isMenuAccessDenied(menuId, Menu.SAVE_ACCESS, request);

			College college =
					new College(name, address, state, city, country, mobileno,
							id);
			projectManagerService.updateCollegesViaId(college);
			utilities.setSuccessResponse(response);

		} catch (Exception ex)
		{
			logger.error("updateCollegesViaId :" + ex.getMessage());
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
		return "college";
	}

	@RequestMapping(value = "/deleteCollegeViaId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String deleteCollegeViaId(HttpServletRequest request,
			@RequestParam("id") int id, @RequestParam(value = "menuId",
					required = false, defaultValue = "0") int menuId,
			HttpServletResponse res, ModelMap model) throws Exception
	{
		String DEACTIVE = "deactive";
		try
		{
			if (menuId != 0)
				isMenuAccessDenied(menuId, Menu.DELETE_ACCESS, request);
			projectManagerService.deleteCollegeViaId(id, DEACTIVE);
			utilities.setSuccessResponse(response);

		} catch (Exception ex)
		{
			logger.error("deleteCollegeViaId :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "college";
	}

	@RequestMapping(value = "/getRoom_index", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getRoom_index(HttpServletRequest request,
			HttpServletResponse res, ModelMap model) throws Exception
	{
		try
		{
			String STATUS_ACTIVE = "active";
			List<College> colleges =
					projectManagerService
							.getAllCollegesViaStatus(STATUS_ACTIVE);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("college", colleges);
			utilities.setSuccessResponse(response, map);

		} catch (Exception ex)
		{
			logger.error("getRoom_index :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "room";
	}

	@RequestMapping(value = "/getRooms", method =
	{ RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	public @ResponseBody String getRooms(HttpServletRequest request,
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

			List<Room> roomList = new ArrayList<Room>();
			int numEntries = 0;

			if (null != searchParameter && !searchParameter.equals(""))
			{
				roomList =
						projectManagerService.getRoomsViaSearchParam(startIndx,
								maxIndx, STATUS_ACTIVE, searchParameter);
				numEntries =
						projectManagerService.getRoomsNumEntriesViaSearchParam(
								STATUS_ACTIVE, searchParameter);
			} else
			{
				roomList =
						projectManagerService.getRooms(startIndx, maxIndx,
								STATUS_ACTIVE);
				numEntries =
						projectManagerService.getRoomsNumEntries(STATUS_ACTIVE);
			}

			CommonDataTableJsonObj<List<Room>> employeeCategoryJsonObj =
					new CommonDataTableJsonObj<List<Room>>();
			// Set Total display record
			employeeCategoryJsonObj.setiTotalDisplayRecords(numEntries);
			// Set Total record
			employeeCategoryJsonObj.setiTotalRecords(numEntries);
			employeeCategoryJsonObj.setAaData(roomList);
			json2 = gson.toJson(employeeCategoryJsonObj);

		} catch (Exception ex)
		{
			logger.error("getRooms :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute(json2);
		return json2;
	}

	@RequestMapping(value = "/addRoom", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String addRoom(HttpServletRequest request,
			@RequestParam("blockid") int blockid,
			@RequestParam("collegeid") int collegeid,
			@RequestParam("floorname") int floorname,
			@RequestParam("hostelid") int hostelid,
			@RequestParam("noofperson") int noofperson,
			@RequestParam("roomno") int roomno,
			@RequestParam("roomtype") String roomtype, HttpServletResponse res,
			ModelMap model) throws Exception
	{
		try
		{
			Room room =
					new Room(blockid, collegeid, floorname, hostelid,
							noofperson, roomno, roomtype);
			projectManagerService.addRoom(room);
			utilities.setSuccessResponse(response);

		} catch (Exception ex)
		{
			logger.error("addRoom :" + ex.getMessage());
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
		return "room";
	}

	@RequestMapping(value = "/getHostelsViaCollegeId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getHostelsViaCollegeId(HttpServletRequest request,
			@RequestParam("collegeId") int collegeId, HttpServletResponse res,
			ModelMap model) throws Exception
	{
		try
		{
			List<Hostel> hostels =
					projectManagerService.getHostelsViaCollegeId(collegeId);
			utilities.setSuccessResponse(response, hostels);
		} catch (Exception ex)
		{
			logger.error("getHostelsViaCollegeId :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "room";

	}

	@RequestMapping(value = "/getBlockViaHostelId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getBlockViaHostelId(HttpServletRequest request,
			@RequestParam("collegeId") int collegeId,
			@RequestParam("hostelId") int hostelId, HttpServletResponse res,
			ModelMap model) throws Exception
	{
		try
		{
			List<Block> blocks =
					projectManagerService.getBlockViaHostelId(collegeId,
							hostelId);
			utilities.setSuccessResponse(response, blocks);

		} catch (Exception ex)
		{
			logger.error("getBlockViaHostelId :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "room";
	}

	@RequestMapping(value = "/getRoomViaId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public
			String getRoomViaId(HttpServletRequest request,
					@RequestParam("id") int id, HttpServletResponse res,
					ModelMap model) throws Exception
	{
		try
		{
			String STATUS_ACTIVE = "active";
			Map<String, Object> map = new HashMap<String, Object>();
			List<Room> rooms = projectManagerService.getRoomViaId(id);
			List<Hostel> hostels =
					projectManagerService
							.getHostelsViaRoomId(id, STATUS_ACTIVE);
			List<Block> blocks =
					projectManagerService.getBlocksViaRoomId(id, STATUS_ACTIVE);
			map.put("rooms", rooms);
			map.put("hostels", hostels);
			map.put("blocks", blocks);
			utilities.setSuccessResponse(response, map);

		} catch (Exception ex)
		{
			logger.error("getRoomViaId :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "room";
	}

	@RequestMapping(value = "/updateRoomViaId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String updateRoomViaId(HttpServletRequest request,
			@RequestParam("blockid") int blockid,
			@RequestParam("collegeid") int collegeid,
			@RequestParam("floorname") int floorname,
			@RequestParam("hostelid") int hostelid,
			@RequestParam("noofperson") int noofperson,
			@RequestParam("roomno") int roomno,
			@RequestParam("id") int id, // Room Id
			@RequestParam("roomtype") String roomtype, HttpServletResponse res,
			ModelMap model) throws Exception
	{
		try
		{

			Room room =
					new Room(blockid, collegeid, floorname, hostelid,
							noofperson, roomno, roomtype, id);
			projectManagerService.updateRoomViaId(room);

			utilities.setSuccessResponse(response);

		} catch (Exception ex)
		{

			logger.error("updateRoomViaId :" + ex.getMessage());
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
		return "room";
	}

	@RequestMapping(value = "/getNoFloorsViaHostelAndBlockId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getNoFloorsViaHostelAndBlockId(HttpServletRequest request,
			@RequestParam("blockId") int blockId,
			@RequestParam("hostelId") int hostelId, HttpServletResponse res,
			ModelMap model) throws Exception
	{
		try
		{
			String STATUS_ACTIVE = "active";
			List<Block> blocks =
					projectManagerService.getNoFloorsViaHostelAndBlockId(
							hostelId, blockId, STATUS_ACTIVE);
			utilities.setSuccessResponse(response, blocks);

		} catch (Exception ex)
		{
			logger.error("getNoFloors :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "room";
	}

	@RequestMapping(value = "/getRoomAllocationDetails", method =
	{ RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	public @ResponseBody
			String getRoomAllocationDetails(HttpServletRequest request,
					HttpServletResponse res, ModelMap model) throws Exception
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = "";
		String STATUS_ACTIVE = "vacated";
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

			List<RoomAllocation> roomAllocationList =
					new ArrayList<RoomAllocation>();
			int numEntries = 0;

			if (null != searchParameter && !searchParameter.equals(""))
			{
				roomAllocationList =
						projectManagerService
								.getRoomAllocationDetailsViaSearchParam(
										startIndx, maxIndx, STATUS_ACTIVE,
										searchParameter);
				numEntries =
						projectManagerService
								.getRoomAllocationDetailsNumEntriesViaSearchParam(
										STATUS_ACTIVE, searchParameter);
			} else
			{
				roomAllocationList =
						projectManagerService.getRoomAllocationDetails(
								startIndx, maxIndx, STATUS_ACTIVE);
				numEntries =
						projectManagerService
								.getRoomAllocationDetailsNumEntries(STATUS_ACTIVE);
			}

			CommonDataTableJsonObj<List<RoomAllocation>> employeeCategoryJsonObj =
					new CommonDataTableJsonObj<List<RoomAllocation>>();
			// Set Total display record
			employeeCategoryJsonObj.setiTotalDisplayRecords(numEntries);
			// Set Total record
			employeeCategoryJsonObj.setiTotalRecords(numEntries);
			employeeCategoryJsonObj.setAaData(roomAllocationList);
			json2 = gson.toJson(employeeCategoryJsonObj);

		} catch (Exception ex)
		{
			logger.error("getRoomAllocationDetails :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute(json2);
		return json2;
	}

	@RequestMapping(value = "/getRoomAllocation_index", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getRoomAllocation_index(HttpServletRequest request,
			HttpServletResponse res, ModelMap model)
	{
		try
		{
			String STATUS_ACTIVE = "active";
			List<College> colleges =
					projectManagerService
							.getAllCollegesViaStatus(STATUS_ACTIVE);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("college", colleges);
			utilities.setSuccessResponse(response, map);

		} catch (Exception ex)
		{
			logger.error("getRoomAllocation_index :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "roomallocation";
	}

	@RequestMapping(value = "/getStudentViaCollegeId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getStudentViaCollegeId(HttpServletRequest request,
			@RequestParam("collegeId") int collegeId, HttpServletResponse res,
			ModelMap model)
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			String STATUS_ACTIVE = "active";
			List<Student> students =
					projectManagerService.getStudentViaCollegeId(collegeId,
							STATUS_ACTIVE);
			List<Hostel> hostels =
					projectManagerService.getHostelViaCollegeId(collegeId);
			map.put("student", students);
			map.put("hostel", hostels);

			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("getStudentViaCollegeId :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "roomallocation";
	}

	@RequestMapping(value = "/getHostelViaCollegeId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getHostelViaCollegeId(HttpServletRequest request,
			@RequestParam("collegeId") int collegeId, HttpServletResponse res,
			ModelMap model)
	{
		try
		{
			String STATUS_ACTIVE = "active";
			List<Hostel> hostels =
					projectManagerService.getHostelViaCollegeId(collegeId);
			utilities.setSuccessResponse(response, hostels);

		} catch (Exception ex)
		{
			logger.error("getHostelViaCollegeId :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "roomallocation";
	}

	// GET AVAILABLE ROOMS
	@RequestMapping(value = "/getRoomNoViaHostelId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getRoomNoViaHostelId(HttpServletRequest request,
			@RequestParam("hostelId") int hostelId, HttpServletResponse res,
			ModelMap model)
	{
		try
		{
			String STATUS_ACTIVE = "active";
			List<Room> rooms =
					projectManagerService.getRoomNoViaHostelId(hostelId,
							STATUS_ACTIVE);
			utilities.setSuccessResponse(response, rooms);

		} catch (Exception ex)
		{
			logger.error("getRoomNoViaHostelId :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "roomallocation";
	}

	@RequestMapping(value = "/addRoomAllocation", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String addRoomAllocation(HttpServletRequest request,
			@RequestParam("roomid") int roomid,
			@RequestParam("studentid") int studentid, HttpServletResponse res,
			ModelMap model)
	{
		try
		{
			String STATUS_ALLOCATED = "alloted";
			RoomAllocation roomAllocation =
					new RoomAllocation(roomid, studentid, STATUS_ALLOCATED,
							utilities.getDate());

			int isAlloted = projectManagerService.chkIsAlloted(roomAllocation);
			if (isAlloted > 0)
			{
				throw new ConstException(
						ConstException.ERR_CODE_DUPLICATE_ROOM_ALLOCATION,
						ConstException.ERR_MSG_DUPLICATE_ROOM_ALLOCATION);
			} else
			{
				projectManagerService.addRoomAllocation(roomAllocation);
			}
			utilities.setSuccessResponse(response);

		} catch (Exception ex)
		{
			logger.error("getRoomNoViaHostelId :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "roomallocation";
	}

	@RequestMapping(value = "/getRoomAllocationViaRoomAllocationId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getRoomAllocationViaRoomAllocationId(
			HttpServletRequest request,
			@RequestParam("roomAllocationId") int roomAllocationId,
			ModelMap model)
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			int collegeId = 0;
			int hostelId = 0;
			String STATUS_ACTIVE = "active";
			List<RoomAllocation> allocations =
					projectManagerService
							.getRoomAllocationDetailsViaRoomId(roomAllocationId);
			if (allocations != null && allocations.size() > 0)
			{
				if (allocations.size() == 1)
				{
					for (int i = 0; i < allocations.size(); i++)
					{
						RoomAllocation roomAllocation =
								(RoomAllocation) allocations.get(i);
						collegeId = roomAllocation.getCollegeid();
						hostelId = roomAllocation.getHostelid();
					}
				} else
				{
					throw new ConstException(
							ConstException.ERR_CODE_ROOM_ALLOCATION_MAX_RECORS,
							ConstException.ERR_MSG_ROOM_ALLOCATION_MAX_RECORS);
				}
			}
			List<Hostel> hostels =
					projectManagerService.getHostelsViaCollegeId(collegeId);
			List<Room> rooms =
					projectManagerService.getRoomNoViaHostelId(hostelId,
							STATUS_ACTIVE);
			/*
			 * List<Student> students =
			 * projectManagerService.getStudentViaCollegeId(collegeId,
			 * STATUS_ACTIVE);
			 */
			// map.put("students", students);
			map.put("hostels", hostels);
			map.put("rooms", rooms);
			map.put("allocations", allocations);

			utilities.setSuccessResponse(response, map);

		} catch (Exception ex)
		{
			logger.error("getRoomAllocationViaRoomAllocationId :"
					+ ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "roomallocation";
	}

	@RequestMapping(value = "/updateRoomAllocationViaId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String updateRoomAllocationViaId(HttpServletRequest request,
			@RequestParam("roomallocationid") int roomallocationid,
			@RequestParam("collegeid") int collegeid,
			@RequestParam("hostelid") int hostelid,
			@RequestParam("roomid") int roomid,
			@RequestParam("status") String status,
			@RequestParam("preStatus") String preStatus,
			@RequestParam("studentid") int studentid, ModelMap model)
	{
		try
		{

			if (preStatus.equals("shifted") && status.equals("alloted"))
			{
				int availableRoom =
						projectManagerService.getRoomNoViaHostelId_RoomNo(
								collegeid, roomid);
				if (availableRoom == 0)
				{
					RoomAllocation roomAllocation =
							new RoomAllocation(roomid, studentid, "alloted",
									utilities.getDate());
					int isAlloted =
							projectManagerService.chkIsAlloted(roomAllocation);
					if (isAlloted > 0)
					{
						throw new ConstException(
								ConstException.ERR_CODE_DUPLICATE_ROOM_ALLOCATION,
								ConstException.ERR_MSG_DUPLICATE_ROOM_ALLOCATION);
					} else
					{
						roomAllocation =
								new RoomAllocation(roomallocationid, roomid,
										studentid, utilities.getDate(), status);
						projectManagerService
								.updateRoomAllocationViaId(roomAllocation);
					}
				} else
				{
					throw new ConstException(
							ConstException.ERR_CODE_ROOM_NOT_AVAILABLE,
							ConstException.ERR_MSG_ROOM_NOT_AVAILABLE);
				}

			} else
			{
				RoomAllocation roomAllocation =
						new RoomAllocation(roomallocationid, roomid, studentid,
								utilities.getDate(), status);
				projectManagerService.updateRoomAllocationViaId(roomAllocation);
			}
			utilities.setSuccessResponse(response);

		} catch (Exception ex)
		{
			logger.error("updateRoomAllocationViaId :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "roomallocation";
	}

	@RequestMapping(value = "/addPayment", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String updateRoomAllocationViaId(HttpServletRequest request,
			@ModelAttribute Payment payment, ModelMap model)
	{
		try
		{
			projectManagerService.addPayment(payment);
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("addPayment :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "payment";
	}

	@RequestMapping(value = "/getPayment_index", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String get_payment(HttpServletRequest request, ModelMap model)
	{
		try
		{
			String STATUS_ACTIVE = "active";
			List<College> colleges =
					projectManagerService
							.getAllCollegesViaStatus(STATUS_ACTIVE);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("college", colleges);
			utilities.setSuccessResponse(response, map);
		} catch (Exception ex)
		{
			logger.error("getPayment_index :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "payment";
	}

	@RequestMapping(value = "/getPayments", method =
	{ RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	public @ResponseBody String getPayments(HttpServletRequest request,
			ModelMap model)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = "";
		String STATUS_ACTIVE = "vacated";
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

			List<Payment> paymentList = new ArrayList<Payment>();
			int numEntries = 0;

			if (null != searchParameter && !searchParameter.equals(""))
			{

				paymentList =
						projectManagerService.getPaymentsViaSearchParam(
								startIndx, maxIndx, searchParameter);
				System.out.println("paymentList :" + paymentList.size());
				numEntries =
						projectManagerService
								.getPaymentsNumEntriesNumEntriesViaSearchParam(
										STATUS_ACTIVE, searchParameter);
				System.out.println("numEntries " + numEntries);
			} else
			{
				paymentList =
						projectManagerService.getPayments(startIndx, maxIndx);
				numEntries =
						projectManagerService
								.getPaymentsNumEntries(STATUS_ACTIVE);
			}

			CommonDataTableJsonObj<List<Payment>> employeeCategoryJsonObj =
					new CommonDataTableJsonObj<List<Payment>>();
			// Set Total display record
			employeeCategoryJsonObj.setiTotalDisplayRecords(numEntries);
			// Set Total record
			employeeCategoryJsonObj.setiTotalRecords(numEntries);
			employeeCategoryJsonObj.setAaData(paymentList);
			json2 = gson.toJson(employeeCategoryJsonObj);

		} catch (Exception ex)
		{
			logger.error("getPayments :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute(json2);
		return json2;
	}

	@RequestMapping(value = "/getPaymentsViaId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String getPaymentsViaId(HttpServletRequest request, ModelMap model,
			@RequestParam("id") int id)
	{
		try
		{
			List<Payment> payments = projectManagerService.getPaymentsViaId(id);
			utilities.setSuccessResponse(response, payments);
		} catch (Exception ex)
		{
			logger.error("getPaymentsViaId :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "payment";
	}

	@RequestMapping(value = "/updatePaymentsViaId", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String updatePaymentsViaId(HttpServletRequest request,
			ModelMap model, @ModelAttribute Payment payment)
	{
		try
		{
			projectManagerService.updatePaymentsViaId(payment);
			utilities.setSuccessResponse(response);
		} catch (Exception ex)
		{
			logger.error("updatePaymentsViaId :" + ex.getMessage());
			utilities.setErrResponse(ex, response);
		}
		model.addAttribute("model", response);
		return "payment";
	}
}
