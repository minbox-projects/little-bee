package org.minbox.framework.little.bee.core.command.response;

import org.minbox.framework.little.bee.core.LittleBeeCommandException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.minbox.framework.little.bee.core.tools.Assert;

/**
 * Command line execution response factory
 * <p>
 * dynamically generate implementation class instances based on {@link CommandResponseType}
 *
 * @author 恒宇少年
 * @see CommandBlockingResponse
 * @see CommandNonBlockingResponse
 */
public class CommandResponseFactory {
    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(CommandResponseFactory.class);

    /**
     * Get instance of {@link CommandResponse} implementation class
     *
     * @param responseType Command response type
     * @return Command response implementation class instance
     */
    public static CommandResponse instance(CommandResponseType responseType) throws LittleBeeCommandException {
        Assert.notNull(responseType, "CommandResponseType cannot be null.");
        try {
            Class<?> responseClassName = Class.forName(responseType.getResponseClassName());
            CommandResponse response = (CommandResponse) responseClassName.newInstance();
            if (logger.isDebugEnabled()) {
                logger.debug("Create %s class instance successfully.", response.getClass().getSimpleName());
            }
            return response;
        } catch (Exception e) {
            throw new LittleBeeCommandException(e.getMessage(), e);
        }
    }
}
