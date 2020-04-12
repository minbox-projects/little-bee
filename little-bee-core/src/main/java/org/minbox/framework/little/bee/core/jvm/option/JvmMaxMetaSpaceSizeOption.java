package org.minbox.framework.little.bee.core.jvm.option;

import org.minbox.framework.little.bee.core.LittleBeeConstant;
import org.minbox.framework.little.bee.core.jvm.AbstractJvmOption;
import org.minbox.framework.little.bee.core.jvm.JvmOptionType;

/**
 * The "-XX:MaxMetaspaceSize" jvm option
 * <p>
 * Use example:
 * <pre>
 *      String maxMetaSpaceSize = JvmOptionFactory.getJvmOption(JvmMaxMetaSpaceSizeOption.class,
 *          JvmOptionSize.MB.value(128).toString()).format();
 *      // -XX:MaxMetaspaceSize=128m
 *      System.out.println(maxMetaSpaceSize);
 * </pre>
 *
 * @author 恒宇少年
 */
public class JvmMaxMetaSpaceSizeOption extends AbstractJvmOption {
    private static final String JVM_MAX_META_SPACE_SIZE = "MaxMetaspaceSize";

    @Override
    public String getOptionName() {
        return JVM_MAX_META_SPACE_SIZE;
    }

    @Override
    public String getAssignmentSymbol() {
        return LittleBeeConstant.ASSIGNMENT_EQ;
    }

    @Override
    public JvmOptionType getOptionType() {
        return JvmOptionType.NonStable;
    }
}
