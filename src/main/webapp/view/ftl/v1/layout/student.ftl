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
										class="form-control m-bot15 hfmsMessType" jsonKey="messtype">
										<option value="veg">Veg</option>
										<option value="nonveg">Non Veg</option>
									</select>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Select Course</label> <select
										class="form-control m-bot15 hfmsStudentCourse"
										jsonKey="course">
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
										class="form-control m-bot15 hfmsCountry" jsonKey="country">
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
								<input type="hidden" jsonKey="id" class="hfmsUpdateStudentId">
								<div class="form-group">
									<label for="exampleInputEmail1">Roll No</label> <input
										type="text" class="form-control hfmsUpdatedRollno"
										placeholder="RollNo" required jsonKey="rollno" disabled>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Batch</label> <input
										type="text" class="form-control hfmsUpdateBatch"
										placeholder="Batch" jsonKey="batch" required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Mess Type</label> <select
										class="form-control m-bot15 hfmsUpdatedMessType"
										jsonKey="messtype">
										<option value="veg">Veg</option>
										<option value="nonveg">Non Veg</option>
									</select>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Select Course</label> <select
										class="form-control m-bot15 hfmsUpdatedStudentCourse"
										jsonKey="course">
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
								<div class="form-group hfmsStateParent">

								</div>
								<div class="form-group hfmsCityParent">

								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Select Country</label> <select
										class="form-control m-bot15 hfmsUpdatedCountry"
										jsonKey="country">
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

    hfmsSetSelect2("#hfmsCity", 'http://' + location.host + '/'
	    + ctDAO.CONTEXT_NAME + '/api/manager/getCityApi.json');
    hfmsSetSelect2("#hfmsState", 'http://' + location.host + '/'
	    + ctDAO.CONTEXT_NAME + '/api/manager/getStateApi.json');

    hfmsSetDataTableValues("#dynamic-table", "http://" + location.host
	    + "${baseURL}/api/manager/getStudent");

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

								    $(".hfmsCityParent")
									    .append(
										    "<label for=\"exampleInputEmail1\">City</label><input style=\"width: 100%;\" type=\"hidden\" data-option=\""+ responseData[0].city + "\" id=\"hfmsUpdatedCity\" value=\""+ responseData[0].city + "\">");

								    $(".hfmsStateParent")
									    .append("<label for=\"exampleInputEmail1\">State</label><input style=\"width: 100%;\" type=\"hidden\" data-option=\""+ responseData[0].state + "\" id=\"hfmsUpdatedState\" value=\""+ responseData[0].state + "\">");

								    hfmsSetSelect2(
									    "#hfmsUpdatedState",
									    'http://'
										    + location.host
										    + '/'
										    + ctDAO.CONTEXT_NAME
										    + '/api/manager/getStateApi.json');
								    hfmsSetSelect2(
									    "#hfmsUpdatedCity",
									    'http://'
										    + location.host
										    + '/'
										    + ctDAO.CONTEXT_NAME
										    + '/api/manager/getCityApi.json');

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
						var city;
						var state, config = {};
						if (stateObj && cityObj) {
						    state = stateObj.text;
						    city = cityObj.text;
						    config.jqSelector = ".hrfsSubmitStudentsUpdateForm";
						    var constJSONParam = new ConstJSONParam(
							    config);
						    var paramMap = constJSONParam
							    .getParamsValue();
						    paramMap["city"] = city;
						    paramMap["state"] = state;
						    ctDAO
							    .updateStudensViaId(
								    paramMap,
								    function(
									    data) {
									if (data
										&& data.responseStatus == bmpUtil.RESPONSE_STATUS) {
									    alert("Updated successfully");
									} else {
									    alert(data.responseMsg);
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
						var city;
						var state, config = {};
						if (stateObj && cityObj) {
						    state = stateObj.text;
						    city = cityObj.text;
						}
						if (state && state.length > 0
							&& city
							&& city.length > 0) {
						    config.jqSelector = ".hrfsSubmitStudentsForm";
						    var constJSONParam = new ConstJSONParam(
							    config);
						    var paramMap = constJSONParam
							    .getParamsValue();
						    paramMap["state"] = state;
						    paramMap["city"] = city;
						    ctDAO
							    .addStudent(
								    paramMap,
								    function(
									    data) {
									if (data
										&& data.responseStatus == bmpUtil.RESPONSE_STATUS) {
									    alert("Student added successfully");
									} else {
									    if (data.responseStatus == bmpUtil.INVALID_LOGIN_STATUS) {
										alert("User already registered");
									    } else {
										alert(data.responseMsg);
									    }
									}
								    });
						} else {
						    alert("Please fill all the details");
						}
					    }
					});

		    });
</script>
