/**
 * 
 */
package com.lastminute.exercise.utility;

import java.text.DecimalFormat;

/**
 * @author Gianluca Colaianni -- Fincons Group S.p.A.
 *
 */
public class DecimalFormatThreadLocal extends ThreadLocal<DecimalFormat> {
    
    private final String pattern;
    
    public DecimalFormatThreadLocal(final String pattern) {
	this.pattern = pattern;
    }
    

    /* (non-Javadoc)
     * @see java.lang.ThreadLocal#initialValue()
     */
    @Override
    protected DecimalFormat initialValue() {
	return new DecimalFormat(pattern);
    }
    
}
