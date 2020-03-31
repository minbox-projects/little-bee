package org.minbox.framework.samples;

import org.minbox.framework.little.bee.core.command.ServerMkdirCommand;

/**
 * mkdir 命令使用示例
 *
 * @author 恒宇少年
 */
public class ServerMkdirCommandSample {
    public static void main(String[] args) {
        ServerMkdirCommand serverMkdirCommand = new ServerMkdirCommand();
        // 设置创建文件夹的名称
        serverMkdirCommand.setDirectory("project");
        // 设置"mkdir"命令的执行目录，也就是在该目录下创建子目录
        serverMkdirCommand.setExecutionDirectory("/Users/yuqiyu/Downloads");
        serverMkdirCommand.execute();
    }
}
