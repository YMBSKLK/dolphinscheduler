package org.apache.dolphinscheduler.api.service;

import java.util.List;
import java.util.Map;

public interface DashboardService {

    int getWorkflowInstanceCount(Long projectCode, Integer state);

    int getTaskInstanceCount(Integer state, Long projectCode);

    int getTaskDefinitionCount(Long projectCode);

    int getWorkflowDefinitionCount(Long projectCode, Integer releaseState);

    List<Map<String, Object>> processFailTop5(Long projectCode);
    List<Map<String, Object>> taskFailTop5(Long projectCode);

}
