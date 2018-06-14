/**
 * 
 */
package com.lastminute.exercise.injection;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.lastminute.exercise.service.CartItemService;
import com.lastminute.exercise.service.CartItemServiceImpl;
import com.lastminute.exercise.service.CartService;
import com.lastminute.exercise.service.CartServiceImpl;
import com.lastminute.exercise.service.TaxCalculator;
import com.lastminute.exercise.service.TaxCalculatorImpl;

/**
 * It is the core business logic module. </br>
 * Here is configured {@link Guice} dependency injection for the {@code CartModule}.
 * 
 * @author Gianluca Colaianni
 *
 */
public class CartModule extends AbstractModule {

    /* (non-Javadoc)
     * @see com.google.inject.AbstractModule#configure()
     */
    @Override
    protected void configure() {
	bind(TaxCalculator.class).to(TaxCalculatorImpl.class);
	bind(CartItemService.class).to(CartItemServiceImpl.class);
	bind(CartService.class).to(CartServiceImpl.class);
    }

}
