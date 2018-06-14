/**
 * 
 */
package com.lastminute.exercise.test.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.math.BigDecimal;

import org.junit.Test;

import com.lastminute.exercise.utility.Constants;
import com.lastminute.exercise.utility.Utility;

/**
 * @author Gianluca Colaianni -- Fincons Group S.p.A.
 *
 */
public class UtilityUnitTest {

    @Test
    public void testValidation() {
	assertThatNullPointerException().isThrownBy(() -> Utility.format(null, null)).withMessageContaining("Number");
	
	assertThatNullPointerException().isThrownBy(() -> Utility.format(BigDecimal.ONE, null)).withMessageContaining("Pattern");
    }
    
    @Test
    public void test() {
	assertThat(Utility.format(BigDecimal.ONE, Constants.FORMAT_AMOUNT)).isEqualTo("1,00");
	assertThat(Utility.format(new BigDecimal("10.3"), Constants.FORMAT_AMOUNT)).isEqualTo("10,30");
	assertThat(Utility.format(new BigDecimal("10.31"), Constants.FORMAT_AMOUNT)).isEqualTo("10,31");
	assertThat(Utility.format(new BigDecimal("10.311"), Constants.FORMAT_AMOUNT)).isEqualTo("10,31");
	assertThat(Utility.format(new BigDecimal("10.316"), Constants.FORMAT_AMOUNT)).isEqualTo("10,32");
    }

}
