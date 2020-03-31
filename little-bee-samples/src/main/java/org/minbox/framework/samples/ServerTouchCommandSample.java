package org.minbox.framework.samples;

import org.minbox.framework.little.bee.core.authenticate.Authenticate;
import org.minbox.framework.little.bee.core.authenticate.AuthenticateDefaultSupport;
import org.minbox.framework.little.bee.core.command.ServerTouchCommand;

import java.util.UUID;

/**
 * touch命令使用示例
 *
 * @author 恒宇少年
 */
public class ServerTouchCommandSample {
    
    public static void main(String[] args) {
        // 在远程服务器上创建文件
        Authenticate authenticate = new AuthenticateDefaultSupport("root", "iot-dev");
        ServerTouchCommand serverTouchCommand = new ServerTouchCommand();
        serverTouchCommand.setAuthenticate(authenticate);
        // 设置文件的名称
        serverTouchCommand.setFile(UUID.randomUUID().toString());
        // 设置"touch"命令在远程服务器执行的目录
        serverTouchCommand.setExecutionDirectory("/home");

        serverTouchCommand.execute();
    }
}
