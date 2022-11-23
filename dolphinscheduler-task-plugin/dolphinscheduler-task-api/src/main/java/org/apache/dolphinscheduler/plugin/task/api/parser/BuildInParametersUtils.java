package org.apache.dolphinscheduler.plugin.task.api.parser;

import org.apache.dolphinscheduler.plugin.task.api.TaskExecutionContext;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuildInParametersUtils {

    private static final String DATE_PARSE_PATTERN = "\\$\\[([^\\$\\]]+)]";

    private static final String DATE_START_PATTERN = "^[0-9]";

    private static final String PROCESS_INSTANCE_ID_PATTERN = "\\$\\[process_instance_id\\]";

    private static final String RANDOM_LONG_ID_PATTERN = "\\$\\[random_long_id\\]";

    private static final String TASK_INSTANCE_ID_PATTERN = "\\$\\[task_instance_id\\]";

    private static final String PROCESS_DEFINE_CODE_PATTERN = "\\$\\[process_define_code\\]";

    private static final String TASK_NAME_PATTERN = "\\$\\[task_name\\]";

    public static String buildInParametersTemplateParse(TaskExecutionContext context, String templateStr, Date date){
        if(templateStr == null){
            return null;
        }
        templateStr = randomLongIdTemplateParse(templateStr);
        templateStr = processInstanceIdTemplateParse(templateStr, context);
        templateStr = taskInstanceIdTemplateParse(templateStr, context);
        templateStr = processDefineCodeTemplateParse(templateStr, context);
        templateStr = taskNameTemplateParse(templateStr, context);
        templateStr = dateTemplateParse(templateStr, date);
        return templateStr;
    }

    private static String randomLongIdTemplateParse(String templateStr){
        Pattern pattern = Pattern.compile(RANDOM_LONG_ID_PATTERN, Pattern.CASE_INSENSITIVE);
        StringBuffer newValue = new StringBuffer(templateStr.length());
        Matcher matcher = pattern.matcher(templateStr);
        while (matcher.find()) {
            matcher.appendReplacement(newValue, String.valueOf(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE));
        }
        matcher.appendTail(newValue);
        return newValue.toString();
    }

    private static String taskNameTemplateParse(String templateStr, TaskExecutionContext taskExecutionContext){
        String taskName = String.valueOf(taskExecutionContext.getTaskName());
        Pattern pattern = Pattern.compile(TASK_NAME_PATTERN, Pattern.CASE_INSENSITIVE);
        StringBuffer newValue = new StringBuffer(templateStr.length());
        Matcher matcher = pattern.matcher(templateStr);
        while (matcher.find()) {
            matcher.appendReplacement(newValue, taskName);
        }
        matcher.appendTail(newValue);
        return newValue.toString();
    }

    private static String processDefineCodeTemplateParse(String templateStr, TaskExecutionContext taskExecutionContext){
        String processDefineCode = String.valueOf(taskExecutionContext.getProcessDefineCode());
        Pattern pattern = Pattern.compile(PROCESS_DEFINE_CODE_PATTERN, Pattern.CASE_INSENSITIVE);
        StringBuffer newValue = new StringBuffer(templateStr.length());
        Matcher matcher = pattern.matcher(templateStr);
        while (matcher.find()) {
            matcher.appendReplacement(newValue, processDefineCode);
        }
        matcher.appendTail(newValue);
        return newValue.toString();
    }

    private static String taskInstanceIdTemplateParse(String templateStr, TaskExecutionContext taskExecutionContext){
        String instanceId = String.valueOf(taskExecutionContext.getTaskInstanceId());
        Pattern pattern = Pattern.compile(TASK_INSTANCE_ID_PATTERN, Pattern.CASE_INSENSITIVE);
        StringBuffer newValue = new StringBuffer(templateStr.length());
        Matcher matcher = pattern.matcher(templateStr);
        while (matcher.find()) {
            matcher.appendReplacement(newValue, instanceId);
        }
        matcher.appendTail(newValue);
        return newValue.toString();
    }

    private static String processInstanceIdTemplateParse(String templateStr, TaskExecutionContext taskExecutionContext){
        String instanceId = String.valueOf(taskExecutionContext.getProcessInstanceId());
        Pattern pattern = Pattern.compile(PROCESS_INSTANCE_ID_PATTERN, Pattern.CASE_INSENSITIVE);
        StringBuffer newValue = new StringBuffer(templateStr.length());
        Matcher matcher = pattern.matcher(templateStr);
        while (matcher.find()) {
            matcher.appendReplacement(newValue, instanceId);
        }
        matcher.appendTail(newValue);
        return newValue.toString();
    }

    private static String dateTemplateParse(String templateStr, Date date) {
        if(date == null){
            return null;
        }
        Pattern pattern = Pattern.compile(DATE_PARSE_PATTERN);
        StringBuffer newValue = new StringBuffer(templateStr.length());
        Matcher matcher = pattern.matcher(templateStr);
        while (matcher.find()) {
            String key = matcher.group(1);
            if (Pattern.matches(DATE_START_PATTERN, key)) {
                continue;
            }
            String value = TimePlaceholderUtils.getPlaceHolderTime(key, date);
            assert value != null;
            matcher.appendReplacement(newValue, value);
        }
        matcher.appendTail(newValue);
        return newValue.toString();
    }

}
