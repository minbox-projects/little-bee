package org.minbox.framework.little.bee.core.authenticate;

/**
 * Remote execution command server authentication information configuration
 * <p>
 * The target server needs to be configured with an authorized ssh rsa public key,
 * and only needs to be configured with "user name" and "IP address" when executing the command remotely
 *
 * @author 恒宇少年
 */
public interface Authenticate {
    /**
     * Set the user name to connect to the remote server
     *
     * @param username login username
     */
    void setUsername(String username);

    /**
     * Set the host name of the connection service
     * <p>
     * To configure the host name instead of the IP address,
     * you need to add the host to IP mapping in the "/etc/hosts" configuration file
     *
     * @param hostName The host name
     */
    void setHostName(String hostName);

    /**
     * Set the serverIp of the connection service
     * <p>
     * can configure intranet or public network IP address
     *
     * @param serverIp The server ip
     */
    void setServerIp(String serverIp);

    /**
     * Get the formatted remote server connection string
     * <p>
     * If serverIP and hostName are set at the same time, hostName has higher priority
     *
     * @return connection string，"username@sever-name" or "username@server-ip"
     */
    String getConnectionInformation();
}
