/**
 * 
 */
package com.lastminute.exercise.test.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.lastminute.exercise.injection.CartModule;
import com.lastminute.exercise.service.TaxCalculator;

/**
 * @author Gianluca Colaianni
 *
 */
public class TaxCalculatorUnitTest {
    
    private static Injector injector;

    @BeforeClass
    public static void setUp() {
	injector = Guice.createInjector(new CartModule()); 
    }
    
    @Test
    public void test() {
	TaxCalculator calculator = injector.getInstance(TaxCalculator.class);
	
	assertThat(calculator.calculate(new BigDecimal("14.99"), 10)).as("Must be correctly rounded").isEqualTo(new BigDecimal("16.49"));
	assertThat(calculator.calculate(new BigDecimal("47.50"), 15)).as("Must be correctly rounded").isEqualTo(new BigDecimal("54.65"));
	assertThat(calculator.calculate(new BigDecimal("18.99"), 10)).as("Must be correctly rounded").isEqualTo(new BigDecimal("20.89"));
	assertThat(calculator.calculate(new BigDecimal("11.25"), 5)).as("Must be correctly rounded").isEqualTo(new BigDecimal("11.85"));
	assertThat(calculator.calculate(new BigDecimal("27.99"), 15)).as("Must be correctly rounded").isEqualTo(new BigDecimal("32.19"));
    }

}
