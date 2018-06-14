/**
 * 
 */
package com.lastminute.exercise.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Implementation of {@link TaxCalculator} service.
 * 
 * @author Gianluca Colaianni
 *
 */
public class TaxCalculatorImpl implements TaxCalculator {

    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public static final BigDecimal INCREMENT = new BigDecimal("0.05");

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.lastminute.exercise.service.TaxCalculator#calculate(java.math.BigDecimal,
     * double)
     */
    @Override
    public BigDecimal calculate(final BigDecimal amount, final double taxRate) {
	Objects.requireNonNull(amount, "Amount must be different from null.");

	/*
	 * If taxRate is less than 0, do nothing.
	 */
	if (taxRate <= 0) {
	    return amount;
	} else {
	    BigDecimal tax = amount.multiply(BigDecimal.valueOf(taxRate)).divide(ONE_HUNDRED);
	    BigDecimal rounded = tax.divide(INCREMENT, 0, RoundingMode.CEILING).multiply(INCREMENT);
	    return amount.add(rounded);
	}
    }

}
