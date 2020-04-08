package org.minbox.framework.little.bee.core.command.response;

import org.minbox.framework.little.bee.core.LittleBeeCommandException;
import org.minbox.framework.little.bee.core.LittleBeeConstant;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
     * Thread Pool
     * <p>
     * create a thread to load the log content for each response after the command line is execute
     */
    private static ExecutorService THREAD_POOL = Executors.newCachedThreadPool();

    @Override
    public void finish() {
        beforeLoading();
        Process process = getProcess();
        InputStream inputStream = process.getInputStream();
        THREAD_POOL.execute(() -> loadContentLineFromProcess(inputStream));
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
            while ((line = bf.readLine()) != null) {
                // need to add "\n" after appending log content
                appendLinesToLogFile(line, LittleBeeConstant.NEW_LINE);
            }
            this.afterLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
