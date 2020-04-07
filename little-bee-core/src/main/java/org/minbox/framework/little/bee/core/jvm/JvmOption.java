package org.minbox.framework.little.bee.core.jvm;

/**
 * The jvm option interface definition
 *
 * @author 恒宇少年
 */
public interface JvmOption {
    /**
     * Set jvm option value
     *
     * @param value option value
     */
    void setValue(String value);

    /**
     * Get formatted jvm option value
     *
     * @return formatted jvm option
     */
    String format();
}
