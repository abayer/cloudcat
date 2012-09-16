
<%@ page import="cloudstack.reporting.Instance" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'instance.label', default: 'Instance')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-instance" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-instance" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list instance">
			
				<g:if test="${instanceInstance?.cpuUsed}">
				<li class="fieldcontain">
					<span id="cpuUsed-label" class="property-label"><g:message code="instance.cpuUsed.label" default="Cpu Used" /></span>
					
						<span class="property-value" aria-labelledby="cpuUsed-label"><g:fieldValue bean="${instanceInstance}" field="cpuUsed"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${instanceInstance?.hostId}">
				<li class="fieldcontain">
					<span id="hostId-label" class="property-label"><g:message code="instance.hostId.label" default="Host Id" /></span>
					
						<span class="property-value" aria-labelledby="hostId-label"><g:fieldValue bean="${instanceInstance}" field="hostId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${instanceInstance?.account}">
				<li class="fieldcontain">
					<span id="account-label" class="property-label"><g:message code="instance.account.label" default="Account" /></span>
					
						<span class="property-value" aria-labelledby="account-label"><g:fieldValue bean="${instanceInstance}" field="account"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${instanceInstance?.cpuCount}">
				<li class="fieldcontain">
					<span id="cpuCount-label" class="property-label"><g:message code="instance.cpuCount.label" default="Cpu Count" /></span>
					
						<span class="property-value" aria-labelledby="cpuCount-label"><g:fieldValue bean="${instanceInstance}" field="cpuCount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${instanceInstance?.created}">
				<li class="fieldcontain">
					<span id="created-label" class="property-label"><g:message code="instance.created.label" default="Created" /></span>
					
						<span class="property-value" aria-labelledby="created-label"><g:formatDate date="${instanceInstance?.created}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${instanceInstance?.instanceId}">
				<li class="fieldcontain">
					<span id="instanceId-label" class="property-label"><g:message code="instance.instanceId.label" default="Instance Id" /></span>
					
						<span class="property-value" aria-labelledby="instanceId-label"><g:fieldValue bean="${instanceInstance}" field="instanceId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${instanceInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="instance.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${instanceInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${instanceInstance?.memory}">
				<li class="fieldcontain">
					<span id="memory-label" class="property-label"><g:message code="instance.memory.label" default="Memory" /></span>
					
						<span class="property-value" aria-labelledby="memory-label"><g:fieldValue bean="${instanceInstance}" field="memory"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${instanceInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="instance.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${instanceInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${instanceInstance?.publicIPs}">
				<li class="fieldcontain">
					<span id="publicIPs-label" class="property-label"><g:message code="instance.publicIPs.label" default="Public IP s" /></span>
					
						<span class="property-value" aria-labelledby="publicIPs-label"><g:fieldValue bean="${instanceInstance}" field="publicIPs"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${instanceInstance?.reportRuns}">
				<li class="fieldcontain">
					<span id="reportRuns-label" class="property-label"><g:message code="instance.reportRuns.label" default="Report Runs" /></span>
					
						<g:each in="${instanceInstance.reportRuns}" var="r">
						<span class="property-value" aria-labelledby="reportRuns-label"><g:link controller="reportRun" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${instanceInstance?.rootDeviceId}">
				<li class="fieldcontain">
					<span id="rootDeviceId-label" class="property-label"><g:message code="instance.rootDeviceId.label" default="Root Device Id" /></span>
					
						<span class="property-value" aria-labelledby="rootDeviceId-label"><g:fieldValue bean="${instanceInstance}" field="rootDeviceId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${instanceInstance?.serviceOfferingName}">
				<li class="fieldcontain">
					<span id="serviceOfferingName-label" class="property-label"><g:message code="instance.serviceOfferingName.label" default="Service Offering Name" /></span>
					
						<span class="property-value" aria-labelledby="serviceOfferingName-label"><g:fieldValue bean="${instanceInstance}" field="serviceOfferingName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${instanceInstance?.state}">
				<li class="fieldcontain">
					<span id="state-label" class="property-label"><g:message code="instance.state.label" default="State" /></span>
					
						<span class="property-value" aria-labelledby="state-label"><g:fieldValue bean="${instanceInstance}" field="state"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${instanceInstance?.templateId}">
				<li class="fieldcontain">
					<span id="templateId-label" class="property-label"><g:message code="instance.templateId.label" default="Template Id" /></span>
					
						<span class="property-value" aria-labelledby="templateId-label"><g:fieldValue bean="${instanceInstance}" field="templateId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${instanceInstance?.templateName}">
				<li class="fieldcontain">
					<span id="templateName-label" class="property-label"><g:message code="instance.templateName.label" default="Template Name" /></span>
					
						<span class="property-value" aria-labelledby="templateName-label"><g:fieldValue bean="${instanceInstance}" field="templateName"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${instanceInstance?.id}" />
					<g:link class="edit" action="edit" id="${instanceInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
