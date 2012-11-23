<%@ page import="cloudstack.reporting.Template" %>



<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'templateTag', 'error')} ">
	<label for="templateTag">
		<g:message code="template.templateTag.label" default="Template Tag" />
		
	</label>
	<g:textField name="templateTag" value="${templateInstance?.templateTag}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'sourceTemplateId', 'error')} ">
	<label for="sourceTemplateId">
		<g:message code="template.sourceTemplateId.label" default="Source Template Id" />
		
	</label>
	<g:textField name="sourceTemplateId" value="${templateInstance?.sourceTemplateId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'removed', 'error')} ">
	<label for="removed">
		<g:message code="template.removed.label" default="Removed" />
		
	</label>
	<g:datePicker name="removed" precision="day"  value="${templateInstance?.removed}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'OSType', 'error')} ">
	<label for="OSType">
		<g:message code="template.OSType.label" default="OST ype" />
		
	</label>
	<g:textField name="OSType" value="${templateInstance?.OSType}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'OSTypeId', 'error')} ">
	<label for="OSTypeId">
		<g:message code="template.OSTypeId.label" default="OST ype Id" />
		
	</label>
	<g:textField name="OSTypeId" value="${templateInstance?.OSTypeId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'account', 'error')} ">
	<label for="account">
		<g:message code="template.account.label" default="Account" />
		
	</label>
	<g:textField name="account" value="${templateInstance?.account}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'accountId', 'error')} ">
	<label for="accountId">
		<g:message code="template.accountId.label" default="Account Id" />
		
	</label>
	<g:textField name="accountId" value="${templateInstance?.accountId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'bootable', 'error')} ">
	<label for="bootable">
		<g:message code="template.bootable.label" default="Bootable" />
		
	</label>
	<g:checkBox name="bootable" value="${templateInstance?.bootable}" />
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'checksum', 'error')} ">
	<label for="checksum">
		<g:message code="template.checksum.label" default="Checksum" />
		
	</label>
	<g:textField name="checksum" value="${templateInstance?.checksum}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'created', 'error')} required">
	<label for="created">
		<g:message code="template.created.label" default="Created" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="created" precision="day"  value="${templateInstance?.created}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'crossZones', 'error')} ">
	<label for="crossZones">
		<g:message code="template.crossZones.label" default="Cross Zones" />
		
	</label>
	<g:checkBox name="crossZones" value="${templateInstance?.crossZones}" />
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'displayText', 'error')} ">
	<label for="displayText">
		<g:message code="template.displayText.label" default="Display Text" />
		
	</label>
	<g:textField name="displayText" value="${templateInstance?.displayText}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'domain', 'error')} ">
	<label for="domain">
		<g:message code="template.domain.label" default="Domain" />
		
	</label>
	<g:textField name="domain" value="${templateInstance?.domain}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'domainId', 'error')} ">
	<label for="domainId">
		<g:message code="template.domainId.label" default="Domain Id" />
		
	</label>
	<g:textField name="domainId" value="${templateInstance?.domainId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'extractable', 'error')} ">
	<label for="extractable">
		<g:message code="template.extractable.label" default="Extractable" />
		
	</label>
	<g:checkBox name="extractable" value="${templateInstance?.extractable}" />
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'featured', 'error')} ">
	<label for="featured">
		<g:message code="template.featured.label" default="Featured" />
		
	</label>
	<g:checkBox name="featured" value="${templateInstance?.featured}" />
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'format', 'error')} ">
	<label for="format">
		<g:message code="template.format.label" default="Format" />
		
	</label>
	<g:textField name="format" value="${templateInstance?.format}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'hostId', 'error')} ">
	<label for="hostId">
		<g:message code="template.hostId.label" default="Host Id" />
		
	</label>
	<g:textField name="hostId" value="${templateInstance?.hostId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'hostName', 'error')} ">
	<label for="hostName">
		<g:message code="template.hostName.label" default="Host Name" />
		
	</label>
	<g:textField name="hostName" value="${templateInstance?.hostName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'hypervisor', 'error')} ">
	<label for="hypervisor">
		<g:message code="template.hypervisor.label" default="Hypervisor" />
		
	</label>
	<g:textField name="hypervisor" value="${templateInstance?.hypervisor}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'ispublic', 'error')} ">
	<label for="ispublic">
		<g:message code="template.ispublic.label" default="Ispublic" />
		
	</label>
	<g:checkBox name="ispublic" value="${templateInstance?.ispublic}" />
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'jobId', 'error')} ">
	<label for="jobId">
		<g:message code="template.jobId.label" default="Job Id" />
		
	</label>
	<g:textField name="jobId" value="${templateInstance?.jobId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'jobStatus', 'error')} ">
	<label for="jobStatus">
		<g:message code="template.jobStatus.label" default="Job Status" />
		
	</label>
	<g:textField name="jobStatus" value="${templateInstance?.jobStatus}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="template.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${templateInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'passwordEnabled', 'error')} ">
	<label for="passwordEnabled">
		<g:message code="template.passwordEnabled.label" default="Password Enabled" />
		
	</label>
	<g:checkBox name="passwordEnabled" value="${templateInstance?.passwordEnabled}" />
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'ready', 'error')} ">
	<label for="ready">
		<g:message code="template.ready.label" default="Ready" />
		
	</label>
	<g:checkBox name="ready" value="${templateInstance?.ready}" />
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'size', 'error')} required">
	<label for="size">
		<g:message code="template.size.label" default="Size" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="size" type="number" value="${templateInstance.size}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="template.status.label" default="Status" />
		
	</label>
	<g:textField name="status" value="${templateInstance?.status}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'stillExists', 'error')} ">
	<label for="stillExists">
		<g:message code="template.stillExists.label" default="Still Exists" />
		
	</label>
	<g:checkBox name="stillExists" value="${templateInstance?.stillExists}" />
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'templateId', 'error')} ">
	<label for="templateId">
		<g:message code="template.templateId.label" default="Template Id" />
		
	</label>
	<g:textField name="templateId" value="${templateInstance?.templateId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'type', 'error')} ">
	<label for="type">
		<g:message code="template.type.label" default="Type" />
		
	</label>
	<g:textField name="type" value="${templateInstance?.type}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'zone', 'error')} ">
	<label for="zone">
		<g:message code="template.zone.label" default="Zone" />
		
	</label>
	<g:textField name="zone" value="${templateInstance?.zone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'zoneId', 'error')} ">
	<label for="zoneId">
		<g:message code="template.zoneId.label" default="Zone Id" />
		
	</label>
	<g:textField name="zoneId" value="${templateInstance?.zoneId}"/>
</div>

