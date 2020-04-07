package org.minbox.framework.little.bee.core.jvm.option;

import org.minbox.framework.little.bee.core.jvm.AbstractJvmOption;
import org.minbox.framework.little.bee.core.jvm.JvmOptionType;

/**
 * The "-Xmn" jvm option
 * <pre class="code">
 *      String xmn = JvmOptionFactory.getJvmOption(JvmXmnOption.class,
 *             JvmOptionSize.MB.value(1024).toString()).format();
 *      // -Xmn1024m
 *      System.out.println(xmn);
 * </pre>
 *
 * @author 恒宇少年
 */
public class JvmXmnOption extends AbstractJvmOption {
    private static final String JVM_XMN = "mn";

    @Override
    public String getOptionName() {
        return JVM_XMN;
    }

    @Override
    public JvmOptionType getOptionType() {
        return JvmOptionType.NonStandard;
    }
}
