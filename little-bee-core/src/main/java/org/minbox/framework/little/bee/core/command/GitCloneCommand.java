package org.minbox.framework.little.bee.core.command;

import org.minbox.framework.little.bee.core.LittleBeeConstant;
import org.minbox.framework.little.bee.core.tools.FileTools;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * Git clone command implementation
 *
 * @author 恒宇少年
 */
public class GitCloneCommand extends GitCommand {
    /**
     * The project git address
     */
    private String projectAddress;
    /**
     * clone the branch of the project, the default is master
     */
    private String branch = "master";
    /**
     * Subdirectory for log files
     */
    private static final String PROJECT_SUB_PATH = "/projects/";

    /**
     * Set the project git address
     *
     * @param projectAddress project git address
     */
    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    /**
     * Set the branch of the clone project
     *
     * @param branch project branch name
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * Pre execution command
     * <p>
     * projects are stored by default in the "projects" folder under "user.home"
     */
    @Override
    void preExecute() {
        Assert.notNull(this.projectAddress, "The address of the clone project cannot be null.");
        String cloneBranch = String.format(GIT_BRANCH, branch);
        String projectStorageDirectory = getProjectStorageDirectory();
        createProjectStorageDirectory(projectStorageDirectory);
        setCommandOptions(new String[]{GIT_CLONE, cloneBranch, projectAddress});
    }

    /**
     * Get the storage location of the project
     * <p>
     * if the location is executed through the {@link #setExecutionDirectory(String)} method,
     * use it directly, otherwise use the default location
     *
     * @return The project storage location
     */
    private String getProjectStorageDirectory() {
        String directory = getExecutionDirectory();
        if (ObjectUtils.isEmpty(directory)) {
            String defaultExecutionDirectory = String.format("%s/%s%s",
                System.getProperty(LittleBeeConstant.USER_HOME),
                LittleBeeConstant.LITTLE_BEE_DIR,
                PROJECT_SUB_PATH);
            setExecutionDirectory(defaultExecutionDirectory);
            return defaultExecutionDirectory;
        }
        return directory;
    }

    /**
     * Create the storage location of the project
     *
     * @param projectDirectory The project storage location directory
     */
    private void createProjectStorageDirectory(String projectDirectory) {
        FileTools.createDirectory(projectDirectory);
    }
}
