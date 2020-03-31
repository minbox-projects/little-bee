package org.minbox.framework.little.bee.core.command;

import org.minbox.framework.little.bee.core.LittleBeeCommandException;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Run shell script command implementation
 *
 * @author 恒宇少年
 */
public class ServerRunScriptCommand extends ServerCommand {
    /**
     * The sh command bash
     */
    private static final String SH_BASH = "sh";
    /**
     * Script file full name
     * for example: "boot-run.sh"
     */
    private String script;
    /**
     * List of parameter items when running the script
     * <p>
     * for example: "sh boot-run.sh --spring.profiles.active=prod --server.port=10000"
     * where "--spring.profiles.active", "--server.port" are passed as options
     * <p>
     * Of course, the separator passed by options is not necessarily "--",
     * so you need to pass it by yourself
     */
    private String[] scriptOptions;

    public void setScript(String script) {
        this.script = script;
    }

    public void setScriptOptions(String[] scriptOptions) {
        this.scriptOptions = scriptOptions;
    }

    public ServerRunScriptCommand() {
        setBash(SH_BASH);
    }

    @Override
    void preExecute() {
        checkValidity();
        List<String> options = getRunScriptOptions();
        setCommandOptions(options.stream().toArray(String[]::new));
    }

    /**
     * Get run script command options
     *
     * @return script options
     */
    private List<String> getRunScriptOptions() {
        List<String> options = new LinkedList<>();
        options.add(this.script);
        // add all script options
        if (!ObjectUtils.isEmpty(this.scriptOptions)) {
            options.addAll(Arrays.asList(this.scriptOptions));
        }
        return options;
    }

    /**
     * check {@link #script} validity，the value is not null.
     *
     * @throws LittleBeeCommandException Exception thrown by validation data
     */
    private void checkValidity() throws LittleBeeCommandException {
        if (ObjectUtils.isEmpty(this.script)) {
            throw new LittleBeeCommandException("The script cannot be null.");
        }
    }
}
