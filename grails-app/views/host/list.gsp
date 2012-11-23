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
<%@ page import="cloudstack.reporting.HostController" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">

		<g:set var="entityName" value="${message(code: 'host.label', default: 'Host')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
                <filterpane:includes />
	</head>
	<body>
		<a href="#list-host" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
                <div>
                  <filterpane:filterPane domain="cloudstack.reporting.Host"
                                         filterProperties="name,tags,state"
                                         titleKey="fp.tag.filterPane.titleText"/>
                  <filterpane:currentCriteria style="margin-left: 5%; margin-top: 1em;" domainBean="${Host}" />
                </div>
		<div id="list-host" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
                                          
						<g:sortableColumn params="${filterParams}" property="name" title="${message(code: 'host.name.label', default: 'Name')}" />

                                                <g:sortableColumn params="${filterParams}" property="cpuUsed" title="${message(code: 'host.cpuUsed.label', default: 'Cpu Used')}" />
					
						<g:sortableColumn params="${filterParams}" property="cpuAllocated" title="${message(code: 'host.cpuAllocated.label', default: 'Cpu Allocated')}" />

                                                <g:sortableColumn params="${filterParams}" property="memoryTotal" title="${message(code: 'host.memoryTotal.label', default: 'Total RAM')}" />
					
						<g:sortableColumn params="${filterParams}" property="memoryAllocated" title="${message(code: 'host.memoryAllocated.label', default: 'RAM Allocated')}" />

                                                <g:sortableColumn params="${filterParams}" property="state" title="${message(code: 'host.state.label', default: 'State')}" />

                                                <g:sortableColumn params="${filterParams}" property="instanceCount" title="${message(code: 'host.instanceCount.label', default: 'Instance Count')}" />

					
					</tr>
				</thead>
				<tbody>
				<g:each in="${hostInstanceList}" status="i" var="hostInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link action="show" id="${hostInstance.id}">${hostInstance.name}</g:link></td>

                                                <td>${hostInstance.cpuUsed}</td>
					
						<td>${hostInstance.cpuAllocated}</td>
					
                                                <td>${HostController.renderBytes(hostInstance.memoryTotal)}</td>
					
						<td>${HostController.renderBytes(hostInstance.memoryAllocated)}</td>

                                                <td>${hostInstance.state}</td>

                                                <td>${hostInstanceCounts[hostInstance.id]}</td>
					

					
					</tr>
				</g:each>
				</tbody>
			</table>
                        <div class="pagination">
                          <filterpane:paginate total="${hostInstanceTotal == null ? Host.count() : hostInstanceTotal}" />
                          <filterpane:filterButton textKey="fp.tag.filterButton.text" appliedTextKey="fp.tag.filterButton.appliedText" text="Filter Me" appliedText="Change Filter" />
                          <filterpane:isNotFiltered>Pure and Unfiltered!</filterpane:isNotFiltered>
                          <filterpane:isFiltered>Filter Applied!</filterpane:isFiltered>
                        </div>
                    </div>

	</body>
</html>
