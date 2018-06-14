/**
 * 
 */
package com.lastminute.exercise.test.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.lastminute.exercise.bean.CartItem;
import com.lastminute.exercise.bean.Product;
import com.lastminute.exercise.bean.ProductType;
import com.lastminute.exercise.injection.CartModule;
import com.lastminute.exercise.service.CartItemService;

/**
 * @author Gianluca Colaianni
 *
 */
public class CartItemServiceUnitTest {

    private static Injector injector;

    @BeforeClass
    public static void setUp() {
	injector = Guice.createInjector(new CartModule());
    }

    @Test
    public void testValidation() {
	CartItemService service = injector.getInstance(CartItemService.class);

	assertThatNullPointerException().isThrownBy(() -> service.createCartItem(null, null))
		.withMessageContaining("Product");

	Product product = new Product("test", "test", BigDecimal.ZERO, ProductType.BOOK, false);
	assertThatNullPointerException().isThrownBy(() -> service.createCartItem(product, null))
		.withMessageContaining("quantity");
    }

    @Test
    public void test() {
	CartItemService service = injector.getInstance(CartItemService.class);
	Product product = new Product("test", "test", BigDecimal.ZERO, ProductType.BOOK, false);

	// test quantity=0, expected quantity 0 and amount 0.
	CartItem item = service.createCartItem(product, BigInteger.ZERO);
	assertThat(item).as("Must be not null").isNotNull();
	assertThat(item.getProduct()).as("Must contains the product").isEqualTo(product);
	assertThat(item.getQuantity()).as("Must have zero quantity").isEqualTo(BigInteger.ZERO);
	assertThat(item.getTotal()).as("Must have zero total amount").isEqualTo(BigDecimal.ZERO);
	assertThat(item.getTotalTaxes()).as("Must have zero total taxes").isEqualTo(BigDecimal.ZERO);

	// test quantity=1, expected quantity 1 and amount 0.
	item = service.createCartItem(product, BigInteger.ONE);
	assertThat(item).as("Must be not null").isNotNull();
	assertThat(item.getProduct()).as("Must contains the product").isEqualTo(product);
	assertThat(item.getQuantity()).as("Must have one quantity").isEqualTo(BigInteger.ONE);
	assertThat(item.getTotal()).as("Must have zero total amount").isEqualTo(BigDecimal.ZERO);
	assertThat(item.getTotalTaxes()).as("Must have zero total taxes").isEqualTo(BigDecimal.ZERO);

	BigDecimal amount = new BigDecimal("12.49");
	BigDecimal taxes = BigDecimal.ZERO;

	// test quantity=1, productType=Book
	product.setAmount(amount);
	item = service.createCartItem(product, BigInteger.ONE);
	assertThat(item).as("Must be not null").isNotNull();
	assertThat(item.getProduct()).as("Must contains the product").isEqualTo(product);
	assertThat(item.getQuantity()).as("Must have one quantity").isEqualTo(BigInteger.ONE);
	assertThat(item.getTotal()).as("Must have total amount: " + amount).isEqualTo(amount);
	assertThat(item.getTotalTaxes()).as("Must have total taxes: " + taxes).isEqualByComparingTo(taxes);

	// test quantity=1, productType=FOOD
	product.setAmount(amount);
	product.setType(ProductType.FOOD);
	item = service.createCartItem(product, BigInteger.ONE);
	assertThat(item).as("Must be not null").isNotNull();
	assertThat(item.getProduct()).as("Must contains the product").isEqualTo(product);
	assertThat(item.getQuantity()).as("Must have one quantity").isEqualTo(BigInteger.ONE);
	assertThat(item.getTotal()).as("Must have total amount: " + amount).isEqualTo(amount);
	assertThat(item.getTotalTaxes()).as("Must have total taxes: " + taxes).isEqualByComparingTo(taxes);

	// test quantity=1, productType=FOOD
	product.setAmount(amount);
	product.setType(ProductType.FOOD);
	item = service.createCartItem(product, BigInteger.ONE);
	assertThat(item).as("Must be not null").isNotNull();
	assertThat(item.getProduct()).as("Must contains the product").isEqualTo(product);
	assertThat(item.getQuantity()).as("Must have one quantity").isEqualTo(BigInteger.ONE);
	assertThat(item.getTotal()).as("Must have total amount: " + amount).isEqualTo(amount);
	assertThat(item.getTotalTaxes()).as("Must have total taxes: " + taxes).isEqualByComparingTo(taxes);

	// test quantity=1, productType=MEDICAL
	product.setAmount(amount);
	product.setType(ProductType.MEDICAL);
	item = service.createCartItem(product, BigInteger.ONE);
	assertThat(item).as("Must be not null").isNotNull();
	assertThat(item.getProduct()).as("Must contains the product").isEqualTo(product);
	assertThat(item.getQuantity()).as("Must have one quantity").isEqualTo(BigInteger.ONE);
	assertThat(item.getTotal()).as("Must have total amount: " + amount).isEqualTo(amount);
	assertThat(item.getTotalTaxes()).as("Must have total taxes: " + taxes).isEqualByComparingTo(taxes);

	// test quantity=1, productType=OTHERS
	amount = new BigDecimal("16.49");
	taxes = new BigDecimal("1.5");
	product.setAmount(amount.subtract(taxes));
	product.setType(ProductType.OTHERS);
	item = service.createCartItem(product, BigInteger.ONE);
	assertThat(item).as("Must be not null").isNotNull();
	assertThat(item.getProduct()).as("Must contains the product").isEqualTo(product);
	assertThat(item.getQuantity()).as("Must have one quantity").isEqualTo(BigInteger.ONE);
	assertThat(item.getTotal()).as("Must have total amount: " + amount).isEqualTo(amount);
	assertThat(item.getTotalTaxes()).as("Must have total taxes: " + taxes).isEqualByComparingTo(taxes);


	// test quantity=1, productType=OTHERS, imported=true
	amount = new BigDecimal("32.19");
	taxes = new BigDecimal("4.2");
	product.setAmount(amount.subtract(taxes));
	product.setType(ProductType.OTHERS);
	product.setImported(true);
	item = service.createCartItem(product, BigInteger.ONE);
	assertThat(item).as("Must be not null").isNotNull();
	assertThat(item.getProduct()).as("Must contains the product").isEqualTo(product);
	assertThat(item.getQuantity()).as("Must have one quantity").isEqualTo(BigInteger.ONE);
	assertThat(item.getTotal()).as("Must have total amount: " + amount).isEqualTo(amount);
	assertThat(item.getTotalTaxes()).as("Must have total taxes: " + taxes).isEqualByComparingTo(taxes);
    }

}
