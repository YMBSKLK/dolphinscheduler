package org.apache.dolphinscheduler.api.service;

public interface DashboardService {

    int getWorkflowInstanceCount();

    int getTaskInstanceCount();

    int getTaskDefinitionCount();

}
