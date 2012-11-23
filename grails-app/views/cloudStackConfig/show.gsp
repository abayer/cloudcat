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

<%@ page import="cloudstack.reporting.CloudStackConfig" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">

		<g:set var="entityName" value="${message(code: 'cloudStackConfig.label', default: 'CloudStackConfig')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-cloudStackConfig" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-cloudStackConfig" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cloudStackConfig">
			
				<g:if test="${cloudStackConfigInstance?.endPointUrl}">
				<li class="fieldcontain">
					<span id="endPointUrl-label" class="property-label"><g:message code="cloudStackConfig.endPointUrl.label" default="End Point Url" /></span>
					
						<span class="property-value" aria-labelledby="endPointUrl-label"><g:fieldValue bean="${cloudStackConfigInstance}" field="endPointUrl"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cloudStackConfigInstance?.apiKey}">
				<li class="fieldcontain">
					<span id="apiKey-label" class="property-label"><g:message code="cloudStackConfig.apiKey.label" default="Api Key" /></span>
					
						<span class="property-value" aria-labelledby="apiKey-label"><g:fieldValue bean="${cloudStackConfigInstance}" field="apiKey"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cloudStackConfigInstance?.secretKey}">
				<li class="fieldcontain">
					<span id="secretKey-label" class="property-label"><g:message code="cloudStackConfig.secretKey.label" default="Secret Key" /></span>
					
						<span class="property-value" aria-labelledby="secretKey-label">(hidden)</span>
					
				</li>
				</g:if>
			
				<g:if test="${cloudStackConfigInstance?.alertEmailTo}">
				<li class="fieldcontain">
					<span id="alertEmailTo-label" class="property-label"><g:message code="cloudStackConfig.alertEmailTo.label" default="Alert Email To Addresses" /></span>
					
						<span class="property-value" aria-labelledby="alertEmailto-label"><g:fieldValue bean="${cloudStackConfigInstance}" field="alertEmailTo"/></span>
					
				</li>
				</g:if>

				<g:if test="${cloudStackConfigInstance?.alertEmailFrom}">
				<li class="fieldcontain">
					<span id="alertEmailFrom-label" class="property-label"><g:message code="cloudStackConfig.alertEmailFrom.label" default="Alert Email From Address" /></span>
					
						<span class="property-value" aria-labelledby="alertEmailFrom-label"><g:fieldValue bean="${cloudStackConfigInstance}" field="alertEmailFrom"/></span>
					
				</li>
				</g:if>

				<g:if test="${cloudStackConfigInstance?.emailDomain}">
				<li class="fieldcontain">
					<span id="emailDomain-label" class="property-label"><g:message code="cloudStackConfig.emailDomain.label" default="Email Domain" /></span>
					
						<span class="property-value" aria-labelledby="emailDomain-label"><g:fieldValue bean="${cloudStackConfigInstance}" field="emailDomain"/></span>
					
				</li>
				</g:if>

                                <g:if test="${cloudStackConfigInstance?.maxProvDestroy}">
				<li class="fieldcontain">
					<span id="maxProvDestroy-label" class="property-label"><g:message code="cloudStackConfig.maxProvDestroy.label" default="Max # of Concurrent Provisions" /></span>
					
						<span class="property-value" aria-labelledby="maxProvDestroy-label"><g:fieldValue bean="${cloudStackConfigInstance}" field="maxProvDestroy"/></span>
					
				</li>
				</g:if>

                                <g:if test="${cloudStackConfigInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="cloudStackConfig.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${cloudStackConfigInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${cloudStackConfigInstance?.id}" />
					<g:link class="edit" action="edit" id="${cloudStackConfigInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
