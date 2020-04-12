package org.minbox.framework.little.bee.core.command.response;

import org.minbox.framework.little.bee.core.command.CommandNonBlocking;

import java.util.Map;

/**
 * Command line response
 * <p>
 * After each command line is executed,
 * it will implement the class instance through this interface,
 * and the execution log can be obtained through the {@link #getRawContent()} method (but if it is a blocking command)
 *
 * @author 恒宇少年
 */
public interface CommandResponse {
    /**
     * Get non-blocking command configuration
     *
     * @return non-blocking command configuration
     */
    CommandNonBlocking getNonBlocking();

    /**
     * Get content after command execution
     * <p>
     * you can use this method to get the response content
     * only after executing the command in {@link CommandResponseType#BLOCKING} mode.
     *
     * @return command execution response content
     */
    default String getRawContent() {
        return null;
    }

    /**
     * Get command execution response notes
     * <p>
     * get the notes after the command is executed.
     * if the command execution response type is {@link CommandResponseType#BLOCKING},
     * you need to call this method after the {@link #getRawContent()} method
     * <p>
     * if the command execution response type is {@link CommandResponseType#THREAD_POOL_NON_BLOCKING},
     * notes are written to the bottom of the command log file
     *
     * @return command execution response notes map
     */
    Map<String, String> getNotes();
}
