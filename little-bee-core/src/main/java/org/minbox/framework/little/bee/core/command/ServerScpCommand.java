package org.minbox.framework.little.bee.core.command;

import org.minbox.framework.little.bee.core.LittleBeeCommandException;
import org.minbox.framework.little.bee.core.LittleBeeConstant;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * File remote copy command implementation
 * <p>
 * Can copy the specified file list or folder to the specified directory on the remote server
 *
 * @author 恒宇少年
 */
public class ServerScpCommand extends ServerCommand {
    /**
     * The scp bash
     */
    private static final String SCP_BASH = "scp";
    /**
     * Target location "username@hostname:targetDirectory"
     */
    private static final String TARGET_LOCATION_PATTERN = "%s@%s:%s";
    /**
     * List of files copied remotely
     */
    private String[] sourceFiles;
    /**
     * Set folder name for remote replication
     */
    private String sourceDirectory;
    /**
     * Host name of the target server
     */
    private String targetHostname;
    /**
     * User name of the target server
     * The username default value is "root"
     */
    private String targetUsername = "root";
    /**
     * Server ip of the target server
     */
    private String targetIp;
    /**
     * Copy destination folder name
     */
    private String targetDirectory;

    public ServerScpCommand() {
        setBash(SCP_BASH);
    }

    public void setSourceFiles(String... files) {
        this.sourceFiles = files;
    }

    public void setSourceDirectory(String sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

    public void setTargetHostname(String targetHostname) {
        this.targetHostname = targetHostname;
    }

    public void setTargetUsername(String targetUsername) {
        this.targetUsername = targetUsername;
    }

    public void setTargetIp(String targetIp) {
        this.targetIp = targetIp;
    }

    public void setTargetDirectory(String targetDirectory) {
        this.targetDirectory = targetDirectory;
    }

    /**
     * Check parameter validity
     * <p>
     * check files or source directory validity
     * <p>
     * check hostname or host ip validity
     * <p>
     * check target directory validity
     */
    private void checkParameterValidity() {
        checkSourceDirectoryOrFiles();
        checkHostnameOrIp();
        checkTargetDirectory();
    }

    /**
     * Pre command execution
     * <p>
     * get scp command options
     * <p>
     * add copy source files or directory
     * <p>
     * add target server info and target directory
     */
    @Override
    void preExecute() {
        checkParameterValidity();
        List<String> options = getScpOptions();
        options.add(getSource());
        options.add(getTarget());
        setCommandOptions(options.stream().toArray(String[]::new));
    }

    /**
     * Check source directory or files
     *
     * @throws LittleBeeCommandException Check validity exception
     */
    private void checkSourceDirectoryOrFiles() throws LittleBeeCommandException {
        if (ObjectUtils.isEmpty(this.sourceFiles) && ObjectUtils.isEmpty(this.sourceDirectory)) {
            throw new IllegalArgumentException("Either file name or directory must exist.");
        }
        if (!ObjectUtils.isEmpty(this.sourceDirectory)) {
            String sourceDirectoryPath = getExecutionDirectory() + LittleBeeConstant.SLASH + this.sourceDirectory;
            File directory = new File(sourceDirectoryPath);
            if (!directory.exists()) {
                throw new LittleBeeCommandException("The sourceDirectory does not exist.");
            }
        }
    }

    /**
     * Check target server hostname or ip address
     *
     * @throws LittleBeeCommandException Check validity exception
     */
    private void checkHostnameOrIp() throws LittleBeeCommandException {
        if (ObjectUtils.isEmpty(this.targetHostname) && ObjectUtils.isEmpty(this.targetIp)) {
            throw new LittleBeeCommandException("The targetHostname or targetIp of the target server must exist.");
        }
    }

    /**
     * Check target directory
     *
     * @throws LittleBeeCommandException Check validity exception
     */
    private void checkTargetDirectory() throws LittleBeeCommandException {
        if (ObjectUtils.isEmpty(this.targetDirectory)) {
            throw new LittleBeeCommandException("The targetDirectory cannot be null.");
        }
    }

    /**
     * Go back to the copied source information
     * <p>
     * {@link #sourceFiles} has priority over {@link #sourceDirectory}
     * <p>
     * If you are copying a folder, you need to add the "-r" parameter item
     *
     * @return Formatted copy source
     * @throws
     */
    private String getSource() throws LittleBeeCommandException {
        if (!ObjectUtils.isEmpty(this.sourceFiles)) {
            return StringUtils.arrayToDelimitedString(this.sourceFiles, LittleBeeConstant.SPACE);
        }
        return this.sourceDirectory;
    }

    /**
     * Get destination of copied files
     *
     * @return target location
     */
    private String getTarget() {
        return String.format(TARGET_LOCATION_PATTERN,
            this.targetUsername,
            getTargetServerInfo(),
            this.targetDirectory);
    }

    /**
     * Get the connection information of the target server
     * <p>
     * {@link #targetHostname} has priority over {@link #targetIp}
     *
     * @return target server connection info
     */
    private String getTargetServerInfo() {
        return !ObjectUtils.isEmpty(this.targetHostname) ? this.targetHostname : this.targetIp;
    }

    /**
     * Get "scp" command execution options
     *
     * @return command options
     */
    private List<String> getScpOptions() {
        List<String> options = new LinkedList<>();
        // if sourceDirectory not empty, need add "-r" option
        if (!ObjectUtils.isEmpty(this.sourceDirectory)) {
            options.add(LittleBeeConstant.RECURSIVE_COPY);
        }
        return options;
    }
}
