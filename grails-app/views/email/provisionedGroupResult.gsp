<%@ page import="cloudstack.reporting.ProvisionedInstanceStatus" %>
<html>
  <body>
    <g:if test="${group.provisionStatus == 3}">
      <p>Your CloudStack instances for the group "${group.shortName}" have been successfully provisioned:</p>
      <ul>
        <g:each in="${group.provisionedInstances.sort { it.instanceName } }" var="p">
          <li>${p.hostname.encodeAsHTML()}</li>
        </g:each>
      </ul>
      <p>Don't forget to clean up your instances when you're done with them, at <g:link controller="provisionedInstanceGroup" action="show" id="${group.id}">CloudCat</g:link>.</p>
    </g:if>
    <g:if test="${group.provisionStatus == 2}">
      <p>Errors have been encountered when provisioning your CloudStack instances for the group "${group.shortName}".</p>
      <ul>
        <g:each in="${group.provisionedInstances.sort { it.instanceName } }" var="p">
          <li>${p.instanceName.encodeAsHTML()}: ${ProvisionedInstanceStatus.instanceStatuses[p.provisionStatus].encodeAsHTML()}</li>
          <g:if test="${p.provisionStatus == 2}">
            <ul>
              <li>Error: ${p.errMsg.encodeAsHTML()}</li>
            </ul>
          </g:if>
        </g:each>
      </ul>
      <p>Please <g:link controller="provisionedInstanceGroup" action="show" id="${group.id}">review your group's instances at CloudCat</g:link>, and if you won't be using any successful instances in your group, destroy the group.</p>
    </g:if>
  </body>
</html>