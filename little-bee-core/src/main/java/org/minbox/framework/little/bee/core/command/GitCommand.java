package org.minbox.framework.little.bee.core.command;

import org.minbox.framework.little.bee.core.LittleBeeConstant;
import org.minbox.framework.little.bee.core.tools.FileTools;
import org.minbox.framework.little.bee.core.tools.ObjectTools;

/**
 * @author 恒宇少年
 */
public abstract class GitCommand extends AbstractCommand {
    /**
     * The git bash
     */
    private static final String GIT_BASH = "git";
    /**
     * "git clone"
     */
    static final String GIT_CLONE = "clone";
    /**
     * "git pull"
     */
    static final String GIT_PULL = "pull";
    /**
     * Pull specified branch parameters
     */
    static final String GIT_BRANCH = "-b %s";
    /**
     * Subdirectory for log files
     */
    private static final String PROJECT_SUB_PATH = "/projects/";

    public GitCommand() {
        setBash(GIT_BASH);
    }


    /**
     * Get the storage location of the project
     * <p>
     * if the location is executed through the {@link #setExecutionDirectory(String)} method,
     * use it directly, otherwise use the default location
     *
     * @return The project storage location
     */
    protected String getProjectDirectory() {
        String directory = getExecutionDirectory();
        if (ObjectTools.isEmpty(directory)) {
            String defaultExecutionDirectory = String.format("%s/%s%s",
                System.getProperty(LittleBeeConstant.USER_HOME),
                LittleBeeConstant.LITTLE_BEE_DIR,
                PROJECT_SUB_PATH);
            return defaultExecutionDirectory;
        }
        return directory;
    }

    /**
     * Create the storage location of the project
     *
     * @param projectDirectory The project storage location directory
     */
    protected void createProjectDirectory(String projectDirectory) {
        FileTools.createDirectory(projectDirectory);
    }
}
