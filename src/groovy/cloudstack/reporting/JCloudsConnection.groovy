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
 */
package cloudstack.reporting

import org.jclouds.Constants;
import org.jclouds.ContextBuilder;
import org.jclouds.apis.Apis
import org.jclouds.cloudstack.compute.strategy.CloudStackComputeServiceAdapter
import org.jclouds.cloudstack.domain.VirtualMachine
import org.jclouds.cloudstack.CloudStackContext
import org.jclouds.cloudstack.internal.CloudStackContextImpl
import org.jclouds.compute.ComputeService
import org.jclouds.compute.ComputeServiceContext
import org.jclouds.compute.config.ComputeServiceProperties
import org.jclouds.compute.domain.ComputeMetadata
import org.jclouds.compute.domain.NodeMetadata
import org.jclouds.crypto.SshKeys
import org.jclouds.enterprise.config.EnterpriseConfigurationModule
import org.jclouds.logging.slf4j.config.SLF4JLoggingModule
import org.jclouds.logging.jdk.config.JDKLoggingModule
import org.jclouds.providers.Providers
import org.jclouds.sshj.config.SshjSshClientModule
import org.jclouds.cloudstack.domain.AsyncCreateResponse
import org.jclouds.cloudstack.options.ListHostsOptions
import org.jclouds.cloudstack.strategy.BlockUntilJobCompletesAndReturnResult

import com.google.common.base.Objects
import com.google.common.base.Predicate
import com.google.common.base.Strings
import com.google.common.collect.ImmutableSet
import com.google.common.collect.ImmutableSortedSet
import com.google.common.collect.Iterables
import com.google.common.collect.ImmutableSet.Builder
import com.google.common.io.Closeables
import com.google.inject.Module
import com.google.common.util.concurrent.ListenableFuture

import org.apache.commons.logging.LogFactory

class JCloudsConnection {
    def endPointUrl
    def identity
    def credential
    

    def getModules() { 
        Iterable<Module> MODULES = new ImmutableSet.Builder<Module>().add(new SshjSshClientModule(),
                                                                          new SLF4JLoggingModule(),
                                                                          new EnterpriseConfigurationModule()).build();
        
        return MODULES
    }
    
    CloudStackContextImpl ctx() {
        Properties overrides = new Properties()
        if (!Strings.isNullOrEmpty(endPointUrl)) {
            overrides.setProperty(Constants.PROPERTY_ENDPOINT, endPointUrl)
        }
        overrides.setProperty(Constants.PROPERTY_CONNECTION_TIMEOUT, "360000")
        overrides.setProperty(Constants.PROPERTY_TIMEOUTS_PREFIX + "VirtualMachineClient", "360000")
        overrides.setProperty(Constants.PROPERTY_TIMEOUTS_PREFIX + "TemplateClient", "360000")
        overrides.setProperty(Constants.PROPERTY_TIMEOUTS_PREFIX + "GlobalHostClient", "360000")
        overrides.setProperty(Constants.PROPERTY_TIMEOUTS_PREFIX + "HostClient", "360000")
        overrides.setProperty(Constants.PROPERTY_TIMEOUTS_PREFIX + "GlobalAlertClient", "360000")
        overrides.setProperty(Constants.PROPERTY_TIMEOUTS_PREFIX + "AlertClient", "360000")
        overrides.setProperty(Constants.PROPERTY_TIMEOUTS_PREFIX + "GlobalAccountClient", "360000")
        overrides.setProperty(Constants.PROPERTY_TIMEOUTS_PREFIX + "AccountClient", "360000")
        
        // correct the classloader so that extensions can be found
        Thread.currentThread().setContextClassLoader(Apis.class.getClassLoader())
        return ContextBuilder.newBuilder("cloudstack")
        .credentials(identity, credential)
        .overrides(overrides)
        .modules(getModules())
        .buildView(ComputeServiceContext.class)
    }

}