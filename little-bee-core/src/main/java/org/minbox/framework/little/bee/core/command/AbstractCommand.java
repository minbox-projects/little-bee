package org.minbox.framework.little.bee.core.command;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.minbox.framework.little.bee.core.LittleBeeCommandException;
import org.minbox.framework.little.bee.core.authenticate.Authenticate;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * Abstract implementation of the {@link Command} interface
 * <p>
 * public properties that handle command execution
 * validate the setting of attributes and return processing
 *
 * @author 恒宇少年
 */
public abstract class AbstractCommand implements Command {
    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(AbstractCommand.class);
    /**
     * Authentication information required for remote command execution
     * If it is executed locally, it can be left unset.
     */
    private Authenticate authenticate;
    /**
     * Basic bash for command execution
     */
    private String bash;
    /**
     * Determine whether to execute the command remotely
     */
    private boolean remoteExecution;
    /**
     * Parameter items when executing the command
     */
    private String[] options;
    /**
     * Command execution directory
     */
    private String executionDirectory;

    /**
     * Set basic bash for command execution
     * <p>
     * for example：touch、tail、mkdir、cd、scp...
     *
     * @param bash The basic bash
     */
    protected void setBash(String bash) {
        Assert.notNull(bash, "Basic bash cannot be null");
        this.bash = bash;
    }

    @Override
    public void setAuthenticate(Authenticate authenticate) {
        Assert.notNull(authenticate, "If you need to set remote authentication information, pass valid parameters");
        this.authenticate = authenticate;
    }

    @Override
    public void setRemoteExecution(boolean remoteExecution) {
        this.remoteExecution = remoteExecution;
        if (remoteExecution) {
            logger.warn("If \"remoteExecution\" is set to true, you also need to set \"Authenticate\"");
        }
    }

    @Override
    public void setCommandOptions(String[] options) {
        this.options = options;
    }

    @Override
    public void setExecutionDirectory(String executionDirectory) {
        this.executionDirectory = executionDirectory;
        if (ObjectUtils.isEmpty(executionDirectory)) {
            logger.warn("If you do not set the execution directory, the command will be executed at \"/root\"");
        }
    }

    public Authenticate getAuthenticate() {
        return authenticate;
    }

    public String getBash() {
        return bash;
    }

    /**
     * Gets whether the command is executed remotely
     * <p>
     * When the command is executed in remote mode, check whether authentication information is set.
     *
     * @return Remote execution when set to true，false for local
     */
    public boolean isRemoteExecution() {
        if (remoteExecution && ObjectUtils.isEmpty(this.authenticate)) {
            throw new LittleBeeCommandException("When setting \"remoteExecution\" to true, you also need to set \"Authenticate\"");
        }
        return remoteExecution;
    }

    /**
     * Gets the parameter item collection when the command is executed
     *
     * @return The parameter item collection
     */
    public String[] getOptions() {
        return options;
    }

    /**
     * Gets the directory where the command is executed
     *
     * @return The command execute directory
     */
    public String getExecutionDirectory() {
        return executionDirectory;
    }
}
