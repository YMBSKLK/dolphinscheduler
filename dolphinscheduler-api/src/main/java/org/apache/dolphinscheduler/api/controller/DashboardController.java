package org.apache.dolphinscheduler.api.controller;

import static org.apache.dolphinscheduler.api.enums.Status.*;

import org.apache.dolphinscheduler.api.exceptions.ApiException;
import org.apache.dolphinscheduler.api.service.DashboardService;
import org.apache.dolphinscheduler.api.utils.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Operation(summary = "getWorkflowInstanceCount", description = "GET_WORKFLOW_INSTANCE_COUNT_NOTES")
    @GetMapping(value = "/getWorkflowInstanceCount")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(GET_WORKFLOW_INSTANCE_COUNT_ERROR)
    public Result getWorkflowInstanceCount() {
        int result = dashboardService.getWorkflowInstanceCount();
        return success(result);
    }

    @Operation(summary = "getTaskInstanceCount", description = "GET_TASK_INSTANCE_COUNT_NOTES")
    @GetMapping(value = "/getTaskInstanceCount")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(GET_TASK_INSTANCE_COUNT_ERROR)
    public Result getTaskInstanceCount() {
        int result = dashboardService.getTaskInstanceCount();
        return success(result);
    }

    @Operation(summary = "getTaskDefinitionCount", description = "GET_TASK_DEFINITION_COUNT_NOTES")
    @GetMapping(value = "/getTaskDefinitionCount")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(GET_TASK_DEFINITION_COUNT_ERROR)
    public Result getTaskDefinitionCount() {
        int result = dashboardService.getTaskDefinitionCount();
        return success(result);
    }

}
