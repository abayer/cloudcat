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
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-cpuUsage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-cpuUsage" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="cpuUsage" title="${message(code: 'cpuUsage.cpuUsage.label', default: 'Cpu Usage')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'cpuUsage.dateCreated.label', default: 'Date Created')}" />
					
						<th><g:message code="cpuUsage.instance.label" default="Instance" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${cpuUsageInstanceList}" status="i" var="cpuUsageInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: cpuUsageInstance, field: "cpuUsage")}</td>
					
						<td><g:formatDate date="${cpuUsageInstance.dateCreated}" /></td>
					
						<td><g:link controller="instance" action="show" id="${cpuUsageInstance?.instance?.id}">${cpuUsageInstance?.instance?.name?.encodeAsHTML()}</g:link></td>
								
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate params="[instanceId: cpuUsageInstanceId]"
                                            total="${cpuUsageInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
