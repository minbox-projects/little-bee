package org.minbox.framework.little.bee.core.command;

import org.minbox.framework.little.bee.core.LittleBeeConstant;

import java.util.UUID;

/**
 * Non-blocking configuration from the command line
 * <p>
 * for example: nohup "command line" > command.log 2>&1 &
 *
 * @author 恒宇少年
 */
public class CommandNonBlocking {
    /**
     * log file suffix
     */
    private static final String LOG_FILE_SUFFIX = ".log";
    /**
     * Subdirectory for log files
     */
    private static final String LOG_SUB_PATH = "/logs/";
    /**
     * Name of the log file where the command execution results are stored
     */
    private String logFileName;
    /**
     * Command execution result log file storage location
     */
    private String logFilePosition;

    /**
     * Get the default name of the log file
     *
     * @return The default file name is random uuid string
     */
    private static String getDefaultFileName() {
        return UUID.randomUUID().toString();
    }

    /**
     * Get the default position of the log file
     *
     * @return The default position is "~/.little-bee/logs"
     */
    private static String getDefaultPosition() {
        return LittleBeeConstant.LITTLE_BEE_DIR + LOG_SUB_PATH;
    }

    /**
     * Instantiate a non-blocking configuration object with the default configuration
     * <p>
     * The log file name generator by {@link #getDefaultFileName()}
     * <p>
     * Use "~/.little-bee" directory as storage position
     *
     * @return non-blocking configuration object instance
     */
    public static CommandNonBlocking instance() {
        CommandNonBlocking nonBlocking = new CommandNonBlocking();
        nonBlocking.setLogFileName(getDefaultFileName());
        nonBlocking.setLogFilePosition(getDefaultPosition());
        return nonBlocking;
    }

    /**
     * Custom file name instantiation non-blocking configuration
     *
     * @param logFileName The log file name
     * @return non-blocking configuration object instance
     */
    public static CommandNonBlocking instance(String logFileName) {
        CommandNonBlocking nonBlocking = instance();
        nonBlocking.setLogFileName(logFileName);
        return nonBlocking;
    }

    /**
     * Custom file name and file position instantiation non-blocking configuration
     *
     * @param logFileName     The log file name
     * @param logFilePosition The log file storage position
     * @return non-blocking configuration object instance
     */
    public static CommandNonBlocking instance(String logFileName, String logFilePosition) {
        CommandNonBlocking nonBlocking = instance();
        nonBlocking.setLogFileName(logFileName);
        nonBlocking.setLogFilePosition(logFilePosition);
        return nonBlocking;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    public void setLogFilePosition(String logFilePosition) {
        this.logFilePosition = logFilePosition;
    }

    public String getLogFileName() {
        return logFileName + LOG_FILE_SUFFIX;
    }

    public String getLogFilePosition() {
        return logFilePosition;
    }
}
