/**
 * 
 */
package com.lastminute.exercise.service;

import java.math.BigInteger;

import com.lastminute.exercise.bean.CartItem;
import com.lastminute.exercise.bean.Product;

/**
 * @author Gianluca Colaianni -- Fincons Group S.p.A.
 *
 */
public interface CartItemService {
    
    public CartItem createCartItem(final Product product, final BigInteger quantity);
    
}
