
<%@ page import="cloudstack.reporting.ProvisionedInstance" %>
<%@ page import="cloudstack.reporting.ProvisionedInstanceStatus" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'provisionedInstance.label', default: 'ProvisionedInstance')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
                <filterpane:includes />
	</head>
	<body>
		<a href="#list-provisionedInstance" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
                <div>
                  <filterpane:filterPane domain="cloudstack.reporting.ProvisionedInstance"
                                         associatedProperties="provisionedInstanceGroup.username,provisionedInstanceGroup.shortName,provisionedInstanceGroup.template.name,provisionedInstanceGroup.team.authority,provisionedInstanceGroup.primaryServiceOffering.name,provisionedInstanceGroup.alternateServiceOffering.name"
                                         titleKey="fp.tag.filterPane.titleText"/>
                  
                  <filterpane:currentCriteria style="margin-left: 5%; margin-top: 1em;" domainBean="${ProvisionedInstance}" />
                </div>
		<div id="list-provisionedInstance" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn params="${filterParams}" property="hostname" title="${message(code: 'provisionedInstance.hostname.label', default: 'Hostname')}" />
					
						<g:sortableColumn params="${filterParams}" property="instanceName" title="${message(code: 'provisionedInstance.instanceName.label', default: 'Instance Name')}" />
					
						<g:sortableColumn params="${filterParams}" property="publicIp" title="${message(code: 'provisionedInstance.publicIp.label', default: 'Public Ip')}" />
					
						<g:sortableColumn params="${filterParams}" property="instanceId" title="${message(code: 'provisionedInstance.instanceId.label', default: 'Instance Id')}" />
					
						<g:sortableColumn params="${filterParams}" property="errorMsg" title="${message(code: 'provisionedInstance.errorMsg.label', default: 'Err Msg')}" />
					
						<g:sortableColumn params="${filterParams}" property="provisionStatus" title="${message(code: 'provisionedInstance.provisionStatus.label', default: 'Status')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${provisionedInstanceInstanceList}" status="i" var="provisionedInstanceInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${provisionedInstanceInstance.id}">${fieldValue(bean: provisionedInstanceInstance, field: "hostname")}</g:link></td>
					
						<td>${fieldValue(bean: provisionedInstanceInstance, field: "instanceName")}</td>
					
						<td>${fieldValue(bean: provisionedInstanceInstance, field: "publicIp")}</td>
					
						<td>${fieldValue(bean: provisionedInstanceInstance, field: "instanceId")}</td>
					
						<td>${fieldValue(bean: provisionedInstanceInstance, field: "errorMsg")}</td>
					
                                                <td>${ProvisionedInstanceStatus.instanceStatuses[provisionedInstanceInstance.provisionStatus]}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
                        <div class="pagination">
                          <filterpane:paginate total="${provisionedInstanceInstanceTotal == null ? ProvisionedInstance.count() : provisionedInstanceInstanceTotal}" />
                          <filterpane:filterButton textKey="fp.tag.filterButton.text" appliedTextKey="fp.tag.filterButton.appliedText" text="Filter Me" appliedText="Change Filter" />
                          <filterpane:isNotFiltered>Pure and Unfiltered!</filterpane:isNotFiltered>
                          <filterpane:isFiltered>Filter Applied!</filterpane:isFiltered>
                        </div>
                    </div>
                  
	</body>
</html>
