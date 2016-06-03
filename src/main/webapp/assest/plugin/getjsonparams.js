var constJSONParam = new ConstJSONParam();
function ConstJSONParam(config) {
    config = config ? config : {};
    this.data = config.data ? config.data : {};
    this.jqSelector = (config.jqSelector ? config.jqSelector : "");

};
ConstJSONParam.prototype.getParamsValue = function() {
    var tObj = this, postParam = {};
    if (tObj.jqSelector) {

	$(tObj.jqSelector + " input").each(function() {
	    if ($(this).attr("jsonKey")) {
		postParam[$(this).attr("jsonKey")] = $(this).val()
	    }

	});
	$(tObj.jqSelector + " textarea").each(function() {
	    if ($(this).attr("jsonKey")) {
		postParam[$(this).attr("jsonKey")] = $(this).val()
	    }
	});
	$(tObj.jqSelector + " select").each(
		function() {
		    var className = $(this).attr('class');
		    if ($(this).attr("jsonKey")) {
			postParam[$(this).attr("jsonKey")] = $(this).find(
				'option:selected').val();
		    }
		});
    }
    return postParam;
};
ConstJSONParam.prototype.setParamValue = function() {
    var tObj, postParam;
    tObj = this;
    if (tObj.jqSelector && tObj.data) {
	$(tObj.jqSelector + " input").each(function() {
	    if ($(this).attr("jsonKey")) {
		$(this).val(tObj.data[$(this).attr("jsonKey")]);
	    }
	});

	$(tObj.jqSelector + " textarea").each(function() {
	    if ($(this).attr("jsonKey")) {
		if ($(this).attr("jsonKey")) {
		    $(this).val(tObj.data[$(this).attr("jsonKey")]);
		}
	    }
	});

	$(tObj.jqSelector + " select").each(
		function() {
		    var className = $(this).attr('class');
		    if ($(this).attr("jsonKey")) {
			$(this).find(
				"option[value="
					+ tObj.data[$(this).attr("jsonKey")]
					+ "]").prop('selected', true);
		    }
		});

    }

};