package org.apache.dolphinscheduler.api.service;

import java.util.List;
import java.util.Map;

public interface DashboardService {

    int getWorkflowInstanceCount(Integer state);

    int getTaskInstanceCount(Integer state, Long projectCode);

    int getTaskDefinitionCount(Long projectCode);

    int getWorkflowDefinitionCount(Integer releaseState);

    List<Map<String, Object>> processFailTop5();


    List<Map<String, Object>> taskFailTop5(Long projectCode);

}
