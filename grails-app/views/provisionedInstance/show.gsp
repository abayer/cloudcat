
<%@ page import="cloudstack.reporting.ProvisionedInstance" %>
<%@ page import="cloudstack.reporting.Instance" %>
<%@ page import="cloudstack.reporting.ProvisionedInstanceStatus" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'provisionedInstance.label', default: 'ProvisionedInstance')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-provisionedInstance" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-provisionedInstance" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list provisionedInstance">
			
				<g:if test="${provisionedInstanceInstance?.hostname}">
				<li class="fieldcontain">
					<span id="hostname-label" class="property-label"><g:message code="provisionedInstance.hostname.label" default="Hostname" /></span>
					
						<span class="property-value" aria-labelledby="hostname-label"><g:fieldValue bean="${provisionedInstanceInstance}" field="hostname"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${provisionedInstanceInstance?.instanceName}">
				<li class="fieldcontain">
					<span id="instanceName-label" class="property-label"><g:message code="provisionedInstance.instanceName.label" default="Instance Name" /></span>
					
						<span class="property-value" aria-labelledby="instanceName-label"><g:fieldValue bean="${provisionedInstanceInstance}" field="instanceName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${provisionedInstanceInstance?.publicIp}">
				<li class="fieldcontain">
					<span id="publicIp-label" class="property-label"><g:message code="provisionedInstance.publicIp.label" default="Public Ip" /></span>
					
						<span class="property-value" aria-labelledby="publicIp-label"><g:fieldValue bean="${provisionedInstanceInstance}" field="publicIp"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${provisionedInstanceInstance?.instanceId}">
				<li class="fieldcontain">
					<span id="instanceId-label" class="property-label"><g:message code="provisionedInstance.instanceId.label" default="Instance Id" /></span>
					
						<span class="property-value" aria-labelledby="instanceId-label"><g:fieldValue bean="${provisionedInstanceInstance}" field="instanceId"/></span>
					
				</li>
                                <g:if test="${Instance.findByInstanceId(provisionedInstanceInstance?.instanceId) != null}">
				<li class="fieldcontain">
					<span id="instanceStatus-label" class="property-label"><g:message code="provisionedInstance.instanceStatus.label" default="Monitoring Status" /></span>
					
						<span class="property-value" aria-labelledby="instanceStatus-label"><g:link controller="instance" action="show" id="${Instance.findByInstanceId(provisionedInstanceInstance.instanceId).id}">${Instance.findByInstanceId(provisionedInstanceInstance.instanceId).state.encodeAsHTML()}</g:link></span>
                                            
					
				</li>
                                  
				</g:if>
                                </g:if>
			
				<g:if test="${provisionedInstanceInstance?.errorMsg}">
				<li class="fieldcontain">
					<span id="errorMsg-label" class="property-label"><g:message code="provisionedInstance.errorMsg.label" default="Err Msg" /></span>
					
						<span class="property-value" aria-labelledby="errorMsg-label"><g:fieldValue bean="${provisionedInstanceInstance}" field="errorMsg"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${provisionedInstanceInstance?.role}">
				<li class="fieldcontain">
					<span id="role-label" class="property-label"><g:message code="provisionedInstance.role.label" default="Role" /></span>
					
						<span class="property-value" aria-labelledby="role-label"><g:fieldValue bean="${provisionedInstanceInstance}" field="role"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${provisionedInstanceInstance?.provisionedInstanceGroup}">
				<li class="fieldcontain">
					<span id="provisionedInstanceGroup-label" class="property-label"><g:message code="provisionedInstance.provisionedInstanceGroup.label" default="Provisioned Instance Group" /></span>
					
						<span class="property-value" aria-labelledby="provisionedInstanceGroup-label"><g:link controller="provisionedInstanceGroup" action="show" id="${provisionedInstanceInstance?.provisionedInstanceGroup?.id}">${provisionedInstanceInstance?.provisionedInstanceGroup?.shortName.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${provisionedInstanceInstance?.provisionedInstanceGroup?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="provisionedInstanceGroup.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:link controller="provisionedInstance" action="list" params="[username: provisionedInstanceInstance.provisionedInstanceGroup.username]"><g:fieldValue bean="${provisionedInstanceInstance.provisionedInstanceGroup}" field="username"/></g:link></span>
					
				</li>
				</g:if>

                                <li class="fieldcontain">
					<span id="provisionStatus-label" class="property-label"><g:message code="provisionedInstance.provisionStatus.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="provisionStatus-label">${ProvisionedInstanceStatus.instanceStatuses[provisionedInstanceInstance.provisionStatus]}</span>
					
				</li>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${provisionedInstanceInstance?.id}" />
                                        <g:if test="${canModify}">
                                          <g:if test="${provisionedInstanceInstance?.provisionStatus in [2, 7]}">
                                            <g:actionSubmit class="reprovision" action="reprovision" value="${message(code: 'provisionedInstance.reprovision.label', default: 'Reprovision Single Instance')}" onclick="return confirm('${message(code: 'provisionedInstance.reprovision.confirm.message', default: 'Are you sure you want to reprovision this instance?')}');" />
                                          </g:if>
                                          <g:if test="${provisionedInstanceInstance?.provisionStatus == 0}">
                                            <g:actionSubmit class="cancel" action="cancel" value="${message(code: 'provisionedInstance.cancel.label', default: 'Cancel Single Instance Creation')}" onclick="return confirm('${message(code: 'provisionedInstance.cancel.confirm.message', default: 'Are you sure you want to cancel creation of this instance?')}');" />
                                          </g:if>
                                          <g:if test="${provisionedInstanceInstance?.provisionStatus in [2, 3]}">
                                            <g:actionSubmit class="destroy" action="destroy" value="${message(code: 'default.button.destroy.label', default: 'Destroy Single Instance')}" onclick="return confirm('${message(code: 'default.button.destroy.confirm.message', default: 'Are you sure you want to destroy this instance?')}');" />
                                          </g:if>
                                        </g:if>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
