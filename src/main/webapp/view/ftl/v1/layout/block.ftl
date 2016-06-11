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
									<th>Hostel Name</th>
									<th>Block Name</th>
									<th>No of floor</th>
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
					<header class="panel-heading"> Block Details </header>
					<button type="button" class="btn btn-success hfmsShowUsresBut">Show
						Block</button>
					<div class="panel-body">
						<div class="position-center">
							<form role="form" class="hrfsSubmitBlocksForm"
								data-parsley-validate="">
								<div class="form-group">
									<label for="exampleInputEmail1">Block Name</label> <input
										type="text" class="form-control hfmsBlockName"
										placeholder="Block Name" jsonKey="blockname" required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Hostel Name</label><input
										style="width: 100%;" type="hidden" name="blockname"
										id="hfmsHostelName">
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">No of floor</label> <input
										type="text" class="form-control hfmsNoofFloor"
										placeholder="No of floor" jsonKey="nooffloor" required>
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


		<!-- ADD USER FORM  -->
		<div class="row hfmsUpdateRow" style="display: none">
			<div class="col-md-12">
				<section class="panel">
					<header class="panel-heading"> Block Details </header>
					<button type="button" class="btn btn-success hfmsShowUsresBut">Show
						Block</button>
					<div class="panel-body">
						<div class="position-center">
							<form role="form" class="hrfsUpdateSubmitBlocksForm"
								data-parsley-validate="">
								<div class="form-group">
									<label for="exampleInputEmail1">Block Name</label> <input
										type="text" class="form-control hfmsUpdateBlockName"
										placeholder="Hostel Name" jsonKey="blockname" required>
								</div>
								<div class="form-group hfmsHostelNameParent"></div>
								<div class="form-group">
									<label for="exampleInputEmail1">No of floor</label> <input
										type="text" class="form-control hfmsNoofFloor"
										placeholder="No of floor" jsonKey="nooffloor" required>
								</div>
								<input type="hidden" jsonKey="id" class="hfmsUpdateBlockId">
								<button class="btn btn-primary" type="submit">Submit</button>
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
    $(".hfms_Block").addClass("active");
    localStorage.setItem("menuId", $(".hfms_Block").attr('pk_id'));

    $(".hfmsNoofFloor").numeric();

    hfmsSetSelect2("#hfmsHostelName", 'http://' + location.host + '/'
	    + ctDAO.CONTEXT_NAME + '/api/manager/getHostelNameApi.json',
	    'Select hostel name');

    $(".hfmsAddNewBtn").click(function() {
	$(".hfmsAddRow").show();
	$(".hfmsShowHostelDataRow").hide();
    });

    $(".hfmsShowUsresBut").click(function() {
	$(".hfmsAddRow").hide();
	$(".hfmsUpdateRow").hide();
	$(".hfmsShowHostelDataRow").show();
    });

    var aoColumns = [ {
	"mData" : "hostelname",
	'bSortable' : false
    }, {
	"mData" : "blockname",
	'bSortable' : false
    }, {
	"mData" : "nooffloor",
	'bSortable' : false
    },{
		"mData" : "editBtn",
		'bSortable' : false
	  },
	];

    hfmsSetDataTableValues("#dynamic-table", "http://" + location.host
	    + "${baseURL}/api/manager/getBlocks", aoColumns);
    $(".hrfsSubmitBlocksForm").submit(function(event){
					event.preventDefault();
		if($('.hrfsSubmitBlocksForm').parsley().validate())
		    {
		    	var hostelObj = $('#hfmsHostelName').select2('data');
		    	var hostelName, config = {};
		    	if(hostelObj)
		    	    {
		    			hostelName = hostelObj.text;
		    			config.jqSelector = ".hrfsSubmitBlocksForm";
					    var constJSONParam = new ConstJSONParam(
						    config);
					    var paramMap = constJSONParam
						    .getParamsValue();
					    paramMap["hostelname"] = hostelName
					    ctDAO.addBlock(paramMap, function(data){
							if(data && data.responseStatus == bmpUtil.RESPONSE_STATUS)
							    {
							    	alert("Block added successfully");
							    }
							else
							    {
							    	alert(data.responseMsg);
							    }
					    });

		    	    }
		    	else
		    	    {
		    	    	alert("Please fill all the details");
		    	    }

		    }
    });
    $(document).on('click', ".hfmsDelWorker", function() {
		if(confirm("Conform to delete!"))
		 {
			var id = $(this).attr("pk_id"), param = {
				    "id" : id
				};
				ctDAO.deleteBlockViaId(param, function(data) {
				    if(data && data.responseStatus == bmpUtil.RESPONSE_STATUS)
					{
						alert("Deleted successfully");
					}
				    else
					{
						alert(data.responseMsg);
					}
				});
		   }
    });
    $(document).on('click', ".hfmsEditWorker", function() {
		$(".hfmsAddRow").hide();
		$(".hfmsShowHostelDataRow").hide();
		$(".hfmsUpdateRow").show();
		var id = $(this).attr("pk_id"), param = {
		    "id" : id
		};
		ctDAO.getBlockViaId(param, function(data) {
				if(data && data.responseStatus == bmpUtil.RESPONSE_STATUS)
				    {
					    var config = {};
					    var responseData = data.responseData;
					    config["jqSelector"] = ".hrfsUpdateSubmitBlocksForm";
					    config["data"] = responseData[0];
					    var constJSONParam = new ConstJSONParam(config);
					    constJSONParam.setParamValue();
					    $(".hfmsHostelNameParent").empty();
					    $(".hfmsHostelNameParent").append("<label for=\"exampleInputEmail1\">City</label><input style=\"width: 100%;\" type=\"hidden\" data-option=\""+ responseData[0].hostelname + "\" id=\"hfmsUpdatedHostelName\" value=\""+ responseData[0].hostelname + "\">");
					    hfmsSetSelect2("#hfmsUpdatedHostelName", 'http://' + location.host + '/'+ ctDAO.CONTEXT_NAME + '/api/manager/getCityApi.json', 'Select hostel name');
				    }
				else
				    {
				    	alert(data.responseMsg());
				    }
		});
    });

    $(".hrfsUpdateSubmitBlocksForm").submit(function(event){
		event.preventDefault();
		if($('.hrfsUpdateSubmitBlocksForm').parsley().validate())
		    {
		   	 var hostelObj = $('#hfmsUpdatedHostelName').select2('data');
		   	 var hostelName, config = {};
		   	 if(hostelObj)
		   	     {
		   	  		hostelName = hostelObj.text;
		   	     	config.jqSelector = ".hrfsUpdateSubmitBlocksForm";
					var constJSONParam = new ConstJSONParam(config);
					var paramMap = constJSONParam.getParamsValue();
					paramMap["hostelname"] = hostelName;
					ctDAO.updateBlockViaId(paramMap, function(data){
						if(data && data.responseStatus == bmpUtil.RESPONSE_STATUS )
						    {
								alert("update successfully ");
						    }
						else
						    {
						    	alert(data.responseMsg);
						    }
					});
		   	     }
		    }
    })


</script>
