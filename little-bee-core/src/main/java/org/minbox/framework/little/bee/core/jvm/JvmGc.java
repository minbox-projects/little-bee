package org.minbox.framework.little.bee.core.jvm;

/**
 * jvm garbage collection
 *
 * @author 恒宇少年
 */
public enum JvmGc {
    UseSerialGC("+UseSerialGC"),
    UseParNewGC("+UseParNewGC"),
    UseParallelGC("+UseParallelGC"),
    UseParallelOldGC("+UseParallelOldGC"),
    UseConcMarkSweepGC("+UseConcMarkSweepGC"),
    UseG1GC("+UseG1GC");

    JvmGc(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
