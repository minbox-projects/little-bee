package org.minbox.framework.little.bee.core.jvm.option;

import org.minbox.framework.little.bee.core.jvm.AbstractJvmOption;
import org.minbox.framework.little.bee.core.jvm.JvmOptionType;

/**
 * The "-client" jvm option
 * <pre class="code">
 *      String client = JvmOptionFactory.getJvmOption(JvmClientOption.class, LittleBeeConstant.EMPTY_STRING).format();
 *      System.out.println(client);
 * </pre>
 *
 * @author 恒宇少年
 */
public class JvmClientOption extends AbstractJvmOption {
    private static final String JVM_CLIENT = "client";

    @Override
    public JvmOptionType getOptionType() {
        return JvmOptionType.Standard;
    }

    @Override
    public String getOptionName() {
        return JVM_CLIENT;
    }
}
