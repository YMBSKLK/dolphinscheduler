package org.apache.dolphinscheduler.dao.entity;

import org.apache.dolphinscheduler.common.enums.WorkflowExecutionStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessExecuteStatusCount extends ExecuteStatusCount<WorkflowExecutionStatus> {

    private WorkflowExecutionStatus state;
    private int count;
}
