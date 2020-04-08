package org.minbox.framework.little.bee.core.jvm;

import org.minbox.framework.little.bee.core.LittleBeeConstant;

/**
 * The {@link JvmOption} abstract implement
 *
 * @author 恒宇少年
 */
public abstract class AbstractJvmOption implements JvmOption {
    /**
     * Jvm option value
     */
    private String optionValue;

    /**
     * The subclass implements this method,
     * and the subclass passes the {@link JvmOptionType} through this method
     *
     * @return
     */
    public abstract JvmOptionType getOptionType();

    /**
     * Get assignment symbol
     * default symbol is empty string
     *
     * @return assignment symbol
     */
    public String getAssignmentSymbol() {
        return LittleBeeConstant.EMPTY_STRING;
    }

    @Override
    public void setValue(String value) {
        this.optionValue = value;
    }

    /**
     * Get formatted Jvm option
     * Such as "-Xms128m" parameter split：
     * <p>
     * "-X" is jvm option prefix {@link JvmOptionType#getPrefix()}
     * <p>
     * "ms" is jvm option name {@link #getOptionName()}
     * <p>
     * "128m" is jvm option value {@link #optionValue}
     *
     * @return formatted jvm option
     */
    @Override
    public String format() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getOptionType().getPrefix());
        buffer.append(getOptionName());
        buffer.append(getAssignmentSymbol());
        buffer.append(this.optionValue);
        return buffer.toString();
    }
}
