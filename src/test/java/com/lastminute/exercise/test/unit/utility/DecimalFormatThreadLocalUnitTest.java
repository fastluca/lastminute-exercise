/**
 * 
 */
package com.lastminute.exercise.test.unit.utility;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.Assert.assertNotNull;

import java.text.DecimalFormat;

import org.junit.Test;

import com.lastminute.exercise.utility.Constants;
import com.lastminute.exercise.utility.DecimalFormatThreadLocal;

/**
 * @author Gianluca Colaianni -- Fincons Group S.p.A.
 *
 */
public class DecimalFormatThreadLocalUnitTest {


    @Test
    public void testValidation() {
	assertThatNullPointerException().isThrownBy(() -> new DecimalFormatThreadLocal(null));
    }
    
    @Test
    public void test() {
	DecimalFormatThreadLocal threadLocal = new DecimalFormatThreadLocal(Constants.FORMAT_AMOUNT);
	DecimalFormat df = threadLocal.get();
	assertNotNull(df);
	assertThat(df.isGroupingUsed()).isFalse();
	assertThat(df.getDecimalFormatSymbols().getDecimalSeparator()).isEqualTo('.');
    }
    
}
