/**
 * 
 */
package com.lastminute.exercise.utility;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * An utility class to manage {@link Number} formatting. </br>
 * The class is a {@code Singleton} with lazy initialization.
 * @author Gianluca Colaianni
 *
 */
public class Utility {
    
    private static Map<String, DecimalFormatThreadLocal> formatCache = new HashMap<>();
    
    private Utility() {}

    
    /**
     * Represents a number as {@code String}, according with the format specified by {@code pattern} parameter.
     * @param number the number to represent.
     * @param pattern the forat to use.
     * @return
     * @author Gianluca Colaianni
     */
    public static String format(final Number number, final String pattern) {
	Objects.requireNonNull(number, "Number must be different from null.");
	Objects.requireNonNull(pattern, "Pattern must be different from null.");
	
	DecimalFormatThreadLocal df = formatCache.get(pattern);
	
	if (df == null) {
	    synchronized (Utility.class) {
		df = new DecimalFormatThreadLocal(pattern);
		formatCache.put(pattern, df);
	    }
	}
	
	return df.get().format(number);
    }
    
}
