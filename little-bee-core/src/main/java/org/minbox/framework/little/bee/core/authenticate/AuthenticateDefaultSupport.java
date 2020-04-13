package org.minbox.framework.little.bee.core.authenticate;

import org.minbox.framework.little.bee.core.tools.Assert;
import org.minbox.framework.little.bee.core.tools.ObjectTools;


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

    /**
     * Default constructor
     */
    public AuthenticateDefaultSupport() {
    }

    /**
     * Instantiate Authenticate object with "username", "hostname"
     *
     * @param username User name to connect to the server
     * @param hostname Host name of the connection server
     */
    public AuthenticateDefaultSupport(String username, String hostname) {
        this.username = username;
        this.hostname = hostname;
    }

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
        if (ObjectTools.isEmpty(hostname) && ObjectTools.isEmpty(serverIp)) {
            throw new IllegalArgumentException("hostname and serverIp at least one");
        }
        return String.format(HOST_CONNECTION_PATTERN, username, !ObjectTools.isEmpty(hostname) ? hostname : serverIp);
    }
}
