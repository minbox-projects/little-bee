package org.minbox.framework.little.bee.core.command;

import org.minbox.framework.little.bee.core.LittleBeeCommandException;
import org.springframework.util.ObjectUtils;

/**
 * Create file "touch" command implementation
 *
 * @author 恒宇少年
 */
public class ServerTouchCommand extends ServerCommand {
    /**
     * The touch bash
     */
    private static final String TOUCH_BASH = "touch";
    /**
     * Create file name
     */
    private String file;

    public void setFile(String file) {
        this.file = file;
    }

    public ServerTouchCommand() {
        setBash(TOUCH_BASH);
    }

    /**
     * Pre command execution
     */
    @Override
    void preExecute() {
        checkValidity();
        setCommandOptions(new String[]{this.file});
    }

    /**
     * check {@link #file} validity，the value is not null.
     *
     * @throws LittleBeeCommandException Exception thrown by validation data
     */
    private void checkValidity() throws LittleBeeCommandException {
        if (ObjectUtils.isEmpty(this.file)) {
            throw new LittleBeeCommandException("The file cannot be null.");
        }
    }
}
