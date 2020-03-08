package org.minbox.framework.little.bee.core.command;

import org.minbox.framework.little.bee.core.LittleBeeCommandException;
import org.minbox.framework.little.bee.core.command.response.CommandResponse;

/**
 * The command line define class
 * <p>
 * Each command is executed by implementing this interface,
 * and the result of the command is processed by {@link CommandResponse}
 *
 * @author 恒宇少年
 */
public interface Command {
    /**
     * Execute command line
     *
     * @return Result of executing the command，specific implementation see {@link CommandResponse} implementation class
     * @throws LittleBeeCommandException Exception instance encountered while executing the command
     */
    CommandResponse execute() throws LittleBeeCommandException;
}
