/**
 * 
 */
package com.lastminute.exercise.service;

import java.math.BigDecimal;

/**
 * The service responsible to calculate taxes.
 * 
 * @author Gianluca Colaianni
 *
 */
public interface TaxCalculator {
    
    public BigDecimal calculate(BigDecimal amount, double taxRate);

}
