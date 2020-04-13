package org.minbox.framework.little.bee.core.tools;

import java.util.StringJoiner;

/**
 * Miscellaneous {@link String} utility methods.
 *
 * @author 恒宇少年
 */
public class StringTools {

    /**
     * Convert a {@code String} array into a delimited {@code String} (e.g. CSV).
     * <p>Useful for {@code toString()} implementations.
     *
     * @param arr   the array to display (potentially {@code null} or empty)
     * @param delim the delimiter to use (typically a ",")
     * @return the delimited {@code String}
     */
    public static String arrayToDelimitedString(Object[] arr, String delim) {
        if (ObjectTools.isEmpty(arr)) {
            return "";
        }
        if (arr.length == 1) {
            return ObjectTools.nullSafeToString(arr[0]);
        }

        StringJoiner sj = new StringJoiner(delim);
        for (Object o : arr) {
            sj.add(String.valueOf(o));
        }
        return sj.toString();
    }
}
