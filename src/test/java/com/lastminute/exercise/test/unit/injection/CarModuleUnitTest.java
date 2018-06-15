/**
 * 
 */
package com.lastminute.exercise.test.unit.injection;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.lastminute.exercise.injection.CartModule;
import com.lastminute.exercise.service.CartItemService;
import com.lastminute.exercise.service.CartItemServiceImpl;
import com.lastminute.exercise.service.CartService;
import com.lastminute.exercise.service.CartServiceImpl;
import com.lastminute.exercise.service.TaxCalculator;
import com.lastminute.exercise.service.TaxCalculatorImpl;

/**
 * @author Gianluca Colaianni -- Fincons Group S.p.A.
 *
 */
public class CarModuleUnitTest {
    
    private static Injector injector;

    @BeforeClass
    public static void setUp() {
	injector = Guice.createInjector(new CartModule());
    }

    @Test
    public void test() {
	assertThat(injector.getInstance(CartService.class)).isInstanceOf(CartServiceImpl.class);
	assertThat(injector.getInstance(CartItemService.class)).isInstanceOf(CartItemServiceImpl.class);
	assertThat(injector.getInstance(TaxCalculator.class)).isInstanceOf(TaxCalculatorImpl.class);
    }

}
