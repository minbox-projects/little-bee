package org.minbox.framework.little.bee.core.command.response;

import org.minbox.framework.little.bee.core.command.CommandNonBlocking;

/**
 * The {@link CommandResponse} instance abstract implement
 *
 * @author 恒宇少年
 */
public abstract class AbstractCommandResponse implements CommandResponse {
    /**
     * The {@link Runtime} execution response
     */
    private Process process;
    /**
     * non-blocking command configuration
     */
    private CommandNonBlocking commandNonBlocking;

    @Override
    public void setProcess(Process process) {
        this.process = process;
    }

    @Override
    public void setCommandNonBlocking(CommandNonBlocking commandNonBlocking) {
        this.commandNonBlocking = commandNonBlocking;
    }

    @Override
    public CommandNonBlocking getNonBlocking() {
        return this.commandNonBlocking;
    }

    protected Process getProcess() {
        return process;
    }
}
