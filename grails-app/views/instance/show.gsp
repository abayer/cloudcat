<!--
/**
 * Licensed to Cloudera, Inc. under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  Cloudera, Inc. licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ -->

<%@ page import="cloudstack.reporting.Instance" %>
<%@ page import="cloudstack.reporting.CpuUsage" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">

		<g:set var="entityName" value="${message(code: 'instance.label', default: 'Instance')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
                <gvisualization:apiImport/>
              </head>
	<body>
		<a href="#show-instance" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-instance" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list instance">
			
				<g:if test="${instanceInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="instance.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${instanceInstance}" field="name"/></span>
					
				</li>
				</g:if>

				<g:if test="${instanceInstance?.state}">
				<li class="fieldcontain">
					<span id="state-label" class="property-label"><g:message code="instance.state.label" default="State" /></span>
					
						<span class="property-value" aria-labelledby="state-label"><g:fieldValue bean="${instanceInstance}" field="state"/></span>
					
				</li>
				</g:if>

				<g:if test="${instanceInstance?.account}">
				<li class="fieldcontain">
					<span id="account-label" class="property-label"><g:message code="instance.account.label" default="Account" /></span>
					
						<span class="property-value" aria-labelledby="account-label"><g:fieldValue bean="${instanceInstance}" field="account"/></span>
					
				</li>
				</g:if>

				<g:if test="${instanceInstance?.serviceOfferingName}">
				<li class="fieldcontain">
					<span id="serviceOfferingName-label" class="property-label"><g:message code="instance.serviceOfferingName.label" default="Size" /></span>
					
						<span class="property-value" aria-labelledby="serviceOfferingName-label"><g:fieldValue bean="${instanceInstance}" field="serviceOfferingName"/></span>
					
				</li>
				</g:if>

				<g:if test="${instanceInstance?.host}">
				<li class="fieldcontain">
					<span id="host-label" class="property-label"><g:message code="instance.host.label" default="Host" /></span>
					
						<span class="property-value" aria-labelledby="host-label"><g:link controller="host" action="show" id="${instanceInstance.host.id}">${instanceInstance?.host?.name.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>

				<g:if test="${instanceInstance?.memory}">
				<li class="fieldcontain">
					<span id="memory-label" class="property-label"><g:message code="instance.memory.label" default="Memory" /></span>
					
						<span class="property-value" aria-labelledby="memory-label"><g:fieldValue bean="${instanceInstance}" field="memory"/>mb</span>
					
				</li>
				</g:if>

                                <g:if test="${instanceInstance?.cpuCount}">
				<li class="fieldcontain">
					<span id="cpuCount-label" class="property-label"><g:message code="instance.cpuCount.label" default="CPU Count" /></span>
					
						<span class="property-value" aria-labelledby="cpuCount-label"><g:fieldValue bean="${instanceInstance}" field="cpuCount"/></span>
				</li>
				</g:if>
			
                                <g:if test="${instanceInstance?.cpuUsages}">
				<li class="fieldcontain">
					<span id="cpuUsages-label" class="property-label"><g:message code="instance.cpuUsages.label" default="CPU Used" /></span>
                                        <gvisualization:areaCoreChart elementId="cpuUsageChart" width="${400}" height="${240}" title="Last five days of CPU usage"
                                                                      columns="${[['string', 'Time'], ['number', 'Usage']]}"
                                                                      data="${cpuUsages}" />
                                        <span class="property-value" aria-labelledby="cpuUsages-label"><div id="cpuUsageChart"></div></span>
                                     					
				</li>
				</g:if>

                                <g:if test="${instanceInstance?.created}">
				<li class="fieldcontain">
					<span id="created-label" class="property-label"><g:message code="instance.created.label" default="Created" /></span>
					
						<span class="property-value" aria-labelledby="created-label"><g:formatDate date="${instanceInstance?.created}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${instanceInstance?.publicIPs}">
				<li class="fieldcontain">
					<span id="publicIPs-label" class="property-label"><g:message code="instance.publicIPs.label" default="Public IPs" /></span>
					
						<span class="property-value" aria-labelledby="publicIPs-label"><g:fieldValue bean="${instanceInstance}" field="publicIPs"/></span>
					
				</li>
				</g:if>

				<g:if test="${instanceInstance?.csTemplateId}">
				<li class="fieldcontain">
					<span id="csTemplateId-label" class="property-label"><g:message code="instance.csTemplateId.label" default="Template Id" /></span>
					
						<span class="property-value" aria-labelledby="csTemplateId-label"><g:link action="find" controller="template" id="${instanceInstance.csTemplateId}"><g:fieldValue bean="${instanceInstance}" field="csTemplateId"/></g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${instanceInstance?.templateName}">
				<li class="fieldcontain">
					<span id="templateName-label" class="property-label"><g:message code="instance.templateName.label" default="Template Name" /></span>
					
						<span class="property-value" aria-labelledby="templateName-label"><g:link action="show" controller="template" id="${instanceInstance.csTemplateId}"><g:fieldValue bean="${instanceInstance}" field="templateName"/></g:link></span>
					
				</li>
				</g:if>

                                <g:if test="${instanceInstance?.instanceId}">
				<li class="fieldcontain">
					<span id="instanceId-label" class="property-label"><g:message code="instance.instanceId.label" default="Instance Id" /></span>
					
						<span class="property-value" aria-labelledby="instanceId-label"><g:fieldValue bean="${instanceInstance}" field="instanceId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${instanceInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="instance.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${instanceInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
			
			</ol>
		</div>
	</body>
</html>
