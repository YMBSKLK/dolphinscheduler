package org.apache.dolphinscheduler.api.service.impl;

import org.apache.dolphinscheduler.api.service.DashboardService;
import org.apache.dolphinscheduler.dao.mapper.DashboardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl extends BaseServiceImpl implements DashboardService {

    @Autowired
    private DashboardMapper dashboardMapper;

    @Override
    public int getWorkflowInstanceCount() {
        return dashboardMapper.getWorkflowInstanceCount();
    }

    @Override
    public int getTaskInstanceCount() {
        return dashboardMapper.getTaskInstanceCount();
    }

    @Override
    public int getTaskDefinitionCount() {
        return dashboardMapper.getTaskDefinitionCount();
    }
}
