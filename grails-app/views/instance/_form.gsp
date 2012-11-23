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



<div class="fieldcontain ${hasErrors(bean: instanceInstance, field: 'cpuUsed', 'error')} ">
	<label for="cpuUsed">
		<g:message code="instance.cpuUsed.label" default="Cpu Used" />
		
	</label>
	<g:textField name="cpuUsed" value="${instanceInstance?.cpuUsed}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: instanceInstance, field: 'hostId', 'error')} ">
	<label for="hostId">
		<g:message code="instance.hostId.label" default="Host Id" />
		
	</label>
	<g:textField name="hostId" value="${instanceInstance?.hostId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: instanceInstance, field: 'account', 'error')} ">
	<label for="account">
		<g:message code="instance.account.label" default="Account" />
		
	</label>
	<g:textField name="account" value="${instanceInstance?.account}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: instanceInstance, field: 'cpuCount', 'error')} required">
	<label for="cpuCount">
		<g:message code="instance.cpuCount.label" default="Cpu Count" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cpuCount" type="number" value="${instanceInstance.cpuCount}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: instanceInstance, field: 'created', 'error')} required">
	<label for="created">
		<g:message code="instance.created.label" default="Created" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="created" precision="day"  value="${instanceInstance?.created}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: instanceInstance, field: 'instanceId', 'error')} ">
	<label for="instanceId">
		<g:message code="instance.instanceId.label" default="Instance Id" />
		
	</label>
	<g:textField name="instanceId" value="${instanceInstance?.instanceId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: instanceInstance, field: 'memory', 'error')} required">
	<label for="memory">
		<g:message code="instance.memory.label" default="Memory" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="memory" type="number" value="${instanceInstance.memory}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: instanceInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="instance.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${instanceInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: instanceInstance, field: 'publicIPs', 'error')} ">
	<label for="publicIPs">
		<g:message code="instance.publicIPs.label" default="Public IP s" />
		
	</label>
	<g:textField name="publicIPs" value="${instanceInstance?.publicIPs}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: instanceInstance, field: 'reportRuns', 'error')} ">
	<label for="reportRuns">
		<g:message code="instance.reportRuns.label" default="Report Runs" />
		
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: instanceInstance, field: 'rootDeviceId', 'error')} ">
	<label for="rootDeviceId">
		<g:message code="instance.rootDeviceId.label" default="Root Device Id" />
		
	</label>
	<g:textField name="rootDeviceId" value="${instanceInstance?.rootDeviceId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: instanceInstance, field: 'serviceOfferingName', 'error')} ">
	<label for="serviceOfferingName">
		<g:message code="instance.serviceOfferingName.label" default="Service Offering Name" />
		
	</label>
	<g:textField name="serviceOfferingName" value="${instanceInstance?.serviceOfferingName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: instanceInstance, field: 'state', 'error')} ">
	<label for="state">
		<g:message code="instance.state.label" default="State" />
		
	</label>
	<g:textField name="state" value="${instanceInstance?.state}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: instanceInstance, field: 'templateId', 'error')} ">
	<label for="templateId">
		<g:message code="instance.templateId.label" default="Template Id" />
		
	</label>
	<g:textField name="templateId" value="${instanceInstance?.templateId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: instanceInstance, field: 'templateName', 'error')} ">
	<label for="templateName">
		<g:message code="instance.templateName.label" default="Template Name" />
		
	</label>
	<g:textField name="templateName" value="${instanceInstance?.templateName}"/>
</div>

