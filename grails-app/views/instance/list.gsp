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

		<g:set var="entityName" value="${message(code: 'instance.label', default: 'Instance')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
                <filterpane:includes />
	</head>
	<body>
		<a href="#list-instance" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
                <div>
                  <filterpane:filterPane domain="cloudstack.reporting.Instance"
                                         filterProperties="name,account,size,serviceOfferingName,templateName,state,lastUpdated"
                                         additionalProperties="lastUpdated"
                                         titleKey="fp.tag.filterPane.titleText"/>
                  <filterpane:currentCriteria style="margin-left: 5%; margin-top: 1em;" domainBean="${Instance}" />
                </div>
		<div id="list-instance" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
                                          
                                          <g:sortableColumn params="${filterParams}" property="name" title="${message(code: 'instance.name.label', default: 'Instance Name')}" />
                                          
                                          <g:sortableColumn params="${filterParams}" property="account" title="${message(code: 'instance.account.label', default: 'Account')}" />
                                          
                                          <g:sortableColumn params="${filterParams}" property="serviceOfferingName" title="${message(code: 'instance.serviceOfferingName.label', default: 'Size')}" />
                                          
                                          <g:sortableColumn params="${filterParams}" property="publicIPs" title="${message(code: 'instance.publicIPs.label', default: 'IP')}" />
                                          
                                          <g:sortableColumn params="${filterParams}" property="state" title="${message(code: 'instance.state.label', default: 'State')}" />

                                          <g:sortableColumn params="${filterParams}" property="created" title="${message(code: 'instance.created.label', default: 'Created')}" />
                                          
					</tr>
				</thead>
				<tbody>
				<g:each in="${instanceInstanceList}" status="i" var="instanceInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${instanceInstance.id}">${fieldValue(bean: instanceInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: instanceInstance, field: "account")}</td>
					
						<td>${fieldValue(bean: instanceInstance, field: "serviceOfferingName")}</td>
					
						<td>${fieldValue(bean: instanceInstance, field: "publicIPs")}</td>

						<td>${fieldValue(bean: instanceInstance, field: "state")}</td>

                                                <td><g:formatDate date="${instanceInstance.created}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
                        <div class="pagination">
                          <filterpane:paginate total="${instanceInstanceTotal == null ? Instance.count() : instanceInstanceTotal}" />
                          <filterpane:filterButton textKey="fp.tag.filterButton.text" appliedTextKey="fp.tag.filterButton.appliedText" text="Filter Me" appliedText="Change Filter" />
                          <filterpane:isNotFiltered>Pure and Unfiltered!</filterpane:isNotFiltered>
                          <filterpane:isFiltered>Filter Applied!</filterpane:isFiltered>
                        </div>
		</div>
	</body>
</html>
