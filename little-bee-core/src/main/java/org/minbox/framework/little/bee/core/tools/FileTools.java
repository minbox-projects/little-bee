package org.minbox.framework.little.bee.core.tools;

import org.springframework.util.ObjectUtils;

import java.io.*;

/**
 * File manipulation tools
 *
 * @author 恒宇少年
 */
public class FileTools {
    /**
     * Create a directory
     *
     * @param dirPath directory path
     */
    public static void createDirectory(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * Create a new file
     *
     * @param filePath          The file full path
     * @param isCreateParentDir Whether to create the parent folder of the file，created when true
     * @throws IOException File io exception
     */
    public static void createFile(String filePath, boolean isCreateParentDir) throws IOException {
        File file = new File(filePath);
        if (isCreateParentDir) {
            File fileParentDir = new File(file.getParent());
            if (!fileParentDir.exists()) {
                fileParentDir.mkdirs();
            }
        }
        file.createNewFile();
    }

    /**
     * Append line to the end of the file
     *
     * @param filePath The file full path
     * @param line     line content
     * @throws IOException File io exception
     */
    public static void writeLine(String filePath, String line) throws IOException {
        writeLines(filePath, line);
    }

    /**
     * Append lines to the end of the file
     *
     * @param filePath The file full path
     * @param lines    line content array
     * @throws IOException File io exception
     */
    public static void writeLines(String filePath, String... lines) throws IOException {
        if (ObjectUtils.isEmpty(lines)) {
            return;
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true)));
            for (String line : lines) {
                writer.write(line);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw e;
            }
        }
    }

    /**
     * Check file or directory exist
     *
     * @param filePath file or directory path
     * @return Return true if present
     */
    public static boolean checkExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}
