/**
 * 
 */
package com.lastminute.exercise.bean;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The model class for a Product object.
 * 
 * @author Gianluca Colaianni
 *
 */
@AllArgsConstructor
@Data
public class Product {
    
    /*
     * The product identifier.
     */
    private String id;
    
    /*
     * The product description.
     */
    private String description;
    
    /*
     * The product amount.
     */
    private BigDecimal amount;
    
    /*
     * The product type.
     */
    private ProductType type;
    
    /*
     * True if the product is imported.
     */
    private boolean imported;

}
