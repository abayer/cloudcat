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
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>

		<title>Welcome to CloudCat</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}
            
			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="page-body" role="main">
			<h1>Welcome to CloudCat</h1>
			<p>CloudCat's functionality is listed below.</p>

			<div id="controller-list" role="navigation">
                          <sec:ifLoggedIn>
                            <h2>Logout</h2>
                            <ul>
                              <li class="controller"><g:link controller="logout">Logout</g:link></li>
                            </ul>
                          </sec:ifLoggedIn>
                          <sec:ifNotLoggedIn>
                            <h2>Login</h2>
                            <ul>
                              <li class="controller"><g:link controller="login">Login</g:link></li>
                            </ul>
                          </sec:ifNotLoggedIn>
                          <h2>Provisioning:</h2>
                          <ul>
                            <li class="controller"><g:link controller="provisionedInstanceGroup" action="create">Provision New Instances</g:link></li>
                            <sec:ifLoggedIn>
                              <li class="controller"><g:link controller="provisionedInstanceGroup" action="list" params="[filter_username: sec.username(), filter_op_username:'Equal']">Report on My Provisioned Instance Groups</g:link></li>
                              <li class="controller"><g:link controller="provisionedInstance" action="list" params="[filter_provisionedInstanceGroup_username: sec.username(), filter_op_provisionedInstanceGroup_username:'Equal']">My Provisioned Instances</g:link></li>
                            </sec:ifLoggedIn>
                            <li class="controller"><g:link controller="provisionedInstanceGroup">Report on All Provisioned Instance Groups</g:link></li>
                            <li class="controller"><g:link controller="provisionedInstance">All Provisioned Instances</g:link></li>
                          </ul>
                          <h2>Available Reports:</h2>
                          <ul>
                            <li class="controller"><g:link controller="reportRun">Instance Usage Reports</g:link></li>
                            <li class="controller"><g:link controller="instance">All Instances</g:link></li>
                            <li class="controller"><g:link controller="host">All Hosts</g:link></li>
                            <li class="controller"><g:link controller="template">All Templates</g:link></li>
                          </ul>
                          
                          <sec:access expression="hasRole('ROLE_ADMIN')">
                            <h2>Admin:</h2>
                            <ul>
                              <li class="controller"><g:link controller="logging">CloudCat Logging</g:link></li>
                              <li class="controller"><g:link controller="cloudStackConfig">Configuration</g:link></li>
                              <li class="controller"><g:link controller="user">User Admin</g:link></li>
                              <li class="controller"><g:link controller="role">Role/Group Admin</g:link></li>
                            </ul>
                          </sec:access>
			</div>
		</div>
	</body>
</html>

