package org.apache.dolphinscheduler.dao.entity;

import org.apache.dolphinscheduler.plugin.task.api.enums.TaskExecutionStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskExecuteStatusCount extends ExecuteStatusCount<TaskExecutionStatus> {

    private TaskExecutionStatus state;
    private int count;
}
