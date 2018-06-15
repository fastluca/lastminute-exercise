/**
 * 
 */
package com.lastminute.exercise.bean;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;

/**
 * The model class for a cart object.
 * 
 * @author Gianluca Colaianni
 *
 */
@Data
public class Cart {
    
   /*
    * The items in the cart. The key is the product identifier.
    */
    private Map<String, CartItem> items;
    
    /*
     * Total sales taxes.
     */
    private BigDecimal totalTaxes;
    
    /*
     * Total amount
     */
    private BigDecimal total;
    
    /**
     * 
     */
    public Cart() {
	//LinkedHashMap to preserve items insertion ordering.
	this(new LinkedHashMap<>(), BigDecimal.ZERO, BigDecimal.ZERO);
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
