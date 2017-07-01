package me.bitfrom.githubsearch.utils;

/**
 * <p>Simple text utility class.</p>
 *
 * @author const
 * @version 1
 * @since 01.07.2017
 */
public class TextUtils {

    /**
     * Returns a new string that is a substring of the passed string.
     *
     * @param string string to operate on.
     * @param start start index.
     * @param length length of the result string.
     *
     * @return a {@link String} value.
     **/
    public static String subString(String string, int start, int length) {
        return string.substring(start, Math.min(start + length, string.length()));
    }
}