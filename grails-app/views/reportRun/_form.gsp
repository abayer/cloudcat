<%@ page import="cloudstack.reporting.ReportRun" %>



<div class="fieldcontain ${hasErrors(bean: reportRunInstance, field: 'instances', 'error')} ">
	<label for="instances">
		<g:message code="reportRun.instances.label" default="Instances" />
		
	</label>
	<g:select name="instances" from="${cloudstack.reporting.Instance.list()}" multiple="multiple" optionKey="id" size="5" value="${reportRunInstance?.instances*.id}" class="many-to-many"/>
</div>

