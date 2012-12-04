
<%@ page import="cloudstack.reporting.ProvisionedInstanceGroup" %>
<%@ page import="cloudstack.reporting.ProvisionedInstanceStatus" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'provisionedInstanceGroup.label', default: 'ProvisionedInstanceGroup')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
                <filterpane:includes />
	</head>
	<body>
		<a href="#list-provisionedInstanceGroup" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
                <div>
                  <filterpane:filterPane domain="cloudstack.reporting.ProvisionedInstanceGroup"
                                         filterProperties="username,shortName,provisionStatus,description,dateCreated"
                                         associatedProperties="template.name,team.authority,primaryServiceOffering.name,alternateServiceOffering.name"
                                         titleKey="fp.tag.filterPane.titleText"
                                         filterPropertyValues="${[provisionStatus:[values:ProvisionedInstanceStatus.groupStatuses]]}"/>
                  <filterpane:currentCriteria style="margin-left: 5%; margin-top: 1em;" domainBean="${ProvisionedInstanceGroup}" />
                </div>
		<div id="list-provisionedInstanceGroup" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn params="${filterParams}" property="shortName"
						title="${message(code:
						'provisionedInstanceGroup.shortName.label',
						default: 'Short Name')}" />
					
						<g:sortableColumn params="${filterParams}" property="provisionStatus" title="${message(code: 'provisionedInstanceGroup.provisionStatus.label', default: 'Status')}" />

                                                <g:sortableColumn params="${filterParams}" property="username" title="${message(code: 'provisionedInstanceGroup.username.label', default: 'Owner')}" />

                                                <g:sortableColumn params="${filterParams}" property="dateCreated" title="${message(code: 'provisionedInstanceGroup.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn params="${filterParams}" property="description" title="${message(code: 'provisionedInstanceGroup.description.label', default: 'Description')}" />
					
						<g:sortableColumn params="${filterParams}" property="lastUpdated" title="${message(code: 'provisionedInstanceGroup.lastUpdated.label', default: 'Last Updated')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${provisionedInstanceGroupInstanceList}" status="i" var="provisionedInstanceGroupInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${provisionedInstanceGroupInstance.id}">${fieldValue(bean: provisionedInstanceGroupInstance, field: "shortName")}</g:link></td>

                                                <td>${ProvisionedInstanceStatus.groupStatuses[provisionedInstanceGroupInstance.provisionStatus]}</td>

                                                <td>${fieldValue(bean: provisionedInstanceGroupInstance, field: "username")}</td>
                                                
						<td><g:formatDate date="${provisionedInstanceGroupInstance.dateCreated}" /></td>
					
						<td>${fieldValue(bean: provisionedInstanceGroupInstance, field: "description")}</td>
					
						<td><g:formatDate date="${provisionedInstanceGroupInstance.lastUpdated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
                        <div class="pagination">
                          <filterpane:paginate total="${provisionedInstanceGroupInstanceTotal == null ? ProvisionedInstanceGroup.count() : provisionedInstanceGroupInstanceTotal}" />
                          <filterpane:filterButton textKey="fp.tag.filterButton.text" appliedTextKey="fp.tag.filterButton.appliedText" text="Filter Me" appliedText="Change Filter" />
                          <filterpane:isNotFiltered>Pure and Unfiltered!</filterpane:isNotFiltered>
                          <filterpane:isFiltered>Filter Applied!</filterpane:isFiltered>
                        </div>
		</div>
	</body>
</html>
