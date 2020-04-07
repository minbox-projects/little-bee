package org.minbox.framework.little.bee.core.jvm;

import org.minbox.framework.little.bee.core.LittleBeeConstant;

/**
 * Jvm options size unit
 *
 * @author 恒宇少年
 * @see JvmOptionKBSize
 * @see JvmOptionMBSize
 * @see JvmOptionGBSize
 */
public abstract class JvmOptionSize {
    /**
     * The {@link JvmOptionKBSize} class instance
     */
    public static final JvmOptionSize KB = new JvmOptionKBSize();
    /**
     * The {@link JvmOptionMBSize} class instance
     */
    public static final JvmOptionSize MB = new JvmOptionMBSize();
    /**
     * The {@link JvmOptionGBSize} class instance
     */
    public static final JvmOptionSize GB = new JvmOptionGBSize();
    /**
     * jvm option size value
     */
    protected int value;

    /**
     * init {@link JvmOptionSize} instance
     *
     * @param value Jvm option size value
     * @return
     */
    public JvmOptionSize value(int value) {
        this.value = value;
        return this;
    }

    /**
     * The KB jvm option size implement class
     * <p>
     * Example of converted value is "128k"
     */
    static class JvmOptionKBSize extends JvmOptionSize {
        @Override
        public String toString() {
            return value + LittleBeeConstant.JVM_OPTION_KB_SUFFIX;
        }
    }

    /**
     * The MB jvm option size implement class
     * <p>
     * Example of converted value is "128m"
     */
    static class JvmOptionMBSize extends JvmOptionSize {
        @Override
        public String toString() {
            return value + LittleBeeConstant.JVM_OPTION_MB_SUFFIX;
        }
    }

    /**
     * The GB jvm option size implement class
     * <p>
     * Example of converted value is "128g"
     */
    static class JvmOptionGBSize extends JvmOptionSize {
        @Override
        public String toString() {
            return value + LittleBeeConstant.JVM_OPTION_GB_SUFFIX;
        }
    }
}
