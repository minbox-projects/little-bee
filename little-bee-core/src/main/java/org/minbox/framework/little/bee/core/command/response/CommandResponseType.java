package org.minbox.framework.little.bee.core.command.response;

/**
 * Define command response type
 *
 * @author 恒宇少年
 */
public enum CommandResponseType {
    /**
     * blocking response type
     */
    BLOCKING("org.minbox.framework.little.bee.core.command.response.CommandBlockingResponse"),
    /**
     * nohup non-blocking response type
     */
    NOHUP_NON_BLOCKING("org.minbox.framework.little.bee.core.command.response.CommandNonBlockingResponse"),
    /**
     * thread pool non-blocking response type
     */
    THREAD_POOL_NON_BLOCKING("org.minbox.framework.little.bee.core.command.response.CommandThreadPoolNonBlockingResponse");
    /**
     * The full path name of the enum value corresponding to {@link CommandResponse}
     */
    private String responseClassName;

    CommandResponseType(String responseClassName) {
        this.responseClassName = responseClassName;
    }

    public String getResponseClassName() {
        return responseClassName;
    }

    /**
     * Determine if the two match
     *
     * @param responseType Current command response type
     * @param checkType    Command response type being checked
     * @return Matches both when the return value is <code>true</code>
     */
    public static boolean isMatch(CommandResponseType responseType, CommandResponseType checkType) {
        return responseType == checkType;
    }
}
