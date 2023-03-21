package org.apache.dolphinscheduler.api.service.impl;

import org.apache.dolphinscheduler.api.service.DashboardService;
import org.apache.dolphinscheduler.dao.mapper.DashboardMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DashboardServiceImpl extends BaseServiceImpl implements DashboardService {

    @Autowired
    private DashboardMapper dashboardMapper;

    @Override
    public int getWorkflowInstanceCount(Integer state) {
        return dashboardMapper.getWorkflowInstanceCount(state);
    }

    @Override
    public int getTaskInstanceCount(Integer state, Long projectCode)  {
        return dashboardMapper.getTaskInstanceCount(projectCode, state);
    }

    @Override
    public int getTaskDefinitionCount(Long projectCode) {
        return dashboardMapper.getTaskDefinitionCount(projectCode);
    }

    @Override
    public int getWorkflowDefinitionCount(Integer releaseState) {
        return dashboardMapper.getWorkflowDefinitionCount(releaseState);
    }

    @Override
    public List<Map<String, Object>> processFailTop5() {
        return dashboardMapper.processFailTop5();
    }

    @Override
    public List<Map<String, Object>> taskFailTop5( Long projectCode) {
        return dashboardMapper.taskFailTop5(projectCode);
    }

}
