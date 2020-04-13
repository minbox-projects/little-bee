package org.minbox.framework.little.bee.core.command;

import org.minbox.framework.little.bee.core.tools.Assert;
import org.minbox.framework.little.bee.core.tools.ObjectTools;

import java.util.LinkedList;
import java.util.List;

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
        if (!ObjectTools.isEmpty(branch)) {
            this.branch = branch;
        }
    }

    /**
     * Pre execution command
     * <p>
     * projects are stored by default in the "projects" folder under "user.home"
     */
    @Override
    void preExecute() {
        Assert.notNull(this.projectAddress, "The address of the clone project cannot be null.");
        String projectStorageDirectory = getProjectDirectory();
        if (!projectStorageDirectory.equals(getExecutionDirectory())) {
            setExecutionDirectory(projectStorageDirectory);
        }
        createProjectDirectory(projectStorageDirectory);
        List<String> options = getGitCloneOptions();
        setCommandOptions(options.stream().toArray(String[]::new));
    }

    /**
     * Get "git clone" command options
     *
     * @return command options
     */
    private List<String> getGitCloneOptions() {
        List<String> options = new LinkedList<>();
        // add "clone" option
        options.add(GIT_CLONE);
        // add "-b" option
        String cloneBranch = String.format(GIT_BRANCH, branch);
        options.add(cloneBranch);
        // add project git address
        options.add(this.projectAddress);
        return options;
    }
}
