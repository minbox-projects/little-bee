package org.minbox.framework.little.bee.core.command.response;

import java.io.InputStream;

/**
 * Blocking command line execution response class
 *
 * @author 恒宇少年
 */
public class CommandBlockingResponse extends AbstractCommandResponse {
    /**
     * Notify response is ready after command execution
     * <p>
     * call the {@link #beforeLoading()} method to calculate the start time
     */
    @Override
    public void finish() {
        beforeLoading();
    }

    /**
     * Get command execution response content
     * <p>
     * the {@link #getContent(InputStream)} method blocks the thread,
     * waiting to get the results of the command execution line by line
     * <p>
     * call the {@link #afterLoading()} method to calculate the end time and time consuming
     *
     * @return response content
     */
    @Override
    public String getRawContent() {
        String content = getContent(getProcess().getInputStream());
        afterLoading();
        return content;
    }
}
