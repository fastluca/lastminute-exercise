/**
 * 
 */
package com.lastminute.exercise.utility;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;

/**
 * It is a {@link ThreadLocal} management of {@link DecimalFormat} objects. </br>
 * Using this class, {@link DecimalFormat} instances obtained are thread-safe.</br>
 * The {@link DecimalFormat} instances obtained use the dot ('.') as decimal separator and has grouping disabled.
 * For more detailed informations, please see <a href="https://docs.oracle.com/javase/8/docs/api/java/text/DecimalFormat.html">DecimalFormat</a> specifications.
 * @author Gianluca Colaianni
 *
 */
public class DecimalFormatThreadLocal extends ThreadLocal<DecimalFormat> {

    private final String pattern;

    private final DecimalFormatSymbols symbols;

    public DecimalFormatThreadLocal(final String pattern) {
	Objects.requireNonNull(pattern, "Format pattern must be different from null.");
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
