package org.minbox.framework.little.bee.core.command;

import org.minbox.framework.little.bee.core.LittleBeeCommandException;
import org.minbox.framework.little.bee.core.authenticate.Authenticate;
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
     * Set authentication information
     * <p>
     * login authentication information required to connect to a remote server
     *
     * @param authenticate Login authentication
     */
    void setAuthenticate(Authenticate authenticate);

    /**
     * Set whether the command executed this time is a remote operation
     *
     * @param remoteExecution Remote execution when set to true，false for local
     */
    void setRemoteExecution(boolean remoteExecution);

    /**
     * Set the parameter item collection when the command is executed
     * <p>
     * When the “touch log_back” command is executed,
     * “touch” is a bash bin, and “log_back” is actually the name of the file.
     * Since the parameter items behind different commands are different, they are passed through the array.
     *
     * @param options The parameter item collection
     */
    void setCommandOptions(String[] options);

    /**
     * Set the directory to execute the command
     * <p>
     * If no directory is passed when executing the command, the default directory "/ root" is used
     *
     * @param executionDirectory The command execute directory
     */
    void setExecutionDirectory(String executionDirectory);

    /**
     * Execute command line
     *
     * @return Result of executing the command，specific implementation see {@link CommandResponse} implementation class
     * @throws LittleBeeCommandException Exception instance encountered while executing the command
     */
    CommandResponse execute() throws LittleBeeCommandException;
}
