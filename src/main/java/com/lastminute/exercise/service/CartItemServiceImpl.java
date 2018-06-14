/**
 * 
 */
package com.lastminute.exercise.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

import com.google.inject.Inject;
import com.lastminute.exercise.bean.CartItem;
import com.lastminute.exercise.bean.Product;
import com.lastminute.exercise.bean.ProductType;

/**
 * Implementation of {@link CartItemService}.
 * 
 * @author Gianluca Colaianni
 *
 */
public class CartItemServiceImpl implements CartItemService {
    
    private static final int IMPORTED_TAX = 5;
    
    private static final int SALES_TAX = 10;
    
    @Inject
    private TaxCalculator taxCalculator;
    
    /* (non-Javadoc)
     * @see com.lastminute.exercise.service.CartItemService#createCartItem(com.lastminute.exercise.bean.Product, java.math.BigInteger)
     */
    @Override
    public CartItem createCartItem(final Product product, final BigInteger quantity) {
	Objects.requireNonNull(product, "Product is required");
	Objects.requireNonNull(quantity, "Product quantity required");
	
	int taxRate = 0;
	
	if (product.isImported()) {
	    taxRate = IMPORTED_TAX;
	}
	
	if (product.getType() == ProductType.OTHERS) {
	    taxRate += SALES_TAX;
	}
	
	BigDecimal total = product.getAmount().multiply(new BigDecimal(quantity));
	BigDecimal totalTaxed = taxCalculator.calculate(total, taxRate);
	BigDecimal totalTaxes = totalTaxed.subtract(total);
	
	CartItem item = new CartItem();
	item.setProduct(product);
	item.setQuantity(quantity);
	item.setTotalTaxes(totalTaxes);
	item.setTotal(totalTaxed);
	
	return item;
    }

}
