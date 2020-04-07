package org.minbox.framework.little.bee.core.jvm;

/**
 * Jvm option types define
 *
 * @author 恒宇少年
 */
public enum JvmOptionType {
    /**
     * Standard jvm option, use "-" as prefix
     */
    Standard("-"),
    /**
     * Non standard jvm option, use "-X" as prefix
     */
    NonStandard("-X"),
    /**
     * Non stable jvm option, use "-XX" as prefix
     */
    NonStable("-XX:");

    private String prefix;

    JvmOptionType(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String format(String suffix) {
        return this.prefix + suffix;
    }
}
