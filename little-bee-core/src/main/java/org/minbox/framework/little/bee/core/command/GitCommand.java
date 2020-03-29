package org.minbox.framework.little.bee.core.command;

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

    public GitCommand() {
        setBash(GIT_BASH);
    }
}
