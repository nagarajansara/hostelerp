<#include "lib/header.ftl"> <#include "lib/leftsidebar.ftl">
<link href="${baseURL}/assest/plugin/cfdatatable.css" rel="stylesheet" />
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/selecttwo/select_min.css" media="screen" />
<section id="main-content" class="hfmsMainContent">
	<section class="wrapper">
		<div class="row hfmsShowStudentDataRow">
			<div class="col-md-12">
				<section class="panel">
					<header class="panel-heading"> Student Lists </header>
					<button type="button" class="btn btn-success hfmsAddNewBtn">ADD
						NEW</button>
					<div class="panel-body">
						<table id="dynamic-table" width="100%"
							class="pkDataCommonTable table table-striped table-bordered dt-responsive nowrap">
							<thead>
								<tr>
									<th>Student Name</th>
									<th>Roll No</th>
									<th>Batch</th>
									<th>Course</th>
									<th>Mess Type</th>
									<th>State</th>
									<th>City</th>
									<th>Country</th>
									<th>Manage</th>
								</tr>
							</thead>
						</table>
					</div>
				</section>
			</div>
		</div>
		<!-- page start-->

		<!-- ADD USER FORM  -->
		<div class="row hfmsAddRow" style="display: none">
			<div class="col-md-12">
				<section class="panel">
					<header class="panel-heading"> Student Details </header>
					<button type="button" class="btn btn-success hfmsShowUsresBut">Show
						Student</button>
					<div class="panel-body">
						<div class="position-center">
							<form role="form" class="hrfsSubmitStudentsForm"
								data-parsley-validate="">
								<div class="form-group">
									<label for="exampleInputEmail1">Student Name</label> <input
										type="text" class="form-control hfmsStudentName"
										placeholder="Student Name" required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Roll No</label> <input
										type="text" class="form-control hfmsRollno"
										placeholder="RollNo" required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Mess Type</label> <select
										class="form-control m-bot15 hfmsMessType">
										<option value="veg">Veg</option>
										<option value="nonveg">Non Veg</option>
									</select>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Select Course</label> <select
										class="form-control m-bot15 hfmsStudentCourse">
										<option value="mca">MCA</option>
										<option value="mba">MBA</option>
									</select>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Address</label>
									<textarea rows="6" class="form-control hfmsStudentAddress" required></textarea>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">State</label> <input
										style="width: 100%;" type="hidden" name="locationid"
										id="hfmsState">
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">City</label> <input
										style="width: 100%;" type="hidden" name="locationid"
										id="hfmsCity">
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Select Country</label> <select
										class="form-control m-bot15 hfmsCountry">
										<option value="india">India</option>
									</select>
								</div>
								<button class="btn btn-primary" type="submit">Submit</button>
								<img class="hfmsLoader" src="${baseURL}/assest/img/index.gif" />
							</form>
						</div>
					</div>
				</section>
			</div>
		</div>
		<!-- ADD USER FORM  END -->

		<!-- UPDATE USER FORM  -->

		<!-- UPDATE USER FORM  END -->
	</section>
</section>


<#include "lib/footer.ftl">
<script src="${baseURL}/assest/plugin/cfdatatable.js"></script>
<script src="${baseURL}/assest/plugin/selecttwo/select_min.js"></script>
<script type="text/javascript">
    $(".hfmsLeftSideMenu li a").removeClass("active");
    $(".hfms_Student").addClass("active");
    localStorage.setItem("menuId", $(".hfms_Student").attr('pk_id'));


	 var remoteDataConfig = {
	dropdownCssClass : 'bmSelect2Class',
	cache : "true",
	placeholder : "Select your state",
	minimumInputLength : 2,
	ajax : {
	    url : 'http://' + location.host + '/' + ctDAO.CONTEXT_NAME
		    + '/api/manager/getStateApi.json',
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

    $("#hfmsState").select2(remoteDataConfig);

	    var remoteCityDataConfig = {
		dropdownCssClass : 'bmSelect2Class',
		cache : "true",
		placeholder : "Select your city",
		minimumInputLength : 2,
		ajax : {
		    url : 'http://' + location.host + '/' + ctDAO.CONTEXT_NAME
			    + '/api/manager/getCityApi.json',
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

	$("#hfmsCity").select2(remoteCityDataConfig);



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

    $(document)
	    .ready(
		    function() {


		    $(".hfmsAddNewBtn").click(function(){
				$(".hfmsAddRow").show();
				$(".hfmsShowStudentDataRow").hide();
		    });

		    $(".hfmsShowUsresBut").click(function(){
				$(".hfmsAddRow").hide();
				$(".hfmsUpdateRow").hide();
				$(".hfmsShowStudentDataRow").show();
		    });

		    $(document).on("click", ".hfmsDelWorker", function(){
		    	if(confirm("Conform to delete!"))
		    	{
					var userId = $(this).attr("pk_id"),
						param = {};
					param = {
						"userId": userId
					};
					  ctDAO.deleteStudents(param, function(data) {
						if (data && data.responseStatus == bmpUtil.RESPONSE_STATUS) {
						    alert("Student delete successfully");
						} else {
						    alert(data.responseMsg);
						}
					   });
				}

		    });

		    $(document).on("click", ".hfmsEditWorker", function(){
		    	$(".hfmsAddRow").hide();
				$(".hfmsShowStudentDataRow").hide();
				$(".hfmsUpdateRow").show();
		    	var userId = $(this).attr("pk_id"),
		    		param = {
		    			"userId": userId
		    		};
				ctDAO.getStudentsViaId(param, function(data){
					if(data && data.responseData)
					{
						for(var i = 0; i < data.responseData.length; i++)
						{
							$(".hfmsStudentUpdateName").val(data.responseData[i].username);
							$(".hfmsUpdateFirstName").val(data.responseData[i].firstname);
							$(".hfmsUpdateStudentType option[value="+ data.responseData[i].usertype +"]").prop('selected', true);
							$(".hfmsStudentId").val(data.responseData[i].userid);
						}

					}
					else
					{
						alert(data.responseMsg);
					}
				});
		    });

			 $(".hrfsSubmitStudentsUpdateForm").submit(function(event) {
				event.preventDefault();
				if ($('.hrfsSubmitStudentsUpdateForm').parsley().validate()) {
				    var param = {
						"userName" : $(".hfmsStudentUpdateName").val(),
						"firstname" : $(".hfmsUpdateFirstName").val(),
						"usertype" : $(".hfmsUpdateStudentType option:selected").val(),
						"userId": $(".hfmsStudentId").val()
				    };
				    ctDAO.updateStudentsViaId(param, function(data) {
					if (data && data.responseStatus == bmpUtil.RESPONSE_STATUS) {
					    	alert("Student updated successfully");
					} else {
					   if(data.responseStatus == bmpUtil.INVALID_LOGIN_STATUS)
					   {
							alert("Student already registered");
					   }
					   else
					   {
					    	alert(data.responseMsg);
					   }
					}
				    });
				}
			    });

			     $(".hrfsSubmitStudentsForm").submit(function(event) {
					event.preventDefault();
					if ($('.hrfsSubmitStudentsForm').parsley().validate()) {
						var stateObj = $('#hfmsState').select2('data');
						var cityObj = $('#hfmsCity').select2('data');
						var city;
						var state;
						if(stateObj && cityObj)
						{
							state = stateObj.text;
							city = cityObj.text;
						}
						if(state.length > 0 && city.length > 0)
						{
							var paramMap = {
								"name": $(".hfmsStudentName").val(),
								"rollno": $(".hfmsRollno ").val(),
								"batch": $(".hfmsStudentCourse option:selected").val(),
								"course": $(".hfmsStudentCourse option:selected").val(),
								"messtype": $(".hfmsMessType option:selected").val(),
								"address": $(".hfmsStudentAddress").val(),
								"state": state,
								"city": city,
								"country":  $(".hfmsCountry option:selected").val()
							};
						}
					}
			    });

			$("#dynamic-table")
				.dataTable(
					{
					    "bProcessing" : true,
					    "bServerSide" : true,
					    "responsive": true,
					    "sort" : "position",
					    //bStateSave variable you can use to save state on client cookies: set value "true"
					    "bStateSave" : false,
					    //Default: Page display length
					    "iDisplayLength" : 10,
					    //We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
					    "iDisplayStart" : 0,
					    "fnDrawCallback" : function() {
						//Get page numer on client. Please note: number start from 0 So
						//for the first page you will see 0 second page 1 third page 2...
						//Un-comment below alert to see page number
						//alert("Current page number: "+this.fnPagingInfo().iPage);
					    },
					    "sAjaxSource" : "http://"
						    + location.host
						    + "${baseURL}/api/manager/getStudent",
					    "aoColumns" : [ {
						"mData" : "name",
						'bSortable' : false
					    }, {
						"mData" : "rollno",
						'bSortable' : false
					    },{
						"mData" : "batch",
						'bSortable' : false
					    },{
						"mData" : "course",
						'bSortable' : false
					    },{
						"mData" : "messtype",
						'bSortable' : false
					    }
					    ,{
						"mData" : "state",
						'bSortable' : false
					    },{
						"mData" : "city",
						'bSortable' : false
					    },
					    {
						"mData" : "country",
						'bSortable' : false
					    },

					    ]
					});

		    });


</script>
