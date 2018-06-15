/**
 * 
 */
package com.lastminute.exercise.test.unit.utility;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;

import org.junit.Test;

import com.lastminute.exercise.utility.ResourceBundleHelper;

/**
 * @author Gianluca Colaianni
 *
 */
public class ResourceBundleUnitTest {

    @Test
    public void testBundle() {
	assertThat(ResourceBundleHelper.getBundle()).as("Must retrieve bundle.").isNotNull();
    }
    
    @Test
    public void testGetString() {
	assertThat(ResourceBundleHelper.getString("test.simple")).as("Must retrieve property").isEqualTo("Test");
	assertThat(ResourceBundleHelper.getString("test.parameters", BigInteger.ONE)).as("Must retrieve formatted property").isEqualTo("Test number 1");
	
	assertThat(ResourceBundleHelper.getString("test.simple", ResourceBundleHelper.getBundle())).as("Must retrieve formatted property").isEqualTo("Test");
	assertThat(ResourceBundleHelper.getString("test.parameters", ResourceBundleHelper.getBundle(), BigInteger.ONE)).as("Must retrieve formatted property").isEqualTo("Test number 1");
    }

}
