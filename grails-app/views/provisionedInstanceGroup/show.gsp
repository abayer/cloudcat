
<%@ page import="cloudstack.reporting.ProvisionedInstanceGroup" %>
<%@ page import="cloudstack.reporting.ProvisionedInstanceStatus" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'provisionedInstanceGroup.label', default: 'ProvisionedInstanceGroup')}" />
                <jqDT:resources />
                <title><g:message code="default.show.label" args="[entityName]" /></title>
 <g:javascript>

   function linkifyInstance(obj) {
     var inst = obj.aData[obj.iDataColumn]
     return '<a href=' + '${request.contextPath + '/provisionedInstance/show/'}' + obj.aData[0] + ">" + inst + "</a>"
    }

    function ajaxStatus() {
    $.ajax({
    url: '${request.contextPath + '/provisionedInstanceGroup/ajaxStatus/' + provisionedInstanceGroupInstance.id}',
    cache: false,
    dataType: 'json',
    type: 'get',
    success: function(data) {
    var div = $('#groupStatus').html(data['provisionStatus']);
    }
    });
    }

    $(document).ready(function() {
    var iTable = $('#instances').dataTable({
          sAjaxSource: '${request.contextPath + '/provisionedInstance/dataTableList?group=' + provisionedInstanceGroupInstance?.id }' ,
          bServerSide: true,
          bProcessing: false,
          bPaginate: false,
          bFilter: false,
          bInfo: false,
          fnServerParams: function ( aoData ) {
            aoData.push( { "group": "${provisionedInstanceGroupInstance?.id}" } );
          },
          aoColumns: [ {bVisible: false}, null, null ],
          aoColumnDefs: [{
            fnRender: linkifyInstance,
            aTargets: [1]
            }]
          
       });

       var polling = setInterval( function () { iTable.fnDraw(false);
        ajaxStatus();
            },
       30000);
       ajaxStatus();
       });
 </g:javascript>
	</head>
	<body>
		<a href="#show-provisionedInstanceGroup" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-provisionedInstanceGroup" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list provisionedInstanceGroup">
			
				<g:if test="${provisionedInstanceGroupInstance?.alternateServiceOffering}">
				<li class="fieldcontain">
					<span id="alternateServiceOffering-label" class="property-label"><g:message code="provisionedInstanceGroup.alternateServiceOffering.label" default="Alternate Service Offering" /></span>
					
						<span class="property-value" aria-labelledby="alternateServiceOffering-label">${provisionedInstanceGroupInstance?.alternateServiceOffering?.name.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${provisionedInstanceGroupInstance?.expirationTime}">
				<li class="fieldcontain">
					<span id="expirationTime-label" class="property-label"><g:message code="provisionedInstanceGroup.expirationTime.label" default="Expiration Time" /></span>
					
						<span class="property-value" aria-labelledby="expirationTime-label"><g:formatDate date="${provisionedInstanceGroupInstance?.expirationTime}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${provisionedInstanceGroupInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="provisionedInstanceGroup.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${provisionedInstanceGroupInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${provisionedInstanceGroupInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="provisionedInstanceGroup.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${provisionedInstanceGroupInstance}" field="description"/></span>
					
				</li>
				</g:if>

                                <g:if test="${provisionedInstanceGroupInstance?.team}">
				<li class="fieldcontain">
					<span id="team-label" class="property-label"><g:message code="provisionedInstanceGroup.team.label" default="Team (optional)" /></span>
					
						<span class="property-value" aria-labelledby="team-label">${provisionedInstanceGroupInstance?.team?.authority.encodeAsHTML()}</span>

                                </li>
				</g:if>
							
				<g:if test="${provisionedInstanceGroupInstance?.initScript}">
				<li class="fieldcontain">
					<span id="initScript-label" class="property-label"><g:message code="provisionedInstanceGroup.initScript.label" default="initScript" /></span>
					
						<span class="property-value" aria-labelledby="initScript-label"><g:fieldValue bean="${provisionedInstanceGroupInstance}" field="initScript"/></span>
					
				</li>
				</g:if>

                                <g:if test="${provisionedInstanceGroupInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="provisionedInstanceGroup.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${provisionedInstanceGroupInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${provisionedInstanceGroupInstance?.offset}">
				<li class="fieldcontain">
					<span id="offset-label" class="property-label"><g:message code="provisionedInstanceGroup.offset.label" default="Offset" /></span>
					
						<span class="property-value" aria-labelledby="offset-label"><g:fieldValue bean="${provisionedInstanceGroupInstance}" field="offset"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${provisionedInstanceGroupInstance?.primaryCount}">
				<li class="fieldcontain">
					<span id="primaryCount-label" class="property-label"><g:message code="provisionedInstanceGroup.primaryCount.label" default="Primary Count" /></span>
					
						<span class="property-value" aria-labelledby="primaryCount-label"><g:fieldValue bean="${provisionedInstanceGroupInstance}" field="primaryCount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${provisionedInstanceGroupInstance?.primaryServiceOffering}">
				<li class="fieldcontain">
					<span id="primaryServiceOffering-label" class="property-label"><g:message code="provisionedInstanceGroup.primaryServiceOffering.label" default="Primary Service Offering" /></span>
					
						<span class="property-value" aria-labelledby="primaryServiceOffering-label">${provisionedInstanceGroupInstance?.primaryServiceOffering?.name.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${provisionedInstanceGroupInstance?.provisionedInstances}">
                                 
				<li class="fieldcontain">
					<span id="provisionedInstances-label" class="property-label"><g:message code="provisionedInstanceGroup.provisionedInstances.label" default="Provisioned Instances" /></span>
                                        <span class="property-value" aria-labelledby="provisionedInstances-label">
                                          
                                          <table id="instances">
                                            <thead>
                                              <tr>
                                                <th>ID</th>
                                                <th>Instance Name</th>
                                                <th>Status</th>
                                              </tr>
                                            </thead>
                                            <tbody></tbody>
                                          </table>
                                        </span>
				</li>
				</g:if>
			
				<g:if test="${provisionedInstanceGroupInstance?.secondaryCount}">
				<li class="fieldcontain">
					<span id="secondaryCount-label" class="property-label"><g:message code="provisionedInstanceGroup.secondaryCount.label" default="Alternate Count" /></span>
					
						<span class="property-value" aria-labelledby="secondaryCount-label"><g:fieldValue bean="${provisionedInstanceGroupInstance}" field="secondaryCount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${provisionedInstanceGroupInstance?.shortName}">
				<li class="fieldcontain">
					<span id="shortName-label" class="property-label"><g:message code="provisionedInstanceGroup.shortName.label" default="Short Name" /></span>
					
						<span class="property-value" aria-labelledby="shortName-label"><g:fieldValue bean="${provisionedInstanceGroupInstance}" field="shortName"/></span>
					
				</li>
				</g:if>
			
				<li class="fieldcontain">
					<span id="provisionStatus-label" class="property-label"><g:message code="provisionedInstanceGroup.provisionStatus.label" default="Status" /></span>
					
                                        <span class="property-value" aria-labelledby="provisionStatus-label"><div id="groupStatus"></div></span>
					
				</li>
			
				<g:if test="${provisionedInstanceGroupInstance?.template}">
				<li class="fieldcontain">
					<span id="template-label" class="property-label"><g:message code="provisionedInstanceGroup.template.label" default="Template" /></span>
					
						<span class="property-value" aria-labelledby="template-label"><g:link controller="template" action="show" id="${provisionedInstanceGroupInstance?.template?.id}">${provisionedInstanceGroupInstance?.template?.name.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${provisionedInstanceGroupInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="provisionedInstanceGroup.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:link controller="provisionedInstance" action="list" params="[username: provisionedInstanceGroupInstance.username]"><g:fieldValue bean="${provisionedInstanceGroupInstance}" field="username"/></g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${provisionedInstanceGroupInstance?.id}" />
                                        <g:if test="${canModify}">
                                          <g:if test="${provisionedInstanceGroupInstance?.provisionStatus == 1}">
                                            <g:actionSubmit class="cancel" action="cancel" value="${message(code: 'provisionedInstanceGroup.cancel.label', default: 'Cancel Instance Creation')}" onclick="return confirm('${message(code: 'provisionedInstanceGroup.cancel.confirm.message', default: 'Are you sure you want to cancel instance creation?')}');" />
                                          </g:if>
                                          <g:if test="${provisionedInstanceGroupInstance?.provisionStatus in [2, 3, 6]}">
                                            <g:actionSubmit class="destroy" action="destroy" value="${message(code: 'default.button.destroy.label', default: 'Destroy Instances')}" onclick="return confirm('${message(code: 'default.button.destroy.confirm.message', default: 'Are you sure you want to destroy all instances from this group?')}');" />
                                          </g:if>
                                        </g:if>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
