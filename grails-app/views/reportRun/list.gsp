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

		<g:set var="entityName" value="${message(code: 'reportRun.label', default: 'ReportRun')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-reportRun" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-reportRun" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
                                          <g:sortableColumn property="id" title="Report ID" defaultOrder="desc"/>
                                          <g:sortableColumn property="dateCreated" title="Report Date" defaultOrder="desc" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${reportRunList}" status="i" var="reportRunInfo">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                          <td><g:link action="report" params="[reportRunId:reportRunInfo.id]">${fieldValue(bean: reportRunInfo, field: "id")}</g:link></td>
                                          <td><g:link action="report" params="[reportRunId:reportRunInfo.id]"><g:formatDate date="${reportRunInfo.dateCreated}" /></g:link></td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${reportRunTotal}" />
			</div>
		</div>
	</body>
</html>
