/**
 * 
 */
package com.lastminute.exercise.service;

import java.math.BigInteger;
import java.util.Objects;

import com.google.inject.Inject;
import com.lastminute.exercise.bean.Cart;
import com.lastminute.exercise.bean.CartItem;
import com.lastminute.exercise.bean.Product;

/**
 * @author Gianluca Colaianni -- Fincons Group S.p.A.
 *
 */
public class CartServiceImpl implements CartService {
    
    @Inject
    private CartItemService itemService;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.lastminute.exercise.service.CartService#addItem(com.lastminute.exercise.
     * bean.Cart, com.lastminute.exercise.bean.Product, java.math.BigInteger)
     */
    @Override
    public Cart addItem(Cart cart, Product product, BigInteger quantity) {
	validate(cart, product, quantity);

	/*
	 * Nothing to do if quantity is 0 or negative.
	 */
	if (quantity.compareTo(BigInteger.ZERO) <= 0) {
	    return cart;
	}

	Cart result = cart;

	CartItem item = result.getItems().get(product.getId());
	if (item == null) {
	    item = itemService.createCartItem(product, quantity);
	} else {
	    /*
	     * Remove from amount and taxes item details.
	     */
	    result.setTotal(result.getTotal().subtract(item.getTotal()));
	    result.setTotalTaxes(result.getTotalTaxes().subtract(item.getTotalTaxes()));

	    /*
	     * Build an updated item.
	     */
	    item = itemService.createCartItem(product, quantity.add(item.getQuantity()));
	}

	result.getItems().put(product.getId(), item);
	result.setTotal(result.getTotal().add(item.getTotal()));
	result.setTotalTaxes(result.getTotalTaxes().add(item.getTotalTaxes()));

	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.lastminute.exercise.service.CartService#removeItem(com.lastminute.
     * exercise.bean.Cart, com.lastminute.exercise.bean.Product,
     * java.math.BigInteger)
     */
    @Override
    public Cart removeItem(final Cart cart, final Product product, final BigInteger quantity) {
	validate(cart, product, quantity);

	Cart result = cart;

	/*
	 * Nothing to do if quantity is 0 or negative.
	 */
	if (quantity.compareTo(BigInteger.ZERO) <= 0) {
	    return result;
	}

	CartItem item = result.getItems().get(product.getId());

	/*
	 * If the item isn't in the cart, do nothing.
	 */
	if (item != null) {

	    result.setTotal(result.getTotal().subtract(item.getTotal()));
	    result.setTotalTaxes(result.getTotalTaxes().subtract(item.getTotalTaxes()));

	    if (quantity.compareTo(item.getQuantity()) >= 0) {
		
		result.getItems().remove(product.getId());
		
	    } else {
		
		BigInteger newQuantity = item.getQuantity().subtract(quantity);
		item = itemService.createCartItem(product, newQuantity);

		/*
		 * Update cart.
		 */
		result.getItems().put(product.getId(), item);
		result.setTotal(result.getTotal().add(item.getTotal()));
		result.setTotalTaxes(result.getTotalTaxes().add(item.getTotalTaxes()));
	    }

	}

	return result;
    }
    
    /**
     * Validates the objects, which are required to be different from {@code null}.
     * @param cart
     * @param product
     * @param quantity
     * @author Gianluca Colaianni -- Fincons Group S.p.A.
     */
    private void validate(final Cart cart, final Product product, final BigInteger quantity) {
	Objects.requireNonNull(cart, "Cart object required");
	Objects.requireNonNull(product, "Product object required.");
	Objects.requireNonNull(quantity, "Product quantity required.");
    }

}
