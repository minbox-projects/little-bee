package org.minbox.framework.little.bee.core.command;

/**
 * Maven compile implementation of command
 * <p>
 * implementation of "mvn clean compile" compile project command
 * <p>
 * if the command is executed remotely, the format is: "ssh username@serverIp mvn clean compile"
 *
 * @author 恒宇少年
 */
public class MavenCompileCommand extends MavenCommand {
    @Override
    public void preExecute() {
        setCommandOptions(new String[]{MAVEN_CLEAN, MAVEN_COMPILE});
    }
}
