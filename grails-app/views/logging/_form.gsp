<%@ page import="cloudstack.reporting.Logging" %>



<div class="fieldcontain ${hasErrors(bean: loggingInstance, field: 'user', 'error')} ">
	<label for="user">
		<g:message code="logging.user.label" default="User" />
		
	</label>
	<g:textField name="user" value="${loggingInstance?.user}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: loggingInstance, field: 'category', 'error')} required">
	<label for="category">
		<g:message code="logging.category.label" default="Category" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="category" from="${cloudstack.reporting.LoggingCategory?.values()}" keys="${cloudstack.reporting.LoggingCategory.values()*.name()}" required="" value="${loggingInstance?.category?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: loggingInstance, field: 'msg', 'error')} ">
	<label for="msg">
		<g:message code="logging.msg.label" default="Msg" />
		
	</label>
	<g:textField name="msg" value="${loggingInstance?.msg}"/>
</div>

