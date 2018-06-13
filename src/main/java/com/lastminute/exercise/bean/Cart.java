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
    
    private Map<String, CartItem> items = new HashMap<>();
    
    private BigDecimal totalTaxes;
    
    private BigDecimal total;

}
