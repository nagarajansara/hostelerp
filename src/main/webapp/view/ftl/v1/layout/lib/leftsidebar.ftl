<aside>
	<div id="sidebar" class="nav-collapse hfmsNavCollapse">
		<!-- sidebar menu start-->
		<div class="leftside-navigation">
			<ul class="sidebar-menu hfmsLeftSideMenu" id="nav-accordion">
				<#if Session.menulist??>
					<#assign parent_id = 0>
					<#assign menu_id = 0>
					<#assign isChild = 0>
					<#list Session.menulist as element>
						<#if element.parent_id == menu_id && element.is_parent == 'no'>
							 <#if isChild == 0>
							 	<ul class="sub">
							 	<#assign isChild = 1>
							 </#if>
								<li><a pk_id="${element.menu_id}" href="${element.menu_url}">${element.menu_name }</a></li>
						</#if>
						<#if  element.parent_id != menu_id && isChild == 1 >
							</ul>
							<#assign isChild = 0>
						</#if>
						<#if element.parent_id == 0 && element.is_parent == 'yes'>
							<#if  menu_id != 0>
								</li>
							</#if>
							 <li>
								<a href="${baseURL}/${element.menu_url}" pk_id="${element.menu_id}" class="hfms_${element.menu_name }">
									<i class="${element.menu_icon}"></i>
									<span>${element.menu_name }</span>
								</a>
								<#assign menu_id = element.menu_id>
						</#if>
					</#list>
				</#if>
			</ul>
		</div>
		<!-- sidebar menu end-->
	</div>
</aside>