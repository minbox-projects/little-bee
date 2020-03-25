package org.minbox.framework.little.bee.core;

/**
 * Inherited from RuntimeException
 * <p>
 * used for command line execution exception
 *
 * @author 恒宇少年
 */
public class LittleBeeCommandException extends LittleBeeException {
    public LittleBeeCommandException() {
    }

    public LittleBeeCommandException(String message) {
        super(message);
    }

    public LittleBeeCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
