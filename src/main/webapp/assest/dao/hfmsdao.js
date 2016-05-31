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

    this.RESPONSE_CACHE = {};

    this.TOTAL_RECORDS_PER_PAGE = 10;

    this.CACHE_MAP = {};

};

CtDAO.prototype.getLoginValidate = function(postParams, cbk) {
    var tObj = this;
    tObj.getData(tObj.LOGIN_VALIDATE, postParams, cbk);
};
CtDAO.prototype.getData = function(url, postParams, callback, isCacheMap,
	isParse) {
    $(".hfmsLoader").show();
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
