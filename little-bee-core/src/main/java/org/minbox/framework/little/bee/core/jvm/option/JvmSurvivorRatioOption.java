package org.minbox.framework.little.bee.core.jvm.option;

import org.minbox.framework.little.bee.core.LittleBeeConstant;
import org.minbox.framework.little.bee.core.jvm.AbstractJvmOption;
import org.minbox.framework.little.bee.core.jvm.JvmOptionType;

/**
 * The "-XX:SurvivorRatio" jvm option
 * <pre class="code">
 *     String survivorRatio = JvmOptionFactory.getJvmOption(JvmSurvivorRatioOption.class,"8").format();
 *     // -XX:SurvivorRatio=8
 *     System.out.println(survivorRatio);
 * </pre>
 *
 * @author 恒宇少年
 */
public class JvmSurvivorRatioOption extends AbstractJvmOption {

    private static final String JVM_SURVIVOR_RATIO = "SurvivorRatio";

    @Override
    public String getOptionName() {
        return JVM_SURVIVOR_RATIO;
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
