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
									<th>College Name</th>
									<th>Roll No</th>
									<th>Mess Type</th>
									<th>Mobile No</th>
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
										placeholder="Student Name" jsonKey="name" required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">College Name</label><input
										style="width: 100%;" type="hidden" name="collgename"
										class="hfmsCollegeName">
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Roll No</label> <input
										type="text" class="form-control hfmsRollno"
										placeholder="RollNo" jsonKey="rollno" required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Batch</label> <input
										type="text" class="form-control hfmsBatch" placeholder="Batch"
										jsonKey="batch" required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Mess Type</label> <select
										class="form-control m-bot15 hfmsMessType hfmsCommonStudentSelectize" jsonKey="messtype" placeholder="Select Mess" data-parsley-required>
										<option value=""></option>
										<option value="veg">Veg</option>
										<option value="nonveg">Non Veg</option>
									</select>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Select Course</label> <select
										class="form-control m-bot15 hfmsStudentCourse hfmsCommonStudentSelectize"
										jsonKey="course" placeholder="Select Course" data-parsley-required>
										<option value=""></option>
										<option value="mca">MCA</option>
										<option value="mba">MBA</option>
									</select>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Address</label>
									<textarea rows="6" class="form-control hfmsStudentAddress"
										required jsonKey="address"></textarea>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Mobile No</label> <input
										type="text" class="form-control hfmsMobileNo"
										placeholder="Mobile No" jsonKey="mobileno" required>
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
										class="form-control m-bot15 hfmsCountry hfmsCommonStudentSelectize" jsonKey="country" placeholder="Select Country" data-parsley-required>
										<option value=""></option>
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
		<div class="row hfmsUpdateRow" style="display: none">
			<div class="col-md-12">
				<section class="panel">
					<header class="panel-heading"> Student Details </header>
					<button type="button" class="btn btn-success hfmsShowUsresBut">Show
						Student</button>
					<div class="panel-body">
						<div class="position-center">
							<form role="form" class="hrfsSubmitStudentsUpdateForm"
								data-parsley-validate="">
								<div class="form-group">
									<label for="exampleInputEmail1">Student Name</label> <input
										type="text" class="form-control hfmsStudentUpdatedName"
										placeholder="Student Name" jsonKey="name" required>
								</div>
								<div class="form-group hfmsCollegeNameParent"></div>
								<input type="hidden" jsonKey="id" class="hfmsUpdateStudentId">
								<div class="form-group">
									<label for="exampleInputEmail1">Roll No</label> <input
										type="text" class="form-control hfmsUpdatedRollno"
										placeholder="RollNo" required jsonKey="rollno">
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Batch</label> <input
										type="text" class="form-control hfmsUpdateBatch"
										placeholder="Batch" jsonKey="batch" required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Mess Type</label> <select
										class="form-control m-bot15 hfmsUpdatedMessType hfmsCommonStudentSelectize"
										jsonKey="messtype" placeholder="Select Mess" data-parsley-required>
										<option value=""></option>
										<option value="veg">Veg</option>
										<option value="nonveg">Non Veg</option>
									</select>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Select Course</label> <select
										class="form-control m-bot15 hfmsUpdatedStudentCourse hfmsCommonStudentSelectize"
										jsonKey="course" placeholder="Select Course" data-parsley-required>
										<option value=""></option>
										<option value="mca">MCA</option>
										<option value="mba">MBA</option>
									</select>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Address</label>
									<textarea rows="6"
										class="form-control hfmsUpdatedStudentAddress" required
										jsonKey="address"></textarea>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Mobile No</label> <input
										type="text" class="form-control hfmsUpdatedMobileNo"
										placeholder="Mobile No" required jsonKey="mobileno">
								</div>
								<div class="form-group hfmsStateParent"></div>
								<div class="form-group hfmsCityParent"></div>
								<div class="form-group">
									<label for="exampleInputEmail1">Select Country</label> <select
										class="form-control m-bot15 hfmsUpdatedCountry hfmsCommonStudentSelectize"
										jsonKey="country" placeholder="Select Country">
										<option value=""></option>
										<option value="india">India</option>
									</select>
								</div>
								<button class="btn btn-primary" type="submit">Update</button>
								<img class="hfmsLoader" src="${baseURL}/assest/img/index.gif" />
							</form>
						</div>
					</div>
				</section>
			</div>
		</div>
		<!-- UPDATE USER FORM  END -->
	</section>
</section>


<#include "lib/footer.ftl">
<script src="${baseURL}/assest/plugin/cfdatatable.js"></script>
<script src="${baseURL}/assest/plugin/selecttwo/select_min.js"></script>
<script src="${baseURL}/assest/plugin/numeric.min.js"></script>
<script src="${baseURL}/assest/plugin/getjsonparams.js"></script>
<script type="text/javascript">
    $(".hfmsLeftSideMenu li a").removeClass("active");
    $(".hfms_Student").addClass("active");
    localStorage.setItem("menuId", $(".hfms_Student").attr('pk_id'));

    $(".hfmsMobileNo").numeric();
    $(".hfmsUpdatedMobileNo").numeric();


    $(".hfmsCommonStudentSelectize").selectize({
	    sortField:
	    {
		     field: "text",
		     direction: "asc"
	    }
	});

    hfmsSetSelect2("#hfmsCity", 'http://' + location.host + '/'
	    + ctDAO.CONTEXT_NAME + '/api/manager/getCityApi.json', 'Type your city name');
    hfmsSetSelect2("#hfmsState", 'http://' + location.host + '/'
	    + ctDAO.CONTEXT_NAME + '/api/manager/getStateApi.json', 'Type your state name');

    hfmsSetSelect2(".hfmsCollegeName", 'http://' + location.host + '/'
	    + ctDAO.CONTEXT_NAME + '/api/manager/getCollegeNameApi.json', 'Type your college name');

    var aoColumns = [ {
	"mData" : "name",
	'bSortable' : false
    }, {
	"mData" : "collegeName",
	'bSortable' : false
    },{
	"mData" : "rollno",
	'bSortable' : false
    }, {
	"mData" : "messtype",
	'bSortable' : false
    }, {
	"mData" : "mobileno",
	'bSortable' : false
    }, {
	"mData" : "editBtn",
	'bSortable' : false
    }, ];

    hfmsSetDataTableValues("#dynamic-table", "http://" + location.host
	    + "${baseURL}/api/manager/getStudent", aoColumns, 'Student name');

    $(document)
	    .ready(
		    function() {

			$(".hfmsAddNewBtn").click(function() {
			    $(".hfmsAddRow").show();
			    $(".hfmsShowStudentDataRow").hide();
			});

			$(".hfmsShowUsresBut").click(function() {
			    $(".hfmsAddRow").hide();
			    $(".hfmsUpdateRow").hide();
			    $(".hfmsShowStudentDataRow").show();
			});

			$(document)
				.on(
					"click",
					".hfmsInfo",
					function() {
					    var studentId = $(this).attr(
						    "pk_id"), URL = "http://"
						    + location.host
						    + "${baseURL}/api/manager/getStudentsViaId?studentId="
						    + studentId;
					    window.open(URL, '_blank');
					});

			$(document)
				.on(
					"click",
					".hfmsDelWorker",
					function() {
					    if (confirm("Conform to delete!")) {
						var userId = $(this).attr(
							"pk_id"), param = {};
						param = {
						    "studentId" : userId
						};
						ctDAO
							.deleteStudent(
								param,
								function(data) {
								    if (data
									    && data.responseStatus == bmpUtil.RESPONSE_STATUS) {
									alert("Student delete successfully");
									bmpUtil.reLoad();
								    } else {
									alert(data.responseMsg);
								    }
								});
					    }

					});

			$(document)
				.on(
					"click",
					".hfmsEditWorker",
					function() {
					    $(".hfmsAddRow").hide();
					    $(".hfmsShowStudentDataRow").hide();
					    $(".hfmsUpdateRow").show();
					    var userId = $(this).attr("pk_id"), param = {
						"studentId" : userId
					    };
					    var config = {};
					    ctDAO
						    .getStudentViaId(
							    param,
							    function(data) {
								if (data
									&& data.responseStatus == bmpUtil.RESPONSE_STATUS) {
								    var responseData = data.responseData;
								    config["jqSelector"] = ".hrfsSubmitStudentsUpdateForm";
								    config["data"] = responseData[0];
								    var constJSONParam = new ConstJSONParam(
									    config);
								    constJSONParam
									    .setParamValue();

								    $(
									    ".hfmsCityParent")
									    .empty();
								    $(
									    ".hfmsStateParent")
									    .empty();
								    $(".hfmsCollegeNameParent").empty();

								    $(
								    ".hfmsCollegeNameParent")
								    .append(
									    "<label>College Name</label><input style=\"width: 100%;\" type=\"hidden\" data-option=\""
										    + responseData[0].collegeName
										    + "\" id=\"hfmsUpdatedCollegeName\" value=\""
										    + responseData[0].collegeName
										    + "\">");
								    $(
									    ".hfmsCityParent")
									    .append(
										    "<label>City</label><input style=\"width: 100%;\" type=\"hidden\" data-option=\""
											    + responseData[0].city
											    + "\" id=\"hfmsUpdatedCity\" value=\""
											    + responseData[0].city
											    + "\">");

								    $(
									    ".hfmsStateParent")
									    .append(
										    "<label>State</label><input style=\"width: 100%;\" type=\"hidden\" data-option=\""
											    + responseData[0].state
											    + "\" id=\"hfmsUpdatedState\" value=\""
											    + responseData[0].state
											    + "\">");

								    hfmsSetSelect2(
									    "#hfmsUpdatedState",
									    'http://'
										    + location.host
										    + '/'
										    + ctDAO.CONTEXT_NAME
										    + '/api/manager/getStateApi.json', "Type your state");
								    hfmsSetSelect2(
									    "#hfmsUpdatedCity",
									    'http://'
										    + location.host
										    + '/'
										    + ctDAO.CONTEXT_NAME
										    + '/api/manager/getCityApi.json', "Type your city");
								    hfmsSetSelect2(
									    "#hfmsUpdatedCollegeName",
									    'http://'
										    + location.host
										    + '/'
										    + ctDAO.CONTEXT_NAME
										    + '/api/manager/getCollegeNameApi.json', "Type your collge name");

								    $(
									    "#hfmsUpdatedState")
									    .select2(
										    'val',
										    responseData[0].state);
								    $(
									    "#hfmsUpdatedCity")
									    .select2(
										    'val',
										    responseData[0].city);
								    $(
									    "#hfmsUpdatedCollegeName")
									    .select2(
										    'val',
										    responseData[0].collegeid);


								    var selectize = $(".hfmsUpdatedMessType")[0].selectize;
									selectize.setValue(responseData[0].messtype);
								    selectize = $(".hfmsUpdatedStudentCourse")[0].selectize;
									selectize.setValue(responseData[0].course);
								    selectize = $(".hfmsUpdatedCountry")[0].selectize;
									selectize.setValue(responseData[0].country);


								} else {
								    alert(data.responseMsg);
								}
							    });
					});

			$(".hrfsSubmitStudentsUpdateForm")
				.submit(
					function(event) {
					    event.preventDefault();
					    if ($(
						    '.hrfsSubmitStudentsUpdateForm')
						    .parsley().validate()) {
						var stateObj = $(
							'#hfmsUpdatedState')
							.select2('data');
						var cityObj = $(
							'#hfmsUpdatedCity')
							.select2('data');
						var collegeObj = $('#hfmsUpdatedCollegeName').select2('data');
						var city, collegeId;
						var state, config = {};
						if (stateObj && cityObj && collegeObj) {
						    state = stateObj.text;
						    city = cityObj.text;
						    collegeId = collegeObj.id;
						    config.jqSelector = ".hrfsSubmitStudentsUpdateForm";
						    var constJSONParam = new ConstJSONParam(
							    config);
						    var paramMap = constJSONParam
							    .getParamsValue();
						    paramMap["city"] = city;
						    paramMap["state"] = state;
						    paramMap["collegeid"] = collegeId;
						    ctDAO
							    .updateStudensViaId(
								    paramMap,
								    function(
									    data) {
									if (data
										&& data.responseStatus == bmpUtil.RESPONSE_STATUS) {
									    alert("Updated successfully");
									    bmpUtil.reLoad();
									} else {
									    alert(data.responseMsg);
									    bmpUtil.reLoad();
									}
								    })
						} else {
						    alert("Please fill all the details");
						}
					    } else {

					    }
					});

			$(".hrfsSubmitStudentsForm")
				.submit(
					function(event) {
					    event.preventDefault();
					    if ($('.hrfsSubmitStudentsForm')
						    .parsley().validate()) {
						var stateObj = $('#hfmsState')
							.select2('data');
						var cityObj = $('#hfmsCity')
							.select2('data');
						var collegeObj = $('.hfmsCollegeName').select2('data');
						var city;
						var collegeId;
						var state, config = {};
						if (stateObj && cityObj && collegeObj)
						{

						    state = stateObj.text;
						    city = cityObj.text;
						    collegeId = collegeObj.id;

						}
						if (state && state.length > 0
							&& city
							&& city.length > 0 && collegeId)
						{
						    config.jqSelector = ".hrfsSubmitStudentsForm";
						    var constJSONParam = new ConstJSONParam(
							    config);
						    var paramMap = constJSONParam
							    .getParamsValue();
						    paramMap["state"] = state;
						    paramMap["city"] = city;
						    paramMap["collegeid"] = collegeId;
						    ctDAO
							    .addStudent(
								    paramMap,
								    function(
									    data) {
									if (data
										&& data.responseStatus == bmpUtil.RESPONSE_STATUS) {
									    alert("Student added successfully");
									    bmpUtil.reLoad();
									} else {
									    alert(data.responseMsg);
									    bmpUtil.reLoad();
									}
								    });
						} else
						{
						    alert("Please fill all the details");
						}
					    }
					});

		    });
</script>
