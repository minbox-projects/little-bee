package org.minbox.framework.samples;

import org.minbox.framework.little.bee.core.command.GitCloneCommand;

/**
 * Git clone项目命令执行示例
 *
 * @author 恒宇少年
 */
public class GitCloneCommandSample {
    public static void main(String[] args) {
        GitCloneCommand gitCloneCommand = new GitCloneCommand();
        gitCloneCommand.setProjectAddress("https://gitee.com/minbox-projects/minbox-framework.git");
        // 设置clone目标目录，默认为user.home/.little-bee/projects
        //gitCloneCommand.setExecutionDirectory("/Users/yuqiyu/Downloads");
        gitCloneCommand.execute();
    }
}
