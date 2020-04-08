package org.minbox.framework.samples;

import org.minbox.framework.little.bee.core.command.ServiceBootCommand;
import org.minbox.framework.little.bee.core.command.response.CommandResponseType;

/**
 * 服务启动命令示例
 *
 * @author 恒宇少年
 */
public class ServiceBootCommandSample {
    public static void main(String[] args) {
        ServiceBootCommand serviceBootCommand = new ServiceBootCommand();
        // jar -jar 命令执行的目录
        serviceBootCommand.setExecutionDirectory("/Users/yuqiyu/study/article-source-code/spring-boot-chapter/developing-first-application/target");
        // jar文件命令
        serviceBootCommand.setFile("service-application-0.0.1-SNAPSHOT.jar");
        // 使用nohup方式接收运行日志
        // 日志默认保存在~/.little-bee/logs下
        serviceBootCommand.setResponseType(CommandResponseType.NOHUP_NON_BLOCKING);
        serviceBootCommand.execute();
    }
}
