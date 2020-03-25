package org.minbox.framework.little.bee.core.command;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.minbox.framework.little.bee.core.LittleBeeCommandException;
import org.minbox.framework.little.bee.core.LittleBeeConstant;
import org.minbox.framework.little.bee.core.authenticate.Authenticate;
import org.minbox.framework.little.bee.core.command.response.*;
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
     * The "bash" command location
     */
    private static final String BIN_BASH_COMMAND = "/bin/bash";
    /**
     * The bash command options
     */
    private static final String BASH_COMMAND_OPTIONS = "-c";
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
     * blocking command execution
     */
    private boolean blocking;
    /**
     * Non-blocking command execution
     * default use {@link CommandNonBlocking#instance()}
     */
    private CommandNonBlocking nonBlocking = CommandNonBlocking.instance();

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

    public void setBlocking() {
        this.blocking = true;
    }

    /**
     * Set authentication information
     * <p>
     * when we set the authentication information,
     * it indicates that it is a remote operation command, so we need to set "remoteExecution" to "true"
     *
     * @param authenticate Login authentication
     */
    @Override
    public void setAuthenticate(Authenticate authenticate) {
        Assert.notNull(authenticate, "If you need to set remote authentication information, pass valid parameters");
        this.authenticate = authenticate;
        this.remoteExecution = true;
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

    @Override
    public void setNonBlocking(CommandNonBlocking nonBlocking) {
        this.nonBlocking = nonBlocking;
    }


    /**
     * Execution command line
     * <p>
     * After executing the command line,
     * instantiate the {@link CommandResponse} instance according to the {@link #blocking}
     *
     * @return The command response instance
     * @throws LittleBeeCommandException Exception encountered while executing the command
     */
    @Override
    public CommandResponse execute() throws LittleBeeCommandException {
        CommandResponse response;
        try {
            String[] commands = new String[]{BIN_BASH_COMMAND, BASH_COMMAND_OPTIONS, getCommandLine()};
            Process process = Runtime.getRuntime().exec(commands);
            CommandResponseType responseType = covertResponseType();
            response = CommandResponseFactory.instance(responseType);
            response.setProcess(process);
            response.setCommandNonBlocking(nonBlocking);
        } catch (Exception e) {
            throw new LittleBeeCommandException(e.getMessage(), e);
        }
        return response;
    }

    protected Authenticate getAuthenticate() {
        return authenticate;
    }

    private String getBash() {
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

    /**
     * Get the formatted execution directory
     * <p>
     * if there is a remote execution directory,
     * you need to "cd" to enter the command before executing the corresponding bash
     *
     * @return "cd /xx/xx;"
     */
    protected String getFormatExecutionDirectory() {
        StringBuffer buffer = new StringBuffer(LittleBeeConstant.EMPTY_STRING);
        buffer.append(LittleBeeConstant.SSH_CD);
        buffer.append(LittleBeeConstant.SPACE);
        buffer.append(getExecutionDirectory());
        buffer.append(LittleBeeConstant.SEMICOLON);
        return buffer.toString();
    }

    /**
     * Get the formatted command options
     * <p>
     * format options array as a string, separated by spaces
     * <p>
     * if you execute "git pull origin master", "git" is a bash command.
     * the "pull", "origin", "master" are options
     * options array = {"pull","origin","master"}
     *
     * @return command options string
     */
    protected String getFormatOptions() {
        if (!ObjectUtils.isEmpty(this.options)) {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < options.length; i++) {
                buffer.append(options[i]);
                buffer.append(i == options.length - 1 ? LittleBeeConstant.EMPTY_STRING : LittleBeeConstant.SPACE);
            }
            return buffer.toString();
        }
        return LittleBeeConstant.EMPTY_STRING;
    }

    /**
     * Get authentication information for connecting to a remote server
     * <p>
     * prefix only exists when accessing remote server
     * the format is "ssh username@serverIp"
     *
     * @return The command line prefix string
     */
    protected String getRemoteAuthenticate() {
        if (isRemoteExecution()) {
            Authenticate authenticate = getAuthenticate();
            String connection = authenticate.getConnectionInformation();
            StringBuffer buffer = new StringBuffer();
            buffer.append(LittleBeeConstant.SSH_BASH);
            buffer.append(LittleBeeConstant.SPACE);
            buffer.append(connection);
            buffer.append(LittleBeeConstant.SPACE);
            return buffer.toString();
        }
        return LittleBeeConstant.EMPTY_STRING;
    }

    /**
     * Formatting the bash command line
     * <p>
     * options when appending a format command
     *
     * @return formatted base command
     */
    private String formatBashCommand() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getBash());
        buffer.append(LittleBeeConstant.SPACE);
        buffer.append(getFormatOptions());
        return buffer.toString();
    }

    /**
     * Formatting command execution non-blocking
     * <p>
     * If the command is executed in non-blocking mode, append nohup "command line" > command.log 2>&1 &
     *
     * @return formatted non-blocking command
     */
    private String formatNonBlocking() {
        StringBuffer buffer = new StringBuffer();
        if (!blocking) {
            buffer.append(LittleBeeConstant.SSH_NO_HUP);
            buffer.append(LittleBeeConstant.SPACE);
        }
        buffer.append(formatBashCommand());
        if (!blocking) {
            buffer.append(LittleBeeConstant.DIRECTION);
            buffer.append(nonBlocking.getLogFilePosition());
            buffer.append(nonBlocking.getLogFileName());
            buffer.append(LittleBeeConstant.SPACE);
            buffer.append(LittleBeeConstant.NO_HUP_SUFFIX);
        }
        return buffer.toString();
    }

    /**
     * Formatting remote execution command
     * <p>
     * If there is a remote execution command, execute append "cd execute-directory"
     *
     * @return formatted remote execution command
     */
    private String formatRemoteExecution() {
        StringBuffer buffer = new StringBuffer();
        if (remoteExecution) {
            buffer.append(LittleBeeConstant.QUOTE);
        }
        if (!ObjectUtils.isEmpty(this.executionDirectory)) {
            buffer.append(getFormatExecutionDirectory());
        }
        String command = formatNonBlocking();
        buffer.append(command);
        buffer.append(LittleBeeConstant.QUOTE);
        return buffer.toString();
    }

    /**
     * Get command line
     * <p>
     * append "ssh username@serverIp" prefix of remote execution command according to "remoteExecution"
     * <p>
     * if "executionDirectory" is set, you need to "cd" to this directory before executing the command
     * <p>
     * if options are required for command execution, append the formatted string after bash
     *
     * @return command line string
     */
    protected String getCommandLine() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getRemoteAuthenticate());
        String command = formatRemoteExecution();
        buffer.append(command);
        String commandLine = buffer.toString();
        if (logger.isDebugEnabled()) {
            logger.debug("Execute the command after formatting：{}", commandLine);
        }
        return commandLine;
    }

    /**
     * CommandResponseType according to {@link #blocking}
     *
     * @return command response type
     */
    private CommandResponseType covertResponseType() {
        CommandResponseType responseType = CommandResponseType.NON_BLOCKING;
        if (blocking) {
            responseType = CommandResponseType.BLOCKING;
        }
        return responseType;
    }


}
