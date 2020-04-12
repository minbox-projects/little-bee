package org.minbox.framework.little.bee.core.jvm.option;

import org.minbox.framework.little.bee.core.jvm.AbstractJvmOption;
import org.minbox.framework.little.bee.core.jvm.JvmOptionType;

/**
 * The "-jar" jvm option
 * <p>
 * Use example:
 * <pre>
 *     String jar = JvmOptionFactory.getJvmOption(JvmJarOption.class, LittleBeeConstant.EMPTY_STRING).format();
 *     // -jar
 *     System.out.println(jar);
 * </pre>
 *
 * @author 恒宇少年
 */
public class JvmJarOption extends AbstractJvmOption {
    private static final String JVM_JAR = "jar";

    @Override
    public String getOptionName() {
        return JVM_JAR;
    }

    @Override
    public JvmOptionType getOptionType() {
        return JvmOptionType.Standard;
    }
}
