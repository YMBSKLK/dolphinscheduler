package org.apache.dolphinscheduler.api.dto;

import org.apache.dolphinscheduler.common.enums.WorkflowExecutionStatus;
import org.apache.dolphinscheduler.dao.entity.ProcessExecuteStatusCount;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessCountDto {

    /**
     * total count
     */
    private int totalCount;

    /**
     * task state count list
     */
    private List<ProcessExecuteStatusCount> processCountDtos;

    public ProcessCountDto(List<ProcessExecuteStatusCount> processInstanceStateCounts) {
        countProcessDtos(processInstanceStateCounts);
    }

    private void countProcessDtos(List<ProcessExecuteStatusCount> processInstanceStateCounts) {
        Map<WorkflowExecutionStatus, Integer> statusCountMap = processInstanceStateCounts.stream()
                .collect(Collectors.toMap(
                        ProcessExecuteStatusCount::getState,
                        ProcessExecuteStatusCount::getCount,
                        Integer::sum));

        processCountDtos = Arrays.stream(WorkflowExecutionStatus.values())
                .map(status -> new ProcessExecuteStatusCount(status, statusCountMap.getOrDefault(status, 0)))
                .collect(Collectors.toList());

        totalCount = processCountDtos.stream()
                .mapToInt(ProcessExecuteStatusCount::getCount)
                .sum();
    }

    // remove the specified state
    public void removeStateFromCountList(WorkflowExecutionStatus status) {
        for (ProcessExecuteStatusCount count : this.processCountDtos) {
            if (count.getState().equals(status)) {
                this.processCountDtos.remove(count);
                break;
            }
        }
    }
}
