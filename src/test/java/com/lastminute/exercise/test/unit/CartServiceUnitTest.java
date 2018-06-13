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
    public void test_input1() {
	CartService service = injector.getInstance(CartService.class);
	Cart cart = new Cart();
	Product book = new Product("0001", "book", new BigDecimal("12.49"), ProductType.BOOK, false);
	Product music = new Product("0002", "music CD", new BigDecimal("14.99"), ProductType.OTHERS, false);
	Product chocolate = new Product("0003", "chocolate bar", new BigDecimal("0.85"), ProductType.FOOD, false);
	
	cart = service.addItem(cart, book, BigInteger.ONE);
	cart = service.addItem(cart, music, BigInteger.ONE);
	cart = service.addItem(cart, chocolate, BigInteger.ONE);
	
	assertThat(cart).as("Must be not null").isNotNull();
	assertThat(cart.getItems().size()).as("Must have right length").isEqualTo(3);
	assertThat(cart.getTotal()).as("Must have right total").isEqualByComparingTo(new BigDecimal("29.83"));
	assertThat(cart.getTotalTaxes()).as("Must have right sales total").isEqualByComparingTo(new BigDecimal("1.50"));
	assertThat(cart.getItems().get(book.getId()).getTotal()).as("Book must have rith total").isEqualByComparingTo(new BigDecimal("12.49"));
	assertThat(cart.getItems().get(book.getId()).getTotalTaxes()).as("Book must have rith total taxes").isEqualByComparingTo(BigDecimal.ZERO);
	assertThat(cart.getItems().get(music.getId()).getTotal()).as("music must have rith total").isEqualByComparingTo(new BigDecimal("16.49"));
	assertThat(cart.getItems().get(music.getId()).getTotalTaxes()).as("music must have rith total taxes").isEqualByComparingTo(new BigDecimal("1.5"));
	assertThat(cart.getItems().get(chocolate.getId()).getTotal()).as("chocolate must have rith total").isEqualByComparingTo(new BigDecimal("0.85"));
	assertThat(cart.getItems().get(chocolate.getId()).getTotalTaxes()).as("chocolate must have rith total taxes").isEqualByComparingTo(BigDecimal.ZERO);
    }
    
    @Test
    public void test_input2() {
	CartService service = injector.getInstance(CartService.class);
	Cart cart = new Cart();
	Product chocolate = new Product("0001", "imported box of chocolates", new BigDecimal("10.00"), ProductType.FOOD, true);
	Product perfume = new Product("0002", "imported bottle of perfume", new BigDecimal("47.50"), ProductType.OTHERS, true);
	
	cart = service.addItem(cart, chocolate, BigInteger.ONE);
	cart = service.addItem(cart, perfume, BigInteger.ONE);
	
	assertThat(cart).as("Must be not null").isNotNull();
	assertThat(cart.getItems().size()).as("Must have right length").isEqualTo(2);
	assertThat(cart.getTotal()).as("Must have right total").isEqualByComparingTo(new BigDecimal("65.15"));
	assertThat(cart.getTotalTaxes()).as("Must have right sales total").isEqualByComparingTo(new BigDecimal("7.65"));
	assertThat(cart.getItems().get(chocolate.getId()).getTotal()).as("chocolate must have rith total").isEqualByComparingTo(new BigDecimal("10.50"));
	assertThat(cart.getItems().get(chocolate.getId()).getTotalTaxes()).as("chocolate must have rith total taxes").isEqualByComparingTo(new BigDecimal("0.50"));
	assertThat(cart.getItems().get(perfume.getId()).getTotal()).as("perfume must have rith total").isEqualByComparingTo(new BigDecimal("54.65"));
	assertThat(cart.getItems().get(perfume.getId()).getTotalTaxes()).as("perfume must have rith total taxes").isEqualByComparingTo(new BigDecimal("7.15"));
    }
    
    @Test
    public void test_input3() {
	CartService service = injector.getInstance(CartService.class);
	Cart cart = new Cart();
	Product perfume = new Product("0001", "imported bottle of perfume", new BigDecimal("27.99"), ProductType.OTHERS, true);
	Product perfumeBottle = new Product("0002", "bottle of perfume", new BigDecimal("18.99"), ProductType.OTHERS, false);
	Product pills = new Product("0003", "packet of headache pills", new BigDecimal("9.75"), ProductType.MEDICAL, false);
	Product chocolate = new Product("0004", "box of imported chocolates", new BigDecimal("11.25"), ProductType.FOOD, true);
	
	cart = service.addItem(cart, perfume, BigInteger.ONE);
	cart = service.addItem(cart, perfumeBottle, BigInteger.ONE);
	cart = service.addItem(cart, pills, BigInteger.ONE);
	cart = service.addItem(cart, chocolate, BigInteger.ONE);
	
	assertThat(cart).as("Must be not null").isNotNull();
	assertThat(cart.getItems().size()).as("Must have right length").isEqualTo(4);
	assertThat(cart.getTotal()).as("Must have right total").isEqualByComparingTo(new BigDecimal("74.68"));
	assertThat(cart.getTotalTaxes()).as("Must have right sales total").isEqualByComparingTo(new BigDecimal("6.70"));
	assertThat(cart.getItems().get(perfume.getId()).getTotal()).as("perfume must have rith total").isEqualByComparingTo(new BigDecimal("32.19"));
	assertThat(cart.getItems().get(perfume.getId()).getTotalTaxes()).as("perfume must have rith total taxes").isEqualByComparingTo(new BigDecimal("4.2"));
	assertThat(cart.getItems().get(perfumeBottle.getId()).getTotal()).as("perfumeBottle must have rith total").isEqualByComparingTo(new BigDecimal("20.89"));
	assertThat(cart.getItems().get(perfumeBottle.getId()).getTotalTaxes()).as("perfumeBottle must have rith total taxes").isEqualByComparingTo(new BigDecimal("1.9"));
	assertThat(cart.getItems().get(pills.getId()).getTotal()).as("pills must have rith total").isEqualByComparingTo(new BigDecimal("9.75"));
	assertThat(cart.getItems().get(pills.getId()).getTotalTaxes()).as("pills must have rith total taxes").isEqualByComparingTo(BigDecimal.ZERO);
	assertThat(cart.getItems().get(chocolate.getId()).getTotal()).as("chocolate must have rith total").isEqualByComparingTo(new BigDecimal("11.85"));
	assertThat(cart.getItems().get(chocolate.getId()).getTotalTaxes()).as("chocolate must have rith total taxes").isEqualByComparingTo(new BigDecimal("0.60"));
    }

}
