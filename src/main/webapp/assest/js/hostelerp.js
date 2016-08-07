function hfmsSetSelect2(jqSelector, url, label) {
    var remoteDataConfig = {
	dropdownCssClass : 'bmSelect2Class',
	cache : "true",
	placeholder : (label ? label : "Select your state"),
	minimumInputLength : 1,
	ajax : {
	    url : url,
	    dataType : 'json',
	    data : function(term, page) {
		searchTerm = term.toUpperCase();
		return {
		    locationname : term,
		    page_limit : 10
		};
	    },
	    results : function(data, page) {
		return {
		    results : getMockData(data.model)
		};
	    }
	},
	formatResult : function(option) {
	    return "<div>" + option.text + "</div>";
	},
	formatSelection : function(option) {
	    return option.text;
	},
	initSelection : function(element, callback) {
	    var id = element.val();
	    var text = element.data('option');
	    var data = {
		id : id,
		text : text
	    };
	    callback(data);
	},
	escapeMarkup : function(m) {
	    return m;
	}
    };
    function getMockData(mockData) {
	mockData = JSON.parse(mockData);
	var foundOptions = [];
	for ( var key in mockData) {
	    if (mockData[key].text.toUpperCase().indexOf(searchTerm) >= 0) {
		foundOptions.push(mockData[key]);
	    }
	}

	return foundOptions;
    }

    $(jqSelector).select2(remoteDataConfig);

}
function hfmsSetDataTableValues(jqSelector, URL, aoColumns, placeHolder) {

    jQuery.fn.dataTableExt.oApi.fnPagingInfo = function(oSettings) {
	return {
	    "iStart" : oSettings._iDisplayStart,
	    "iEnd" : oSettings.fnDisplayEnd(),
	    "iLength" : oSettings._iDisplayLength,
	    "iTotal" : oSettings.fnRecordsTotal(),
	    "iFilteredTotal" : oSettings.fnRecordsDisplay(),
	    "iPage" : oSettings._iDisplayLength === -1 ? 0 : Math
		    .ceil(oSettings._iDisplayStart / oSettings._iDisplayLength),
	    "iTotalPages" : oSettings._iDisplayLength === -1 ? 0 : Math
		    .ceil(oSettings.fnRecordsDisplay()
			    / oSettings._iDisplayLength)
	};
    };

    $(jqSelector).dataTable({
	"bProcessing" : true,
	"bServerSide" : true,
	"responsive" : true,
	"sort" : "position",
	// bStateSave variable you can use to save state on client
	// cookies: set value "true"
	"bStateSave" : false,
	// Default: Page display length
	"iDisplayLength" : 10,
	// We will use below variable to track page number on server
	// side(For more information visit:
	// http://legacy.datatables.net/usage/options#iDisplayStart)
	"iDisplayStart" : 0,
	"fnDrawCallback" : function() {
	    // Get page numer on client. Please note: number start from
	    // 0 So
	    // for the first page you will see 0 second page 1 third
	    // page 2...
	    // Un-comment below alert to see page number
	    // alert("Current page number: "+this.fnPagingInfo().iPage);
	},
	"sAjaxSource" : URL,
	"aoColumns" : aoColumns
    });

    if(placeHolder)
    {
	$('#dynamic-table_filter input').attr('placeholder',  placeHolder);
    }



};