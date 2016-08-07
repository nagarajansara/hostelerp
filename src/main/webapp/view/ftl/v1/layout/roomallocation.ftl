<#include "lib/header.ftl"> <#include "lib/leftsidebar.ftl">
<link href="${baseURL}/assest/plugin/cfdatatable.css" rel="stylesheet" />
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/selecttwo/select_min.css" media="screen" />
<style>
	.hfmsDelBtn
	{
		display: none !important;
	}
</style>

<section id="main-content" class="hfmsMainContent">
	<section class="wrapper">
		<div class="row hfmsShowRoomDataRow">
			<div class="col-md-12">
				<section class="panel">
					<header class="panel-heading"> Room Allocation List </header>
					<button type="button" class="btn btn-success hfmsAddNewBtn">ADD
						NEW</button>
					<div class="panel-body">
						<a type="button" target="_blank" href="${baseURL}/api/report/generateReport?type=pdf&filename=hostelerptest" class="btn btn-danger pull-right"><i class="fa fa-print"></i></a>
						<table id="dynamic-table" width="100%"
							class="pkDataCommonTable table table-striped table-bordered dt-responsive nowrap">
							<thead>
								<tr>
									<th>College Name</th>
									<th>Hostel Name</th>
									<th>Student Name</th>
									<th>Roll No</th>
									<th>Status</th>
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
					<header class="panel-heading"> Room Allocation Details </header>
					<button type="button" class="btn btn-success hfmsShowRoomBut">Show
						Room Allocation</button>
					<div class="panel-body">
						<div class="position-center">
							<form role="form" class="hrfsSubmitRoomForm"
								data-parsley-validate="">
								<div class="form-group">
									<label for="inputSuccess">College Name</label>
									<select
										class="form-control hfmsRoomAllocationColName hfmsCommonRoomSelectize" placeholder="Select College" data-parsley-required jsonKey="collegeid">
										<option value=""></option>
										<#if
										model.responseData.college??> <#list
										model.responseData.college as element>
										<option value="${element.id}">${element.name}</option>
										</#list> </#if>
									</select>
								</div>
								<div class="form-group">
									<label for="inputSuccess">Hostel Name</label> <select
										class="form-control hfmsHostelNameSelect hfmsCommonRoomSelectize"
										data-parsley-required jsonKey="hostelid" placeholder="Select Hostel">
										<option value=""></option>
									</select>
								</div>
								<div class="form-group">
									<label for="inputSuccess">Room No</label>
									<select
										class="form-control hfmsRoomNoSelect hfmsCommonRoomSelectize" placeholder="Select Floor" data-parsley-required jsonKey="roomid">
										<option value=""></option>
									</select>
								</div>
								<div class="form-group">
									<label for="inputSuccess">Student Name</label> <select
										class="form-control hfmsStudentNameSelect hfmsCommonRoomSelectize"
										data-parsley-required jsonKey="studentid" placeholder="Select Student">
										<option value=""></option>
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
					<header class="panel-heading"> Room Allocation Details </header>
					<button type="button" class="btn btn-success hfmsShowRoomBut">Show
						Room Allocation</button>
					<div class="panel-body">
						<div class="position-center">
							<form role="form" class="hrfsUpdateSubmitRoomForm"
								data-parsley-validate="">
								<div class="form-group">
									<label for="inputSuccess">College Name</label>
									<select
										class="form-control hfmsUpdateRoomAllocationColName hfmsCommonRoomSelectize" placeholder="Select College" data-parsley-required jsonKey="collegeid">
										<option value=""></option>
										<#if
										model.responseData.college??> <#list
										model.responseData.college as element>
										<option value="${element.id}">${element.name}</option>
										</#list> </#if>
									</select>
								</div>
								<div class="form-group">
									<label for="inputSuccess">Hostel Name</label> <select
										class="form-control hfmsUpdateHostelNameSelect hfmsCommonRoomSelectize"
										data-parsley-required jsonKey="hostelid" placeholder="Select Hostel">
										<option value=""></option>
									</select>
								</div>
								<div class="form-group">
									<label for="inputSuccess">Room No</label>
									<select
										class="form-control hfmsUpdateRoomNoSelect hfmsCommonRoomSelectize" placeholder="Select Floor" data-parsley-required jsonKey="roomid">
										<option value=""></option>
									</select>
								</div>
								<div class="form-group">
									<label for="inputSuccess">Student Name</label> <select
										class="form-control hfmsUpdateStudentNameSelect hfmsCommonRoomSelectize"
										data-parsley-required jsonKey="studentid" placeholder="Select Student">
										<option value=""></option>
									</select>
								</div>
								<input type="hidden" class="hfmsUpdateRoomAllocationId" jsonKey="roomallocationid">
								<div class="form-group">
									<label for="inputSuccess">Status</label>
									<select
										class="form-control hfmsUpdateStatusSelect hfmsCommonRoomSelectize" placeholder="Select Status" data-parsley-required jsonKey="status">
										<option value=""></option>
										<option value="alloted">Alloted</option>
										<option value="vacated">Vacated</option>
										<option value="shifted">Shifted</option>
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
		<!-- UPDATE USER FORM  -->

		<!-- UPDATE USER FORM  -->

	</section>
</section>


<#include "lib/footer.ftl">
<script src="${baseURL}/assest/plugin/cfdatatable.js"></script>
<script src="${baseURL}/assest/plugin/selecttwo/select_min.js"></script>
<script src="${baseURL}/assest/plugin/numeric.min.js"></script>
<script src="${baseURL}/assest/plugin/getjsonparams.js"></script>
<script type="text/javascript">

	var isEditableMode = false;
	var PRE_ROOM_ALLOCATION_STATUS = "alloted";

    $(".hfmsLeftSideMenu li a").removeClass("active");
    $(".hfms_RoomAllocation").addClass("active");
    localStorage.setItem("menuId", $(".hfms_RoomAllocation").attr('pk_id'));


    $(".hfmsAddNewBtn").click(function() {
		$(".hfmsAddRow").show();
		$(".hfmsShowRoomDataRow").hide();
		$(".hfmsUpdateRow").hide();

		var selectize = $(".hfmsRoomAllocationColName")[0].selectize;
		selectize.setValue("");
	});

	$(".hfmsShowRoomBut").click(function() {
			$(".hfmsAddRow").hide();
			$(".hfmsShowRoomDataRow").show();
			$(".hfmsUpdateRow").hide();
		});


    var aoColumns = [ {
	"mData" : "collegename",
	'bSortable' : false
    }, {
	"mData" : "hostelname",
	'bSortable' : false
    }, {
	"mData" : "studentname",
	'bSortable' : false
    }, {
	"mData" : "rollno",
	'bSortable' : false
    }, {
	"mData" : "roomallocationstatus",
	'bSortable' : false
    }, {
	"mData" : "editBtn",
	'bSortable' : false
    }, ];

    hfmsSetDataTableValues("#dynamic-table", "http://" + location.host
	    + "${baseURL}/api/manager/getRoomAllocationDetails", aoColumns, 'College, hostel name');



    $(".hfmsCommonRoomSelectize").selectize({
		    sortField:
		    {
			     field: "text",
			     direction: "asc"
		    }
	});


    $('.hfmsRoomAllocationColName').on('change', function() {
		var collegeId = $(this).val();
		hfmsGetStudentsViaCollegeId(collegeId, '.hfmsStudentNameSelect', '.hfmsRoomNoSelect', '.hfmsHostelNameSelect');
	});
    $('.hfmsHostelNameSelect').on('change', function() {
		var hostelId = $(this).val();
		hfmsGetRoomsViaHostelId(hostelId, '.hfmsRoomNoSelect');
	});
    $('.hfmsUpdateRoomAllocationColName').on('change', function() {
		var collegeId = $(this).val();
		hfmsGetStudentsViaCollegeId(collegeId, '.hfmsUpdateStudentNameSelect', '.hfmsUpdateRoomNoSelect', '.hfmsUpdateHostelNameSelect');
	});
    $('.hfmsUpdateHostelNameSelect').on('change', function() {
		var hostelId = $(this).val();
		hfmsGetRoomsViaHostelId(hostelId, '.hfmsUpdateRoomNoSelect');
	});


    $(document).on('click', ".hfmsEditBtn", function() {
		$(".hfmsAddRow").hide();
		$(".hfmsShowRoomDataRow").hide();
		$(".hfmsUpdateRow").show();
		isEditableMode = true;
		var roomAllocationId = $(this).attr('pk_id');
		var paramMap = {
			"roomAllocationId": roomAllocationId
		};

		ctDAO.getRoomAllocationViaRoomAllocationId(paramMap, function(data){
			if(data && data.responseStatus == bmpUtil.RESPONSE_STATUS)
			    {
					var roomAllocationDetails =  data.responseData.allocations;
					var hostels =  data.responseData.hostels;
					var rooms =  data.responseData.rooms;
					var selectize = $(".hfmsUpdateRoomAllocationColName")[0].selectize;
					selectize.setValue(roomAllocationDetails[0].collegeid);
					selectize.disable();
					$(".hfmsUpdateRoomAllocationId").val(roomAllocationDetails[0].roomallocationid);
					selectize = $(".hfmsUpdateStatusSelect")[0].selectize;
					selectize.setValue(roomAllocationDetails[0].roomallocationstatus);
					PRE_ROOM_ALLOCATION_STATUS = roomAllocationDetails[0].roomallocationstatus;
					$(".hfmsUpdateRoomAllocationId").val(roomAllocationDetails[0].roomallocationid);
					if(hostels)
				    {
					    var selectize = $(".hfmsUpdateHostelNameSelect")[0].selectize
					    selectize.clearOptions();
					    for(var i = 0; i < hostels.length; i++)
					    {
							selectize.addOption({
						             text: hostels[i].name,
						             value: hostels[i].id
						        });
							selectize.setValue(roomAllocationDetails[0].hostelid);
					    }

				    }
					if(rooms)
				    {
					    var selectize = $(".hfmsUpdateRoomNoSelect")[0].selectize;
					    selectize.clearOptions();
					    var isAvailable = false;
					    for(var i = 0; i < rooms.length; i++)
					    {
							if(roomAllocationDetails[0].roomid == rooms[i].roomid)
						    {
							    isAvailable = true;
						    }
							selectize.addOption({
						             text: rooms[i].roomno,
						             value: rooms[i].roomid
						        });
					    }
					    if(!isAvailable)
						{

							selectize.addOption({
						             text: roomAllocationDetails[0].roomno,
						             value: roomAllocationDetails[0].roomid
						        });
						}
					    selectize.setValue(roomAllocationDetails[0].roomid);

				    }
					if(true)
				    {

					    var selectize = $(".hfmsUpdateStudentNameSelect")[0].selectize;
					    selectize.clearOptions();
					    var isAvailable = false;
					 	selectize.addOption({
					             text: roomAllocationDetails[0].studentname,
					             value: roomAllocationDetails[0].studentid
					        });
					    selectize.setValue(roomAllocationDetails[0].studentid);
					    selectize.disable();

				    }
					isEditableMode = false;
			    }
			else
			    {
			    	alert(data.responseMsg);
			    }
		});

    });

    $(".hrfsSubmitRoomForm").submit(function(event) {
		event.preventDefault();
		if ($('.hrfsSubmitRoomForm').parsley().validate()) {
			var config = {};
			config.jqSelector = ".hrfsSubmitRoomForm";
			var constJSONParam = new ConstJSONParam(config);
			var paramMap = constJSONParam.getParamsValue();
			ctDAO.addRoomAllocation(paramMap, function(data){
				if(data && data.responseStatus == bmpUtil.RESPONSE_STATUS)
				    {
						alert("Room alloted successfully");
				    }
				else
				    {
				    	alert(data.responseMsg);
				    }
			});
		}
	});

    $(".hrfsUpdateSubmitRoomForm").submit(function(event) {
		event.preventDefault();
		if ($('.hrfsUpdateSubmitRoomForm').parsley().validate()) {
			var config = {};
			config.jqSelector = ".hrfsUpdateSubmitRoomForm";
			var constJSONParam = new ConstJSONParam(config);
			var paramMap = constJSONParam.getParamsValue();
			paramMap["preStatus"] = PRE_ROOM_ALLOCATION_STATUS;
			ctDAO.updateRoomAllocationViaId(paramMap, function(data){
				if(data && data.responseStatus == bmpUtil.RESPONSE_STATUS)
				    {
						alert("Room allocation updated successfully");
				    }
				else
				    {
				    	alert(data.responseMsg);
				    }
			});
		}
	});



	function hfmsGetStudentsViaCollegeId(collegeId, jqStudentSelector, jqRoomNoSelector, jqHostelSelector)
	{
		if(collegeId && !isEditableMode)
		{
			var param = {
				"collegeId": collegeId
			}
			ctDAO.getStudentViaCollegeId(param, function(data){
				if(data && data.responseStatus == bmpUtil.RESPONSE_STATUS)
				{
					var studentSelectize = $(jqStudentSelector)[0].selectize;
					studentSelectize.clearOptions();

					var roomSelectize = $(jqRoomNoSelector)[0].selectize;
					roomSelectize.clearOptions();

					var hostelSelectize = $(jqHostelSelector)[0].selectize;
					hostelSelectize.clearOptions();

					var responseData = data.responseData.student;
					if(responseData && responseData.length > 0)
					  {

						    for(var i=0; i< responseData.length; i++)
						    {
							     studentSelectize.addOption({
							             text: responseData[i].name,
							             value: responseData[i].id
							        });
						    }
						    studentSelectize.refreshItems();

					  }
					responseData = data.responseData.hostel;
					if(responseData && responseData.length > 0)
					  {

						    for(var  i = 0; i < responseData.length; i++)
						    {
								hostelSelectize.addOption({
								             text: responseData[i].name,
								             value: responseData[i].id
								        });
						    }
						    hostelSelectize.refreshItems();

					  }

				}
				else
				{
					alert(data.responseMsg);
				}
			});
		}
	}
	function hfmsGetHostelsViaCollegeId(collegeId, jqHostelSelector, jqRoomNoSelector)
	{
	    if(collegeId && !isEditableMode)
		{
			var param = {
				"collegeId": collegeId
			}
			ctDAO.getHostelViaCollegeId(param, function(data){
				if(data && data.responseStatus == bmpUtil.RESPONSE_STATUS)
				{
					var hostelSelector = $(jqHostelSelector)[0].selectize;
					hostelSelector.clearOptions();

					var roomSelectize = $(jqRoomNoSelector)[0].selectize;
					roomSelectize.clearOptions();

					var responseData = data.responseData;
					if(responseData && responseData.length > 0)
					  {

						    for(var i=0; i< responseData.length; i++)
						    {
								hostelSelector.addOption({
								             text: responseData[i].name,
								             value: responseData[i].id
								        });
						    }
						    hostelSelector.refreshItems();
					  }

				}
				else
				{
					alert(data.responseMsg);
				}
			});
		}
	}
	function hfmsGetRoomsViaHostelId(hostelId, jqHostelSelector)
	{
	    if(hostelId && !isEditableMode)
		{
			var param = {
				"hostelId": hostelId
			}
			ctDAO.getRoomNoViaHostelId(param, function(data){
				if(data && data.responseStatus == bmpUtil.RESPONSE_STATUS)
				{
					var roomsSelectize = $(jqHostelSelector)[0].selectize;
					roomsSelectize.clearOptions();
					var responseData = data.responseData;
					if(responseData && responseData.length > 0)
					  {

						    for(var i = 0; i < responseData.length; i++)
						    {
								roomsSelectize.addOption({
						             text: responseData[i].roomno,
						             value: responseData[i].roomid
						        });
						    }
						    roomsSelectize.refreshItems();
					  }

				}
				else
				{
					alert(data.responseMsg);
				}
			});
		}
	}


</script>


