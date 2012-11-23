
<%@ page import="cloudstack.reporting.Template" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">

		<g:set var="entityName" value="${message(code: 'template.label', default: 'Template')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-template" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-template" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list template">
			
				<g:if test="${templateInstance?.templateTag}">
				<li class="fieldcontain">
					<span id="templateTag-label" class="property-label"><g:message code="template.templateTag.label" default="Template Tag" /></span>
					
						<span class="property-value" aria-labelledby="templateTag-label"><g:fieldValue bean="${templateInstance}" field="templateTag"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.sourceTemplateId}">
				<li class="fieldcontain">
					<span id="sourceTemplateId-label" class="property-label"><g:message code="template.sourceTemplateId.label" default="Source Template Id" /></span>
					
						<span class="property-value" aria-labelledby="sourceTemplateId-label"><g:fieldValue bean="${templateInstance}" field="sourceTemplateId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.removed}">
				<li class="fieldcontain">
					<span id="removed-label" class="property-label"><g:message code="template.removed.label" default="Removed" /></span>
					
						<span class="property-value" aria-labelledby="removed-label"><g:formatDate date="${templateInstance?.removed}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.OSType}">
				<li class="fieldcontain">
					<span id="OSType-label" class="property-label"><g:message code="template.OSType.label" default="OSType" /></span>
					
						<span class="property-value" aria-labelledby="OSType-label"><g:fieldValue bean="${templateInstance}" field="OSType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.OSTypeId}">
				<li class="fieldcontain">
					<span id="OSTypeId-label" class="property-label"><g:message code="template.OSTypeId.label" default="OSType Id" /></span>
					
						<span class="property-value" aria-labelledby="OSTypeId-label"><g:fieldValue bean="${templateInstance}" field="OSTypeId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.account}">
				<li class="fieldcontain">
					<span id="account-label" class="property-label"><g:message code="template.account.label" default="Account" /></span>
					
						<span class="property-value" aria-labelledby="account-label"><g:fieldValue bean="${templateInstance}" field="account"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.accountId}">
				<li class="fieldcontain">
					<span id="accountId-label" class="property-label"><g:message code="template.accountId.label" default="Account Id" /></span>
					
						<span class="property-value" aria-labelledby="accountId-label"><g:fieldValue bean="${templateInstance}" field="accountId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.bootable}">
				<li class="fieldcontain">
					<span id="bootable-label" class="property-label"><g:message code="template.bootable.label" default="Bootable" /></span>
					
						<span class="property-value" aria-labelledby="bootable-label"><g:formatBoolean boolean="${templateInstance?.bootable}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.checksum}">
				<li class="fieldcontain">
					<span id="checksum-label" class="property-label"><g:message code="template.checksum.label" default="Checksum" /></span>
					
						<span class="property-value" aria-labelledby="checksum-label"><g:fieldValue bean="${templateInstance}" field="checksum"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.created}">
				<li class="fieldcontain">
					<span id="created-label" class="property-label"><g:message code="template.created.label" default="Created" /></span>
					
						<span class="property-value" aria-labelledby="created-label"><g:formatDate date="${templateInstance?.created}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.crossZones}">
				<li class="fieldcontain">
					<span id="crossZones-label" class="property-label"><g:message code="template.crossZones.label" default="Cross Zones" /></span>
					
						<span class="property-value" aria-labelledby="crossZones-label"><g:formatBoolean boolean="${templateInstance?.crossZones}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.displayText}">
				<li class="fieldcontain">
					<span id="displayText-label" class="property-label"><g:message code="template.displayText.label" default="Display Text" /></span>
					
						<span class="property-value" aria-labelledby="displayText-label"><g:fieldValue bean="${templateInstance}" field="displayText"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.domain}">
				<li class="fieldcontain">
					<span id="domain-label" class="property-label"><g:message code="template.domain.label" default="Domain" /></span>
					
						<span class="property-value" aria-labelledby="domain-label"><g:fieldValue bean="${templateInstance}" field="domain"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.domainId}">
				<li class="fieldcontain">
					<span id="domainId-label" class="property-label"><g:message code="template.domainId.label" default="Domain Id" /></span>
					
						<span class="property-value" aria-labelledby="domainId-label"><g:fieldValue bean="${templateInstance}" field="domainId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.extractable}">
				<li class="fieldcontain">
					<span id="extractable-label" class="property-label"><g:message code="template.extractable.label" default="Extractable" /></span>
					
						<span class="property-value" aria-labelledby="extractable-label"><g:formatBoolean boolean="${templateInstance?.extractable}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.featured}">
				<li class="fieldcontain">
					<span id="featured-label" class="property-label"><g:message code="template.featured.label" default="Featured" /></span>
					
						<span class="property-value" aria-labelledby="featured-label"><g:formatBoolean boolean="${templateInstance?.featured}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.format}">
				<li class="fieldcontain">
					<span id="format-label" class="property-label"><g:message code="template.format.label" default="Format" /></span>
					
						<span class="property-value" aria-labelledby="format-label"><g:fieldValue bean="${templateInstance}" field="format"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.hostId}">
				<li class="fieldcontain">
					<span id="hostId-label" class="property-label"><g:message code="template.hostId.label" default="Host Id" /></span>
					
						<span class="property-value" aria-labelledby="hostId-label"><g:fieldValue bean="${templateInstance}" field="hostId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.hostName}">
				<li class="fieldcontain">
					<span id="hostName-label" class="property-label"><g:message code="template.hostName.label" default="Host Name" /></span>
					
						<span class="property-value" aria-labelledby="hostName-label"><g:fieldValue bean="${templateInstance}" field="hostName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.hypervisor}">
				<li class="fieldcontain">
					<span id="hypervisor-label" class="property-label"><g:message code="template.hypervisor.label" default="Hypervisor" /></span>
					
						<span class="property-value" aria-labelledby="hypervisor-label"><g:fieldValue bean="${templateInstance}" field="hypervisor"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.ispublic}">
				<li class="fieldcontain">
					<span id="ispublic-label" class="property-label"><g:message code="template.ispublic.label" default="Ispublic" /></span>
					
						<span class="property-value" aria-labelledby="ispublic-label"><g:formatBoolean boolean="${templateInstance?.ispublic}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.jobId}">
				<li class="fieldcontain">
					<span id="jobId-label" class="property-label"><g:message code="template.jobId.label" default="Job Id" /></span>
					
						<span class="property-value" aria-labelledby="jobId-label"><g:fieldValue bean="${templateInstance}" field="jobId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.jobStatus}">
				<li class="fieldcontain">
					<span id="jobStatus-label" class="property-label"><g:message code="template.jobStatus.label" default="Job Status" /></span>
					
						<span class="property-value" aria-labelledby="jobStatus-label"><g:fieldValue bean="${templateInstance}" field="jobStatus"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="template.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${templateInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="template.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${templateInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.passwordEnabled}">
				<li class="fieldcontain">
					<span id="passwordEnabled-label" class="property-label"><g:message code="template.passwordEnabled.label" default="Password Enabled" /></span>
					
						<span class="property-value" aria-labelledby="passwordEnabled-label"><g:formatBoolean boolean="${templateInstance?.passwordEnabled}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.ready}">
				<li class="fieldcontain">
					<span id="ready-label" class="property-label"><g:message code="template.ready.label" default="Ready" /></span>
					
						<span class="property-value" aria-labelledby="ready-label"><g:formatBoolean boolean="${templateInstance?.ready}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.size}">
				<li class="fieldcontain">
					<span id="size-label" class="property-label"><g:message code="template.size.label" default="Size" /></span>
					
						<span class="property-value" aria-labelledby="size-label"><g:fieldValue bean="${templateInstance}" field="size"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="template.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${templateInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.stillActive}">
				<li class="fieldcontain">
					<span id="stillActive-label" class="property-label"><g:message code="template.stillActive.label" default="Still Active" /></span>
					
						<span class="property-value" aria-labelledby="stillActive-label"><g:formatBoolean boolean="${templateInstance?.stillActive}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.templateId}">
				<li class="fieldcontain">
					<span id="templateId-label" class="property-label"><g:message code="template.templateId.label" default="Template Id" /></span>
					
						<span class="property-value" aria-labelledby="templateId-label"><g:fieldValue bean="${templateInstance}" field="templateId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.type}">
				<li class="fieldcontain">
					<span id="type-label" class="property-label"><g:message code="template.type.label" default="Type" /></span>
					
						<span class="property-value" aria-labelledby="type-label"><g:fieldValue bean="${templateInstance}" field="type"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.zone}">
				<li class="fieldcontain">
					<span id="zone-label" class="property-label"><g:message code="template.zone.label" default="Zone" /></span>
					
						<span class="property-value" aria-labelledby="zone-label"><g:fieldValue bean="${templateInstance}" field="zone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${templateInstance?.zoneId}">
				<li class="fieldcontain">
					<span id="zoneId-label" class="property-label"><g:message code="template.zoneId.label" default="Zone Id" /></span>
					
						<span class="property-value" aria-labelledby="zoneId-label"><g:fieldValue bean="${templateInstance}" field="zoneId"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${templateInstance?.id}" />
					<g:link class="edit" action="edit" id="${templateInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
