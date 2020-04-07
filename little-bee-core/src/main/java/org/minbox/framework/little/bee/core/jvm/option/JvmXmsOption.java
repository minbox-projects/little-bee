package org.minbox.framework.little.bee.core.jvm.option;

import org.minbox.framework.little.bee.core.jvm.AbstractJvmOption;
import org.minbox.framework.little.bee.core.jvm.JvmOptionType;

/**
 * The "-Xms" jvm option
 * <pre class="code">
 *     String xms = JvmOptionFactory.getJvmOption(JvmXmsOption.class,
 *             JvmOptionSize.MB.value(256).toString()).format();
 *     // -Xms256m
 *     System.out.println(xms);
 * </pre>
 *
 * @author 恒宇少年
 */
public class JvmXmsOption extends AbstractJvmOption {
    /**
     * Initial java heap size option name
     */
    private static final String JVM_XMS = "ms";

    @Override
    public String getOptionName() {
        return JVM_XMS;
    }

    /**
     * "ms" is a Non Standard type parameter, and finally formatted as "-Xms"
     *
     * @return This jvm option type
     * @see JvmOptionType
     */
    @Override
    public JvmOptionType getOptionType() {
        return JvmOptionType.NonStandard;
    }
}
