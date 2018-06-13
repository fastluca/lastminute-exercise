/**
 * 
 */
package com.lastminute.exercise.bean;

import java.math.BigDecimal;
import java.math.BigInteger;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Gianluca Colaianni -- Fincons Group S.p.A.
 *
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class CartItem {

    @EqualsAndHashCode.Include
    private Product product;
    
    private BigInteger quantity;
    
    private BigDecimal total;
    
    private BigDecimal totalTaxes;

}
