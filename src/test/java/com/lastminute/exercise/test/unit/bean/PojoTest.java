/**
 * 
 */
package com.lastminute.exercise.test.unit.bean;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import com.lastminute.exercise.bean.Cart;
import com.lastminute.exercise.bean.CartItem;
import com.lastminute.exercise.bean.Product;

/**
 * @author Gianluca Colaianni -- Fincons Group S.p.A.
 *
 */
public class PojoTest {

    @Test
    public void test() {
	assertPojoMethodsFor(Cart.class).areWellImplemented();
	assertPojoMethodsFor(CartItem.class).areWellImplemented();
	assertPojoMethodsFor(Product.class).areWellImplemented();
    }
}
