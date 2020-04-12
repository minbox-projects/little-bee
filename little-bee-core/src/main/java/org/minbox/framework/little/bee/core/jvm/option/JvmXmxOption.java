package org.minbox.framework.little.bee.core.jvm.option;

import org.minbox.framework.little.bee.core.jvm.AbstractJvmOption;
import org.minbox.framework.little.bee.core.jvm.JvmOptionType;

/**
 * The "-Xmx" jvm option
 * <p>
 * Use example:
 * <pre>
 *      String xmx = JvmOptionFactory.getJvmOption(JvmXmxOption.class,
 *             JvmOptionSize.MB.value(1024).toString()).format();
 *      // -Xmx1024m
 *      System.out.println(xmx);
 * </pre>
 *
 * @author 恒宇少年
 */
public class JvmXmxOption extends AbstractJvmOption {
    private static final String JVM_XMX = "mx";

    @Override
    public String getOptionName() {
        return JVM_XMX;
    }

    @Override
    public JvmOptionType getOptionType() {
        return JvmOptionType.NonStandard;
    }
}
