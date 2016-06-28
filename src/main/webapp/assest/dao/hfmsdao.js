var ctDAO = new CtDAO();

function CtDAO() {
    this.CONTEXT_NAME = "hostelerp";
    this.DOMAIN_NAME = window.location.host;
    this.HTTP_URL_PREFIX = "http://" + window.location.host + "/"
	    + this.CONTEXT_NAME + "/";
    this.HTTPS_URL_PREFIX = "https://" + window.location.host + "/"
	    + this.CONTEXT_NAME + "/";
    this.API_PREFIX = this.HTTP_URL_PREFIX + "api";

    this.LOGIN_VALIDATE = this.API_PREFIX + "/login/validate.json";
    this.ADD_USERS = this.API_PREFIX + "/manager/addUsers.json";
    this.DELETE_USERS = this.API_PREFIX + "/manager/deleteUsers.json";
    this.GET_USERS_VIA_ID = this.API_PREFIX + "/manager/getUsersViaId.json";
    this.GET_UPDATE_USERS_VIA_ID = this.API_PREFIX
	    + "/manager/updateUsersViaId.json";
    this.ADD_STUDENT = this.API_PREFIX + "/manager/addStudent.json";
    this.DELETE_STUDENT = this.API_PREFIX + "/manager/deleteStudentViaId.json";
    this.GET_STUDENT_VIA_ID = this.API_PREFIX
	    + "/manager/getStudentsViaId.json";
    this.UPDATE_STUDENT_VIA_ID = this.API_PREFIX
	    + "/manager/updateStudensViaId.json";
    this.ADD_HOSTEL = this.API_PREFIX + "/manager/addHostel.json";
    this.GET_HOSTEL_VIA_ID = this.API_PREFIX + "/manager/getHostelViaId.json";
    this.UPDATE_HOSTEL_VIA_ID = this.API_PREFIX
	    + "/manager/updateHostelViaId.json";
    this.DELETE_HOSTEL_VIA_ID = this.API_PREFIX
	    + "/manager/deleteHostelViaId.json";
    this.ADD_BLOCK = this.API_PREFIX + "/manager/addBlock.json";
    this.GET_BLOCK_VIA_ID = this.API_PREFIX + "/manager/getBlockViaId.json";
    this.UPADTE_BLOCK_VIA_ID = this.API_PREFIX
	    + "/manager/updateBlockViaId.json";
    this.DELETE_BLOCK_VIA_ID = this.API_PREFIX
	    + "/manager/deleteBlockViaId.json";
    this.GET_MENUS_VIA_USERID = this.API_PREFIX + "/manager/getUserMenus.json";
    this.ADD_USER_MENU_RIGHTS = this.API_PREFIX
	    + "/manager/addUserMenuRights.json";
    this.ADD_COLLEGE = this.API_PREFIX + "/manager/addColleges.json";
    this.GET_COLLEGE_VIA_ID = this.API_PREFIX + "/manager/getCollegeViaId.json";
    this.UPDATE_COLLEGE_VIA_ID = this.API_PREFIX
	    + "/manager/updateCollegesViaId.json";
    this.DELETE_COLLEGE_VIA_ID = this.API_PREFIX
	    + "/manager/deleteCollegeViaId.json";
    this.GET_HOSTEL_VIA_COLLEGE_ID = this.API_PREFIX
	    + "/manager/getHostelsViaCollegeId.json";
    this.GET_BLOCK_VIA_HOSTEL_ID = this.API_PREFIX
	    + "/manager/getBlockViaHostelId.json";
    this.ADD_ROOM = this.API_PREFIX + "/manager/addRoom.json";
    this.GET_ROOM_VIA_ID = this.API_PREFIX + "/manager/getRoomViaId.json";
    this.UPDATE_ROOM_VIA_ID = this.API_PREFIX + "/manager/updateRoomViaId.json";
    this.GET_NO_FLOORS_VIA_HOSTLE_BLOCK = this.API_PREFIX
	    + "/manager/getNoFloorsViaHostelAndBlockId.json";
    this.GET_STUDENT_VIA_COLLEGE_ID = this.API_PREFIX
	    + "/manager/getStudentViaCollegeId.json";
    this.GET_HOSTELS_VIA_COLLEGE_ID = this.API_PREFIX
	    + "/manager/getHostelViaCollegeId.json";
    this.GET_ROOMS_VIA_HOSTEL_ID = this.API_PREFIX
	    + "/manager/getRoomNoViaHostelId.json";
    this.ADD_ROOMS_ALLOCATION = this.API_PREFIX
	    + "/manager/addRoomAllocation.json";
    this.ADD_ROOMS_ALLOCATION_ROOMALLOCATION_ID = this.API_PREFIX
	    + "/manager/getRoomAllocationViaRoomAllocationId.json";
    this.UPDATE_ROOMS_ALLOCATION_ROOMALLOCATION_ID = this.API_PREFIX
    + "/manager/updateRoomAllocationViaId.json";

    this.RESPONSE_CACHE = {};

    this.TOTAL_RECORDS_PER_PAGE = 10;

    this.CACHE_MAP = {};

};

CtDAO.prototype.getLoginValidate = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.LOGIN_VALIDATE, postParams, cbk);
};
CtDAO.prototype.addUsers = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.ADD_USERS, postParams, cbk);
};
CtDAO.prototype.deleteUsers = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.DELETE_USERS, postParams, cbk);
};
CtDAO.prototype.getUsersViaId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_USERS_VIA_ID, postParams, cbk);
};
CtDAO.prototype.updateUsersViaId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_UPDATE_USERS_VIA_ID, postParams, cbk);
};
CtDAO.prototype.addStudent = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.ADD_STUDENT, postParams, cbk);
};
CtDAO.prototype.deleteStudent = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.DELETE_STUDENT, postParams, cbk);
};
CtDAO.prototype.getStudentViaId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_STUDENT_VIA_ID, postParams, cbk);
};
CtDAO.prototype.updateStudensViaId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.UPDATE_STUDENT_VIA_ID, postParams, cbk);
};
CtDAO.prototype.addHostel = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.ADD_HOSTEL, postParams, cbk);
};
CtDAO.prototype.getHostelViaId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_HOSTEL_VIA_ID, postParams, cbk);
};
CtDAO.prototype.updateHostelViaId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.UPDATE_HOSTEL_VIA_ID, postParams, cbk);
};
CtDAO.prototype.deleteHostelViaId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.DELETE_HOSTEL_VIA_ID, postParams, cbk);
};
CtDAO.prototype.addBlock = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.ADD_BLOCK, postParams, cbk);
};
CtDAO.prototype.getBlockViaId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_BLOCK_VIA_ID, postParams, cbk);
};
CtDAO.prototype.updateBlockViaId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.UPADTE_BLOCK_VIA_ID, postParams, cbk);
};
CtDAO.prototype.deleteBlockViaId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.DELETE_BLOCK_VIA_ID, postParams, cbk);
};
CtDAO.prototype.getUserMenus = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_MENUS_VIA_USERID, postParams, cbk);
};
CtDAO.prototype.addUserMenuRights = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.ADD_USER_MENU_RIGHTS, postParams, cbk);
};
CtDAO.prototype.addColleges = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.ADD_COLLEGE, postParams, cbk);
};
CtDAO.prototype.getCollegeViaId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_COLLEGE_VIA_ID, postParams, cbk);
};
CtDAO.prototype.updateCollegesViaId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.UPDATE_COLLEGE_VIA_ID, postParams, cbk);
};
CtDAO.prototype.deleteCollegeViaId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.DELETE_COLLEGE_VIA_ID, postParams, cbk);
};
CtDAO.prototype.getHostelsViaCollegeId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_HOSTEL_VIA_COLLEGE_ID, postParams, cbk);
};
CtDAO.prototype.getBlockViaHostelId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_BLOCK_VIA_HOSTEL_ID, postParams, cbk);
};
CtDAO.prototype.addRoom = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.ADD_ROOM, postParams, cbk);
};
CtDAO.prototype.getRoomViaId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_ROOM_VIA_ID, postParams, cbk);
};

CtDAO.prototype.updateRoomViaId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.UPDATE_ROOM_VIA_ID, postParams, cbk);
};
CtDAO.prototype.getNoFloorsViaHostelAndBlockId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_NO_FLOORS_VIA_HOSTLE_BLOCK, postParams, cbk);
};
CtDAO.prototype.getStudentViaCollegeId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_STUDENT_VIA_COLLEGE_ID, postParams, cbk);
};
CtDAO.prototype.getHostelViaCollegeId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_HOSTELS_VIA_COLLEGE_ID, postParams, cbk);
};
CtDAO.prototype.getRoomNoViaHostelId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_ROOMS_VIA_HOSTEL_ID, postParams, cbk);
};
CtDAO.prototype.addRoomAllocation = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.ADD_ROOMS_ALLOCATION, postParams, cbk);
};
CtDAO.prototype.getRoomAllocationViaRoomAllocationId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.ADD_ROOMS_ALLOCATION_ROOMALLOCATION_ID, postParams, cbk);
};
CtDAO.prototype.updateRoomAllocationViaId = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.UPDATE_ROOMS_ALLOCATION_ROOMALLOCATION_ID, postParams, cbk);
};
CtDAO.prototype.getData = function(url, postParams, callback, isCacheMap,
	isParse) {
    $(".hfmsLoader").show();
    if (localStorage.getItem("menuId") && postParams) {
	postParams["menuId"] = localStorage.getItem("menuId");
    }

    var tObj = this, cbk = function(data) {
	if (data && data.model) {
	    data = data.model;
	    if (!isParse) {

	    }
	    if (isCacheMap && !tObj.CACHE_MAP[url]) {
		tObj.CACHE_MAP[url] = data;
	    }
	}
	callback(data);
    }, ajaxConfig = {
	"type" : (postParams ? "POST" : "GET"),
	"url" : url,
	"dataType" : "json",
	"data" : postParams,
	error : function(err) {

	},
	success : function(data) {
	    $(".hfmsLoader").hide();
	    cbk(data);
	},
	statusCode : {
	    404 : function() {
		alert("Check your internet connection");
	    }
	}

    };
    jQuery.ajax(ajaxConfig);
};
CtDAO.prototype.parseJSON = function(jsonData) {
    try {
	jsonData = JSON.parse(jsonData);
    } catch (ex) {
	console.log(ex);
    }
    return jsonData;
};
