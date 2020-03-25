package org.minbox.framework.little.bee.core;

/**
 * Inherited from RuntimeException
 *
 * @author 恒宇少年
 */
public class LittleBeeException extends RuntimeException {
    public LittleBeeException() {
    }

    public LittleBeeException(String message) {
        super(message);
    }

    public LittleBeeException(String message, Throwable cause) {
        super(message, cause);
    }
}
