package org.minbox.framework.little.bee.core.jvm.option;

import org.minbox.framework.little.bee.core.LittleBeeConstant;
import org.minbox.framework.little.bee.core.jvm.AbstractJvmOption;
import org.minbox.framework.little.bee.core.jvm.JvmOptionType;

/**
 * The "-XX:MetaspaceSize" jvm option
 * <p>
 * Use example:
 * <pre>
 *     String metaSpaceSize = JvmOptionFactory.getJvmOption(JvmMetaSpaceSizeOption.class,
 *          JvmOptionSize.MB.value(128).toString()).format();
 *     // -XX:MetaspaceSize=128m
 *     System.out.println(metaSpaceSize);
 * </pre>
 *
 * @author 恒宇少年
 */
public class JvmMetaSpaceSizeOption extends AbstractJvmOption {

    private static final String JVM_META_SPACE_SIZE = "MetaspaceSize";

    @Override
    public String getOptionName() {
        return JVM_META_SPACE_SIZE;
    }

    /**
     * Get assignment symbol
     * <p>
     * non-stable jvm option use {@link LittleBeeConstant#ASSIGNMENT_EQ} as assignment symbol
     *
     * @return assignment symbol
     */
    @Override
    public String getAssignmentSymbol() {
        return LittleBeeConstant.ASSIGNMENT_EQ;
    }

    @Override
    public JvmOptionType getOptionType() {
        return JvmOptionType.NonStable;
    }
}
