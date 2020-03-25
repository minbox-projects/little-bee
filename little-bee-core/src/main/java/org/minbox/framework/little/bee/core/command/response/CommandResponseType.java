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
     * non-blocking response type
     */
    NON_BLOCKING("org.minbox.framework.little.bee.core.command.response.CommandNonBlockingResponse");
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
}
