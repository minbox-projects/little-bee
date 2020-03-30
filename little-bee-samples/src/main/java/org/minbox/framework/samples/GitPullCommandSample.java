package org.minbox.framework.samples;

import org.minbox.framework.little.bee.core.command.GitPullCommand;

/**
 * Git 拉取代码命令示例
 *
 * @author 恒宇少年
 */
public class GitPullCommandSample {
    public static void main(String[] args) {
        GitPullCommand gitPullCommand = new GitPullCommand();
        // 项目名称
        gitPullCommand.setProjectName("minbox-framework");
        // 设置拉取分支的名称，默认为master
        //gitPullCommand.setBranch("master");
        // 设置仓库远程名称，默认为origin
        //gitPullCommand.setRemote("origin");
        gitPullCommand.execute();
    }
}
