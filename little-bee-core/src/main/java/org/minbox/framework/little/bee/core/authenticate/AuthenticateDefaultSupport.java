package org.minbox.framework.little.bee.core.authenticate;

import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * The {@link Authenticate} default support class
 *
 * @author 恒宇少年
 */
public class AuthenticateDefaultSupport implements Authenticate {
    /**
     * User name to connect to the server
     */
    private String username;
    /**
     * IP address of the connection server
     */
    private String serverIp;
    /**
     * Host name of the connection server
     */
    private String hostname;
    /**
     * Host connection string expression
     */
    private static final String HOST_CONNECTION_PATTERN = "%s@%s";

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    @Override
    public String getConnectionInformation() {
        Assert.notNull(username, "Username to connect to the server cannot be empty");
        if (ObjectUtils.isEmpty(hostname) && ObjectUtils.isEmpty(serverIp)) {
            throw new IllegalArgumentException("hostname and serverIp at least one");
        }
        return String.format(HOST_CONNECTION_PATTERN, username, !ObjectUtils.isEmpty(hostname) ? hostname : serverIp);
    }
}
