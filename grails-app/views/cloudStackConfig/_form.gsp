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



<div class="fieldcontain ${hasErrors(bean: cloudStackConfigInstance, field: 'endPointUrl', 'error')} required">
	<label for="endPointUrl">
		<g:message code="cloudStackConfig.endPointUrl.label" default="End Point Url" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="endPointUrl" required="" value="${cloudStackConfigInstance?.endPointUrl}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cloudStackConfigInstance, field: 'apiKey', 'error')} required">
	<label for="apiKey">
		<g:message code="cloudStackConfig.apiKey.label" default="Api Key" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="apiKey" required="" value="${cloudStackConfigInstance?.apiKey}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cloudStackConfigInstance, field: 'secretKey', 'error')} required">
	<label for="secretKey">
		<g:message code="cloudStackConfig.secretKey.label" default="Secret Key" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="password" name="secretKey" required="" value="${cloudStackConfigInstance?.secretKey}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cloudStackConfigInstance, field: 'alertEmailTo', 'error')}">
	<label for="alertEmailTo">
		<g:message code="cloudStackConfig.alertEmailTo.label" default="Alert Email To Addresses" />
	</label>
	<g:textField name="alertEmailTo" required="" value="${cloudStackConfigInstance?.alertEmailTo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cloudStackConfigInstance, field: 'alertEmailFrom', 'error')}">
	<label for="alertEmailFrom">
		<g:message code="cloudStackConfig.alertEmailFrom.label" default="Alert Email From Address" />
	</label>
	<g:textField name="alertEmailFrom" required="" value="${cloudStackConfigInstance?.alertEmailFrom}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cloudStackConfigInstance, field: 'instanceAdminUser', 'error')}">
	<label for="instanceAdminUser">
		<g:message code="cloudStackConfig.instanceAdminUser.label" default="Instance Admin User" />
	</label>
	<g:textField name="instanceAdminUser" required="" value="${cloudStackConfigInstance?.instanceAdminUser}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cloudStackConfigInstance, field: 'instanceAdminPassword', 'error')}">
	<label for="instanceAdminPassword">
		<g:message code="cloudStackConfig.instanceAdminPassword.label" default="Instance Admin Password" />
	</label>
	<g:field type="password" name="instanceAdminPassword" required="" value="${cloudStackConfigInstance?.instanceAdminPassword}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cloudStackConfigInstance, field: 'emailDomain', 'error')}">
	<label for="emailDomain">
		<g:message code="cloudStackConfig.emailDomain.label" default="Email Domain" />
	</label>
	<g:textField name="emailDomain" required="" value="${cloudStackConfigInstance?.emailDomain}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cloudStackConfigInstance, field: 'setupStaticNat', 'error')} ">
	<label for="setupStaticNat">
		<g:message code="cloudStackConfig.setupStaticNat.label" default="Setup static NAT?" />
		
	</label>
	<g:checkBox name="setupStaticNat" value="${cloudStackConfigInstance?.setupStaticNat}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cloudStackConfigInstance, field: 'networkId', 'error')}">
	<label for="networkId">
		<g:message code="cloudStackConfig.networkId.label" default="Optional Network ID" />
	</label>
	<g:textField name="networkId" value="${cloudStackConfigInstance?.networkId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cloudStackConfigInstance, field: 'maxProvDestroy', 'error')} required">
	<label for="maxProvDestroy">
		<g:message code="cloudStackConfig.maxProvDestroy.label" default="Maximum # of Concurrent Provisions" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="maxProvDestroy" required="" value="${cloudStackConfigInstance?.maxProvDestroy}"/>
</div>
