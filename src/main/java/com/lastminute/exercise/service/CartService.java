/**
 * 
 */
package com.lastminute.exercise.service;

import java.math.BigInteger;

import com.lastminute.exercise.bean.Cart;
import com.lastminute.exercise.bean.Product;

/**
 * @author Gianluca Colaianni -- Fincons Group S.p.A.
 *
 */
public interface CartService {
    
    public Cart addItem(final Cart cart, final Product product, final BigInteger quantity);
    
    public Cart removeItem(final Cart cart, final Product product, final BigInteger quantity);
    
}
