<#include "lib/header.ftl"> <#include "lib/leftsidebar.ftl">
<link href="${baseURL}/theme/js/iCheck/skins/minimal/minimal.css"
	rel="stylesheet" />
<section id="main-content" class="hfmsMainContent">
	<section class="wrapper">
		<div class="row hfmsShowUserDataRow">
			<div class="col-md-12">
				<section class="panel">
					<header class="panel-heading"> User Lists </header>
					<div class="panel-body">
						<form method="PUT" class="form-horizontal"
							action="${baseURL}/api/manager/getUserMenus">
							<#if model.responseData.users??>
							<div class="form-group">
								<label for="inputSuccess"
									class="col-sm-3 control-label col-lg-3">Selects User</label>
								<div class="col-lg-6">
									<select class="form-control m-bot15 hfmsSelectUsers"
										name="userId">
										<option value="0">Select Users</option> <#list
										model.responseData.users as element>
										<option id="hfmsSelectUsers_${element.userid}"
											value="${element.userid}">${element.username}</option>
										</#list>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-offset-5 col-lg-4">
									<button class="btn btn-info" type="submit">Submit</button>
								</div>
							</div>
							</#if>
						</form>
					</div>
				</section>
			</div>
		</div>
		<#if model.responseData.userMenu??>
		<div class="row hfmsMenuTableHeader">
			<div class="col-sm-12">
				<section class="panel">
					<div class="panel-body">
						<table class="table  table-hover general-table">
							<thead>
								<tr>
									<th>Menu Name</th>
									<th>Save Access</th>
									<th>Edit Access</th>
									<th>Delete Access</th>
								</tr>
							</thead>
							<tbody>
								<#list model.responseData.userMenu as element>
								<tr>
									<td>${element.menu_name}</td>
									<td>
										<#if element.save_access == 'norecord'>
											<input type="checkbox" class="hfmsComonMenuChkBox"
											 pk_MenuId="${element.menu_id}" manageType="save_access"
											status="insert">
										</#if>
										<#if element.save_access != 'norecord'>
											<#if element.save_access == 'yes'>
												<input
													type="checkbox" checked class="hfmsComonMenuChkBox"
													pk_MenuId="${element.menu_id}" manageType="save_access"
													status="update">
											</#if>
											<#if element.save_access =='no'>
												<input type="checkbox" class="hfmsComonMenuChkBox"
												pk_MenuId="${element.menu_id}" manageType="save_access"
												status="update">
											</#if>
										</#if>
									</td>
									<td>
										<#if element.edit_access == 'norecord'>
											<input type="checkbox" class="hfmsComonMenuChkBox"
											 pk_MenuId="${element.menu_id}" manageType="edit_access"
											status="insert">
										</#if>
										<#if element.edit_access != 'norecord'>
											<#if element.edit_access == 'yes'>
												<input
													type="checkbox" checked class="hfmsComonMenuChkBox"
													pk_MenuId="${element.menu_id}" manageType="edit_access"
													status="update">
											</#if>
											<#if element.edit_access =='no'>
												<input type="checkbox" class="hfmsComonMenuChkBox"
												pk_MenuId="${element.menu_id}" manageType="edit_access"
												status="update">
											</#if>
										</#if>
									</td>
									<td>
										<#if element.delete_access == 'norecord'>
											<input type="checkbox" class="hfmsComonMenuChkBox"
											 pk_MenuId="${element.menu_id}" manageType="delete_access"
											status="insert">
										</#if>
										<#if element.delete_access != 'norecord'>
											<#if element.delete_access == 'yes'>
												<input
													type="checkbox" checked class="hfmsComonMenuChkBox"
													pk_MenuId="${element.menu_id}" manageType="delete_access"
													status="update">
											</#if>
											<#if element.delete_access =='no'>
												<input type="checkbox" class="hfmsComonMenuChkBox"
												pk_MenuId="${element.menu_id}" manageType="delete_access"
												status="update">
											</#if>
										</#if>
									</td>
								</tr>
								</#list>
							</tbody>
						</table>
					</div>
				</section>
			</div>
		</div>
		</#if>
		<!-- page start-->
	</section>
</section>
<#include "lib/footer.ftl">
<!--icheck init -->
<script src="${baseURL}/theme/js/iCheck/jquery.icheck.js"></script>
<script src="${baseURL}/theme/js/icheck-init.js"></script>
<script type="text/javascript">
    $(".hfmsLeftSideMenu li a").removeClass("active");
    $(".hfms_UserMenu").addClass("active");
    localStorage.setItem("menuId", $(".hfms_UserMenu").attr('pk_id'));

    var userId = '${model.responseData.userId}';
    if (userId) {
	$("#hfmsSelectUsers_" + userId).attr("selected", "selected");
    }

    $(document).ready(function() {

	$(document).on("click", '.hfmsComonMenuChkBox', function() {
	    var isApproved = ($(this).is(":checked") ? "yes" : "no");
	  	var status = $(this).attr("status");
		var manageType = $(this).attr("manageType");
		var pk_MenuId = $(this).attr("pk_MenuId");
		var paramMap = {
		    "status" : status,
		    "manageType" : manageType,
		    "pk_MenuId" : pk_MenuId,
		    "userId" : $('.hfmsSelectUsers :selected').val(),
		    "isApproved": isApproved
		};
		ctDAO.addUserMenuRights(paramMap, function(data){
		    if(data && data.responseStatus == bmpUtil.RESPONSE_STATUS)
			{
				location.reload();
			}
		    else
			{
				alert(data.responseMsg);
			}
		});
	});
    });
</script>
