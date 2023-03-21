package org.apache.dolphinscheduler.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DashboardMapper extends BaseMapper {

    int getWorkflowInstanceCount(Integer state);

    int getTaskInstanceCount(@Param("projectCode") Long projectCode, @Param("state") Integer state);

    int getTaskDefinitionCount(@Param("projectCode") Long projectCode);

    int getWorkflowDefinitionCount(Integer releaseState);

    List<Map<String, Object>> processFailTop5();

    List<Map<String, Object>> taskFailTop5(@Param("projectCode") Long projectCode);


}
