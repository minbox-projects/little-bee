package org.minbox.framework.little.bee.core.command.response;

import org.minbox.framework.little.bee.core.LittleBeeCommandException;
import org.springframework.util.FileCopyUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Blocking command line execution response class
 *
 * @author 恒宇少年
 */
public class CommandBlockingResponse extends AbstractCommandResponse {

    @Override
    public String getRawContent() {
        return getContent(getProcess().getInputStream());
    }

    @Override
    public void writeToFile(String filePath) throws LittleBeeCommandException {
        File file = new File(filePath);
        writeToFile(file);
    }

    /**
     * Write command line execution to a file
     * <p>
     * create when file does not exist
     * when the parent directory of the file does not exist, perform all levels of creation
     *
     * @param file target file
     * @throws LittleBeeCommandException Problems encountered in executing the command
     */
    @Override
    public void writeToFile(File file) throws LittleBeeCommandException {
        try {
            String rawContent = getRawContent();
            if (!file.exists()) {
                File fileDirectory = new File(file.getParent());
                if (!fileDirectory.exists()) {
                    fileDirectory.mkdirs();
                }
                file.createNewFile();
            }
            FileCopyUtils.copy(rawContent.getBytes(), file);
        } catch (Exception e) {
            throw new LittleBeeCommandException(e.getMessage(), e);
        }
    }

    /**
     * Format the input stream content as a string
     *
     * @param is {@link InputStream}
     * @return Input stream content
     */
    protected String getContent(InputStream is) {
        String content = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            content = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}
