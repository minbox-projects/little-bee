package org.minbox.framework.little.bee.core.jvm.option;

import org.minbox.framework.little.bee.core.jvm.AbstractJvmOption;
import org.minbox.framework.little.bee.core.jvm.JvmOptionType;

/**
 * The "-Xss" jvm option
 * <p>
 * Use example:
 * <pre>
 *      String xss = JvmOptionFactory.getJvmOption(JvmXssOption.class,
 *             JvmOptionSize.KB.value(256).toString()).format();
 *      // -Xss256k
 *      System.out.println(xss);
 * </pre>
 *
 * @author 恒宇少年
 */
public class JvmXssOption extends AbstractJvmOption {
    private static final String JVM_XSS = "ss";

    @Override
    public String getOptionName() {
        return JVM_XSS;
    }

    @Override
    public JvmOptionType getOptionType() {
        return JvmOptionType.NonStandard;
    }
}
