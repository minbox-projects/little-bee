package org.minbox.framework.samples;

import org.minbox.framework.little.bee.core.authenticate.Authenticate;
import org.minbox.framework.little.bee.core.authenticate.AuthenticateDefaultSupport;
import org.minbox.framework.little.bee.core.command.ServerRunScriptCommand;

/**
 * 运行shell脚本命令使用示例
 *
 * @author 恒宇少年
 */
public class ServerRunScriptCommandSample {
    public static void main(String[] args) {
        ServerRunScriptCommand serverRunScriptCommand = new ServerRunScriptCommand();
        // 执行远程服务器上的脚本
        Authenticate authenticate = new AuthenticateDefaultSupport("root", "iot-dev");
        serverRunScriptCommand.setAuthenticate(authenticate);
        // 脚本所处的目录
        serverRunScriptCommand.setExecutionDirectory("/home");
        // 脚本名称
        serverRunScriptCommand.setScript("clear-cache.sh");
        // 脚本执行时的options
        serverRunScriptCommand.setScriptOptions(new String[]{
            "--spring.profiles.active=prod",
            "--server.port=10000"
        });
        serverRunScriptCommand.execute();
    }
}
