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

class CloudStackConfig {
    String endPointUrl
    String apiKey
    String secretKey
    String alertEmailFrom
    String alertEmailTo
    String emailDomain
    boolean setupStaticNat
    String networkId
    
    int maxProvDestroy = 10
    Date lastUpdated
    
    static constraints = {
        endPointUrl(blank: false, nullable: false)
        apiKey(blank: false, nullable: false)
        secretKey(blank: false, nullable: false, password: true)
        alertEmailFrom(nullable: true)
        alertEmailTo(nullable: true)
        emailDomain(nullable:true)
        maxProvDestroy(min:1)
        setupStaticNat(nullable:true)
        networkId(nullable:true)
    }
}
