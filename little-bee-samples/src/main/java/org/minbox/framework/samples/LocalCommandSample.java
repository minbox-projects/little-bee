package org.minbox.framework.samples;

import org.minbox.framework.little.bee.core.command.MavenCompileCommand;

/**
 * 执行本地命令示例
 *
 * @author 恒宇少年
 */
public class LocalCommandSample {
    public static void main(String[] args) {
        // Maven编译命令
        MavenCompileCommand compileCommand = new MavenCompileCommand();
        // 设置命令执行的目录
        compileCommand.setExecutionDirectory("/Users/yuqiyu/work/iot-server-projects");
        // 执行命令（默认使用非阻塞方式执行）
        // 日志文件默认保存在"~/.little-bee/logs/"
        // ~/ = System.getProperty("user.home")
        compileCommand.execute();
    }
}
