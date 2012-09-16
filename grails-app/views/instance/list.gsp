
<%@ page import="cloudstack.reporting.Instance" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'instance.label', default: 'Instance')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-instance" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-instance" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
                                          
                                          <g:sortableColumn property="name" title="${message(code: 'instance.name.label', default: 'Instance Name')}" />
                                          
                                          <g:sortableColumn property="account" title="${message(code: 'instance.account.label', default: 'Account')}" />
                                          
                                          <g:sortableColumn property="serviceOfferingName" title="${message(code: 'instance.serviceOfferingName.label', default: 'Size')}" />
                                          
                                          <g:sortableColumn property="publicIPs" title="${message(code: 'instance.publicIPs.label', default: 'IP')}" />
                                          
                                          <g:sortableColumn property="state" title="${message(code: 'instance.state.label', default: 'State')}" />

                                          <g:sortableColumn property="created" title="${message(code: 'instance.created.label', default: 'Created')}" />
                                          
					</tr>
				</thead>
				<tbody>
				<g:each in="${instanceInstanceList}" status="i" var="instanceInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${instanceInstance.id}">${fieldValue(bean: instanceInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: instanceInstance, field: "account")}</td>
					
						<td>${fieldValue(bean: instanceInstance, field: "serviceOfferingName")}</td>
					
						<td>${fieldValue(bean: instanceInstance, field: "publicIPs")}</td>

						<td>${fieldValue(bean: instanceInstance, field: "state")}</td>

                                                <td><g:formatDate date="${instanceInstance.created}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${instanceInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
