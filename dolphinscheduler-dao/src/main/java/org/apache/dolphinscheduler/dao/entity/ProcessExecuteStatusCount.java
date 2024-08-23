package org.apache.dolphinscheduler.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.dolphinscheduler.common.enums.WorkflowExecutionStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessExecuteStatusCount extends ExecuteStatusCount<WorkflowExecutionStatus>{
    private WorkflowExecutionStatus state;
    private int count;
}
