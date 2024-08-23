package org.apache.dolphinscheduler.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.dolphinscheduler.plugin.task.api.enums.TaskExecutionStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskExecuteStatusCount extends ExecuteStatusCount<TaskExecutionStatus> {
    private TaskExecutionStatus state;
    private int count;
}