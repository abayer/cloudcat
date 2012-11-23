<%@ page import="cloudstack.reporting.ProvisionedInstance" %>



<div class="fieldcontain ${hasErrors(bean: provisionedInstanceInstance, field: 'hostname', 'error')} ">
	<label for="hostname">
		<g:message code="provisionedInstance.hostname.label" default="Hostname" />
		
	</label>
	<g:textField name="hostname" value="${provisionedInstanceInstance?.hostname}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: provisionedInstanceInstance, field: 'instanceName', 'error')} ">
	<label for="instanceName">
		<g:message code="provisionedInstance.instanceName.label" default="Instance Name" />
		
	</label>
	<g:textField name="instanceName" value="${provisionedInstanceInstance?.instanceName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: provisionedInstanceInstance, field: 'publicIp', 'error')} ">
	<label for="publicIp">
		<g:message code="provisionedInstance.publicIp.label" default="Public Ip" />
		
	</label>
	<g:textField name="publicIp" value="${provisionedInstanceInstance?.publicIp}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: provisionedInstanceInstance, field: 'instanceId', 'error')} ">
	<label for="instanceId">
		<g:message code="provisionedInstance.instanceId.label" default="Instance Id" />
		
	</label>
	<g:textField name="instanceId" value="${provisionedInstanceInstance?.instanceId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: provisionedInstanceInstance, field: 'errorMsg', 'error')} ">
	<label for="errorMsg">
		<g:message code="provisionedInstance.errorMsg.label" default="Err Msg" />
		
	</label>
	<g:textField name="errorMsg" value="${provisionedInstanceInstance?.errorMsg}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: provisionedInstanceInstance, field: 'role', 'error')} ">
	<label for="role">
		<g:message code="provisionedInstance.role.label" default="Role" />
		
	</label>
	<g:textField name="role" value="${provisionedInstanceInstance?.role}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: provisionedInstanceInstance, field: 'provisionedInstanceGroup', 'error')} required">
	<label for="provisionedInstanceGroup">
		<g:message code="provisionedInstance.provisionedInstanceGroup.label" default="Provisioned Instance Group" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="provisionedInstanceGroup" name="provisionedInstanceGroup.id" from="${cloudstack.reporting.ProvisionedInstanceGroup.list()}" optionKey="id" required="" value="${provisionedInstanceInstance?.provisionedInstanceGroup?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: provisionedInstanceInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="provisionedInstance.status.label" default="Status" />
		
	</label>
	<g:textField name="status" value="${provisionedInstanceInstance?.status}"/>
</div>

