package org.minbox.framework.little.bee.core;

import java.util.ArrayList;
import java.util.List;

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
     * Assignment EQ
     */
    String ASSIGNMENT_EQ = "=";
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
    /**
     * slash
     */
    String SLASH = "/";
    /**
     * new line
     */
    String NEW_LINE = "\n";
    /**
     * Recursive copy option
     */
    String RECURSIVE_COPY = "-r";
    /**
     * The note dividing line
     */
    String NOTE_DIVIDING_LINE = "---";
    /**
     * The note collection
     */
    List<String> NOTES = new ArrayList() {
        {
            add(NOTE_COMMAND);
            add(NOTE_START_TIME);
            add(NOTE_END_TIME);
            add(NOTE_TIME_CONSUMING);
            add(NOTE_REMOTE_EXECUTION);
            add(NOTE_EXECUTION_DIRECTORY);
        }
    };
    /**
     * The note of command line
     */
    String NOTE_COMMAND = "command";
    /**
     * The note of command execution start time
     */
    String NOTE_START_TIME = "startTime";
    /**
     * The note of command execution end time
     */
    String NOTE_END_TIME = "endTime";
    /**
     * The note of command execution time consuming
     */
    String NOTE_TIME_CONSUMING = "timeConsuming";
    /**
     * The note of command is remote execution
     */
    String NOTE_REMOTE_EXECUTION = "remoteExecution";
    /**
     * The note of command execution directory
     */
    String NOTE_EXECUTION_DIRECTORY = "executionDirectory";

    /**
     * The jvm option kb unit size
     */
    String JVM_OPTION_KB_SUFFIX = "k";
    /**
     * The jvm option mb unit size
     */
    String JVM_OPTION_MB_SUFFIX = "m";
    /**
     * The jvm option gb unit size
     */
    String JVM_OPTION_GB_SUFFIX = "g";
}
