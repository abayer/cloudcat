
<%@ page import="cloudstack.reporting.Logging" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'logging.label', default: 'Logging')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-logging" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-logging" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list logging">
			
				<g:if test="${loggingInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="logging.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:fieldValue bean="${loggingInstance}" field="user"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${loggingInstance?.category}">
				<li class="fieldcontain">
					<span id="category-label" class="property-label"><g:message code="logging.category.label" default="Category" /></span>
					
						<span class="property-value" aria-labelledby="category-label"><g:fieldValue bean="${loggingInstance}" field="category"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${loggingInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="logging.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${loggingInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${loggingInstance?.msg}">
				<li class="fieldcontain">
					<span id="msg-label" class="property-label"><g:message code="logging.msg.label" default="Msg" /></span>
					
						<span class="property-value" aria-labelledby="msg-label"><g:fieldValue bean="${loggingInstance}" field="msg"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${loggingInstance?.id}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
