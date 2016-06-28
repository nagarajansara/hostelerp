<#include "lib/header.ftl"> <#include "lib/leftsidebar.ftl">
<link href="${baseURL}/assest/plugin/cfdatatable.css" rel="stylesheet" />
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/selecttwo/select_min.css" media="screen" />
<section id="main-content" class="hfmsMainContent">
	<section class="wrapper">
		<div class="row hfmsShowHostelDataRow">
			<div class="col-md-12">
				<section class="panel">
					<header class="panel-heading"> Hostel Lists </header>
					<button type="button" class="btn btn-success hfmsAddNewBtn">ADD
						NEW</button>
					<div class="panel-body">
						<table id="dynamic-table" width="100%"
							class="pkDataCommonTable table table-striped table-bordered dt-responsive nowrap">
							<thead>
								<tr>
									<th>Name</th>
									<th>College Name</th>
									<th>Mobile No</th>
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
					<header class="panel-heading"> Hostel Details </header>
					<button type="button" class="btn btn-success hfmsShowUsresBut">Show
						Hostel</button>
					<div class="panel-body">
						<div class="position-center">
							<form role="form" class="hrfsSubmitHostelsForm"
								data-parsley-validate="">
								<div class="form-group">
									<label for="exampleInputEmail1">Hostel Name</label> <input
										type="text" class="form-control hfmsHostelName"
										placeholder="Hostel Name" jsonKey="name" required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">College Name</label><input
										style="width: 100%;" type="hidden" name="hostel"
										id="hfmsCollegeName">
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Address</label>
									<textarea rows="6" class="form-control hfmsHostelAddress"
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
										class="form-control m-bot15 hfmsCountry hfmsCommonHostelSelectize"  placeholder="Select Country" data-parsley-required jsonKey="country">
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


		<div class="row hfmsUpdateRow" style="display: none">
			<div class="col-md-12">
				<section class="panel">
					<header class="panel-heading"> Update Hostel Details </header>
					<button type="button" class="btn btn-success hfmsShowUsresBut">Show
						Hostel</button>
					<div class="panel-body">
						<div class="position-center">
							<form role="form" class="hrfsUpdateHostelsForm"
								data-parsley-validate="">
								<div class="form-group">
									<label for="exampleInputEmail1">Hostel Name</label> <input
										type="text" class="form-control hfmsHostelName"
										placeholder="Hostel Name" jsonKey="name" required>
								</div>
								<div class="form-group hfmsCollegeNameParent"></div>
								<div class="form-group">
									<label for="exampleInputEmail1">Address</label>
									<textarea rows="6" class="form-control hfmsHostelAddress"
										required jsonKey="address"></textarea>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Mobile No</label> <input
										type="text" class="form-control hfmsMobileNo"
										placeholder="Mobile No" jsonKey="mobileno" required>
								</div>
								<div class="form-group hfmsStateParent"></div>
								<div class="form-group hfmsCityParent"></div>
								<div class="form-group">
									<label for="exampleInputEmail1">Select Country</label> <select
										class="form-control m-bot15 hfmsUpdateCountry hfmsCommonHostelSelectize" placeholder="Select Country" data-parsley-required jsonKey="country">
										<option value=""></option>
										<option value="india">India</option>
									</select>
								</div>
								<input type="hidden" jsonKey="id" class="hfmsUpdateStudentId">
								<button class="btn btn-primary" type="submit">Update</button>
								<img class="hfmsLoader" src="${baseURL}/assest/img/index.gif" />
							</form>
						</div>
					</div>
				</section>
			</div>
		</div>

		<!-- UPDATE USER FORM  -->

	</section>
</section>


<#include "lib/footer.ftl">
<script src="${baseURL}/assest/plugin/cfdatatable.js"></script>
<script src="${baseURL}/assest/plugin/selecttwo/select_min.js"></script>
<script src="${baseURL}/assest/plugin/numeric.min.js"></script>
<script src="${baseURL}/assest/plugin/getjsonparams.js"></script>
<script type="text/javascript">
    $(".hfmsLeftSideMenu li a").removeClass("active");
    $(".hfms_Hostel").addClass("active");
    localStorage.setItem("menuId", $(".hfms_Hostel").attr('pk_id'));

    $(".hfmsMobileNo").numeric();
    $(".hfmsUpdatedMobileNo").numeric();

    hfmsSetSelect2("#hfmsCity", 'http://' + location.host + '/'
	    + ctDAO.CONTEXT_NAME + '/api/manager/getCityApi.json',
	    'Type your city');
    hfmsSetSelect2("#hfmsState", 'http://' + location.host + '/'
	    + ctDAO.CONTEXT_NAME + '/api/manager/getStateApi.json',
	    'Type your state');

    hfmsSetSelect2("#hfmsCollegeName", 'http://' + location.host + '/'
	    + ctDAO.CONTEXT_NAME + '/api/manager/getCollegeNameApi.json',
	    'Type your college name');

    $(".hfmsAddNewBtn").click(function() {
	$(".hfmsAddRow").show();
	$(".hfmsShowHostelDataRow").hide();
    });

    $(".hfmsShowUsresBut").click(function() {
	$(".hfmsAddRow").hide();
	$(".hfmsUpdateRow").hide();
	$(".hfmsShowHostelDataRow").show();
    });


    $(".hfmsCommonHostelSelectize").selectize({
	    sortField:
	    {
		     field: "text",
		     direction: "asc"
	    }
	});

    var aoColumns = [ {
	"mData" : "name",
	'bSortable' : false
    }, {
	"mData" : "collegeName",
	'bSortable' : false
    }, {
	"mData" : "mobileno",
	'bSortable' : false
    }/* , {
	"mData" : "address",
	'bSortable' : false
    } */, {
	"mData" : "state",
	'bSortable' : false
    }, {
	"mData" : "city",
	'bSortable' : false
    }, {
	"mData" : "country",
	'bSortable' : false
    }, {
	"mData" : "editbtn",
	'bSortable' : false
    }, ];

    hfmsSetDataTableValues("#dynamic-table", "http://" + location.host
	    + "${baseURL}/api/manager/getHostels", aoColumns);
    $(document).on("click", ".hfmsInfo", function(){
	    var hostelId = $(this).attr("pk_id"),
	    		URL = "http://"+ location.host + "${baseURL}/api/manager/getHostelViaId?hostelId="+hostelId;
	    window.open(URL, '_blank');
	});
    	$(document).on('click', ".hfmsDelWorker", function() {
			if(confirm("Conform to delete!"))
			    {
				    var hostelId = $(this).attr("pk_id"), param = {
					    "hostelId" : hostelId
					};
				    ctDAO.deleteHostelViaId(param, function(data) {
						if(data
							    && data.responseStatus == bmpUtil.RESPONSE_STATUS)
						    {
								alert("Deleted successfully");
								bmpUtil.reLoad();
						    }
						else
						    {
								alert(data.responseMsg);
								bmpUtil.reLoad();
						    }
				    })
			    }
    });
    $(document).on('click', ".hfmsInfo", function(){

    });
    $(document).on('click', ".hfmsEditWorker", function() {
		$(".hfmsAddRow").hide();
		$(".hfmsShowHostelDataRow").hide();
		$(".hfmsUpdateRow").show();
		var hostelId = $(this).attr("pk_id"), param = {
		    "hostelId" : hostelId
		};
		ctDAO.getHostelViaId(param, function(data) {
		    var config = {};
		    var responseData = data.responseData;
		    config["jqSelector"] = ".hrfsUpdateHostelsForm";
		    config["data"] = responseData[0];
		    var constJSONParam = new ConstJSONParam(config);
		    constJSONParam.setParamValue();
		    $(".hfmsCityParent").empty();
		    $(".hfmsStateParent").empty();
		    $(".hfmsCollegeNameParent").empty();
			$(".hfmsCityParent").append("<label for=\"exampleInputEmail1\">City</label><input style=\"width: 100%;\" type=\"hidden\" data-option=\""+ responseData[0].city + "\" id=\"hfmsUpdatedCity\" value=\""+ responseData[0].city + "\">");
		   	$(".hfmsStateParent").append("<label for=\"exampleInputEmail1\">State</label><input style=\"width: 100%;\" type=\"hidden\" data-option=\""+ responseData[0].state + "\" id=\"hfmsUpdatedState\" value=\""+ responseData[0].state + "\">");
		   	$(".hfmsCollegeNameParent").append("<label for=\"exampleInputEmail1\">College Name</label><input style=\"width: 100%;\" type=\"hidden\" data-option=\""+ responseData[0].collegeName + "\" id=\"hfmsUpdatedCollegeName\" value=\""+ responseData[0].collegeName + "\">");
		   	 hfmsSetSelect2("#hfmsUpdatedCity", 'http://' + location.host + '/'+ ctDAO.CONTEXT_NAME + '/api/manager/getCityApi.json', 'Select city');
		   	 hfmsSetSelect2("#hfmsUpdatedState", 'http://' + location.host + '/'+ ctDAO.CONTEXT_NAME + '/api/manager/getStateApi.json','Select state');
		   	 hfmsSetSelect2("#hfmsUpdatedCollegeName", 'http://' + location.host + '/'+ ctDAO.CONTEXT_NAME + '/api/manager/getCollegeNameApi.json','Select college name');
		   	var selectize = $(".hfmsUpdateCountry")[0].selectize;
		   	selectize.setValue(responseData[0].country);
		    $(
			    "#hfmsUpdatedCollegeName")
			    .select2(
				    'val',
				    responseData[0].collegeid);
		});
    });
	$(".hrfsUpdateHostelsForm").submit(function(event){
	    event.preventDefault();
	    if($('.hrfsUpdateHostelsForm').parsley().validate())
		{
		 	var stateObj = $('#hfmsUpdatedState').select2('data');
		    var cityObj = $('#hfmsUpdatedCity').select2('data');
		    var collegeObj = $('#hfmsUpdatedCollegeName').select2( 'data');
		    var city;
		    var state, colleneName, config = {};
		    if(stateObj && cityObj && collegeObj )
			{
				state = stateObj.text;
				city = cityObj.text;
				colleneName = collegeObj.id;
				config.jqSelector = ".hrfsUpdateHostelsForm";
				var constJSONParam = new ConstJSONParam(config);
				var paramMap = constJSONParam.getParamsValue();
				paramMap["city"] = city;
				paramMap["state"] = state;
				paramMap["collegeid"] = colleneName;
				ctDAO.updateHostelViaId(paramMap, function(data){
				    if (data
					    && data.responseStatus == bmpUtil.RESPONSE_STATUS)
				    {
						alert("Update successfully");
						bmpUtil.reLoad();
				    } else
				    {
						alert(data.responseMsg);
						bmpUtil.reLoad();
				    }
				});

			}
		    else
			{
				alert("Please fill all the details");
			}
		}
	});
    $(".hrfsSubmitHostelsForm")
	    .submit(
		    function(event) {
			event.preventDefault();
			if ($('.hrfsSubmitHostelsForm').parsley().validate()) {
			    var stateObj = $('#hfmsState').select2('data');
			    var cityObj = $('#hfmsCity').select2('data');
			    var collegeObj = $('#hfmsCollegeName').select2(
				    'data');

			    var city;
			    var state, colleneName, config = {};
			    if (stateObj && cityObj && collegeObj) {
				state = stateObj.text;
				city = cityObj.text;
				colleneName = collegeObj.id;
				config.jqSelector = ".hrfsSubmitHostelsForm";
				var constJSONParam = new ConstJSONParam(config);
				var paramMap = constJSONParam.getParamsValue();
				paramMap["city"] = city;
				paramMap["state"] = state;
				paramMap["collegeid"] = colleneName;
				ctDAO
					.addHostel(
						paramMap,
						function(data) {
						    if (data
							    && data.responseStatus == bmpUtil.RESPONSE_STATUS) {
							alert("Hostel added successfully");
							bmpUtil.reLoad();
						    } else {
								alert(data.responseMsg);
								bmpUtil.reLoad();
						    }
						});

			    } else {
				alert("Please fill all the details");
			    }
			}

		    });
</script>
