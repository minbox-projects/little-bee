package org.minbox.framework.little.bee.core.command;

/**
 * The maven base command
 *
 * @author 恒宇少年
 * @see MavenCompileCommand
 * @see MavenPackageCommand
 */
public abstract class MavenCommand extends AbstractCommand {
    /**
     * The maven bash
     */
    private static final String MAVEN_BASH = "mvn";
    /**
     * "mvn clean"
     */
    protected static final String MAVEN_CLEAN = "clean";
    /**
     * "mvn compile"
     */
    protected static final String MAVEN_COMPILE = "compile";
    /**
     * "mvn package"
     */
    protected static final String MAVEN_PACKAGE = "package";

    public MavenCommand() {
        setBash(MAVEN_BASH);
    }
}
