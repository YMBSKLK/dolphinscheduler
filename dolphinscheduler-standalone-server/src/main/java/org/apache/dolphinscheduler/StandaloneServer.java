/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dolphinscheduler;

import org.apache.curator.test.TestingServer;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import lombok.NonNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

@SpringBootApplication
public class StandaloneServer implements ApplicationListener<ApplicationEvent> {

    private static final Logger logger = LoggerFactory.getLogger(StandaloneServer.class);

    private static TestingServer zookeeperServer;

    public static void main(String[] args) throws Exception {
        int zkPort = Integer.parseInt(System.getProperty("zkPort", "-1"));
        String zkTmpDir = System.getProperty("zkTmpDir", null);
        if (Objects.nonNull(zkTmpDir)) {
            File zkTmpDirFile = new File(zkTmpDir);
            zookeeperServer = new TestingServer(zkPort, zkTmpDirFile);
        } else {
            zookeeperServer = new TestingServer(zkPort);
        }

        System.setProperty("registry.zookeeper.connect-string", zookeeperServer.getConnectString());
        SpringApplication.run(StandaloneServer.class, args);
    }

    @Override
    public void onApplicationEvent(@NonNull ApplicationEvent event) {
        if (event instanceof ApplicationFailedEvent || event instanceof ContextClosedEvent) {
            try (TestingServer closedServer = zookeeperServer) {
                // close the zookeeper server
                logger.info("Receive spring context close event: {}, will closed zookeeper server", event);
            } catch (IOException e) {
                logger.error("Close zookeeper server error", e);
            }
        }
    }
}
