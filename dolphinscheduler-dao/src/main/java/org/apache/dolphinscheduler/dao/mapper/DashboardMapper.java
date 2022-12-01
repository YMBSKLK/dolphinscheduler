package org.apache.dolphinscheduler.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface DashboardMapper extends BaseMapper {

    int getWorkflowInstanceCount();

    int getTaskInstanceCount();

    int getTaskDefinitionCount();

}
