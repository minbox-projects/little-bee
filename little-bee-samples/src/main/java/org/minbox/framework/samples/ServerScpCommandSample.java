package org.minbox.framework.samples;

import org.minbox.framework.little.bee.core.command.ServerScpCommand;

/**
 * "scp"命令使用示例
 *
 * @author 恒宇少年
 * @see ServerScpCommand
 */
public class ServerScpCommandSample {

    public static void main(String[] args) {
        copyFiles();
        //copyDirectory();
    }

    /**
     * 复制文件夹示例
     */
    static void copyDirectory() {
        ServerScpCommand serverScpCommand = new ServerScpCommand();
        // 复制的文件夹
        serverScpCommand.setSourceDirectory("seata");
        // "scp" 命令执行目录，根据该目录进行判断复制文件、复制文件夹是否存在
        serverScpCommand.setExecutionDirectory("/Users/yuqiyu/Downloads");
        // 设置复制文件的目标文件夹
        serverScpCommand.setTargetDirectory("/home");
        // 远程服务器的用户名默认值为"root"
        //serverScpCommand.setTargetUsername("root");
        // 使用主机名的方式连接
        serverScpCommand.setTargetHostname("iot-dev");
        // 使用IP方式连接
        //serverScpCommand.setTargetIp("39.106.161.160");
        serverScpCommand.execute();
    }

    /**
     * 复制文件列表示例方法
     */
    static void copyFiles() {
        ServerScpCommand serverScpCommand = new ServerScpCommand();
        // 复制的文件列表
        serverScpCommand.setSourceFiles("up.sql", "test.sql");
        // "scp" 命令执行目录，根据该目录进行判断复制文件、复制文件夹是否存在
        serverScpCommand.setExecutionDirectory("/Users/yuqiyu/Downloads");
        // 设置复制文件的目标文件夹
        serverScpCommand.setTargetDirectory("/home");
        // 远程服务器的用户名默认值为"root"
        //serverScpCommand.setTargetUsername("root");
        // 使用IP方式连接
        serverScpCommand.setTargetIp("39.106.161.160");
        // 使用主机名的方式连接
        //serverScpCommand.setTargetHostname("iot-dev");
        serverScpCommand.execute();
    }
}
