/**
 * 
 */
package com.lastminute.exercise.test.unit;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.lastminute.exercise.bean.Cart;
import com.lastminute.exercise.bean.Product;
import com.lastminute.exercise.bean.ProductType;
import com.lastminute.exercise.injection.CarModule;
import com.lastminute.exercise.service.CartService;

/**
 * @author Gianluca Colaianni -- Fincons Group S.p.A.
 *
 */
public class CartServiceUnitTest {

    private static Injector injector;

    @BeforeClass
    public static void setUp() {
	injector = Guice.createInjector(new CarModule());
    }

    @Test
    public void testValidation() {
	CartService service = injector.getInstance(CartService.class);
	assertThatNullPointerException().isThrownBy(() -> service.addItem(null, null, null))
		.withMessageContaining("Cart");

	Cart cart = new Cart();
	assertThatNullPointerException().isThrownBy(() -> service.addItem(cart, null, null))
		.withMessageContaining("Product");

	Product product = new Product("test", "test", BigDecimal.ZERO, ProductType.BOOK, false);
	assertThatNullPointerException().isThrownBy(() -> service.addItem(cart, product, null))
		.withMessageContaining("quantity");

	assertThatNullPointerException().isThrownBy(() -> service.removeItem(null, null, null))
		.withMessageContaining("Cart");

	assertThatNullPointerException().isThrownBy(() -> service.removeItem(cart, null, null))
		.withMessageContaining("Product");

	assertThatNullPointerException().isThrownBy(() -> service.removeItem(cart, product, null))
		.withMessageContaining("quantity");
    }
    
    @Test
    public void test() {
	
    }

}
