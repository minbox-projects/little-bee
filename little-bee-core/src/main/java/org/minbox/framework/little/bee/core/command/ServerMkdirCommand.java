package org.minbox.framework.little.bee.core.command;

import org.minbox.framework.little.bee.core.LittleBeeCommandException;
import org.springframework.util.ObjectUtils;

/**
 * Create directory command implementation
 *
 * @author 恒宇少年
 */
public class ServerMkdirCommand extends ServerCommand {
    /**
     * The "mkdir" command bash
     */
    private static final String MKDIR_BASH = "mkdir";
    /**
     * Created directory name
     */
    private String directory;

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public ServerMkdirCommand() {
        setBash(MKDIR_BASH);
    }

    @Override
    void preExecute() {
        checkValidity();
        setCommandOptions(new String[]{this.directory});
    }

    /**
     * check {@link #directory} validity，the value is not null.
     *
     * @throws LittleBeeCommandException Exception thrown by validation data
     */
    private void checkValidity() throws LittleBeeCommandException {
        if (ObjectUtils.isEmpty(this.directory)) {
            throw new LittleBeeCommandException("The directory cannot be null.");
        }
    }
}
