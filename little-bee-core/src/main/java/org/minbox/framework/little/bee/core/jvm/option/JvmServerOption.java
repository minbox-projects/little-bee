package org.minbox.framework.little.bee.core.jvm.option;

import org.minbox.framework.little.bee.core.jvm.AbstractJvmOption;
import org.minbox.framework.little.bee.core.jvm.JvmOptionType;

/**
 * The "-server" jvm option
 * <p>
 * Use example:
 * <pre>
 *     String server = JvmOptionFactory.getJvmOption(JvmServerOption.class, LittleBeeConstant.EMPTY_STRING).format();
 *     // -server
 *     System.out.println(server);
 * </pre>
 *
 * @author 恒宇少年
 */
public class JvmServerOption extends AbstractJvmOption {
    private static final String JVM_SERVER = "server";

    @Override
    public String getOptionName() {
        return JVM_SERVER;
    }

    @Override
    public JvmOptionType getOptionType() {
        return JvmOptionType.Standard;
    }
}
