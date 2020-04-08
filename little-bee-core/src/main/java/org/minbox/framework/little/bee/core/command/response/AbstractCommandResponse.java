package org.minbox.framework.little.bee.core.command.response;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.minbox.framework.little.bee.core.LittleBeeCommandException;
import org.minbox.framework.little.bee.core.LittleBeeConstant;
import org.minbox.framework.little.bee.core.command.AbstractCommand;
import org.minbox.framework.little.bee.core.command.CommandNonBlocking;
import org.minbox.framework.little.bee.core.tools.FileTools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * The {@link CommandResponse} instance abstract implement
 *
 * @author 恒宇少年
 */
public abstract class AbstractCommandResponse implements CommandResponse {
    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(AbstractCommandResponse.class);
    /**
     * The {@link Runtime} execution response
     */
    private Process process;
    /**
     * The command abstract implement
     */
    private AbstractCommand command;
    /**
     * Command execute start time
     */
    private long startTime;
    /**
     * Command execute end time
     */
    private long endTime;
    /**
     * time consuming
     */
    private long timeConsuming;

    /**
     * Tell response is done
     * <p>
     * this method allows the execution response implementation class to do some logic processing
     */
    public abstract void finish();

    /**
     * Set the response instance after {@link Runtime} execution {@link Process}
     *
     * @param process The Runtime execution response isntance
     */
    public void setProcess(Process process) {
        this.process = process;
    }

    /**
     * Set execution command instance
     * <p>
     * write part of command to log file
     *
     * @param command The {@link org.minbox.framework.little.bee.core.command.Command} abstract implement class
     */

    public void setCommand(AbstractCommand command) {
        this.command = command;
    }

    /**
     * Get command execute start millisecond
     * <p>
     * if it is a {@link CommandResponseType#BLOCKING} type command execution
     *
     * @return start time
     */
    protected long getStartTime() {
        return this.startTime;
    }

    /**
     * Get command execute end millisecond
     *
     * <p>
     * if it is a {@link CommandResponseType#BLOCKING} type command execution,
     * you need to get endTime after {@link #getRawContent()} method
     * <p>
     * if it is a {@link CommandResponseType#THREAD_POOL_NON_BLOCKING} type command execution,
     * need to go to the notes list in the log file to get endTime
     *
     * @return end time
     */
    protected long getEndTime() {
        return this.endTime;
    }

    /**
     * Gets the time it takes to execute the command, in milliseconds
     *
     * @return execution command time consuming
     */
    protected long getTimeConsuming() {
        return this.timeConsuming;
    }


    protected Process getProcess() {
        return process;
    }

    protected AbstractCommand getCommand() {
        return this.command;
    }

    @Override
    public CommandNonBlocking getNonBlocking() {
        return command.getCommandNonBlocking();
    }

    /**
     * Get list of remarks for command execution
     *
     * @return notes
     */
    @Override
    public Map<String, String> getNotes() {
        Map<String, String> notes = new HashMap<>();
        notes.put(LittleBeeConstant.NOTE_COMMAND, this.command.getCommandLine());
        notes.put(LittleBeeConstant.NOTE_START_TIME, String.valueOf(this.startTime));
        notes.put(LittleBeeConstant.NOTE_END_TIME, String.valueOf(this.endTime));
        notes.put(LittleBeeConstant.NOTE_TIME_CONSUMING, String.valueOf(this.timeConsuming));
        notes.put(LittleBeeConstant.NOTE_REMOTE_EXECUTION, String.valueOf(this.command.isRemoteExecution()));
        notes.put(LittleBeeConstant.NOTE_EXECUTION_DIRECTORY, this.command.getExecutionDirectory());
        return notes;
    }


    /**
     * Convert notes
     * <p>
     * beautify the output of notes to a log file
     *
     * @param notes The command notes
     * @return Beautified notes
     */
    protected List<String> convertNotes(Map<String, String> notes) {
        Iterator<String> iterator = notes.keySet().iterator();
        List<String> formatNotes = new LinkedList<>();
        formatNotes.add(LittleBeeConstant.NOTE_DIVIDING_LINE);
        formatNotes.add(LittleBeeConstant.NEW_LINE);
        while (iterator.hasNext()) {
            String noteKey = iterator.next();
            String noteValue = notes.get(noteKey);
            formatNotes.add(String.format("%s: %s", noteKey, noteValue));
            formatNotes.add(LittleBeeConstant.NEW_LINE);
        }
        formatNotes.add(LittleBeeConstant.NOTE_DIVIDING_LINE);
        return formatNotes;
    }

    /**
     * Create a command response log file
     * <p>
     * when creating a log file, create the parent directory of the file
     *
     * @throws LittleBeeCommandException Problems encountered in executing the command
     */
    private void createLogFile() throws LittleBeeCommandException {
        try {
            CommandNonBlocking commandNonBlocking = getNonBlocking();
            FileTools.createFile(commandNonBlocking.getLogFilePath(), true);
            if (logger.isDebugEnabled()) {
                logger.debug("Create log file : {}", commandNonBlocking.getLogFilePath());
            }
        } catch (Exception e) {
            throw new LittleBeeCommandException(e.getMessage(), e);
        }
    }

    /**
     * Append lines content to log file
     *
     * @param lines The log content lines
     * @throws LittleBeeCommandException Problems encountered in executing the command
     */
    protected void appendLinesToLogFile(String... lines) throws LittleBeeCommandException {
        try {
            String filePath = getNonBlocking().getLogFilePath();
            if (!FileTools.checkExist(filePath)) {
                createLogFile();
            }
            FileTools.writeLines(filePath, lines);
        } catch (Exception e) {
            throw new LittleBeeCommandException(e.getMessage(), e);
        }
    }

    /**
     * Format the input stream content as a string
     *
     * @param is {@link InputStream}
     * @return Input stream content
     */
    protected String getContent(InputStream is) {
        String content = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(LittleBeeConstant.NEW_LINE);
            }
            content = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * Method to execute when starting to load log content
     * <p>
     * get the timestamp when execution started
     */
    protected void beforeLoading() {
        this.startTime = System.currentTimeMillis();
    }


    /**
     * Method to execute after the log content is completely loaded
     * <p>
     * get the timestamp when executing end
     */
    protected void afterLoading() {
        this.endTime = System.currentTimeMillis();
        this.timeConsuming = this.endTime - this.startTime;
        Map<String, String> notes = getNotes();
        List<String> formatNotes = convertNotes(notes);
        this.appendLinesToLogFile(formatNotes.toArray(new String[]{}));
    }
}
