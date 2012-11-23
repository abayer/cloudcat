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



<div class="fieldcontain ${hasErrors(bean: hostInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="host.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${hostInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostInstance, field: 'state', 'error')} ">
	<label for="state">
		<g:message code="host.state.label" default="State" />
		
	</label>
	<g:textField name="state" value="${hostInstance?.state}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostInstance, field: 'cpuNumber', 'error')} required">
	<label for="cpuNumber">
		<g:message code="host.cpuNumber.label" default="Cpu Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cpuNumber" type="number" value="${hostInstance.cpuNumber}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostInstance, field: 'cpuAllocated', 'error')} ">
	<label for="cpuAllocated">
		<g:message code="host.cpuAllocated.label" default="Cpu Allocated" />
		
	</label>
	<g:textField name="cpuAllocated" value="${hostInstance?.cpuAllocated}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostInstance, field: 'cpuUsed', 'error')} ">
	<label for="cpuUsed">
		<g:message code="host.cpuUsed.label" default="Cpu Used" />
		
	</label>
	<g:textField name="cpuUsed" value="${hostInstance?.cpuUsed}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostInstance, field: 'cpuSpeed', 'error')} required">
	<label for="cpuSpeed">
		<g:message code="host.cpuSpeed.label" default="Cpu Speed" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cpuSpeed" type="number" value="${hostInstance.cpuSpeed}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostInstance, field: 'cpuWithOverProvisioning', 'error')} required">
	<label for="cpuWithOverProvisioning">
		<g:message code="host.cpuWithOverProvisioning.label" default="Cpu With Over Provisioning" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cpuWithOverProvisioning" value="${fieldValue(bean: hostInstance, field: 'cpuWithOverProvisioning')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostInstance, field: 'memoryTotal', 'error')} required">
	<label for="memoryTotal">
		<g:message code="host.memoryTotal.label" default="Memory Total" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="memoryTotal" type="number" value="${hostInstance.memoryTotal}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostInstance, field: 'memoryAllocated', 'error')} required">
	<label for="memoryAllocated">
		<g:message code="host.memoryAllocated.label" default="Memory Allocated" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="memoryAllocated" type="number" value="${hostInstance.memoryAllocated}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostInstance, field: 'hasEnoughCapacity', 'error')} ">
	<label for="hasEnoughCapacity">
		<g:message code="host.hasEnoughCapacity.label" default="Has Enough Capacity" />
		
	</label>
	<g:checkBox name="hasEnoughCapacity" value="${hostInstance?.hasEnoughCapacity}" />
</div>

<div class="fieldcontain ${hasErrors(bean: hostInstance, field: 'created', 'error')} required">
	<label for="created">
		<g:message code="host.created.label" default="Created" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="created" precision="day"  value="${hostInstance?.created}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: hostInstance, field: 'disconnected', 'error')} ">
	<label for="disconnected">
		<g:message code="host.disconnected.label" default="Disconnected" />
		
	</label>
	<g:datePicker name="disconnected" precision="day"  value="${hostInstance?.disconnected}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: hostInstance, field: 'averageLoad', 'error')} required">
	<label for="averageLoad">
		<g:message code="host.averageLoad.label" default="Average Load" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="averageLoad" type="number" value="${hostInstance.averageLoad}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostInstance, field: 'capabilities', 'error')} ">
	<label for="capabilities">
		<g:message code="host.capabilities.label" default="Capabilities" />
		
	</label>
	<g:textField name="capabilities" value="${hostInstance?.capabilities}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostInstance, field: 'diskSizeAllocated', 'error')} required">
	<label for="diskSizeAllocated">
		<g:message code="host.diskSizeAllocated.label" default="Disk Size Allocated" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="diskSizeAllocated" type="number" value="${hostInstance.diskSizeAllocated}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostInstance, field: 'diskSizeTotal', 'error')} required">
	<label for="diskSizeTotal">
		<g:message code="host.diskSizeTotal.label" default="Disk Size Total" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="diskSizeTotal" type="number" value="${hostInstance.diskSizeTotal}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostInstance, field: 'hostId', 'error')} ">
	<label for="hostId">
		<g:message code="host.hostId.label" default="Host Id" />
		
	</label>
	<g:textField name="hostId" value="${hostInstance?.hostId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostInstance, field: 'instances', 'error')} ">
	<label for="instances">
		<g:message code="host.instances.label" default="Instances" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${hostInstance?.instances?}" var="i">
    <li><g:link controller="instance" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="instance" action="create" params="['host.id': hostInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'instance.label', default: 'Instance')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: hostInstance, field: 'memoryUsed', 'error')} required">
	<label for="memoryUsed">
		<g:message code="host.memoryUsed.label" default="Memory Used" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="memoryUsed" type="number" value="${hostInstance.memoryUsed}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostInstance, field: 'reportRuns', 'error')} ">
	<label for="reportRuns">
		<g:message code="host.reportRuns.label" default="Report Runs" />
		
	</label>
	
</div>

