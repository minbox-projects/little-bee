package org.minbox.framework.little.bee.core.jvm;

import org.minbox.framework.little.bee.core.LittleBeeCommandException;
import org.springframework.util.Assert;

/**
 * The {@link JvmOption} subclass factory
 *
 * @author 恒宇少年
 */
public class JvmOptionFactory {
    /**
     * Disable the use of constructors to instantiate factory classes
     */
    private JvmOptionFactory() {
    }

    /**
     * Get {@link JvmOption} subclass instance
     *
     * @param subClass    The {@link JvmOption} subclass
     * @param optionValue The jvm option value
     * @return JvmOption subclass instance
     * @throws LittleBeeCommandException Exception encountered during instantiation
     */
    public static JvmOption getJvmOption(Class<? extends JvmOption> subClass, String optionValue)
        throws LittleBeeCommandException {
        Assert.notNull(subClass, "The JvmOption subclass can't not null.");
        JvmOption jvmOption;
        try {
            jvmOption = subClass.newInstance();
            jvmOption.setValue(optionValue);
        } catch (Exception e) {
            throw new LittleBeeCommandException(e.getMessage(), e);
        }
        return jvmOption;
    }
}
