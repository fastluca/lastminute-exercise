/**
 * 
 */
package com.lastminute.exercise.test.unit.bean;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import com.lastminute.exercise.bean.Cart;
import com.lastminute.exercise.bean.CartItem;
import com.lastminute.exercise.bean.Product;

import pl.pojo.tester.api.assertion.Method;

/**
 * @author Gianluca Colaianni
 *
 */
public class PojoTest {

    @Test
    public void test() {
	assertPojoMethodsFor(Cart.class).testing(Method.GETTER, Method.SETTER, Method.CONSTRUCTOR).areWellImplemented();
	assertPojoMethodsFor(CartItem.class).testing(Method.GETTER, Method.SETTER, Method.CONSTRUCTOR).areWellImplemented();
	assertPojoMethodsFor(Product.class).testing(Method.GETTER, Method.SETTER, Method.CONSTRUCTOR).areWellImplemented();
    }
}
