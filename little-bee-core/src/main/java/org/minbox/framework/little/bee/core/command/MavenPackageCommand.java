package org.minbox.framework.little.bee.core.command;

/**
 * Commands executed when Maven packages a project, inherited from {@link MavenCommand}
 * <p>
 * implementation of "mvn clean package" compile project command
 * <p>
 * if the command is executed remotely, the format is: "ssh username@serverIp mvn clean package"
 *
 * @author 恒宇少年
 */
public class MavenPackageCommand extends MavenCommand {
    public MavenPackageCommand() {
        setCommandOptions(new String[]{MAVEN_CLEAN, MAVEN_PACKAGE});
    }
}
