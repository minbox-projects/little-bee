package org.minbox.framework.little.bee.core.jvm.option;

import org.minbox.framework.little.bee.core.LittleBeeConstant;
import org.minbox.framework.little.bee.core.jvm.AbstractJvmOption;
import org.minbox.framework.little.bee.core.jvm.JvmOptionType;

/**
 * The {@link org.minbox.framework.little.bee.core.jvm.JvmGc} jvm option
 * <p>
 * Use example:
 * <pre>
 *   String gc = JvmOptionFactory.getJvmOption(JvmGcOption.class, JvmGc.UseConcMarkSweepGC.getValue()).format();
 *   // -XX:+UseConcMarkSweepGC
 *   System.out.println(gc);
 * </pre>
 *
 * @author 恒宇少年
 * @see org.minbox.framework.little.bee.core.jvm.JvmGc
 */
public class JvmGcOption extends AbstractJvmOption {
    /**
     * Get jvm option name
     * <p>
     * gc option name does not return anything
     *
     * @return Jvm option name
     */
    @Override
    public String getOptionName() {
        return LittleBeeConstant.EMPTY_STRING;
    }

    @Override
    public JvmOptionType getOptionType() {
        return JvmOptionType.NonStable;
    }
}
