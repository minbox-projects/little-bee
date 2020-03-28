package org.minbox.framework.little.bee.core.command.response;

/**
 * Non-blocking command line execution response class
 *
 * @author 恒宇少年
 */
public class CommandNonBlockingResponse extends AbstractCommandResponse {
    /**
     * Notify response is ready after command execution
     * <p>
     * call the {@link #beforeLoading()} method to calculate the start time
     */
    @Override
    public void finish() {
        beforeLoading();
    }
}
