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

<%@ page import="cloudstack.reporting.Host" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">

		<g:set var="entityName" value="${message(code: 'host.label', default: 'Host')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-host" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-host" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list host">
			
				<g:if test="${hostInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="host.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${hostInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${hostInstance?.state}">
				<li class="fieldcontain">
					<span id="state-label" class="property-label"><g:message code="host.state.label" default="State" /></span>
					
						<span class="property-value" aria-labelledby="state-label"><g:fieldValue bean="${hostInstance}" field="state"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${hostInstance?.tags}">
				<li class="fieldcontain">
					<span id="tags-label" class="property-label"><g:message code="host.tags.label" default="Host Tags" /></span>
					
						<span class="property-value" aria-labelledby="tags-label"><g:fieldValue bean="${hostInstance}" field="tags"/></span>
					
				</li>
				</g:if>

                                <g:if test="${hostInstance?.cpuNumber}">
				<li class="fieldcontain">
					<span id="cpuNumber-label" class="property-label"><g:message code="host.cpuNumber.label" default="Cpu Number" /></span>
					
						<span class="property-value" aria-labelledby="cpuNumber-label"><g:fieldValue bean="${hostInstance}" field="cpuNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${hostInstance?.cpuAllocated}">
				<li class="fieldcontain">
					<span id="cpuAllocated-label" class="property-label"><g:message code="host.cpuAllocated.label" default="Cpu Allocated" /></span>
					
						<span class="property-value" aria-labelledby="cpuAllocated-label"><g:fieldValue bean="${hostInstance}" field="cpuAllocated"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${hostInstance?.cpuSpeed}">
				<li class="fieldcontain">
					<span id="cpuSpeed-label" class="property-label"><g:message code="host.cpuSpeed.label" default="Cpu Speed" /></span>
					
						<span class="property-value" aria-labelledby="cpuSpeed-label"><g:fieldValue bean="${hostInstance}" field="cpuSpeed"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${hostInstance?.cpuWithOverProvisioning}">
				<li class="fieldcontain">
					<span id="cpuWithOverProvisioning-label" class="property-label"><g:message code="host.cpuWithOverProvisioning.label" default="Cpu With Over Provisioning" /></span>
					
						<span class="property-value" aria-labelledby="cpuWithOverProvisioning-label"><g:fieldValue bean="${hostInstance}" field="cpuWithOverProvisioning"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${hostInstance?.memoryTotal}">
				<li class="fieldcontain">
					<span id="memoryTotal-label" class="property-label"><g:message code="host.memoryTotal.label" default="Memory Total" /></span>
					
						<span class="property-value" aria-labelledby="memoryTotal-label"><g:fieldValue bean="${hostInstance}" field="memoryTotal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${hostInstance?.memoryAllocated}">
				<li class="fieldcontain">
					<span id="memoryAllocated-label" class="property-label"><g:message code="host.memoryAllocated.label" default="Memory Allocated" /></span>
					
						<span class="property-value" aria-labelledby="memoryAllocated-label"><g:fieldValue bean="${hostInstance}" field="memoryAllocated"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${hostInstance?.hasEnoughCapacity}">
				<li class="fieldcontain">
					<span id="hasEnoughCapacity-label" class="property-label"><g:message code="host.hasEnoughCapacity.label" default="Has Enough Capacity" /></span>
					
						<span class="property-value" aria-labelledby="hasEnoughCapacity-label"><g:formatBoolean boolean="${hostInstance?.hasEnoughCapacity}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${hostInstance?.created}">
				<li class="fieldcontain">
					<span id="created-label" class="property-label"><g:message code="host.created.label" default="Created" /></span>
					
						<span class="property-value" aria-labelledby="created-label"><g:formatDate date="${hostInstance?.created}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${hostInstance?.disconnected && hostInstance?.state != 'Up'}">
				<li class="fieldcontain">
					<span id="disconnected-label" class="property-label"><g:message code="host.disconnected.label" default="Disconnected" /></span>
					
						<span class="property-value" aria-labelledby="disconnected-label"><g:formatDate date="${hostInstance?.disconnected}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${hostInstance?.capabilities}">
				<li class="fieldcontain">
					<span id="capabilities-label" class="property-label"><g:message code="host.capabilities.label" default="Capabilities" /></span>
					
						<span class="property-value" aria-labelledby="capabilities-label"><g:fieldValue bean="${hostInstance}" field="capabilities"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${hostInstance?.diskSizeAllocated}">
				<li class="fieldcontain">
					<span id="diskSizeAllocated-label" class="property-label"><g:message code="host.diskSizeAllocated.label" default="Disk Size Allocated" /></span>
					
						<span class="property-value" aria-labelledby="diskSizeAllocated-label"><g:fieldValue bean="${hostInstance}" field="diskSizeAllocated"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${hostInstance?.diskSizeTotal}">
				<li class="fieldcontain">
					<span id="diskSizeTotal-label" class="property-label"><g:message code="host.diskSizeTotal.label" default="Disk Size Total" /></span>
					
						<span class="property-value" aria-labelledby="diskSizeTotal-label"><g:fieldValue bean="${hostInstance}" field="diskSizeTotal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${hostInstance?.hostId}">
				<li class="fieldcontain">
					<span id="hostId-label" class="property-label"><g:message code="host.hostId.label" default="Host Id" /></span>
					
						<span class="property-value" aria-labelledby="hostId-label"><g:fieldValue bean="${hostInstance}" field="hostId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${hostInstance?.instances}">
				<li class="fieldcontain">
					<span id="instances-label" class="property-label"><g:message code="host.instances.label" default="Instances" /></span>
					
						<g:each in="${hostInstance.instances}" var="i">
                                                  <g:if test="${i.reportRuns.collect { it.id }.max() == latestReportRunId }">
                                                    <span class="property-value" aria-labelledby="instances-label"><g:link controller="instance" action="show" id="${i.id}">${i?.name.encodeAsHTML()}</g:link></span>
                                                  </g:if>

						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${hostInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="host.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${hostInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${hostInstance?.id}" />
					<g:link class="edit" action="edit" id="${hostInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
			</ol>
		</div>
	</body>
</html>
