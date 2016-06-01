<#include "lib/header.ftl"> <#include "lib/leftsidebar.ftl">
<link href="${baseURL}/assest/plugin/cfdatatable.css" rel="stylesheet" />
<section id="main-content" class="hfmsMainContent">
	<section class="wrapper">
		<!-- page start-->
		<div class="row">
			<div class="col-md-12">
				<section class="panel">
					<header class="panel-heading"> User Details </header>
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
	</section>
</section>

<#include "lib/footer.ftl">
<script src="${baseURL}/assest/plugin/cfdatatable.js"></script>
<script type="text/javascript">
    $(".hfmsLeftSideMenu li a").removeClass("active");
    $(".hfms_Users").addClass("active");

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
					    alert(data.responseStatus);
					}
				    });
				}
			    });

			$("#example")
				.dataTable(
					{
					    "bProcessing" : true,
					    "bServerSide" : true,
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
						    + "/colorful/colorfulapi/works/getWorksCategory",
					    "aoColumns" : [ {
						"mData" : "work_name",
						'bSortable' : false
					    }, {
						"mData" : "editBtn",
						'bSortable' : false
					    },

					    ]
					});

		    });


</script>
