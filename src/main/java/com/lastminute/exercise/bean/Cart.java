/**
 * 
 */
package com.lastminute.exercise.bean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Gianluca Colaianni -- Fincons Group S.p.A.
 *
 */
@Getter
@Setter
public class Cart {
    
    private Map<String, CartItem> items;
    
    private BigDecimal totalTaxes;
    
    private BigDecimal total;
    
    /**
     * 
     */
    public Cart() {
	this(new HashMap<>(), BigDecimal.ZERO, BigDecimal.ZERO);
    }

    /**
     * @param items
     * @param totalTaxes
     * @param total
     */
    public Cart(Map<String, CartItem> items, BigDecimal totalTaxes, BigDecimal total) {
	this.items = items;
	this.totalTaxes = totalTaxes;
	this.total = total;
    }
    
    

}
