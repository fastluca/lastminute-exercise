/**
 * 
 */
package com.lastminute.exercise.service;

import java.math.BigDecimal;

/**
 * @author Gianluca Colaianni -- Fincons Group S.p.A.
 *
 */
public interface TaxCalculator {
    
    public BigDecimal calculate(BigDecimal amount, double taxRate);

}
