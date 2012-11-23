
<%@ page import="cloudstack.reporting.Template" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">

		<g:set var="entityName" value="${message(code: 'template.label', default: 'Template')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-template" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-template" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'template.name.label', default: 'Template Name')}" />
					
						<g:sortableColumn property="templateId" title="${message(code: 'template.templateId.label', default: 'Template Id')}" />
					
						<g:sortableColumn property="stillActive" title="${message(code: 'template.stillActive.label', default: 'Active')}" />
					
						<g:sortableColumn property="displayText" title="${message(code: 'template.displayText.label', default: 'Details')}" />

                                                <g:sortableColumn property="instanceCount" title="${message(code: 'template.instanceCount.label', default: 'Instances')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${templateInstanceList}" status="i" var="templateInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${templateInstance.id}">${templateInstance.name}</g:link></td>
					
						<td>${templateInstance.templateId}</td>
					
						<td>${templateInstance.stillActive}</td>
					
						<td>${templateInstance.displayText}</td>

						<td><g:link controller="instance" action="find" params="[templateId: templateInstance.id]">${templateInstance.instanceCount}</g:link></td>
                                                
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${templateInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
