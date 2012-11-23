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
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">

		<g:set var="entityName" value="${message(code: 'instance.label', default: 'Instances')}" />
		<title><g:message code="instance.find.label" args="[entityName, account, templateName]" /></title>
	</head>
	<body>
		<a href="#find-instance" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="find-instance" class="content scaffold-find" role="main">
			<h1><g:message code="instance.find.label" args="[entityName, account, templateName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
                                          
                                          <g:sortableColumn property="name" title="${message(code: 'instance.name.label', default: 'Instance Name')}"
                                                            params="[account: account, reportRun: reportRun, templateId: templateId]"/>
                                          
                                          <g:sortableColumn property="account" title="${message(code: 'instance.account.label', default: 'Account')}"
                                                            params="[account: account, reportRun: reportRun, templateId: templateId]"/>
                                          
                                          <g:sortableColumn property="serviceOfferingName" title="${message(code: 'instance.serviceOfferingName.label', default: 'Size')}"
                                                            params="[account: account, reportRun: reportRun, templateId: templateId]"/>
                                          
                                          <g:sortableColumn property="publicIPs" title="${message(code: 'instance.publicIPs.label', default: 'IP')}"
                                                            params="[account: account, reportRun: reportRun, templateId: templateId]"/>
                                          
                                          <g:sortableColumn property="state" title="${message(code: 'instance.state.label', default: 'State')}"
                                                            params="[account: account, reportRun: reportRun, templateId: templateId]"/>

                                          <g:sortableColumn property="created" title="${message(code: 'instance.templateName.label', default: 'Template')}"
                                                            params="[account: account, reportRun: reportRun, templateId: templateId]"/>

                                          <g:sortableColumn property="created" title="${message(code: 'instance.created.label', default: 'Created')}"
                                                            params="[account: account, reportRun: reportRun, templateId: templateId]"/>
                                          
					</tr>
				</thead>
				<tbody>
				<g:each in="${instanceInstanceList}" status="i" var="instanceInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${instanceInstance.id}">${fieldValue(bean: instanceInstance, field: "name")}</g:link></td>
					
                                                <td><g:link action="find" params="[account: instanceInstance.account, reportRun: reportRun]">${fieldValue(bean: instanceInstance, field: "account")}</g:link></td>
					
						<td>${fieldValue(bean: instanceInstance, field: "serviceOfferingName")}</td>
					
						<td>${fieldValue(bean: instanceInstance, field: "publicIPs")}</td>

						<td>${fieldValue(bean: instanceInstance, field: "state")}</td>

                                                <td><g:link action="find" controller="template" id="${instanceInstance.csTemplateId}">${fieldValue(bean: instanceInstance, field: "templateName")}</g:link></td>

                                                <td><g:formatDate date="${instanceInstance.created}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${instanceInstanceTotal}"
                                            params="[account: account, reportRun: reportRun, templateId: templateId]"/>
                                
			</div>
		</div>
	</body>
</html>
