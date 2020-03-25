package org.minbox.framework.little.bee.core;

/**
 * Public constant definition
 * <p>
 * provide all constants that can be used by the system architecture
 *
 * @author 恒宇少年
 */
public interface LittleBeeConstant {
    /**
     * Empty string
     */
    String EMPTY_STRING = "";
    /**
     * Space
     */
    String SPACE = " ";
    /**
     * The "ssh" bash
     */
    String SSH_BASH = "ssh";
    /**
     * The "nohup" bash
     */
    String SSH_NO_HUP = "nohup";
    /**
     * The "cd" ssh bash
     */
    String SSH_CD = "cd";
    /**
     * Quotation marks
     */
    String QUOTE = "\"";
    /**
     * Semicolon
     */
    String SEMICOLON = ";";
    /**
     * The little bee execution configuration directory
     */
    String LITTLE_BEE_DIR = ".little-bee";
    /**
     * direction command options
     */
    String DIRECTION = " > ";
    /**
     * the "nohup" command suffix
     */
    String NO_HUP_SUFFIX = " 2>&1 &";
    /**
     * java system variable current user home directory
     */
    String USER_HOME = "user.home";
}
