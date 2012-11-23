<%@ page import="cloudstack.reporting.ProvisionedInstanceGroup" %>
<%@ page import="cloudstack.reporting.Template" %>
<%@ page import="grails.plugins.springsecurity.SpringSecurityService" %>

<div class="fieldcontain ${hasErrors(bean: provisionedInstanceGroupInstance, field: 'shortName', 'error')} required">
	<label for="shortName">
		<g:message code="provisionedInstanceGroup.shortName.label" default="Short Name" />
		<span class="required-indicator">*</span>
	</label>
        <g:textField name="shortName" value="${provisionedInstanceGroupInstance?.shortName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: provisionedInstanceGroupInstance, field: 'template', 'error')}">
	<label for="template">
		<g:message code="provisionedInstanceGroup.template.label" default="Template" />
		<span class="required-indicator">*</span>
	</label>
        Select from list <input type="radio" id="templateChooserList" name="templateChooser" value="1" ${templateChooser == null || templateChooser == 1 ? 'checked="checked"' : ''}/>
        Enter name <input type="radio" id="templateChooserText" name="templateChooser" value="2" ${templateChooser == 2 ? 'checked="checked"' : ''}/>
</div>
<!-- template chooser: ${templateChooser} -->
<div class="fieldcontain">
  <label for="template">
  </label>
  <g:select style="${templateChooser == null || templateChooser == 1 ? '' : 'display: none'}" id="templateDropDown" name="template.id" from="${Template.findAllByFeatured(true, [sort: 'name'])}" optionKey="id" optionValue="${{it.displayText + ' (' + it.name + ')'}}" required="" value="${provisionedInstanceGroupInstance?.template?.id}" class="many-to-one"/>

<g:field type="textfield" size="64" id="templateTextField" name="templateText" value="${provisionedInstanceGroupInstance?.template?.name}" style="${templateChooser == 2 ? '' : 'display: none'}" autocomplete="true" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: provisionedInstanceGroupInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="provisionedInstanceGroup.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${provisionedInstanceGroupInstance?.description}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: provisionedInstanceGroupInstance, field: 'sendEmail', 'error')} ">
	<label for="sendEmail">
		<g:message code="provisionedInstanceGroup.sendEmail.label" default="Send Email When Complete" />
		
	</label>
	<g:checkBox name="sendEmail" value="${provisionedInstanceGroupInstance?.sendEmail}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: provisionedInstanceGroupInstance, field: 'team', 'error')} ">
	<label for="team">
		<g:message code="provisionedInstanceGroup.team.label" default="Team (optional)" />
		
	</label>
	<g:select id="team" name="team.id" from="${cloudstack.reporting.SecRole.findAllByAuthorityNotLike('ROLE_%', [sort: 'authority'])}" optionKey="id" optionValue="authority" value="${provisionedInstanceGroupInstance?.team?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>


<div class="fieldcontain ${hasErrors(bean: provisionedInstanceGroupInstance, field: 'initScript', 'error')} ">
	<label for="initScript">
		<g:message code="provisionedInstanceGroup.initScript.label" default="Init Script (optional)" />
		
	</label>
	<g:textArea name="initScript" value="${provisionedInstanceGroupInstance?.initScript}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: provisionedInstanceGroupInstance, field: 'offset', 'error')} required">
	<label for="offset">
		<g:message code="provisionedInstanceGroup.offset.label" default="Offset to add to instance number" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="offset" type="number" value="${provisionedInstanceGroupInstance.offset}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: provisionedInstanceGroupInstance, field: 'primaryServiceOffering', 'error')} required">
	<label for="primaryServiceOffering">
		<g:message code="provisionedInstanceGroup.primaryServiceOffering.label" default="Primary Service Offering" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="primaryServiceOffering" name="primaryServiceOffering.id" from="${cloudstack.reporting.ServiceOffering.findAllByStillActive(true, [sort: 'memory'])}" optionKey="id"  optionValue="${{it.name + ' (' + it.cpuNumber + ' CPUs, ' + Math.round(it.memory.div(1024)) + 'gb RAM)'}}" required="" value="${provisionedInstanceGroupInstance?.primaryServiceOffering?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: provisionedInstanceGroupInstance, field: 'primaryCount', 'error')} required">
	<label for="primaryCount">
		<g:message code="provisionedInstanceGroup.primaryCount.label" default="Primary Count" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="primaryCount" type="number" value="${provisionedInstanceGroupInstance.primaryCount}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: provisionedInstanceGroupInstance, field: 'alternateServiceOffering', 'error')} ">
	<label for="alternateServiceOffering">
		<g:message code="provisionedInstanceGroup.alternateServiceOffering.label" default="Alternate Service Offering" />
		
	</label>
	<g:select id="alternateServiceOffering" name="alternateServiceOffering.id" from="${cloudstack.reporting.ServiceOffering.findAllByStillActive(true, [sort: 'memory'])}" optionKey="id"  optionValue="${{it.name + ' (' + it.cpuNumber + ' CPUs, ' + Math.round(it.memory.div(1024)) + 'gb RAM)'}}" value="${provisionedInstanceGroupInstance?.alternateServiceOffering?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: provisionedInstanceGroupInstance, field: 'secondaryCount', 'error')} required">
	<label for="secondaryCount">
		<g:message code="provisionedInstanceGroup.secondaryCount.label" default="Alternate Count" />
	</label>
	<g:field name="secondaryCount" type="number" value="${provisionedInstanceGroupInstance.secondaryCount}" required=""/>
</div>

<g:hiddenField name="username" value="${sec.username()}" />
<g:hiddenField name="provisionStatus" value="0" />


