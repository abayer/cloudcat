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

<%@ page import="cloudstack.reporting.CpuUsage" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">

		<g:set var="entityName" value="${message(code: 'cpuUsage.label', default: 'CpuUsage')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-cpuUsage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-cpuUsage" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cpuUsage">
			
				<g:if test="${cpuUsageInstance?.cpuUsage}">
				<li class="fieldcontain">
					<span id="cpuUsage-label" class="property-label"><g:message code="cpuUsage.cpuUsage.label" default="Cpu Usage" /></span>
					
						<span class="property-value" aria-labelledby="cpuUsage-label"><g:fieldValue bean="${cpuUsageInstance}" field="cpuUsage"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cpuUsageInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="cpuUsage.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${cpuUsageInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${cpuUsageInstance?.instance}">
				<li class="fieldcontain">
					<span id="instance-label" class="property-label"><g:message code="cpuUsage.instance.label" default="Instance" /></span>
					
						<span class="property-value" aria-labelledby="instance-label"><g:link controller="instance" action="show" id="${cpuUsageInstance?.instance?.id}">${cpuUsageInstance?.instance?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
		</div>
	</body>
</html>
