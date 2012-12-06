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

<%@ page import="cloudstack.reporting.ReportRun" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">

		<g:set var="entityName" value="${message(code: 'reportRun.label', default: 'ReportRun Details of Running Instances')}" />
		<title><g:message code="default.report.label" args="[entityName,latestReportRun.dateCreated]" /></title>
	</head>
	<body>
		<a href="#list-reportRunDetails" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-reportRunDetails" class="content scaffold-list" role="main">
			<h1><g:message code="default.report.label" args="[entityName,latestReportRun.dateCreated]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
                                          <g:sortableColumn params="[reportRunId: latestReportRun.id]" property="account" title="Account" />
                                          <g:each in="${topSizes}" status="i" var="instanceSize">
                                            <g:sortableColumn params="[reportRunId: latestReportRun.id]" property="${instanceSize}" title="${instanceSize.length() > 10 ? instanceSize[0..6] + '...' : instanceSize }" />
                                          </g:each>
                                          <g:sortableColumn params="[reportRunId: latestReportRun.id]" property="other" title="Other" />
                                          <g:sortableColumn params="[reportRunId: latestReportRun.id]" property="total" title="Total Count" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${accountInstances}" status="i" var="accountInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                          <td><g:link action="find" controller="Instance" params="[account: accountInstance['account'], reportRun: latestReportRun.id]">${accountInstance["account"]}</g:link></td>
                                          <g:each in="${topSizes}" status="s" var="instanceSize">
                                            <td>${accountInstance[instanceSize]}</td>
                                          </g:each>
                                          <td>${accountInstance["other"]}</td>
                                          <td>${accountInstance["total"]}</td>
					</tr>
				</g:each>
                                <tr style="background: #66ffcc;">
                                  <td>All Accounts</td>
                                  <g:each in="${topSizes}" status="s" var="instanceSize">
                                    <td>${totalInstances[instanceSize]}</td>
                                  </g:each>
                                  <td>${totalInstances["other"]}</td>
                                  <td>${totalInstances["total"]}</td>
                                </tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
