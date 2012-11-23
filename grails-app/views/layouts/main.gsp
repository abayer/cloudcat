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
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Cloudstack Reporting"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
                  <g:javascript library="jquery" plugin="jquery"/>
                  <jqui:resources />
                  <jqval:resources />
		<g:javascript library="application"/>
                <g:layoutHead/>

              </head>
	<body>
		<div id="cloudstackLogo" role="banner"><a href="http://cloudstack.org"><img src="${resource(dir: 'images', file: 'cloudstackApache_logo_trans.png')}" alt="Cloudstack Reporting"/></a></div>
                <g:if test="${params.controller != 'login'}">
			<div id='cc_login_header_body'>
				<span id='cc_login_link_container'>

				<nobr>
				<div id='loginLinkContainer'>
				<sec:ifLoggedIn>
				Logged in as <sec:username/> (<g:link controller='logout'>Logout</g:link>)
				</sec:ifLoggedIn>
				<sec:ifNotLoggedIn>
                                  <g:link controller='login'>Login</g:link>
				</sec:ifNotLoggedIn>

				<sec:ifSwitched>
				<a href='${request.contextPath}/j_spring_security_exit_user'>
					Resume as <sec:switchedUserOriginalUsername/>
				</a>
				</sec:ifSwitched>
				</div>
				</nobr>

				</span>
			</div>
                </g:if>                
                <g:layoutBody/>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>


	</body>
</html>
