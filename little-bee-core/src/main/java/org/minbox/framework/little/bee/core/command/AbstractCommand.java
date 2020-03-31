package org.minbox.framework.little.bee.core.command;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.minbox.framework.little.bee.core.LittleBeeCommandException;
import org.minbox.framework.little.bee.core.LittleBeeConstant;
import org.minbox.framework.little.bee.core.authenticate.Authenticate;
import org.minbox.framework.little.bee.core.command.response.AbstractCommandResponse;
import org.minbox.framework.little.bee.core.command.response.CommandResponse;
import org.minbox.framework.little.bee.core.command.response.CommandResponseFactory;
import org.minbox.framework.little.bee.core.command.response.CommandResponseType;
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
     * Command line to be executed
     */
    private String commandLine;
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
     * The command response type
     * default use {@link CommandResponseType#THREAD_POOL_NON_BLOCKING}
     */
    private CommandResponseType responseType = CommandResponseType.THREAD_POOL_NON_BLOCKING;
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

    /**
     * Modify the default response type
     *
     * @param responseType The type of response you expect
     */
    public void setResponseType(CommandResponseType responseType) {
        Assert.notNull(responseType, "CommandResponseType cannot be null.");
        this.responseType = responseType;
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
     * Before executing the command
     * <p>
     * handle some necessary validation and business before executing the command
     * <p>
     * this method is called before the command is executed and can be used to construct command parameters
     */
    abstract void preExecute();

    /**
     * Execution command line
     * <p>
     * After executing the command line,
     * instantiate the {@link CommandResponse} instance according to the {@link CommandResponseType#isMatch(CommandResponseType, CommandResponseType)}
     * <p>
     * Use {@link AbstractCommandResponse#finish()} method to tell response is done
     *
     * @return The command response instance
     * @throws LittleBeeCommandException Exception encountered while executing the command
     */
    @Override
    public CommandResponse execute() throws LittleBeeCommandException {
        AbstractCommandResponse response;
        try {
            preExecute();
            this.commandLine = formatCommandLine();
            String[] commands = new String[]{BIN_BASH_COMMAND, BASH_COMMAND_OPTIONS, this.commandLine};
            Process process = Runtime.getRuntime().exec(commands);
            response = (AbstractCommandResponse) CommandResponseFactory.instance(responseType);
            response.setCommand(this);
            response.setProcess(process);
            response.finish();
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
     * Get the non-blocking command configuration
     *
     * @return non-blocking command
     */
    public CommandNonBlocking getCommandNonBlocking() {
        return this.nonBlocking;
    }

    public String getCommandLine() {
        return this.commandLine;
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
                String option = options[i];
                if (ObjectUtils.isEmpty(option)) {
                    continue;
                }
                buffer.append(option);
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
        boolean isNohupNonBlocking = CommandResponseType.isMatch(responseType, CommandResponseType.NOHUP_NON_BLOCKING);
        if (isNohupNonBlocking) {
            buffer.append(LittleBeeConstant.SSH_NO_HUP);
            buffer.append(LittleBeeConstant.SPACE);
        }
        buffer.append(formatBashCommand());
        if (isNohupNonBlocking) {
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
        if (remoteExecution) {
            buffer.append(LittleBeeConstant.QUOTE);
        }
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
    protected String formatCommandLine() {
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


}
