package org.minbox.framework.little.bee.core.command;

import org.minbox.framework.little.bee.core.LittleBeeConstant;
import org.minbox.framework.little.bee.core.jvm.*;
import org.minbox.framework.little.bee.core.jvm.option.*;

import java.util.*;

/**
 * Service startup command line
 *
 * @author 恒宇少年
 */
public class ServiceBootCommand extends ServiceCommand {
    /**
     * The java bash
     */
    private static final String JAVA_BASH = "java";
    /**
     * Jvm mode
     * The default is {@link JvmMode#Server}
     */
    private JvmMode mode = JvmMode.Server;
    /**
     * "-XX:MetaspaceSize=128m"
     */
    private JvmOptionSize metaSpaceSize = JvmOptionSize.MB.value(128);
    /**
     * "-XX:MaxMetaspaceSize=128m"
     */
    private JvmOptionSize maxMetaSpaceSize = JvmOptionSize.MB.value(128);
    /**
     * "-Xms256m"
     */
    private JvmOptionSize xms = JvmOptionSize.MB.value(256);
    /**
     * "-Xmx1024m"
     */
    private JvmOptionSize xmx = JvmOptionSize.MB.value(1024);
    /**
     * "-Xmn256m"
     */
    private JvmOptionSize xmn = JvmOptionSize.MB.value(256);
    /**
     * "-Xss256k"
     */
    private JvmOptionSize xss = JvmOptionSize.KB.value(256);
    /**
     * "-XX:SurvivorRatio=8"
     */
    private int survivorRatio = 8;
    /**
     * jvm garbage collection
     * The default value is {@link JvmGc#UseConcMarkSweepGC}
     */
    private JvmGc gc = JvmGc.UseConcMarkSweepGC;
    /**
     * Whether to use the default JvmOptions value
     */
    private boolean isUseDefaultOptions = true;
    /**
     * Application execution jar file name
     */
    private String file;
    /**
     * Jvm options
     * use {@link JvmOption#getOptionName()} as the key of the map
     */
    private Map<String, JvmOption> options = new LinkedHashMap<>();

    public void setFile(String file) {
        this.file = file;
    }

    public void setMode(JvmMode mode) {
        this.mode = mode;
    }

    public void setMetaSpaceSize(JvmOptionSize metaSpaceSize) {
        this.metaSpaceSize = metaSpaceSize;
    }

    public void setMaxMetaSpaceSize(JvmOptionSize maxMetaSpaceSize) {
        this.maxMetaSpaceSize = maxMetaSpaceSize;
    }

    public void setXms(JvmOptionSize xms) {
        this.xms = xms;
    }

    public void setXmx(JvmOptionSize xmx) {
        this.xmx = xmx;
    }

    public void setXmn(JvmOptionSize xmn) {
        this.xmn = xmn;
    }

    public void setXss(JvmOptionSize xss) {
        this.xss = xss;
    }

    public void setSurvivorRatio(int survivorRatio) {
        this.survivorRatio = survivorRatio;
    }

    public void setGc(JvmGc gc) {
        this.gc = gc;
    }

    /**
     * If you do not use the default JvmOptions,
     * set false by this method
     */
    public void disableDefaultJvmOptions() {
        this.isUseDefaultOptions = false;
    }

    public ServiceBootCommand() {
        setBash(JAVA_BASH);
    }

    /**
     * Pre command execute
     * <p>
     * determine whether to use the default JvmOptions value based on {@link #isUseDefaultOptions}
     */
    @Override
    void preExecute() {
        if (isUseDefaultOptions) {
            this.addDefaultOptions();
        }
        List<String> formattedJvmOptions = getFormattedJvmOptions();
        formattedJvmOptions.add(this.file);
        setCommandOptions(formattedJvmOptions.stream().toArray(String[]::new));
    }

    /**
     * Add customize {@link JvmOption} implement class instance
     * <p>
     * JvmOption determines the uniqueness based on the name,
     * if it already exists in the collection, it performs an overwrite
     *
     * @param option JvmOption implement class instance
     */
    public void addOption(JvmOption option) {
        if (this.options.containsKey(option.getOptionName())) {
            logger.warn("JvmOption [" + option.getOptionName() + "] already exists, will be overwritten.");
        }
        this.options.put(option.getOptionName(), option);
    }

    /**
     * Add customize {@link JvmOption} implement class instances
     * <p>
     * add all the subclasses in the map to the {@link #options}
     *
     * @param jvmOptions JvmOption map collection
     */
    public void addOptions(Map<Class<? extends JvmOption>, String> jvmOptions) {
        Iterator<Class<? extends JvmOption>> iterator = jvmOptions.keySet().iterator();
        while (iterator.hasNext()) {
            Class<? extends JvmOption> subclass = iterator.next();
            String optionValue = jvmOptions.get(subclass);
            JvmOption jvmOption = getJvmOption(subclass, optionValue);
            this.addOption(jvmOption);
        }
    }

    /**
     * Add default JvmOptions
     * <p>
     * if you customize the JvmOption with the same name,
     * the default will be overwritten
     */
    private void addDefaultOptions() {
        Map<Class<? extends JvmOption>, String> jvmOptions = new LinkedHashMap();
        jvmOptions.put(JvmMode.Server.equals(this.mode) ? JvmServerOption.class : JvmClientOption.class,
            LittleBeeConstant.EMPTY_STRING);
        jvmOptions.put(JvmMetaSpaceSizeOption.class, this.metaSpaceSize.toString());
        jvmOptions.put(JvmMaxMetaSpaceSizeOption.class, this.maxMetaSpaceSize.toString());
        jvmOptions.put(JvmXmsOption.class, this.xms.toString());
        jvmOptions.put(JvmXmxOption.class, this.xmx.toString());
        jvmOptions.put(JvmXmnOption.class, this.xmn.toString());
        jvmOptions.put(JvmXssOption.class, this.xss.toString());
        jvmOptions.put(JvmSurvivorRatioOption.class, String.valueOf(this.survivorRatio));
        jvmOptions.put(JvmGcOption.class, this.gc.getValue());
        jvmOptions.put(JvmJarOption.class, LittleBeeConstant.EMPTY_STRING);

        this.addOptions(jvmOptions);
    }

    /**
     * Get JvmOption instance
     *
     * @param subclass The {@link JvmOption} subclass
     * @param value    JvmOption value
     * @return JvmOption subclass instance
     */
    private JvmOption getJvmOption(Class<? extends JvmOption> subclass, String value) {
        return JvmOptionFactory.getJvmOption(subclass, value);
    }

    /**
     * Get formatted jvm option value string
     *
     * @param jvmOption The {@link JvmOption} subclass instance
     * @return formatted jvm option string
     */
    private String getFormattedJvmOption(JvmOption jvmOption) {
        return jvmOption.format();
    }

    /**
     * Get all formatted JvmOptions value
     *
     * @return JvmOption formatted value list
     */
    private List<String> getFormattedJvmOptions() {
        List<String> formattedJvmOptions = new ArrayList<>();
        Iterator<String> iterator = this.options.keySet().iterator();
        while (iterator.hasNext()) {
            String optionName = iterator.next();
            JvmOption jvmOption = this.options.get(optionName);
            String formattedJvmOption = getFormattedJvmOption(jvmOption);
            formattedJvmOptions.add(formattedJvmOption);
        }
        return formattedJvmOptions;
    }
}
