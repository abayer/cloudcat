<%@ page import="cloudstack.reporting.ProvisionedInstanceGroup" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'provisionedInstanceGroup.label', default: 'ProvisionedInstanceGroup')}" />
                <resource:include components="autoComplete" autoComplete="[skin: 'default']" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
                <g:javascript>
                 $(document).ready(function () {
                    $("#templateChooserText").change(function() {
                      if ($("#templateChooserText").prop('checked', true)) {

                        $("#templateDropDown").hide();
                        $("#templateTextField").show();
                      }
                    });
                    $("#templateChooserList").change(function() {
                      if ($("#templateChooserList").prop('checked', true)) {

                        $("#templateDropDown").show();
                        $("#templateTextField").hide();
                      }
                    });
                    $("#templateTextField").autocomplete({
                      source: function (request, response) {
                        $.ajax({
                          url: '${request.contextPath}' + '/template/templateListAJAX?term=' + request.term,
                          cache: true,
                          dataType: 'json',
                          type: 'get',
                          success: function(data) {
                            response(data);
                          },
                          error: function(message) {
                            response([]);
                          }
                        });
                      },
                      minLength: 2
                    });
                    $("#createForm").validate({
                      rules: {
                        templateText: { remote: '${request.contextPath}/template/validTemplateAJAX' },
                      },
                      messages: {
                        templateText: { remote: 'Must enter a valid template name.' },
                      },
                      onkeyup: true,
                      onblur: true
                    });
                    
                 });
                </g:javascript>
	</head>
	<body>
		<a href="#create-provisionedInstanceGroup" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-provisionedInstanceGroup" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${provisionedInstanceGroupInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${provisionedInstanceGroupInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form name="createForm" action="save" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
