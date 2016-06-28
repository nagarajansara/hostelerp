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
					<header class="panel-heading"> Room Lists </header>
					<button type="button" class="btn btn-success hfmsAddNewBtn">ADD
						NEW</button>
					<div class="panel-body">
						<table id="dynamic-table" width="100%"
							class="pkDataCommonTable table table-striped table-bordered dt-responsive nowrap">
							<thead>
								<tr>
									<th>College Name</th>
									<th>Hostel Name</th>
									<th>Block Name</th>
									<th>Room Type</th>
									<th>Floor Name</th>
									<th>Total Members</th>
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
					<header class="panel-heading"> Room Details </header>
					<button type="button" class="btn btn-success hfmsShowRoomBut">Show
						Room</button>
					<div class="panel-body">
						<div class="position-center">
							<form role="form" class="hrfsSubmitRoomForm"
								data-parsley-validate="">
								<div class="form-group">
									<label for="inputSuccess">College Name</label> <select
										class="form-control hfmsRoomColName hfmsCommonRoomSelectize" placeholder="Select College" data-parsley-required jsonKey="collegeid">
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
									<label for="inputSuccess">Block Name</label> <select
										class="form-control hfmsBlockNameSelect hfmsCommonRoomSelectize" placeholder="Select Block" data-parsley-required jsonKey="blockid">
										<option value=""></option>
									</select>
								</div>
								<div class="form-group">
									<label for="inputSuccess">Floor No</label>
									<select
										class="form-control hfmsFloorNoSelect hfmsCommonRoomSelectize" placeholder="Select Floor" data-parsley-required jsonKey="floorname">
										<option value=""></option>
									</select>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Room No</label> <input
										type="text" class="form-control hfmsRoomNo"
										placeholder="Room No (numeric values)" jsonKey="roomno"
										required>
								</div>
								<div class="form-group">
									<label for="inputSuccess">Room Type</label> <select
										class="form-control hfmsRoomNameSelect hfmsCommonRoomSelectize" placeholder="Select room type" jsonKey="roomtype"
										data-parsley-required>
										<option value=""></option>
										<option value="AC">AC</option>
										<option value="nonAC">non AC</option>
									</select>
								</div>
								<div class="form-group">
									<label for="inputSuccess"
										class="col-sm-3 control-label col-lg-3">No of Person</label> <input
										type="text" placeholder="numeric values"
										class="form-control hfmsNoPerson" jsonKey="noofperson"
										required>
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
					<header class="panel-heading"> Room Details </header>
					<button type="button" class="btn btn-success hfmsShowRoomBut">Show
						Room</button>
					<div class="panel-body">
						<div class="position-center">
							<form role="form" class="hrfsUpdateSubmitRoomForm"
								data-parsley-validate="">
								<div class="form-group">
									<label for="inputSuccess">College Name</label> <select
										class="form-control hfmsUpdateRoomColName hfmsCommonRoomSelectize" placeholder="Select College" data-parsley-required jsonKey="collegeid">
										<option value=""></option> <#if
										model.responseData.college??> <#list
										model.responseData.college as element>
										<option value="${element.id}">${element.name}</option>
										</#list> </#if>
									</select>
								</div>
								<div class="form-group">
									<label for="inputSuccess">Hostel Name</label> <select
										class="form-control hfmsUpdateHostelNameSelect hfmsCommonRoomSelectize" placeholder="Select Hostel"
										data-parsley-required jsonKey="hostelid">
										<option value=""></option>
									</select>
								</div>
								<div class="form-group">
									<label for="inputSuccess">Block Name</label> <select
										class="form-control hfmsUpdateBlockNameSelect hfmsCommonRoomSelectize" placeholder="Select Block" data-parsley-required jsonKey="blockid">
										<option value="">Select Block</option>
									</select>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Floor Name</label>
										 <select
										class="form-control hfmsUpdateFloorNo hfmsCommonRoomSelectize" placeholder="Select Block" data-parsley-required jsonKey="floorname">
										<option value="">Select Floor</option>
										</select>
								</div>
								<div class="form-group">
									<label for="inputSuccess">Room Type</label> <select
										class="form-control hfmsUpdateRoomNameSelect hfmsCommonRoomSelectize" placeholder="Select room type" jsonKey="roomtype"
										data-parsley-required>
										<option value=""></option>
										<option value="AC">AC</option>
										<option value="nonAC">non AC</option>
									</select>
								</div>
								<div class="form-group">
									<label for="inputSuccess">Room No</label> <input type="text"
										placeholder="numeric values" class="form-control hfmsUpdateRoomNo"
										jsonKey="roomno" required>

								</div>
								<div class="form-group">
									<label for="inputSuccess"
										class="col-sm-3 control-label col-lg-3">No of Person</label> <input
										type="text" placeholder="numeric values"
										class="form-control hfmsUpdateNoPerson" jsonKey="noofperson"
										required>
								</div>
								<input type="hidden" jsonKey="id">
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

var isEditableMode = false;

    $(".hfmsLeftSideMenu li a").removeClass("active");
    $(".hfms_Room").addClass("active");
    localStorage.setItem("menuId", $(".hfms_Room").attr('pk_id'));

    $(".hfmsRoomNo").numeric();
    $(".hfmsFloorNo").numeric();
    $(".hfmsNoPerson").numeric();

    $(".hfmsAddNewBtn").click(function() {
		$(".hfmsAddRow").show();
		$(".hfmsShowRoomDataRow").hide();
		$(".hfmsUpdateRow").hide();
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
	"mData" : "blockname",
	'bSortable' : false
    }, {
	"mData" : "roomtype",
	'bSortable' : false
    }, {
	"mData" : "floorname",
	'bSortable' : false
    }, {
	"mData" : "noofperson",
	'bSortable' : false
    }, {
	"mData" : "editBtn",
	'bSortable' : false
    }, ];

    hfmsSetDataTableValues("#dynamic-table", "http://" + location.host
	    + "${baseURL}/api/manager/getRooms", aoColumns);


    $(".hfmsCommonRoomSelectize").selectize({
		    sortField:
		    {
			     field: "text",
			     direction: "asc"
		    }
	});


    $(".hrfsSubmitRoomForm").submit(function(event) {
		event.preventDefault();
		if ($('.hrfsSubmitRoomForm').parsley().validate()) {
			var config = {};
			config.jqSelector = ".hrfsSubmitRoomForm";
			var constJSONParam = new ConstJSONParam(config);
			var paramMap = constJSONParam.getParamsValue();
			ctDAO.addRoom(paramMap, function(data){
				if(data && data.responseStatus == bmpUtil.RESPONSE_STATUS)
				    {
						alert("Room added successfully");
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

    $('.hfmsRoomColName').on('change', function() {
		var collegeId = $(this).val();
		hfmsGetHostelViaCollegeId(collegeId, '.hfmsHostelNameSelect', '.hfmsBlockNameSelect' , '.hfmsFloorNoSelect');
	});
    $('.hfmsHostelNameSelect').on('change', function(){
		var hostelId = $(this).val();
		var collegeId = $(".hfmsRoomColName option:selected").val();
		hfmsGetBlocksViaHostelAndCollegeId(hostelId, collegeId,  '.hfmsBlockNameSelect', '.hfmsFloorNoSelect');
    });

    $('.hfmsBlockNameSelect').on('change', function(){
		var hostelId = $(".hfmsHostelNameSelect option:selected").val();
		var blockId = $(this).val();
		hfmsGetFlolorNo(hostelId, blockId, '.hfmsFloorNoSelect');
	});

     $('.hfmsUpdateRoomColName').on('change', function() {
		var collegeId = $(this).val();
		hfmsGetHostelViaCollegeId(collegeId, '.hfmsUpdateHostelNameSelect', '.hfmsUpdateBlockNameSelect',  '.hfmsUpdateFloorNo');
	});
	 $('.hfmsUpdateHostelNameSelect').on('change', function(){
		var hostelId = $(this).val();
		var collegeId = $(".hfmsUpdateRoomColName option:selected").val();
		hfmsGetBlocksViaHostelAndCollegeId(hostelId, collegeId,  '.hfmsUpdateBlockNameSelect',  '.hfmsFloorNoSelect');
    });
	 $('.hfmsUpdateBlockNameSelect').on('change', function(){
		var hostelId = $(".hfmsUpdateHostelNameSelect option:selected").val();
		var blockId = $(this).val();
		hfmsGetFlolorNo(hostelId, blockId, '.hfmsUpdateFloorNo');
	});



    $(document).on('click', ".hfmsEditBtn", function() {

		isEditableMode = true;

			$(".hfmsAddRow").hide();
			$(".hfmsShowRoomDataRow").hide();
			$(".hfmsUpdateRow").show();

			var roomId = $(this).attr('pk_id'),
					param = {
						"id":  roomId
					};
			if(roomId)
			{

				ctDAO.getRoomViaId(param, function(data){
					if(data && data.responseStatus == bmpUtil.RESPONSE_STATUS)
					{
						var hostels = data.responseData.hostels;
						var blocks = data.responseData.blocks;
						var rooms = data.responseData.rooms;
						if(rooms && rooms.length > 0)
						{
						    var selectize = $(".hfmsUpdateRoomColName")[0].selectize;
						    selectize.setValue(rooms[0].collegeid);
						    var noOfFloor = 0;

							if(hostels && hostels.length > 0)
							{
							    var selectize = $(".hfmsUpdateHostelNameSelect")[0].selectize
							    selectize.clearOptions();
							    for(var i = 0; i <  hostels.length; i++)
							    {
									selectize.addOption({
									             text: hostels[i].name,
									             value: hostels[i].id
									        });
							    }
							    selectize.setValue(rooms[0].hostelid);
							}
							if(blocks && blocks.length > 0)
							{
								var selectize = $(".hfmsUpdateBlockNameSelect")[0].selectize
								selectize.clearOptions();
								for(var i = 0; i < blocks.length; i++)
								{
								    selectize.addOption({
								             text: blocks[i].blockname,
								             value: blocks[i].id
								        });
								    if(rooms[0].blockid ==  blocks[i].id)
									{
										noOfFloor = blocks[i].nooffloor;
									}
								    selectize.setValue(rooms[0].blockid);
								}
							}
							if(noOfFloor > 0)
						    {
							    var selectize = $(".hfmsUpdateFloorNo")[0].selectize
								selectize.clearOptions();
							    for(var i = 1; i <= noOfFloor; i++)
							    {
									selectize.addOption({
								             text: i,
								             value: i
								        });
							    }
							    selectize.setValue(rooms[0].floorname);
						    }
							var selectize = $(".hfmsUpdateRoomNameSelect")[0].selectize;
							selectize.setValue(rooms[0].roomtype);
							var config = {};
							config["jqSelector"] = ".hrfsUpdateSubmitRoomForm";
						    config["data"] = rooms[0];
						    var constJSONParam = new ConstJSONParam(config);
						    constJSONParam.setParamValue();

						    isEditableMode = false;
						}

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
			ctDAO.updateRoomViaId(paramMap, function(data){
				if(data && data.responseStatus == bmpUtil.RESPONSE_STATUS)
				    {
						alert("Update successfully");
						bmpUtil.reLoad();
				    }
				else
				    {
				    	alert(data.responseMsg);
				    }
			});
		}
	});




	function hfmsGetHostelViaCollegeId(collegeId, hostelJqSelector, blockJqSelector, floorJqSelector)
	{
		if(collegeId && collegeId.length > 0 && !isEditableMode)
		    {
				param = {
					"collegeId": collegeId
				}
				ctDAO.getHostelsViaCollegeId(param, function(data){
					if(data && data.responseStatus == bmpUtil.RESPONSE_STATUS)
					{
					    var responseData = data.responseData;

					    var floorSelectize = $(floorJqSelector)[0].selectize;
					    floorSelectize.clearOptions();
					  	var blockSelectize = $(blockJqSelector)[0].selectize;
					   blockSelectize.clearOptions();
						var hostelSelectize = $(hostelJqSelector)[0].selectize;
						hostelSelectize.clearOptions();

						if(responseData && responseData.length > 0)
						  {

							    for(var i=0; i< responseData.length; i++)
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
	function hfmsGetBlocksViaHostelAndCollegeId(hostelId, collegeId, blockJqSelector, floorJqSelector)
	{
		if(hostelId && collegeId && !isEditableMode)
		{
			var param = {
				"collegeId": collegeId,
				"hostelId": hostelId
			}
		    ctDAO.getBlockViaHostelId(param, function(data){
				if(data && data.responseStatus == bmpUtil.RESPONSE_STATUS)
				    {
				    	var responseData = data.responseData;
				    	var floorSelectize = $(floorJqSelector)[0].selectize;
					    floorSelectize.clearOptions();
					  	var blockSelectize = $(blockJqSelector)[0].selectize;
					  	 blockSelectize.clearOptions();
				    	if(responseData && responseData.length > 0)
						  {

							for(var i=0; i< responseData.length; i++)
							    {
							    	blockSelectize.addOption({
								             text: responseData[i].blockname,
								             value: responseData[i].id
								        });
							    }
								blockSelectize.refreshItems();

						  }

				    }
				else
				    {
				    	alert(data.responseMsg);
				    }
		    });
		}

	}
	function hfmsGetFlolorNo(hostelId, blockId, jqSelector)
	{
		if(hostelId && blockId && jqSelector && !isEditableMode)
		    {
				var param = {
					"blockId": blockId,
					"hostelId": hostelId
				};
				ctDAO.getNoFloorsViaHostelAndBlockId(param, function(data){
					if(data && data.responseStatus == bmpUtil.RESPONSE_STATUS)
					{
					    var floorSelectize = $(jqSelector)[0].selectize;
					    floorSelectize.clearOptions();
					    var responseData = data.responseData;
					    for(var i = 0; i < responseData.length; i++)
						{
							for(var j = 1; j <= responseData[i].nooffloor; j++)
							    {
								    floorSelectize.addOption({
									             text: j,
									             value: j
									        });
							    }
							floorSelectize.refreshItems();
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


