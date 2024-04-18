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

package org.apache.dolphinscheduler.plugin.task.seatunnel.flink;

import org.apache.dolphinscheduler.common.utils.JSONUtils;
import org.apache.dolphinscheduler.plugin.task.api.TaskConstants;
import org.apache.dolphinscheduler.plugin.task.api.TaskException;
import org.apache.dolphinscheduler.plugin.task.api.TaskExecutionContext;
import org.apache.dolphinscheduler.plugin.task.api.utils.LogUtils;
import org.apache.dolphinscheduler.plugin.task.seatunnel.EngineEnum;
import org.apache.dolphinscheduler.plugin.task.seatunnel.SeatunnelTask;
import org.apache.dolphinscheduler.plugin.task.seatunnel.util.FlinkArgsUtils;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class SeatunnelFlinkTask extends SeatunnelTask {

    private SeatunnelFlinkParameters seatunnelParameters;
    public SeatunnelFlinkTask(TaskExecutionContext taskExecutionContext) {
        super(taskExecutionContext);
    }

    @Override
    public void init() {
        seatunnelParameters =
                JSONUtils.parseObject(taskExecutionContext.getTaskParams(), SeatunnelFlinkParameters.class);
        setSeatunnelParameters(seatunnelParameters);
        super.init();
    }

    @Override
    public List<String> buildOptions() throws Exception {
        List<String> args = super.buildOptions();
        args.add(
                Objects.isNull(seatunnelParameters.getRunMode()) ? SeatunnelFlinkParameters.RunModeEnum.RUN.getCommand()
                        : seatunnelParameters.getRunMode().getCommand());
        if (StringUtils.isNotBlank(seatunnelParameters.getOthers())) {
            args.add(seatunnelParameters.getOthers());
        }
        return args;
    }

    @Override
    public void cancelApplication() throws TaskException {
        super.cancelApplication();
        if (EngineEnum.FLINK.equals(seatunnelParameters.getEngine())) {
            List<String> jobIds = LogUtils.getJobIdsFromLogFile(taskRequest.getLogPath());
            if (!jobIds.isEmpty()) {
                taskExecutionContext.setAppIds(String.join(TaskConstants.COMMA, jobIds));
                List<String> args = FlinkArgsUtils.buildCancelCommandLine(taskExecutionContext);

                logger.info("cancel application args:{}", args);

                ProcessBuilder processBuilder = new ProcessBuilder();
                processBuilder.command(args);
                try {
                    processBuilder.start();
                } catch (IOException e) {
                    throw new TaskException("cancel application error", e);
                }
            }
        }

    }
}
