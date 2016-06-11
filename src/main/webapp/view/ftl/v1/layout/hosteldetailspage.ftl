<#include "lib/header.ftl"> <#include "lib/leftsidebar.ftl">
<link href="${baseURL}/assest/plugin/cfdatatable.css" rel="stylesheet" />
<section id="main-content" class="hfmsMainContent">
	<section class="wrapper">
		<div class="row hfmsShowUserDataRow">
			<div class="col-md-12">
				<section class="panel">
					<header class="panel-heading"> Show Details </header>
					<div class="panel-body">
						<div class="position-center">
							<form role="form">
								<#if model.responseData?has_content> <#list model.responseData
								as element>
								<div class="form-group">
									<label for="exampleInputPassword1">Hostel Name </label> <input
										type="text" class="form-control" id="exampleInputPassword1"
										placeholder="Password" disabled value="${element.name}">
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">College Name </label> <input
										type="text" class="form-control" id="exampleInputPassword1"
										placeholder="Password" disabled value="${element.collegename}">
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Mobile No </label> <input
										type="text" class="form-control" id="exampleInputPassword1"
										placeholder="Password" disabled value="${element.mobileno}">
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Address </label>
									<textarea rows="6"
										class="form-control hfmsUpdatedStudentAddress" required
										jsonKey="address" disabled>${element.address}</textarea>
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">State </label> <input
										type="text" class="form-control" id="exampleInputPassword1"
										placeholder="Password" disabled value="${element.state}">
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">City </label> <input
										type="text" class="form-control" id="exampleInputPassword1"
										placeholder="Password" disabled value="${element.city}">
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Country </label> <input
										type="text" class="form-control" id="exampleInputPassword1"
										placeholder="Password" disabled value="${element.country}">
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Status </label> <input
										type="text" class="form-control" id="exampleInputPassword1"
										placeholder="Password" disabled value="${element.status}">
								</div>
								</#list> </#if> <#if model.responseData?size == 0> <span
									style="font-family: verdana; color: red; size: 12px;">No
									data found</span> </#if>
							</form>
						</div>
					</div>
				</section>
			</div>
		</div>
		<!-- page start-->
	</section>
</section>
<#include "lib/footer.ftl">
<script type="text/javascript">

</script>
