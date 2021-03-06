/**
 * 
 */
package com.lastminute.exercise.bean;

import java.math.BigDecimal;
import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;

/**
 * The model class for a cart item object.
 * 
 * @author Gianluca Colaianni
 *
 */
@Getter
@Setter
public class CartItem {

    /*
     * The item product.
     */
    private Product product;
    
    /*
     * The quantity in the cart for the product.
     */
    private BigInteger quantity;
    
    /*
     * The item total amount.
     */
    private BigDecimal total;
    
    /*
     * The item total sales taxes.
     */
    private BigDecimal totalTaxes;

}
