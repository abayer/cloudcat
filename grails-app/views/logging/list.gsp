
<%@ page import="cloudstack.reporting.Logging" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'logging.label', default: 'Logging')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-logging" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-logging" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="dateCreated" title="${message(code: 'logging.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="category" title="${message(code: 'logging.category.label', default: 'Category')}" />
					
						<g:sortableColumn property="user" title="${message(code: 'logging.user.label', default: 'User')}" />

                                                <g:sortableColumn property="msg" title="${message(code: 'logging.msg.label', default: 'Msg')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${loggingInstanceList}" status="i" var="loggingInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${loggingInstance.id}"><g:formatDate date="${loggingInstance.dateCreated}" /></g:link></td>
					
						<td>${fieldValue(bean: loggingInstance, field: "category")}</td>
					
						<td>${fieldValue(bean: loggingInstance, field: "user")}</td>
					
						<td>${fieldValue(bean: loggingInstance, field: "msg")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${loggingInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
