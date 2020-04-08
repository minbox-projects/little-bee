package org.minbox.framework.little.bee.core.jvm;

/**
 * The jvm option interface definition
 * <p>
 * If you need to customize the Jvm Option,
 * you can accomplish this by implementing {@link JvmOption}.
 * Example "-XX:NewRatio":
 * <pre class="code">
 *     public class NewRatio extends AbstractJvmOption {
 *
 *     @Override
 *     public String getOptionName() {
 *         return "NewRatio";
 *     }
 *
 *     @Override
 *     public String getAssignmentSymbol() {
 *         return super.getAssignmentSymbol();
 *     }
 *
 *     @Override
 *     public JvmOptionType getOptionType() {
 *         return JvmOptionType.NonStable;
 *     }
 * }
 * </pre>
 * <p>
 * option prefix is determined by {@link JvmOptionType}
 * <p>
 * If you customize the {@link JvmOptionType#Standard} option,
 * you can refer to {@link org.minbox.framework.little.bee.core.jvm.option.JvmJarOption}
 * <p>
 * If you customize the {@link JvmOptionType#NonStandard} option,
 * you can refer to {@link org.minbox.framework.little.bee.core.jvm.option.JvmXmnOption}
 * <p>
 * If you customize the {@link JvmOptionType#NonStable} option,
 * you can refer to {@link org.minbox.framework.little.bee.core.jvm.option.JvmSurvivorRatioOption}
 * <p>
 * It is worth noting that the return value of the {@link AbstractJvmOption#getAssignmentSymbol()} method
 *
 * @author 恒宇少年
 * @see AbstractJvmOption
 * @see JvmOptionType
 * @see JvmOptionSize
 */
public interface JvmOption {
    /**
     * Set jvm option value
     *
     * @param value option value
     */
    void setValue(String value);

    /**
     * Get formatted jvm option value
     *
     * @return formatted jvm option
     */
    String format();
}
