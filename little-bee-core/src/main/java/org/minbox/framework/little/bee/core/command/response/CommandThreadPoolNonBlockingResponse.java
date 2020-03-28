package org.minbox.framework.little.bee.core.command.response;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.minbox.framework.little.bee.core.LittleBeeCommandException;
import org.minbox.framework.little.bee.core.LittleBeeConstant;
import org.minbox.framework.little.bee.core.command.CommandNonBlocking;
import org.minbox.framework.little.bee.core.tools.FileTools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Thread pool non-blocking command response implementation
 * <p>
 * create a file for this command response log
 * according to {@link org.minbox.framework.little.bee.core.command.CommandNonBlocking}
 * <p>
 * log content that responds in real time after a thread waits for a read command to execute
 *
 * @author 恒宇少年
 */
public class CommandThreadPoolNonBlockingResponse extends AbstractCommandResponse {
    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(CommandThreadPoolNonBlockingResponse.class);
    /**
     * Thread Pool
     * <p>
     * create a thread to load the log content for each response after the command line is execute
     */
    private static ExecutorService THREAD_POOL = Executors.newCachedThreadPool();

    @Override
    public void finish() {
        createLogFile();
        Process process = getProcess();
        InputStream inputStream = process.getInputStream();
        THREAD_POOL.execute(() -> loadContentLineFromProcess(inputStream));
    }

    @Override
    protected void afterLoading() {
        super.afterLoading();
        Map<String, String> notes = getNotes();
        List<String> formatNotes = convertNotes(notes);
        this.appendLinesToFile(formatNotes.toArray(new String[]{}));
    }

    /**
     * Create a command response log file
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
     * Read log content line by line from the input stream of Process
     *
     * @param inputStream The process input stream
     * @return log content line
     * @throws LittleBeeCommandException Problems encountered in executing the command
     */
    private void loadContentLineFromProcess(InputStream inputStream) throws LittleBeeCommandException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            beforeLoading();
            while ((line = bf.readLine()) != null) {
                // need to add "\n" after appending log content
                appendLinesToFile(line, LittleBeeConstant.NEW_LINE);
            }
            this.afterLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Append lines content to log file
     *
     * @param lines The log content lines
     * @throws LittleBeeCommandException Problems encountered in executing the command
     */
    private void appendLinesToFile(String... lines) throws LittleBeeCommandException {
        try {
            String filePath = getNonBlocking().getLogFilePath();
            FileTools.writeLines(filePath, lines);
        } catch (Exception e) {
            throw new LittleBeeCommandException(e.getMessage(), e);
        }
    }

    /**
     * Convert notes
     * <p>
     * beautify the output of notes to a log file
     *
     * @param notes The command notes
     * @return Beautified notes
     */
    private List<String> convertNotes(Map<String, String> notes) {
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
}
