package org.minbox.framework.little.bee.core.command.response;

import org.minbox.framework.little.bee.core.command.CommandNonBlocking;

import java.io.File;

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
     * Set the response instance after {@link Runtime} execution {@link Process}
     *
     * @param process The Runtime execution response isntance
     */
    void setProcess(Process process);

    /**
     * Set non-blocking command configuration
     *
     * @param commandNonBlocking non-blocking command configuration
     */
    void setCommandNonBlocking(CommandNonBlocking commandNonBlocking);

    /**
     * Get non-blocking command configuration
     *
     * @return non-blocking command configuration
     */
    CommandNonBlocking getNonBlocking();

    /**
     * Get content after command execution
     *
     * @return command execution response content
     */
    String getRawContent();

    /**
     * Write response content to file
     *
     * @param file target file instance
     */
    void writeToFile(File file);
}
