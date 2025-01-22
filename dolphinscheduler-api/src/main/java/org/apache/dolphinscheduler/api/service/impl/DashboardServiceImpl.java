package org.apache.dolphinscheduler.api.service.impl;

import org.apache.dolphinscheduler.api.service.DashboardService;
import org.apache.dolphinscheduler.dao.mapper.DashboardMapper;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl extends BaseServiceImpl implements DashboardService {

    @Autowired
    private DashboardMapper dashboardMapper;

    @Override
    public int getWorkflowInstanceCount(Long projectCode, Integer state) {
        return dashboardMapper.getWorkflowInstanceCount(projectCode, state);
    }

    @Override
    public int getTaskInstanceCount(Integer state, Long projectCode) {
        return dashboardMapper.getTaskInstanceCount(projectCode, state);
    }

    @Override
    public int getTaskDefinitionCount(Long projectCode) {
        return dashboardMapper.getTaskDefinitionCount(projectCode);
    }

    @Override
    public int getWorkflowDefinitionCount(Long projectCode, Integer releaseState) {
        return dashboardMapper.getWorkflowDefinitionCount(projectCode, releaseState);
    }

    @Override
    public List<Map<String, Object>> processFailTop5(Long projectCode) {
        return dashboardMapper.processFailTop5(projectCode);
    }

    @Override
    public List<Map<String, Object>> taskFailTop5(Long projectCode) {
        return dashboardMapper.taskFailTop5(projectCode);
    }

}
