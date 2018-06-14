/**
 * 
 */
package com.lastminute.exercise.service;

import java.math.BigInteger;

import com.lastminute.exercise.bean.CartItem;
import com.lastminute.exercise.bean.Product;

/**
 * The service responsible to manage operations about {@link CartItem} objects.
 * 
 * @author Gianluca Colaianni
 *
 */
public interface CartItemService {
    
    public CartItem createCartItem(final Product product, final BigInteger quantity);
    
}
