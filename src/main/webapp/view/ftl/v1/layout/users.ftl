<#include "lib/header.ftl"> <#include "lib/leftsidebar.ftl">
<link href="${baseURL}/assest/plugin/cfdatatable.css" rel="stylesheet" />
<section id="main-content" class="hfmsMainContent">
	<section class="wrapper">
		<div class="row hfmsShowUserDataRow">
			<div class="col-md-12">
				<section class="panel">
					<header class="panel-heading"> User Lists </header>
					<button type="button" class="btn btn-success hfmsAddNewBtn">ADD NEW</button>
					<div class="panel-body">
						<table id="dynamic-table" width="100%" class="pkDataCommonTable table table-striped table-bordered dt-responsive nowrap">
						 	<thead>
								<tr>
									<th>User Name</th>
									<th>User Type</th>
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
					<header class="panel-heading"> User Details </header>
					<button type="button" class="btn btn-success hfmsShowUsresBut">Show Users</button>
					<div class="panel-body">
						<div class="position-center">
							<form role="form" class="hrfsSubmitUsersForm"
								data-parsley-validate="">
								<div class="form-group">
									<label for="exampleInputEmail1">User Name</label> <input
										type="text" class="form-control hfmsUserName"
										placeholder="User Name" required>
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Password</label> <input
										type="password" class="form-control hfmsPassword"
										placeholder="Password" required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">First Name</label> <input
										type="text" class="form-control hfmsFirstName"
										placeholder="First Name" required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Select Usertype</label> <select
										class="form-control m-bot15 hfmsUserType">
										<option value="admin">Admin</option>
										<option value="projectmanager">projectmanager</option>
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
					<header class="panel-heading"> Update User Details </header>
					<button type="button" class="btn btn-success hfmsShowUsresBut">Show Users</button>
					<div class="panel-body">
						<div class="position-center">
							<form role="form" class="hrfsSubmitUsersUpdateForm"
								data-parsley-validate="">
								<div class="form-group">
									<label for="exampleInputEmail1">User Name</label> <input
										type="text" disabled="" class="form-control hfmsUserUpdateName"
										placeholder="User Name" required>
								</div>
								<!--<div class="form-group">
									<label for="exampleInputPassword1">Password</label> <input
										type="password" class="form-control hfmsUpdatePassword"
										placeholder="Password" required>
								</div>-->
								<div class="form-group">
									<label for="exampleInputEmail1">First Name</label> <input
										type="text" class="form-control hfmsUpdateFirstName"
										placeholder="First Name" required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Select Usertype</label> <select
										class="form-control m-bot15 hfmsUpdateUserType">
										<option value="admin">Admin</option>
										<option value="projectmanager">projectmanager</option>
									</select>
								</div>
								<input type="hidden" class="hfmsUserId">
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
<script type="text/javascript">
    $(".hfmsLeftSideMenu li a").removeClass("active");
    $(".hfms_Users").addClass("active");
    localStorage.setItem("menuId", $(".hfms_Users").attr('pk_id'));

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
				$(".hfmsShowUserDataRow").hide();
		    });

		    $(".hfmsShowUsresBut").click(function(){
				$(".hfmsAddRow").hide();
				$(".hfmsUpdateRow").hide();
				$(".hfmsShowUserDataRow").show();
		    });

		    $(document).on("click", ".hfmsDelWorker", function(){
		    	if(confirm("Conform to delete!"))
		    	{
					var userId = $(this).attr("pk_id"),
						param = {};
					param = {
						"userId": userId
					};
					  ctDAO.deleteUsers(param, function(data) {
						if (data && data.responseStatus == bmpUtil.RESPONSE_STATUS) {
						    alert("User delete successfully");
						} else {
						    alert(data.responseMsg);
						}
					   });
				}

		    });

		    $(document).on("click", ".hfmsEditWorker", function(){
		    	$(".hfmsAddRow").hide();
				$(".hfmsShowUserDataRow").hide();
				$(".hfmsUpdateRow").show();
		    	var userId = $(this).attr("pk_id"),
		    		param = {
		    			"userId": userId
		    		};
				ctDAO.getUsersViaId(param, function(data){
					if(data && data.responseData)
					{
						for(var i = 0; i < data.responseData.length; i++)
						{
							$(".hfmsUserUpdateName").val(data.responseData[i].username);
							$(".hfmsUpdateFirstName").val(data.responseData[i].firstname);
							$(".hfmsUpdateUserType option[value="+ data.responseData[i].usertype +"]").prop('selected', true);
							$(".hfmsUserId").val(data.responseData[i].userid);
						}

					}
					else
					{
						alert(data.responseMsg);
					}
				});
		    });

			 $(".hrfsSubmitUsersUpdateForm").submit(function(event) {
				event.preventDefault();
				if ($('.hrfsSubmitUsersUpdateForm').parsley().validate()) {
				    var param = {
						"userName" : $(".hfmsUserUpdateName").val(),
						"firstname" : $(".hfmsUpdateFirstName").val(),
						"usertype" : $(".hfmsUpdateUserType option:selected").val(),
						"userId": $(".hfmsUserId").val()
				    };
				    ctDAO.updateUsersViaId(param, function(data) {
					if (data && data.responseStatus == bmpUtil.RESPONSE_STATUS) {
					    	alert("User updated successfully");
					} else {
					   if(data.responseStatus == bmpUtil.INVALID_LOGIN_STATUS)
					   {
							alert("User already registered");
					   }
					   else
					   {
					    	alert(data.responseMsg);
					   }
					}
				    });
				}
			    });

			     $(".hrfsSubmitUsersForm").submit(function(event) {
					event.preventDefault();
					if ($('.hrfsSubmitUsersForm').parsley().validate()) {
					    var param = {
						"userName" : $(".hfmsUserName").val(),
						"password" : $(".hfmsPassword").val(),
						"firstname" : $(".hfmsFirstName").val(),
						"usertype" : $(".hfmsUserType option:selected").val()
					    };
					    ctDAO.addUsers(param, function(data) {
						if (data && data.responseStatus == bmpUtil.RESPONSE_STATUS) {
						    alert("User added successfully");
						} else {
						   if(data.responseStatus == bmpUtil.INVALID_LOGIN_STATUS)
						   {
								alert("User already registered");
						   }
						   else
						   {
						    	alert(data.responseMsg);
						   }
						}
					    });
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
						    + "${baseURL}/api/manager/getUsers",
					    "aoColumns" : [ {
						"mData" : "username",
						'bSortable' : false
					    }, {
						"mData" : "usertype",
						'bSortable' : false
					    },
						{
						"mData" : "editBtn",
						'bSortable' : false
					    },
					    ]
					});

		    });


</script>
