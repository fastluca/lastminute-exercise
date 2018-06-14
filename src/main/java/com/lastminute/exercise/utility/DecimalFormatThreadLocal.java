/**
 * 
 */
package com.lastminute.exercise.utility;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * @author Gianluca Colaianni -- Fincons Group S.p.A.
 *
 */
public class DecimalFormatThreadLocal extends ThreadLocal<DecimalFormat> {

    private final String pattern;

    private final DecimalFormatSymbols symbols;

    public DecimalFormatThreadLocal(final String pattern) {
	this.pattern = pattern;
	this.symbols = new DecimalFormatSymbols(Locale.getDefault());
	this.symbols.setDecimalSeparator('.');
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.ThreadLocal#initialValue()
     */
    @Override
    protected DecimalFormat initialValue() {
	DecimalFormat df = new DecimalFormat(pattern, this.symbols);
	df.setGroupingUsed(false);
	return df;
    }

}
