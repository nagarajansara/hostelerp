<#include "lib/header.ftl"> <#include "lib/leftsidebar.ftl">
<link href="${baseURL}/assest/plugin/cfdatatable.css" rel="stylesheet" />
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/selecttwo/select_min.css" media="screen" />
<section id="main-content" class="hfmsMainContent">
	<section class="wrapper">
		<div class="row hfmsShowStudentDataRow">
			<div class="col-md-12">
				<section class="panel">
					<header class="panel-heading"> College Lists </header>
					<button type="button" class="btn btn-success hfmsAddNewBtn">ADD
						NEW</button>
					<div class="panel-body">
						<table id="dynamic-table" width="100%"
							class="pkDataCommonTable table table-striped table-bordered dt-responsive nowrap">
							<thead>
								<tr>
									<th>College Name</th>
									<th>Mobile No</th>
									<th>Address</th>
									<th>State</th>
									<th>City</th>
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
					<header class="panel-heading"> College Details </header>
					<button type="button" class="btn btn-success hfmsShowUsresBut">Show
						College</button>
					<div class="panel-body">
						<div class="position-center">
							<form role="form" class="hrfsSubmitCollegeForm"
								data-parsley-validate="">
								<div class="form-group">
									<label for="exampleInputEmail1">College Name</label> <input
										type="text" class="form-control hfmsCollegeName"
										placeholder="College Name" jsonKey="name" required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Mobile No</label> <input
										type="text" class="form-control hfmsMobileNo"
										placeholder="Mobile No" jsonKey="mobileno" required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Address</label>
									<textarea rows="6" class="form-control hfmsCollegeAddress"
										required jsonKey="address"></textarea>
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
										class="form-control m-bot15 hfmsCountry hfmsCommonCollegeSelectize" placeholder="Select Country" data-parsley-required jsonKey="country">
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
					<header class="panel-heading"> College Details </header>
					<button type="button" class="btn btn-success hfmsShowUsresBut">Show
						College</button>
					<div class="panel-body">
						<div class="position-center">
							<form role="form" class="hrfsUpdateCollegeForm"
								data-parsley-validate="">
								<div class="form-group">
									<label for="exampleInputEmail1">College Name</label> <input
										type="text" class="form-control hfmsUpdateCollegeName"
										placeholder="Student Name" jsonKey="name" required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Mobile No</label> <input
										type="text" class="form-control hfmsUpdateMobileNo"
										placeholder="Mobile No" jsonKey="mobileno" required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Address</label>
									<textarea rows="6" class="form-control hfmsUpdateCollegeAddress"
										required jsonKey="address"></textarea>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Select Country</label> <select
										class="form-control m-bot15 hfmsUpdateCountry hfmsCommonCollegeSelectize"  placeholder="Select Country" data-parsley-required jsonKey="country">
										<option value="india">India</option>
									</select>
								</div>
								<div class="form-group hfmsStateParent"></div>
								<div class="form-group hfmsCityParent"></div>
								<input type="hidden" jsonKey="id" class="hfmsUpdateCollegeId">
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
    $(".hfms_College").addClass("active");
    localStorage.setItem("menuId", $(".hfms_College").attr('pk_id'));

    $(".hfmsMobileNo").numeric();
    $(".hfmsUpdatedMobileNo").numeric();

    hfmsSetSelect2("#hfmsCity", 'http://' + location.host + '/'
	    + ctDAO.CONTEXT_NAME + '/api/manager/getCityApi.json', 'Type your city name');
    hfmsSetSelect2("#hfmsState", 'http://' + location.host + '/'
	    + ctDAO.CONTEXT_NAME + '/api/manager/getStateApi.json', 'Type your state name');

    var aoColumns = [ {
	"mData" : "name",
	'bSortable' : false
    }, {
	"mData" : "mobileno",
	'bSortable' : false
    },{
	"mData" : "address",
	'bSortable' : false
    }, {
	"mData" : "state",
	'bSortable' : false
    }, {
	"mData" : "city",
	'bSortable' : false
    }, {
	"mData" : "editBtn",
	'bSortable' : false
    }, ];

    hfmsSetDataTableValues("#dynamic-table", "http://" + location.host
	    + "${baseURL}/api/manager/getColleges", aoColumns);

    $(document).ready(function() {
			$(".hfmsAddNewBtn").click(function() {
			    $(".hfmsAddRow").show();
			    $(".hfmsShowStudentDataRow").hide();
			});

			$(".hfmsShowUsresBut").click(function() {
			    $(".hfmsAddRow").hide();
			    $(".hfmsUpdateRow").hide();
			    $(".hfmsShowStudentDataRow").show();



			});

			$(".hrfsSubmitCollegeForm").submit(function(event){
			    event.preventDefault();
			    if ($('.hrfsSubmitCollegeForm').parsley().validate())
				{
				 	var stateObj = $('#hfmsState').select2('data');
				    var cityObj = $('#hfmsCity').select2('data');
				    var config = {};
					if(stateObj && cityObj)
					    {
					    	var state = stateObj.text;
							var city = cityObj.text;
							config.jqSelector = ".hrfsSubmitCollegeForm";
							var constJSONParam = new ConstJSONParam(config);
							var paramMap = constJSONParam.getParamsValue();
							paramMap["city"] = city;
							paramMap["state"] = state;
							ctDAO.addColleges(paramMap, function(data)
							{
							    if (data
								    && data.responseStatus == bmpUtil.RESPONSE_STATUS)
							    {
									alert("College added successfully");
									bmpUtil.reLoad();
							    }
							    else
							    {
									alert(data.responseMsg);
									bmpUtil.reLoad();
							    }
							});
					    }
					else
					    {
					    	alert("Please enter all the fields");
					    	bmpUtil.reLoad();
					    }
				}

			});

			$(document).on('click', ".hfmsEditBtn", function() {
			    $(".hfmsAddRow").hide();
				$(".hfmsShowStudentDataRow").hide();
				$(".hfmsUpdateRow").show();
				var collegeId = $(this).attr("pk_id"), param = {
				    "id" : collegeId
				};
				ctDAO.getCollegeViaId(param, function(data) {
				    var config = {};
				    var responseData = data.responseData;
				    config["jqSelector"] = ".hrfsUpdateCollegeForm";
				    config["data"] = responseData[0];
				    var constJSONParam = new ConstJSONParam(config);
				    constJSONParam.setParamValue();
				    $(".hfmsCityParent").empty();
				    $(".hfmsStateParent").empty();
				    $(".hfmsCityParent").append("<label for=\"exampleInputEmail1\">City</label><input style=\"width: 100%;\" type=\"hidden\" data-option=\""+ responseData[0].city + "\" id=\"hfmsUpdatedCity\" value=\""+ responseData[0].city + "\">");
				   	$(".hfmsStateParent").append("<label for=\"exampleInputEmail1\">State</label><input style=\"width: 100%;\" type=\"hidden\" data-option=\""+ responseData[0].state + "\" id=\"hfmsUpdatedState\" value=\""+ responseData[0].state + "\">");
				   	hfmsSetSelect2("#hfmsUpdatedCity", 'http://' + location.host + '/'+ ctDAO.CONTEXT_NAME + '/api/manager/getCityApi.json', 'Type city name');
				   	hfmsSetSelect2("#hfmsUpdatedState", 'http://' + location.host + '/'+ ctDAO.CONTEXT_NAME + '/api/manager/getStateApi.json','Type  state name');
				   	var selectize = $(".hfmsUpdateCountry")[0].selectize
				   	selectize.setValue(responseData[0].country);

				});
			});
			$(".hrfsUpdateCollegeForm").submit(function(event){
			    event.preventDefault();
			    var state, colleneName, config = {};
				if($('.hrfsUpdateCollegeForm').parsley().validate())
			    {
				    var stateObj = $('#hfmsUpdatedState').select2('data');
				    var cityObj = $('#hfmsUpdatedCity').select2('data');
				    if(stateObj && cityObj)
					{
						state = stateObj.text;
						city = cityObj.text;
						config.jqSelector = ".hrfsUpdateCollegeForm";
						var constJSONParam = new ConstJSONParam(config);
						var paramMap = constJSONParam.getParamsValue();
						paramMap["city"] = city;
						paramMap["state"] = state;
						ctDAO.updateCollegesViaId(paramMap, function(data){
							if(data && data.responseStatus == bmpUtil.RESPONSE_STATUS)
						    {
							    alert("Update college successfully");
							    bmpUtil.reLoad();
						    }
							else
						    {
							    alert(data.responseMsg);
							    bmpUtil.reLoad();
						    }
						});
					}
			    }
			});
			$(document).on('click', ".hfmsDelBtn", function() {
				if(confirm("Conform to delete!"))
				 {
				    var id = $(this).attr("pk_id"), param = {};
					param = {
						"id": id
					};
					ctDAO.deleteCollegeViaId(param, function(data){
						if(data && data.responseStatus == bmpUtil.RESPONSE_STATUS)
					    {
						    alert("Deleted college successfully");
						    bmpUtil.reLoad();
					    }
						else
					    {
						    alert(data.responseMsg);
						    bmpUtil.reLoad();
					    }
					});
				 }
			});

			  $(".hfmsCommonCollegeSelectize").selectize({
				    sortField:
				    {
					     field: "text",
					     direction: "asc"
				    }
			});


	});
</script>
