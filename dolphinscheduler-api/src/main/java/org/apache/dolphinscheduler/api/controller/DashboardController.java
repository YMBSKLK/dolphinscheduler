package org.apache.dolphinscheduler.api.controller;

import static org.apache.dolphinscheduler.api.enums.Status.*;

import org.apache.dolphinscheduler.api.exceptions.ApiException;
import org.apache.dolphinscheduler.api.service.DashboardService;
import org.apache.dolphinscheduler.api.utils.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "DOME_DASHBOARD")
@RestController
@RequestMapping("/dome/dashboard")
public class DashboardController extends BaseController {

    @Autowired
    private DashboardService dashboardService;

    /**
     * 工作流实例数量
     * @return
     */
    @Operation(summary = "getWorkflowInstanceCount", description = "GET_WORKFLOW_INSTANCE_COUNT_NOTES")
    @GetMapping(value = "/getWorkflowInstanceCount")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(GET_WORKFLOW_INSTANCE_COUNT_ERROR)
    public Result getWorkflowInstanceCount(@RequestParam(value = "state", required = false) Integer state) {
        int result = dashboardService.getWorkflowInstanceCount(state);
        return success(result);
    }

    /**
     * 任务实例数量
     * @return
     */
    @Operation(summary = "getTaskInstanceCount", description = "GET_TASK_INSTANCE_COUNT_NOTES")
    @GetMapping(value = "/getTaskInstanceCount")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(GET_TASK_INSTANCE_COUNT_ERROR)
    public Result getTaskInstanceCount(@RequestParam(value = "state", required = false) Integer state,
                                       @RequestParam(value = "projectCode") Long projectCode) {
        int result = dashboardService.getTaskInstanceCount(state, projectCode);
        return success(result);
    }

    /**
     * 任务定义数量
     * @return
     */
    @Operation(summary = "getTaskDefinitionCount", description = "GET_TASK_DEFINITION_COUNT_NOTES")
    @GetMapping(value = "/getTaskDefinitionCount")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(GET_TASK_DEFINITION_COUNT_ERROR)
    public Result getTaskDefinitionCount(@RequestParam("projectCode") Long projectCode) {
        int result = dashboardService.getTaskDefinitionCount(projectCode);
        return success(result);
    }

    /**
     * 工作流定义数量
     * @return
     */
    @Operation(summary = "getWorkflowDefinitionCount", description = "GET_WORKFLOW_DEFINITION_COUNT_NOTES")
    @GetMapping(value = "/getWorkflowDefinitionCount")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(GET_WORKFLOW_DEFINITION_COUNT_ERROR)
    public Result getWorkflowDefinitionCount(@RequestParam(value = "releaseState", required = false) Integer releaseState) {
        int result = dashboardService.getWorkflowDefinitionCount(releaseState);
        return success(result);
    }

    /**
     * 流程失败次数Top5
     * @return
     */
    @Operation(summary = "processFailTop5", description = "GET_WORKFLOW_DEFINITION_COUNT_NOTES")
    @GetMapping(value = "/processFailTop5")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(GET_WORKFLOW_DEFINITION_COUNT_ERROR)
    public Result processFailTop5() {
        return success(dashboardService.processFailTop5());
    }

    /**
     * 任务失败次数Top5
     * @return
     */
    @Operation(summary = "taskFailTop5", description = "GET_WORKFLOW_DEFINITION_COUNT_NOTES")
    @GetMapping(value = "/taskFailTop5")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(GET_WORKFLOW_DEFINITION_COUNT_ERROR)
    public Result taskFailTop5(@RequestParam(value = "projectCode") Long projectCode) {
        return success(dashboardService.taskFailTop5(projectCode));
    }

}
