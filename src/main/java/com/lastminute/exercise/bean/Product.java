/**
 * 
 */
package com.lastminute.exercise.bean;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Gianluca Colaianni -- Fincons Group S.p.A.
 *
 */
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {
    
    @EqualsAndHashCode.Include
    private String id;
    
    private String description;
    
    private BigDecimal amount;
    
    private ProductType type;
    
    private boolean imported;

}
